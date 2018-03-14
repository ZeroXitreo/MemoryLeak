

package group9.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import data.GameData;

/**
 *
 * @author Jorge Báez Garrido
 */
public class GameInputProcessor extends InputAdapter {
	public GameInputProcessor()
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	public void keyPress(){
		InputAdapter input = new InputAdapter();
		if(Gdx.input.isKeyPressed(Keys.A)){
			System.out.println("yeah boiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
		}
	}
	

}
