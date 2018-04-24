/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import data.MovableEntity;
import java.util.ArrayList;
import movableentityparts.iWeapon;

/**
 *
 * @author jonas
 */
public class SetupScreen implements Screen {

    private Stage stage;
    private ParentScreen parentScreen;
    private ScrollPane scrollPane;
    private List<String> list;
    private Skin skin;
    private SpriteBatch batch;
    private float width;
    private float height;
    private int size;
    private ArrayList<MovableEntity> enemies;
    private Button startButton;
    private Button addEnemyButton;
    private Button removeEnemyButton;
    //private ArrayList<iWeapon> weapons;

    public SetupScreen() {
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        stage = new Stage(new ScreenViewport());

    }

    @Override
    public void show() {

        createStartButton();
        createAddEnemyButton();
        createRemoveEnemyButton();

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        Gdx.gl.glClearColor(25 / 255f, 24 / 255f, 27 / 255f, 0); // Clear screen

        stage.act(f);
        stage.draw();
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

    public void createStartButton() {
        startButton = new TextButton("Start", parentScreen.getButtonSkin(), "default");
        startButton.setSize(260, 36);
        startButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 - 150);
        startButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ParentScreen.getGame().setScreen(new GUIPlayScreen());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(startButton);
    }

    public void createAddEnemyButton() {
        addEnemyButton = new TextButton(" + ", parentScreen.getButtonSkin(), "default");
        addEnemyButton.setSize(40, 30);
        addEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() / 2 + 50);
        addEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("This should probably do something :^)");
                //Maybe like add an enemy
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(addEnemyButton);
    }

    public void createRemoveEnemyButton() {
        removeEnemyButton = new TextButton(" - ", parentScreen.getButtonSkin(), "default");
        removeEnemyButton.setSize(40, 30);
        removeEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getHeight() / 2 + 50);
        removeEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("This should probably do something :^)");
                //Maybe like remove an enemy
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(removeEnemyButton);
    }

}
