/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.group9.commonplayer.Player;
import data.Entity;
import data.ImmovableEntity;
import data.MovableEntity;
import java.util.TreeMap;
import movableentityparts.WeaponPart;
import movableentityparts.iWeapon;

/**
 *
 * @author jonas
 */
public class SetupScreen implements Screen {

    private Stage stage;
    private ParentScreen parentScreen;
    private List<String> list;
    private SpriteBatch batch;
    private TreeMap<String, Entity> entityMap;
    private String[] entityArray;
    private Button startButton;
    private Button addEnemyButton;
    private Button removeEnemyButton;
    private Button classMage;
    private Button classMelee;
    private ButtonGroup buttonGroup;
    private BitmapFont font;
    private ScrollPane scrollpane;
    private String messageToUser;

    public SetupScreen() {
        messageToUser = "";
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        list = new List<>(parentScreen.getListSkin());
        entityMap = new TreeMap<>();
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
        showAddableEntities();

        //Don't let user start without picking a class
        startButton.setVisible(false);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        Gdx.gl.glClearColor(25 / 255f, 24 / 255f, 27 / 255f, 0); // Clear screen
        batch.begin();
        font.draw(batch, "SELECT WEAPON", ParentScreen.getGameData().getDisplayWidth() / 2 - 170, ParentScreen.getGameData().getDisplayHeight() / 2 + 200);
        font.draw(batch, messageToUser, ParentScreen.getGameData().getDisplayWidth() / 2 - 170, ParentScreen.getGameData().getDisplayHeight() / 2 - 50);
        batch.end();

        //If a class is selected let the user start.
        if (classMage.isPressed() || classMelee.isPressed()) {
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
    private void createStartButton() {
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
    private void createAddEnemyButton() {
        addEnemyButton = new TextButton(" + ", parentScreen.getButtonSkin(), "default");
        addEnemyButton.setSize(40, 30);
        addEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 150, Gdx.graphics.getHeight() / 2 + 50);
        addEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                messageToUser = "You added: " + list.getSelected();
                if (entityMap.get(list.getSelected()) instanceof MovableEntity) {
                    ParentScreen.getWorld().addGameMovableEntity((MovableEntity) entityMap.get(list.getSelected()));
                } else if (entityMap.get(list.getSelected()) instanceof ImmovableEntity) {
                    ParentScreen.getWorld().addGameImmovableEntity((ImmovableEntity) entityMap.get(list.getSelected()));
                }

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
    private void createRemoveEnemyButton() {
        removeEnemyButton = new TextButton(" - ", parentScreen.getButtonSkin(), "default");
        removeEnemyButton.setSize(40, 30);
        removeEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 200, Gdx.graphics.getHeight() / 2 + 50);
        removeEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                messageToUser = "You removed: " + list.getSelected();
                if (entityMap.get(list.getSelected()) instanceof MovableEntity) {
                    ParentScreen.getWorld().removeGameMovableEntity((MovableEntity) entityMap.get(list.getSelected()));
                } else if (entityMap.get(list.getSelected()) instanceof ImmovableEntity) {
                    ParentScreen.getWorld().removeGameImmovableEntity((ImmovableEntity) entityMap.get(list.getSelected()));
                }
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
    private void createClassButton() {

        classMage = new TextButton("Fireball", parentScreen.getButtonSkin(), "default");
        classMage.setSize(220, 30);
        classMage.setPosition(Gdx.graphics.getWidth() / 2 - 250, Gdx.graphics.getHeight() / 2 + 150);
        classMage.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (MovableEntity entity : parentScreen.getWorld().getMovableEntities()) {
                    if (entity instanceof Player) {
                        for (iWeapon wep : parentScreen.getWorld().getWeaponEntities()) {
                            if (wep.getWeaponName().equalsIgnoreCase("fireball")) {
                                WeaponPart temp = entity.getPart(WeaponPart.class);
                                temp.setWeapon(wep);
                                messageToUser = "Weapon selected: Fireball";
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
        classMelee = new TextButton("Flail", parentScreen.getButtonSkin(), "default");
        classMelee.setSize(220, 30);
        classMelee.setPosition(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2 + 150);
        classMelee.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (MovableEntity entity : parentScreen.getWorld().getMovableEntities()) {
                    if (entity instanceof Player) {
                        for (iWeapon wep : parentScreen.getWorld().getWeaponEntities()) {
                            if (wep.getWeaponName().equalsIgnoreCase("sword")) {
                                WeaponPart temp = entity.getPart(WeaponPart.class);
                                temp.setWeapon(wep);
                                messageToUser = "Weapon selected: Flail";
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
    private void createClassButtonGroup() {
        buttonGroup.add(classMage);
        buttonGroup.add(classMelee);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);
    }

    private void showAddableEntities() {
        int j = 1;
        for (MovableEntity entity : ParentScreen.getWorld().getMovableEntities()) {
            entityMap.put(j + ") " + entity.getNameInstance().getName(), entity);
            j++;
        }
        for (ImmovableEntity entity : ParentScreen.getWorld().getImmovableEntities()) {
            entityMap.put(j + ") " + entity.getNameInstance().getName(), entity);
            j++;
        }
        entityArray = new String[entityMap.size()];
        int i = 0;
        for (String s : entityMap.keySet()) {
            entityArray[i] = s;
            i++;
        }
        list.setItems(entityArray);
        scrollpane = new ScrollPane(list, parentScreen.getScrollPaneSkin());
        scrollpane.setBounds(ParentScreen.getGameData().getDisplayWidth() / 2 - 180, ParentScreen.getGameData().getDisplayHeight() / 2 - 50, 320, 150);
        stage.addActor(scrollpane); //Add list to the stage.
    }
}
