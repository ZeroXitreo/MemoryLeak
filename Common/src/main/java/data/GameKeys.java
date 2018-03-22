

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
        
        public void setKeys(int key, boolean pressed){
            keys[key] = pressed;
        }
        
        public boolean isDown(int k){
            return keys[k];
        }
	
//	public boolean keyPress(){
//		if(Gdx.input.isKeyPressed(Keys.W)){
//			
//		}
//		if(Gdx.input.isKeyPressed(Keys.A)){
//			System.out.println("A");
//		}
//		if(Gdx.input.isKeyPressed(Keys.S)){
//			System.out.println("S");
//		}
//		if(Gdx.input.isKeyPressed(Keys.D)){
//			System.out.println("D");
//		}
//		if(Gdx.input.isKeyPressed(Keys.UP)){
//			System.out.println("up");
//		}
//		if(Gdx.input.isKeyPressed(Keys.DOWN)){
//			System.out.println("down");
//		}
//		if(Gdx.input.isKeyPressed(Keys.RIGHT)){
//			System.out.println("right");
//		}
//		if(Gdx.input.isKeyPressed(Keys.LEFT)){
//			System.out.println("left");
//		}
//		return false;
//	}

}
