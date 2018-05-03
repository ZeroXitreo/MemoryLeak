/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
import data.ImmovableEntity;
import data.MovableEntity;
import movableentityparts.HealthPart;
import movableentityparts.Position;
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
    private TiledDrawable emptyHeart;
    private TiledDrawable fullHeart;
    private TiledDrawable wall;
    private TextButton toMenu;
    private TextureAtlas lava;
    private TextureAtlas memoryLeakPack;
    private Animation animationLava;
    private Animation animationSpaceSlime;
    private Animation animationPlayer;
    private Animation animationIdlePlayer;
    private Animation animationProjectile;
    private Animation animationSlime;
    private Animation animationFlail;
    private HealthPart playerhp;
    private Position pos;
    private int state;
    private BitmapFont font;
    private float time; //time used to know what picture to show from the GIFs.
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
    private TextureRegion fireballRegion;
    private TextureRegion flailRegion;
    private TextureRegion slimeRegion;

    public GUIPlayScreen() {
        parentScreen = ParentScreen.getInstance();
        stage = new Stage(new ScreenViewport());
        sr = new ShapeRenderer();
        batch = new SpriteBatch();
        state = 1;
        time = 0f;
        lava = parentScreen.getLava();
        memoryLeakPack = parentScreen.getMemoryLeakPack();
        font = parentScreen.getFont();

        //create the animation of entities
        animationLava = new Animation(1 / 11f, lava.findRegions("lava"), PlayMode.LOOP);
        animationSpaceSlime = new Animation(1 / 5f, memoryLeakPack.findRegions("SpaceSlime"), PlayMode.LOOP);
        animationPlayer = new Animation(1 / 6f, memoryLeakPack.findRegions("player"), PlayMode.LOOP);
        animationIdlePlayer = new Animation(1 / 7f, memoryLeakPack.findRegions("playeridle"), PlayMode.LOOP);
        animationProjectile = new Animation(1 / 4f, memoryLeakPack.findRegions("fireball"), PlayMode.LOOP);
        animationSlime = new Animation(1 / 7f, memoryLeakPack.findRegions("Slime"), PlayMode.LOOP);
        animationFlail = new Animation(1/2f, memoryLeakPack.findRegions("Flail"), PlayMode.LOOP);

        //instanciate the wall blocks' graphic
        bottomWall = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom"));
        topWall = new TiledDrawable(memoryLeakPack.findRegion("wall_top"));
        leftWall = new TiledDrawable(memoryLeakPack.findRegion("wall_left"));
        rightWall = new TiledDrawable(memoryLeakPack.findRegion("wall_right"));
        centerWall = new TiledDrawable(memoryLeakPack.findRegion("wall_center"));
        wallBottomLeftCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom_left"));
        wallTopLeftCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_top_left"));
        wallBottomRightCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_bottom_right"));
        wallTopRightCorner = new TiledDrawable(memoryLeakPack.findRegion("wall_top_right"));
        wall = new TiledDrawable(memoryLeakPack.findRegion("roundwall"));

        //instanciate the hearts' graphic
        fullHeart = new TiledDrawable(memoryLeakPack.findRegion("heart_full"));
        emptyHeart = new TiledDrawable(memoryLeakPack.findRegion("heart_loss"));
    }

    @Override
    public void show() {
        makeMenuButton();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        time += f;
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        lavaRegion = animationLava.getKeyFrame(time, true);

        alive = false;
        stageClear = true;
        for (MovableEntity movableEntity : ParentScreen.getWorld().getGameMovableEntities()) {
            if (movableEntity.getType().equalsPlayer()) {
                alive = true;
            }
            if (movableEntity.getType().equalsEnemy()) {
                stageClear = false;
            }
        }

        //which state the game is in
        if (alive) {
            state = GAME_RUNNING;
        } else {
            state = GAME_OVER;
        }
        if (stageClear) {
            state = GAME_WON;
        }

        //draw map
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

        switch (state) {
            case GAME_RUNNING:
                update();
                ParentScreen.getGip().keyPress();
                drawEntities(time);
                break;
            case GAME_OVER:
                batch.begin();
                font.draw(batch, "YOU DIED", ParentScreen.getGameData().getDisplayWidth() / 3 + 60, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                drawEntities(time);
                stage.addActor(toMenu); //Add the button to the stage. 
                break;
            case GAME_WON:
                batch.begin();
                font.draw(batch, "YOU WIN... THIS ROUND", ParentScreen.getGameData().getDisplayWidth() / 4, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                drawEntities(time);
                stage.addActor(toMenu); //Add the button to the stage. 
                break;
        }
        stage.draw();
    }

    private void drawEntities(float time) {
        //Show the animation of the different entities.
        for (MovableEntity movableEntity : ParentScreen.getWorld().getGameMovableEntities()) {
            if (movableEntity.getNameInstance().equalsPlayer()) {
                pos = movableEntity.getPart(Position.class);
                batch.begin();
                playerhp = movableEntity.getPart(HealthPart.class);
                for (int i = 0; i < playerhp.getMaxHealth(); i++) {
                    emptyHeart.draw(batch, 8 + (i * (32 + 8)), ParentScreen.getGameData().getDisplayHeight() - 40, 32, 32);
                }
                for (int i = 0; i < playerhp.getHealth(); i++) {
                    fullHeart.draw(batch, 8 + (i * (32 + 8)), ParentScreen.getGameData().getDisplayHeight() - 40, 32, 32);
                }
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
            } else if (movableEntity.getNameInstance().equalsFireball()) {
                batch.begin();
                Position pos = movableEntity.getPart(Position.class);
                fireballRegion = animationProjectile.getKeyFrame(time, true);
                batch.draw(fireballRegion, pos.getX() - 8, pos.getY() - 8, 16, 16);
                batch.end();
                sr.setColor(1, 0, 0, 1);
            } else if (movableEntity.getNameInstance().equalsFlail()){
                batch.begin();
                Position pos = movableEntity.getPart(Position.class);
                flailRegion = animationFlail.getKeyFrame(time * 2, true);
                batch.draw(flailRegion, pos.getX() - 8, pos.getY() - 8, 16, 16);
                batch.end();
                sr.setColor(1, 0, 0, 1);
            } else if (movableEntity.getNameInstance().equalsSpaceSlime()) {
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
            } else if (movableEntity.getNameInstance().equalsSlime()) {
                slimeRegion = animationSlime.getKeyFrame(time, true);
                Position pos = movableEntity.getPart(Position.class);
                batch.begin();
                if (movableEntity.getMoveDirection() == 1) {
                    batch.draw(slimeRegion, pos.getX() + 12, pos.getY() - 12, -32, 32);
                } else if (movableEntity.getMoveDirection() == 0) {
                    batch.draw(slimeRegion, pos.getX() - 12, pos.getY() - 12, 32, 32);
                } else {
                    batch.draw(slimeRegion, pos.getX() + 12, pos.getY() - 12, -32, 32);
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
        for(ImmovableEntity immovableEntity : ParentScreen.getWorld().getGameImmovableEntities()){
            pos = immovableEntity.getPart(Position.class);
            if(immovableEntity.getType().equalsWalls()){
                batch.begin();
                wall.draw(batch, pos.getX()-32, pos.getY()-32, 64, 64);
                batch.end();
            }
        }

    }

    private void makeMenuButton() {
        //create the toMenu button
        toMenu = new TextButton("To Menu", parentScreen.getButtonSkin(), "default");

        //make button
        toMenu.setSize(260, 36);
        toMenu.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 50);
        toMenu.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                //Remove entities and movable entities from the game
                for (MovableEntity entity : ParentScreen.getWorld().getMovableEntities()) {
                    ParentScreen.getWorld().removeGameMovableEntity(entity);
                    ParentScreen.getWorld().removeMovableEntity(entity);
                }
                for (ImmovableEntity entity : ParentScreen.getWorld().getImmovableEntities()) {
                    ParentScreen.getWorld().removeGameImmovableEntity(entity);
                    ParentScreen.getWorld().removeImmovableEntity(entity);
                }
                //Return to the menu screen
                ParentScreen.getGame().setScreen(new MenuScreen());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

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

    /**
     * Calls the process on the different entity parts.
     */
    private void update() {
        for (iEntityProcessingService entityProcessor : parentScreen.getEntityProcessingServices()) {
            entityProcessor.process(ParentScreen.getGameData(), ParentScreen.getWorld());
        }
        for (iPostEntityProcessingService postEntityProcessor : parentScreen.getPostEntityProcessingService()) {
            postEntityProcessor.process(ParentScreen.getGameData(), ParentScreen.getWorld());
        }
    }

}
