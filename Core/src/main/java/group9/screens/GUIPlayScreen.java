/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.sun.javafx.text.GlyphLayout;
import data.Entity;

import data.MovableEntity;
import movableentityparts.Position;
import movableentityparts.iWeapon;
import services.iEntityProcessingService;
import services.iPostEntityProcessingService;

/**
 *
 * @author jonas
 */
public class GUIPlayScreen implements Screen {

    private Stage stage;
    private SpriteBatch batch;
    private TiledDrawable bottomWall;
    private TiledDrawable topWall;
    private TiledDrawable leftWall;
    private TiledDrawable rightWall;
    private TiledDrawable centerWall;
    private TiledDrawable wallBottomLeftCorner;
    private TiledDrawable wallTopLeftCorner;
    private TiledDrawable wallBottomRightCorner;
    private TiledDrawable wallTopRightCorner;
    private TextButton toMenu;
    private TextureAtlas lava;
    private TextureAtlas memoryLeakPack;
    private Animation animationLava;
    private Animation animationSpaceSlime;
    private Animation animationPlayer;
    private Animation animationIdlePlayer;
    private Animation animationProjectile;
    private int state;
    private BitmapFont font;
    private float time;
    private ParentScreen parentScreen;
    private ShapeRenderer sr;
    public static final int GAME_WON = 2;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_OVER = 0;
    private boolean alive;
    private boolean stageClear;
    private TextureRegion lavaRegion;
    private TextureRegion spaceSlimeRegion;
    private TextureRegion playerRegion;
    private TextureRegion playerIdleRegion;
    private TextureRegion projectileRegion;

    public GUIPlayScreen() {
        parentScreen = ParentScreen.getInstance();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        sr = new ShapeRenderer();
        parentScreen.setResult();
        state = 1;
        batch = new SpriteBatch();

        lava = parentScreen.getLava();
        memoryLeakPack = parentScreen.getMemoryLeakPack();
        font = parentScreen.getFont();

        //create the animation of entities
        animationLava = new Animation(1 / 11f, lava.findRegions("lava"), PlayMode.LOOP);
        animationSpaceSlime = new Animation(1 / 5f, memoryLeakPack.findRegions("SpaceSlime"), PlayMode.LOOP);
        animationPlayer = new Animation(1 / 6f, memoryLeakPack.findRegions("player"), PlayMode.LOOP);
        animationIdlePlayer = new Animation(1 / 7f, memoryLeakPack.findRegions("playeridle"), PlayMode.LOOP);
        animationProjectile = new Animation(1 / 4f, memoryLeakPack.findRegions("fireball"), PlayMode.LOOP);

        //instanciate the wall blocks
        bottomWall = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom"));
        topWall = new TiledDrawable(memoryLeakPack.findRegion("wall_top"));
        leftWall = new TiledDrawable(memoryLeakPack.findRegion("wall_left"));
        rightWall = new TiledDrawable(memoryLeakPack.findRegion("wall_right"));
        centerWall = new TiledDrawable(memoryLeakPack.findRegion("wall_center"));
        wallBottomLeftCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom_left"));
        wallTopLeftCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_top_left"));
        wallBottomRightCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom_right"));
        wallTopRightCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_top_right"));

        //create the toMenu button
        toMenu = new TextButton("To Menu", parentScreen.getButtonSkin(), "default");

        //set time (used in the lava animation)
        time = 0f;

        //make button
        toMenu.setSize(260, 36);
        toMenu.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 50);
        toMenu.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("CLICK");
                for (Entity entity : ParentScreen.getWorld().getEntities()) {
                    ParentScreen.getWorld().removeEntity(entity);
                }
                for (MovableEntity entity : ParentScreen.getWorld().getMovableEntities()) {
                    ParentScreen.getWorld().removeMovableEntity(entity);
                }

                ParentScreen.getGame().setScreen(new MenuScreen());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("CLICKY");
                return true;
            }
        });
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        time += f;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        lavaRegion = animationLava.getKeyFrame(time, true);

        alive = false;
        stageClear = true;
        for (MovableEntity movableEntity : ParentScreen.getWorld().getMovableEntities()) {
            if (movableEntity.getType().equalsIgnoreCase("player")) {
                alive = true;
            }
            if (movableEntity.getType().equalsIgnoreCase("enemy")) {
                stageClear = false;
            }
        }
        if (alive) {
            state = GAME_RUNNING;
        } else {
            state = GAME_OVER;
        }
        if (stageClear) {
            state = GAME_WON;
        }

        switch (state) {
            case GAME_RUNNING:
                update();
                ParentScreen.getGip().keyPress();
                batch.begin();
                for (int x = 0; x < 960; x += 124) {
                    for (int y = 0; y < 540; y += 124) {
                        batch.draw(lavaRegion, x, y);
                    }
                }
                centerWall.draw(batch, 80, 64, 800, 412);
                bottomWall.draw(batch, 80, 32, 800, 32);
                topWall.draw(batch, 80, 476, 800, 32);
                leftWall.draw(batch, 48, 64, 32, 412);
                rightWall.draw(batch, 864, 64, 32, 416);

                wallBottomLeftCorner.draw(batch, 48, 32, 32, 32);
                wallTopLeftCorner.draw(batch, 48, 476, 32, 32);
                wallBottomRightCorner.draw(batch, 864, 32, 32, 32);
                wallTopRightCorner.draw(batch, 864, 476, 32, 32);
                batch.end();
                drawEntities(time);
                break;
            case GAME_OVER:
                batch.begin();
                for (int x = 0; x < 960; x += 124) {
                    for (int y = 0; y < 540; y += 124) {
                        batch.draw(lavaRegion, x, y);
                    }
                }
                centerWall.draw(batch, 80, 64, 800, 412);
                bottomWall.draw(batch, 80, 32, 800, 32);
                topWall.draw(batch, 80, 476, 800, 32);
                leftWall.draw(batch, 48, 64, 32, 412);
                rightWall.draw(batch, 864, 64, 32, 416);

                wallBottomLeftCorner.draw(batch, 48, 32, 32, 32);
                wallTopLeftCorner.draw(batch, 48, 476, 32, 32);
                wallBottomRightCorner.draw(batch, 864, 32, 32, 32);
                wallTopRightCorner.draw(batch, 864, 476, 32, 32);

                font.draw(batch, "YOU DIED", ParentScreen.getGameData().getDisplayWidth() / 3 + 60, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                drawEntities(time);

                stage.addActor(toMenu); //Add the button to the stage. 
                break;
            case GAME_WON:
                batch.begin();
                for (int x = 0; x < 960; x += 124) {
                    for (int y = 0; y < 540; y += 124) {
                        batch.draw(lavaRegion, x, y);
                    }
                }
                centerWall.draw(batch, 80, 64, 800, 412);
                bottomWall.draw(batch, 80, 32, 800, 32);
                topWall.draw(batch, 80, 476, 800, 32);
                leftWall.draw(batch, 48, 64, 32, 412);
                rightWall.draw(batch, 864, 64, 32, 416);

                wallBottomLeftCorner.draw(batch, 48, 32, 32, 32);
                wallTopLeftCorner.draw(batch, 48, 476, 32, 32);
                wallBottomRightCorner.draw(batch, 864, 32, 32, 32);
                wallTopRightCorner.draw(batch, 864, 476, 32, 32);

                font.draw(batch, "YOU WIN... THIS ROUND", ParentScreen.getGameData().getDisplayWidth() / 4, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                drawEntities(time);
                stage.addActor(toMenu); //Add the button to the stage. 
                break;
        }
        stage.draw();
    }

    private void drawEntities(float time) {
        for (MovableEntity movableEntity : ParentScreen.getWorld().getMovableEntities()) {
            if (movableEntity.getType().equalsIgnoreCase("player")) {
                batch.begin();
                Position pos = movableEntity.getPart(Position.class);
                playerRegion = animationPlayer.getKeyFrame(2 * time, true);
                playerIdleRegion = animationIdlePlayer.getKeyFrame(2 * time, true);
                if (movableEntity.getMoveDirection() == 1) {
                    batch.draw(playerIdleRegion, pos.getX() - 16, pos.getY() - 16, 32, 32);
                } else if (movableEntity.getMoveDirection() == 0) {
                    batch.draw(playerRegion, pos.getX() + 16, pos.getY() - 16, -32, 32);
                } else {
                    batch.draw(playerRegion, pos.getX() - 16, pos.getY() - 16, 32, 32);
                }
                batch.end();
                sr.setColor(1, 1, 1, 1);
            } else if (movableEntity.getType().equalsIgnoreCase("friendlyBullet") || movableEntity.getType().equalsIgnoreCase("enemyBullet")) {
                batch.begin();
                Position pos = movableEntity.getPart(Position.class);
                projectileRegion = animationProjectile.getKeyFrame(time, true);
                projectileRegion = animationProjectile.getKeyFrame(time, true);
                if (movableEntity.getMoveDirection() == 1) {
                    batch.draw(projectileRegion, pos.getX() - 8, pos.getY() - 8, 16, 16);
                } else if (movableEntity.getMoveDirection() == 0) {
                    batch.draw(projectileRegion, pos.getX() + 8, pos.getY() - 8, -16, 16);
                } else {
                    batch.draw(projectileRegion, pos.getX() - 8, pos.getY() - 8, 16, 16);
                }
                batch.end();
                sr.setColor(1, 0, 0, 1);
            } else if (movableEntity.getType().equalsIgnoreCase("enemy")) {
                spaceSlimeRegion = animationSpaceSlime.getKeyFrame(time, true);
                Position pos = movableEntity.getPart(Position.class);
                batch.begin();
                if (movableEntity.getMoveDirection() == 1) {
                    batch.draw(spaceSlimeRegion, pos.getX() - 16, pos.getY() - 16, 32, 32);
                } else if (movableEntity.getMoveDirection() == 0) {
                    batch.draw(spaceSlimeRegion, pos.getX() + 16, pos.getY() - 16, -32, 32);
                } else {
                    batch.draw(spaceSlimeRegion, pos.getX() - 16, pos.getY() - 16, 32, 32);
                }
                batch.end();
                sr.setColor(0, 1, 0, 1);
            } else {
                sr.setColor(1, 1, 0, 0);
            }
            sr.begin(ShapeRenderer.ShapeType.Line);
            float[] shapeX = movableEntity.getShapeX();
            float[] shapeY = movableEntity.getShapeY();
            for (int i = 0, j = shapeX.length - 1;
                    i < shapeX.length;
                    j = i++) {
                sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
            }
            sr.end();
        }

    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void update() {
        for (iEntityProcessingService entityProcessor : parentScreen.getEntityProcessingServices()) {
            entityProcessor.process(ParentScreen.getGameData(), ParentScreen.getWorld());
        }
        for (iPostEntityProcessingService postEntityProcessor : parentScreen.getPostEntityProcessingService()) {
            postEntityProcessor.process(ParentScreen.getGameData(), ParentScreen.getWorld());
        }
    }

}
