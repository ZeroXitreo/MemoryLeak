

package movableentityparts;

import data.Entity;
import data.GameData;
import data.MovableEntity;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Position implements EntityPart {
	
	private float x, y;
	private float radians;
	
	public Position(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}
	
	public void setX(float x){
		this.x = x;
	}
	
	public void setY(float y){
		this.y = y;
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void setRadians(float radians){
		this.radians = radians;
	}
	
	public float getRadians(){
		return radians;
	}
	
	

	@Override
	public void process(GameData gameData, MovableEntity entity)
	{
		
	}

}
