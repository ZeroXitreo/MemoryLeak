

package data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;


/**
 *
 * @author Jorge Báez Garrido
 */
public class GameKeys {
	
	//private final GameData gameData;
    
    private static boolean[] keys;
    
    public static final int NUM_KEYS =8;
    public static final int W =0;
    public static final int A =1;
    public static final int S =2;
    public static final int D =3;
    public static final int UP =4;
    public static final int DOWN =5;
    public static final int LEFT =6;
    public static final int RIGHT =7;
	
	public GameKeys()
	{
            keys = new boolean[NUM_KEYS];
	}
        
        /**
         * @param key what key.
         * @param pressed boolean if it is down.
         */
        public void setKeys(int key, boolean pressed){
            keys[key] = pressed;
        }
        
        /**
         * @param key what key.
         * @return boolean if the key is down.
         */
        public boolean isDown(int key){
            return keys[key];
        }

}
