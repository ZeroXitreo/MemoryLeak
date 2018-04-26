/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

/**
 *
 * @author jonas
 */
public class MenuScreen implements Screen {

    private Stage stage;
    private Image menuBackground;
    private Button playButton;
    private Button exitButton;
    private ParentScreen parentScreen;

    public MenuScreen() {
        parentScreen = ParentScreen.getInstance();
        stage = new Stage(new ScreenViewport());
        playButton = new TextButton("Play", parentScreen.getButtonSkin(), "default");
        exitButton = new TextButton("Exit", parentScreen.getButtonSkin(), "default");
        menuBackground = parentScreen.getMenuBackground();
    }

    @Override
    public void show() {
        menuBackground.setPosition(0, 0);
        playButton.setSize(260, 36);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2 + 50);
        playButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                ParentScreen.getGame().setScreen(new SetupScreen());
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
        exitButton.setSize(260, 36);
        exitButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 2);
        exitButton.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                System.exit(0);
            }
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });

        stage.addActor(menuBackground); //Add background picture.
        stage.addActor(playButton); //Add the button to the stage.  
        stage.addActor(exitButton); //Add the button to the stage.
        Gdx.input.setInputProcessor(stage); //Enables the things added to stage.
    }

    @Override
    public void render(float f) {
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
