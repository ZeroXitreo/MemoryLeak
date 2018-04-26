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
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import data.MovableEntity;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 *
 * @author jonas
 */
public class SetupScreen implements Screen {

    private Stage stage;
    private ParentScreen parentScreen;
    private ScrollPane scrollPane;
    private List<String> list;
    private SpriteBatch batch;
    private float width;
    private float height;
    private ArrayList<MovableEntity> enemies;
    private TreeMap<String, MovableEntity> entityMap;
    private String[] entityArray;

    public SetupScreen() {
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        enemies = new ArrayList<>();
        width = ParentScreen.getGameData().getDisplayWidth();
        height = ParentScreen.getGameData().getDisplayHeight();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        list = new List<>(parentScreen.getListSkin());
        width = ParentScreen.getGameData().getDisplayWidth();
        height = ParentScreen.getGameData().getDisplayHeight();
        entityMap = new TreeMap<>();

        for (MovableEntity entity : ParentScreen.getWorld().getEnemyEntities()) {
            entityMap.put(entity.getName(), entity);
        }
        entityArray = new String[entityMap.size()];
        int i = 0;
        for (String s : entityMap.keySet()) {
            System.out.println(s);
            entityArray[i] = s;
            i++;
        }
        list.setItems(entityArray);
        list.setBounds(0, 0, 100, 100);
        list.setPosition(0, 0);
        stage.addActor(list); //Add list to the stage.
    }

    @Override
    public void show() {

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

}
