/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import data.GameData;
import data.World;
import group9.core.MemoryLeak;
import group9.manager.GameInputProcessor;
import java.io.File;

/**
 *
 * @author jonas
 */
public class MenuScreen implements Screen {

    private Stage stage;
//    private Game game;
//    private World world;
//    private GameData gameData;
//    GameInputProcessor gip;
    private Image setupBackground;
    private Skin buttonSkin;
    private Button playButton;
    private ParentScreen parentScreen;

    public MenuScreen() {
        parentScreen = ParentScreen.getInstance();
//        super.game = game;
//        super.world = world;
//        super.gameData = gameData;
//        super.gip = gip;
        stage = new Stage(new ScreenViewport());
//        File file = new File(Gdx.files.getLocalStoragePath() + "/hallo");
//        Gdx.app.log("internal ", Gdx.files.internal("assets/skin/memoryleakTextButton.json").file().getAbsolutePath());
//        Gdx.app.log("external ", Gdx.files.external("assets/skin/memoryleakTextButton.json").file().getAbsolutePath());
//        Gdx.app.log("classpath ", Gdx.files.classpath("assets/skin/memoryleakTextButton.json").file().getAbsolutePath());
//        Gdx.app.log("local ", Gdx.files.local("/hallo").file().getAbsolutePath());
        buttonSkin = new Skin(Gdx.files.internal("assets/skin/memoryleakTextButton.json"));
        setupBackground = new Image(new Texture(Gdx.files.internal("assets/background/mainmenu.png")));
        setupBackground.setPosition(0, 0);
        playButton = new TextButton("Play", buttonSkin, "default");

    }

    @Override
    public void show() {
        playButton.setSize(260, 36);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 50);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("CLICK");
                ParentScreen.getGame().setScreen(new PlayScreen());
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("CLICKY ");
                return true;
            }
        });

        stage.addActor(setupBackground); //Add background picture.
        stage.addActor(playButton); //Add the button to the stage.    
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float f) {
        stage.act(); //Perform ui logic
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
