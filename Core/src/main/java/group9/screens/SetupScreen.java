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
import com.group9.commonplayer.Player;
import com.group9.commonspaceslime.SpaceSlime;
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
    private ArrayList<iWeapon> weapons;

    public SetupScreen() {
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        enemies = new ArrayList<>();
        weapons = ParentScreen.getWorld().getWeapons();
        skin = parentScreen.getListSkin();
        width = ParentScreen.getGameData().getDisplayWidth();
        height = ParentScreen.getGameData().getDisplayHeight();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        list = new List<String>(skin);
        for (MovableEntity entity : ParentScreen.getWorld().getMovableEntities()) {
            System.out.println(entity.getType());
            enemies.add(entity);
        }
        System.out.println(enemies.size());
        String[] strings = new String[enemies.size()];
        for (int i = 0, k = 0; i < strings.length; i++) {
            strings[k++] = "Enemy: " + enemies.get(i).getType();
        }
        list.setItems(strings);
        list.setPosition(width / 2, height + 100);
        //list.setStyle(parentScreen.getListStyle());
        scrollPane = new ScrollPane(list, skin);
        scrollPane = new ScrollPane(list, skin);
        scrollPane.setBounds(0, 0, width - 500, height + 100);
        scrollPane.setSmoothScrolling(false);
        scrollPane.setPosition(width / 2 - scrollPane.getWidth() / 4,
                height / 2 - scrollPane.getHeight() / 4);
        scrollPane.setTransform(true);
        scrollPane.setScale(0.5f);
        
        stage.addActor(scrollPane);
        //stage.addActor(list);
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        Gdx.gl.glClearColor(25 / 255f, 24 / 255f, 27 / 255f, 0); // Clear screen
        System.out.println(list.getSelected());

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
