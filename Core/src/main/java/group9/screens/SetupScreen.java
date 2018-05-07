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
    private List<String> entityList;
    private List<String> weaponList;
    private SpriteBatch batch;
    private TreeMap<String, Entity> entityMap;
    private TreeMap<String, iWeapon> weaponMap;
    private String[] entityArray;
    private String[] weaponArray;
    private Button startButton;
    private Button addEnemyButton;
    private Button removeEnemyButton;
    private Button addWeaponButton;
    private BitmapFont font;
    private ScrollPane entityScrollPane;
    private ScrollPane weaponScrollPane;
    private String messageToUser;

    public SetupScreen() {
        messageToUser = "";
        parentScreen = ParentScreen.getInstance();
        parentScreen.setResult();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        entityList = new List<>(parentScreen.getListSkin());
        weaponList = new List<>(parentScreen.getListSkin());
        entityMap = new TreeMap<>();
        weaponMap = new TreeMap<>();
        stage = new Stage(new ScreenViewport());
        batch = new SpriteBatch();
        font = parentScreen.getFont();
    }

    @Override
    public void show() {
        createStartButton();
        createAddEnemyButton();
        createRemoveEnemyButton();
        createAddWeapon();
        showAddableEntities();
        showWeaponEntities();
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
        font.draw(batch, messageToUser, ParentScreen.getGameData().getDisplayWidth() / 2 - 250, ParentScreen.getGameData().getDisplayHeight() / 2 - 60);
        batch.end();

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
        addEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 360, Gdx.graphics.getHeight() / 2 + 50);
        addEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                messageToUser = "You added: " + entityList.getSelected();
                if (entityMap.get(entityList.getSelected()) instanceof MovableEntity) {
                    ParentScreen.getWorld().addGameMovableEntity((MovableEntity) entityMap.get(entityList.getSelected()));
                } else if (entityMap.get(entityList.getSelected()) instanceof ImmovableEntity) {
                    ParentScreen.getWorld().addGameImmovableEntity((ImmovableEntity) entityMap.get(entityList.getSelected()));
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
     * Creates a button that will remove the selected enemy entity from the game
     * stage.
     */
    private void createRemoveEnemyButton() {
        removeEnemyButton = new TextButton(" - ", parentScreen.getButtonSkin(), "default");
        removeEnemyButton.setSize(40, 30);
        removeEnemyButton.setPosition(Gdx.graphics.getWidth() / 2 + 360, Gdx.graphics.getHeight() / 2 + 10);
        removeEnemyButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                messageToUser = "You removed: " + entityList.getSelected();
                if (entityMap.get(entityList.getSelected()) instanceof MovableEntity) {
                    ParentScreen.getWorld().removeGameMovableEntity((MovableEntity) entityMap.get(entityList.getSelected()));
                } else if (entityMap.get(entityList.getSelected()) instanceof ImmovableEntity) {
                    ParentScreen.getWorld().removeGameImmovableEntity((ImmovableEntity) entityMap.get(entityList.getSelected()));
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
     * Creates a button that will add a selected weapon to the player
     */
    private void createAddWeapon() {
        addWeaponButton = new TextButton(" + ", parentScreen.getButtonSkin(), "default");
        addWeaponButton.setSize(40, 30);
        addWeaponButton.setPosition(Gdx.graphics.getWidth() / 2 - 70, Gdx.graphics.getHeight() / 2 + 50);
        addWeaponButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                for (MovableEntity entity : parentScreen.getWorld().getMovableEntities()) {
                    if (entity instanceof Player) {
                        for (iWeapon wep : parentScreen.getWorld().getWeapons()) {
                            if (wep.getWeaponName().equalsIgnoreCase(weaponList.getSelected())) {
                                WeaponPart temp = entity.getPart(WeaponPart.class);
                                temp.setWeapon(wep);
                                messageToUser = "Weapon selected: " + weaponList.getSelected();
                                startButton.setVisible(true);
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
        stage.addActor(addWeaponButton);
    }

    /**
     * Adds all the entities to the libgdx list that is used to let the user
     * select which of them to add to the game.
     */
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
        entityList.setItems(entityArray);
        entityScrollPane = new ScrollPane(entityList, parentScreen.getScrollPaneSkin());
        entityScrollPane.setBounds(ParentScreen.getGameData().getDisplayWidth() / 2 + 30, ParentScreen.getGameData().getDisplayHeight() / 2 - 50, 320, 150);
        stage.addActor(entityScrollPane); //Add list to the stage.
    }

    /**
     * Adds all the weapons in the game to a libgdx list that the user selects
     * their weapon of choice to use in the game.
     */
    private void showWeaponEntities() {
        for (iWeapon weapon : ParentScreen.getWorld().getWeapons()) {
            weaponMap.put(weapon.getWeaponName(), weapon);
        }
        weaponArray = new String[weaponMap.size()];
        int i = 0;
        for (String s : weaponMap.keySet()) {
            weaponArray[i] = s;
            i++;
        }
        weaponList.setItems(weaponArray);
        weaponScrollPane = new ScrollPane(weaponList, parentScreen.getScrollPaneSkin());
        weaponScrollPane.setBounds(ParentScreen.getGameData().getDisplayWidth() / 2 - 400, ParentScreen.getGameData().getDisplayHeight() / 2 - 50, 320, 150);
        stage.addActor(weaponScrollPane);
    }
}
