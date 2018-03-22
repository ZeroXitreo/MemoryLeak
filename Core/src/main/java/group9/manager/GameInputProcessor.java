package group9.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import data.GameData;
import data.GameKeys;
import static data.GameKeys.*;

/**
 *
 * @author Jorge Báez Garrido
 */
public class GameInputProcessor
{

	private final GameData gameData;

	public GameInputProcessor(GameData gameData)
	{
		this.gameData = gameData;
	}

	public void keyPress()
	{

		if(Gdx.input.isKeyPressed(Keys.W))
		{
			gameData.getKeys().setKeys(W, true);
		}
		else
		{
			gameData.getKeys().setKeys(W, false);
		}

		if(Gdx.input.isKeyPressed(Keys.A))
		{
			gameData.getKeys().setKeys(A, true);
		}
		else
		{
			gameData.getKeys().setKeys(A, false);
		}

		if(Gdx.input.isKeyPressed(Keys.S))
		{
			gameData.getKeys().setKeys(S, true);
		}
		else
		{
			gameData.getKeys().setKeys(S, false);
		}

		if(Gdx.input.isKeyPressed(Keys.D))
		{
			gameData.getKeys().setKeys(D, true);
		}
		else
		{
			gameData.getKeys().setKeys(D, false);
		}
	}

}
