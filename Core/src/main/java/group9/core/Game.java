

package group9.core;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import data.GameData;
import group9.manager.GameInputProcessor;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Game implements ApplicationListener{
	
	private GameData gameData = new GameData();
	private static OrthographicCamera cam;
	
	@Override
	public void create()
	{
		gameData.setDisplayHeight(1080);
		gameData.setDisplayWidth(1920);
		cam = new OrthographicCamera(gameData.getDisplayWidth(), gameData.getDisplayHeight());
        cam.translate(gameData.getDisplayWidth() / 2, gameData.getDisplayHeight() / 2);
        cam.update();
		
		 Gdx.input.setInputProcessor(
                new GameInputProcessor()
        );
	}

	@Override
	public void resize(int i, int i1)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void render()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void pause()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void resume()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void dispose()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}
