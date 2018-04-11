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
public class GameInputProcessor {
    //Instance of GameData from the common component

    private final GameData gameData;

    public GameInputProcessor(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * This method will check rather or not the keys that are used in the game
     * are pressed. If one of the keys is pressed it will set it true in the
     * setKeys() method and tell what key it is.
     */
    public void keyPress() {

        if (Gdx.input.isKeyPressed(Keys.W)) {
            gameData.getKeys().setKeys(W, true);
        } else {
            gameData.getKeys().setKeys(W, false);
        }

        if (Gdx.input.isKeyPressed(Keys.A)) {
            gameData.getKeys().setKeys(A, true);
        } else {
            gameData.getKeys().setKeys(A, false);
        }

        if (Gdx.input.isKeyPressed(Keys.S)) {
            gameData.getKeys().setKeys(S, true);
        } else {
            gameData.getKeys().setKeys(S, false);
        }

        if (Gdx.input.isKeyPressed(Keys.D)) {
            gameData.getKeys().setKeys(D, true);
        } else {
            gameData.getKeys().setKeys(D, false);
        }
        
        if (Gdx.input.isKeyPressed(Keys.UP)) {
            gameData.getKeys().setKeys(UP, true);
        } else {
            gameData.getKeys().setKeys(UP, false);
        }
        
        if (Gdx.input.isKeyPressed(Keys.DOWN)) {
            gameData.getKeys().setKeys(DOWN, true);
        } else {
            gameData.getKeys().setKeys(DOWN, false);
        }
        
        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            gameData.getKeys().setKeys(LEFT, true);
        } else {
            gameData.getKeys().setKeys(LEFT, false);
        }
        
        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            gameData.getKeys().setKeys(RIGHT, true);
        } else {
            gameData.getKeys().setKeys(RIGHT, false);
        }
    }

}
