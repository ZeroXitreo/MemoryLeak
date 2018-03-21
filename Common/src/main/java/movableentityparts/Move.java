

package movableentityparts;

import data.GameData;
import data.MovableEntity;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Move implements EntityPart {
	
	private float maxSpeed;
	private boolean left, up, right, down;
	
	public Move(float maxSpeed){
		this.maxSpeed = maxSpeed;
	}
	
	public void setLeft(boolean left){
		this.left = left;
	}
	public void setRight(boolean right){
		this.right = right;
	}
	public void setUp(boolean up){
		this.up = up;
	}
	public void setDown(boolean down){
		this.down = down;
	}
	
	@Override
	public void process(GameData gameData, MovableEntity movableEntity)
	{
		Position position = movableEntity.getPart(Position.class);
		float x = position.getX();
		float y = position.getY();
		
		if(left){
			System.out.println(position.getX() + " before");
			x++;
			System.out.println(position.getX());
		}
		
		if(right){
			x--;
		}
		
		if(up){
			y++;
		}
		
		if(down){
			y--;
		}
		
		if(x > gameData.getDisplayWidth()){
			x=gameData.getDisplayWidth();
		}
		else if(x < 0){
			x =0 ;
		}
		
		if (y > gameData.getDisplayHeight()) {
            y = gameData.getDisplayHeight();
        }
        else if (y < 0) {
            y = 0;
        }
		
		position.setX(x);
		position.setY(y);
		
	}
	
	

}
