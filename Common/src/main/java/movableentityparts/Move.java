

package movableentityparts;

import data.Entity;
import data.GameData;
import static data.GameKeys.D;
import data.MovableEntity;
import data.World;

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
	public void process(GameData gameData, MovableEntity entity)
	{
		
		Position position = entity.getPart(Position.class);
		float x = position.getX();
		float y = position.getY();
		
	
		if(left){
//			System.out.println(position.getX() + " x before");
			x--;
//			System.out.println(position.getX());
		}
		
		if(right){
//			System.out.println(position.getX() + " x before");
			x++;
//			System.out.println(position.getX());
		}
		
		if(up){
//                    System.out.println(position.getY() + " y before");
			y++;
//                    System.out.println(position.getY());
		}
		
		if(down){
//			System.out.println(position.getY() + " y before");
			y--;
//                    System.out.println(position.getY());
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
