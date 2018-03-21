

package group9.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import data.GameData;
import data.GameKeys;

/**
 *
 * @author Jorge Báez Garrido
 */
public class GameInputProcessor {
	
	private final GameData gameData;
	public GameInputProcessor(GameData gameData)
	{
		this.gameData = gameData;
	}
	
	public void keyPress(){
         
		if(Gdx.input.isKeyPressed(Keys.W)){
                    gameData.getKeys().setKeys(GameKeys.W, true);
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
		}
	}
	

}
