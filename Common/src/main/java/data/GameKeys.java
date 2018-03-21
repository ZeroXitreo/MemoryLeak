

package data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


/**
 *
 * @author Jorge Báez Garrido
 */
public class GameKeys {
	
	private final GameData gameData;
	
	public GameKeys(GameData gameData)
	{
		this.gameData = gameData;
	}
	
	public boolean keyPress(){
		if(Gdx.input.isKeyPressed(Keys.W)){
			System.out.println("W");
		}
		if(Gdx.input.isKeyPressed(Keys.A)){
			System.out.println("A");
		}
		if(Gdx.input.isKeyPressed(Keys.S)){
			System.out.println("S");
		}
		if(Gdx.input.isKeyPressed(Keys.D)){
			System.out.println("D");
		}
		if(Gdx.input.isKeyPressed(Keys.UP)){
			System.out.println("up");
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			System.out.println("down");
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			System.out.println("right");
		}
		if(Gdx.input.isKeyPressed(Keys.LEFT)){
			System.out.println("left");
		}
		return false;
	}

}
