package group9.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import data.GameData;
import group9.manager.GameInputProcessor;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Game implements ApplicationListener {

    private GameData gameData = new GameData();
    private static OrthographicCamera cam;
	private GameInputProcessor gip = new GameInputProcessor(gameData);

    @Override
    public void create() {
        gameData.setDisplayHeight(540);
        gameData.setDisplayWidth(960);
        cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();

//        Gdx.input.setInputProcessor(
//                new GameInputProcessor(gameData)
//        );
    }

    @Override
    public void resize(int i, int i1) {
		
    }

    @Override
    public void render() {
		gip.keyPress();
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
