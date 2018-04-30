/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.group9.commonplayer.Player;
import data.MovableEntity;
import java.util.ArrayList;
import movableentityparts.WeaponPart;
import movableentityparts.iWeapon;

/**
 *
 * @author jonas
 */
public class SetupScreen implements Screen {

    private Stage stage;
    private ParentScreen parentScreen;
    private SpriteBatch batch;
    private Button startButton;
    private Button addEnemyButton;
    private Button removeEnemyButton;
    private Button classMage;
    private Button classMelee;
    private ButtonGroup buttonGroup;
    private BitmapFont font;

    public SetupScreen() {
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        font = parentScreen.getFont();
        buttonGroup = new ButtonGroup();

    }

    @Override
    public void show() {
        createStartButton();
        createAddEnemyButton();
        createRemoveEnemyButton();
        createClassButton();
        createClassButtonGroup();
        
        //Don't let user start without picking a class
        startButton.setVisible(false);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        Gdx.gl.glClearColor(25 / 255f, 24 / 255f, 27 / 255f, 0); // Clear screen

        batch.begin();
        font.draw(batch, "SELECT WEAPON", ParentScreen.getGameData().getDisplayWidth() / 2 - 204, ParentScreen.getGameData().getDisplayHeight() / 2 + 110);
        batch.end();
        
        //If a class is selected let the user start.
        if(classMage.isPressed() || classMelee.isPressed()) {
            startButton.setVisible(true);
        }

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

    /**
     * Creates the start button centered towards the button of the game. The
     * button transitions the player into the actual game.
     */
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

    /**
     * Creates a button that will add an enemy entity to the game stage when
     * start is pressed.
     */
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

    /**
     * Creates a button that will remove an enemy entity from the game stage.
     */
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

    /**
     * Creates two buttons that will select a weapon type for the player.
     */
    public void createClassButton() {

        classMage = new TextButton("MAGE", parentScreen.getButtonSkin(), "default");
        classMage.setSize(150, 30);
        classMage.setPosition(Gdx.graphics.getWidth() / 2 + -200, Gdx.graphics.getHeight() / 2 + 50);
        classMage.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (MovableEntity entity : parentScreen.getWorld().getMovableEntities()) {
                    if (entity instanceof Player) {
                        for (iWeapon wep : parentScreen.getWorld().getWeapons()) {
                            if (wep.getWeaponName().equalsIgnoreCase("fireball")) {
                                WeaponPart temp = entity.getPart(WeaponPart.class);
                                temp.setWeapon(wep);
                            }
                        }
                    }
                }

            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        classMelee = new TextButton("MELEE", parentScreen.getButtonSkin(), "default");
        classMelee.setSize(150, 30);
        classMelee.setPosition(Gdx.graphics.getWidth() / 2 + -50, Gdx.graphics.getHeight() / 2 + 50);
        classMelee.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (MovableEntity entity : parentScreen.getWorld().getMovableEntities()) {
                    if (entity instanceof Player) {
                        //WeaponPart weapon = entity.getPart(WeaponPart.class);
                        for (iWeapon wep : parentScreen.getWorld().getWeapons()) {
                            if (wep.getWeaponName().equalsIgnoreCase("sword")) {
                                WeaponPart temp = entity.getPart(WeaponPart.class);
                                temp.setWeapon(wep);
                            }
                        }
                    }
                }
            }
            
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        stage.addActor(classMage);
        stage.addActor(classMelee);
    }

    /**
     * Groups the two class buttons together, so the player can only have one
     * button selected.
     */
    public void createClassButtonGroup() {
        buttonGroup.add(classMage);
        buttonGroup.add(classMelee);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);
    }

}
