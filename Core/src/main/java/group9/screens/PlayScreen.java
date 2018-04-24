/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import data.MovableEntity;
import services.iEntityProcessingService;
import services.iPostEntityProcessingService;

/**
 *
 * @author jonas
 */
public class PlayScreen implements Screen {

    private Stage stage;
    private ShapeRenderer sr;
    public static final int GAME_WON = 2;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_OVER = 0;
    private int state;
    private SpriteBatch batch;
    private BitmapFont font;
    private boolean alive;
    private boolean stageClear;
    private ParentScreen parentScreen;

    public PlayScreen() {
        parentScreen = ParentScreen.getInstance();
        stage = new Stage(new ScreenViewport());
        sr = new ShapeRenderer();
        parentScreen.setResult();
        state = GAME_RUNNING;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.setScale(2);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
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
            state = 1;
        } else {
            state = 0;
        }
        if (stageClear) {
            state = 2;
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen

        switch (state) {
            case GAME_RUNNING:
                stage.act();
                update();
                ParentScreen.getGip().keyPress();
                draw();
                break;
            case GAME_OVER:
                draw();
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(new Color(0.5f, 0, 0, 0.7f));
                sr.rect(0, 0, ParentScreen.getGameData().getDisplayWidth(), ParentScreen.getGameData().getDisplayHeight());
                sr.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
                batch.begin();
                font.draw(batch, "U DED", ParentScreen.getGameData().getDisplayWidth() / 2, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                break;
            case GAME_WON:
                draw();
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(new Color(0, 0.5f, 0, 0.7f));
                sr.rect(0, 0, ParentScreen.getGameData().getDisplayWidth(), ParentScreen.getGameData().getDisplayHeight());
                sr.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
                batch.begin();
                font.draw(batch, "U WON", ParentScreen.getGameData().getDisplayWidth() / 2, ParentScreen.getGameData().getDisplayHeight() / 2);
                batch.end();
                break;
        }

        //stage.draw();
        //stage.clear();
    }
private void draw() {
        for (MovableEntity movableEntity : ParentScreen.getWorld().getMovableEntities()) {
            if (movableEntity.getType().equalsIgnoreCase("player")) {
                sr.setColor(1, 1, 1, 1);
            } else {
                sr.setColor(0, 1, 0, 1);
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
