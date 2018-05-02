package movableentityparts;

import data.GameData;
import data.MovableEntity;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Move implements EntityPart
{

	private float maxSpeed, direction, wasd;
	private boolean left, up, right, down, useDirection, moveEntity;
	private double pi = Math.PI;

	public Move(float maxSpeed)
	{
		this.maxSpeed = maxSpeed;
	}

	public void setLeft(boolean left)
	{
		this.left = left;
	}

	public void setRight(boolean right)
	{
		this.right = right;
	}

	public void setUp(boolean up)
	{
		this.up = up;
	}

	public void setDown(boolean down)
	{
		this.down = down;
	}

	public void setUseDirection(boolean useDirection)
	{
		this.useDirection = useDirection;
	}

	public void setDirection(float direction)
	{
		this.direction = direction;
	}

	public float getMaxSpeed()
	{
		return maxSpeed;
	}

	@Override
	public void process(GameData gameData, MovableEntity entity)
	{
		Position position = entity.getPart(Position.class);
		float x = position.getX();
		float y = position.getY();

		// is Enemy
		if (useDirection)
		{

		}
		else // is Player
		{
			x += right ? maxSpeed : 0;
			x -= left ? maxSpeed : 0;
			y += up ? maxSpeed : 0;
			y -= down ? maxSpeed : 0;
			System.out.println("===");
			System.out.println(position.getX() + ":" + position.getY());
			System.out.println(x + ":" + y);
			processMovementTo(position, x, y);
			System.out.println("===");
		}

//		
//		direction = (float) (direction % (2 * pi));
//		entity.setMoveDirection(1);
//		if (useDirection)
//		{
//			if (direction >= 0f && direction <= pi / 2f)
//			{
//				entity.setMoveDirection(2); //go right
//			}
//			else if (direction > pi / 2 && direction <= pi)
//			{
//				entity.setMoveDirection(0); //go left
//			}
//			else if (direction >= -(pi / 2) && direction < 0)
//			{
//				entity.setMoveDirection(2); //go right
//			}
//			else if (direction < -1 / 2)
//			{
//				entity.setMoveDirection(0); //go left
//			}
//			x += (float) cos(direction) * maxSpeed;
//			y += (float) sin(direction) * maxSpeed;
//
//		}
//		else
//		{
//			moveEntity = false;
//			wasd = 0;
//
//			if (left)
//			{
//				wasd = (float) pi;
//				moveEntity = true;
//			}
//			if (right)
//			{
//				wasd = (float) (2 * pi);
//				moveEntity = true;
//			}
//			if (up)
//			{
//				wasd = (float) (pi / 2);
//				moveEntity = true;
//			}
//			if (down)
//			{
//				wasd = (float) pi + (float) (pi / 2);
//				moveEntity = true;
//			}
//			if (left && up)
//			{
//				wasd = (float) (3 * pi) / 4;
//				moveEntity = true;
//			}
//			if (left && down)
//			{
//				wasd = (float) (4 * pi) / 3;
//				moveEntity = true;
//			}
//			if (right && up)
//			{
//				wasd = (float) (pi / 4);
//				moveEntity = true;
//			}
//			if (right && down)
//			{
//				wasd = (float) (7 * pi) / 4;
//				moveEntity = true;
//			}
//			if (wasd >= pi / 2 && wasd <= (3 * pi) / 2)
//			{
//				entity.setMoveDirection(0);
//			}
//			else if (wasd > 0 && wasd < pi / 2 || wasd > (3 * pi / 2))
//			{
//				entity.setMoveDirection(2);
//			}
//			if (moveEntity)
//			{
//				x += (float) cos(wasd) * maxSpeed;
//				y += (float) sin(wasd) * maxSpeed;
//			}
//
//		}
//
//		if (x > gameData.getDisplayWidth() - 85)
//		{
//			x = gameData.getDisplayWidth() - 85;
//		}
//		else if (x < 68)
//		{
//			x = 68;
//		}
//
//		if (y > gameData.getDisplayHeight() - 45)
//		{
//			y = gameData.getDisplayHeight() - 45;
//		}
//		else if (y < 64)
//		{
//			y = 64;
//		}
//
//		position.setX(x);
//		position.setY(y);
	}
	
	private void processMovementTo(Position position, float x, float y)
	{
		if(position.getX() == x && position.getY() == y)
		{
			return;
		}
		
		float moveX = position.getX();
		float moveY = position.getY();
		float fixedX = x;
		float fixedY = y;
		
		float a = moveX - fixedX;
		float b = moveY - fixedY;
		float c = (float) Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		float angle_B = (float) Math.atan(b / a);

		float pushPos_c = c - maxSpeed;
		float pushPos_b = (float) (Math.sin(angle_B) * pushPos_c);
		float pushPos_a = (float) (Math.cos(angle_B) * pushPos_c);

		float multiplier = 1;
		multiplier = moveX < fixedX ? -multiplier : multiplier;

		System.out.println((fixedX + pushPos_a * multiplier) + ":" + (fixedY + pushPos_b * multiplier));
		position.setX((float) Math.round(fixedX + pushPos_a * multiplier));
		position.setY((float) Math.round(fixedY + pushPos_b * multiplier));
	}

}
