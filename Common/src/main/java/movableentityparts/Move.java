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

	private float maxSpeed, direction;
	private boolean left, up, right, down, useDirection;
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

		// Anything else than player
		direction = (float) (direction % (2 * pi));
		entity.setMoveDirection(1);
		if (useDirection)
		{
			if (direction >= 0f && direction <= pi / 2f)
			{
				entity.setMoveDirection(2); //go right
			}
			else if (direction > pi / 2 && direction <= pi)
			{
				entity.setMoveDirection(0); //go left
			}
			else if (direction >= -(pi / 2) && direction < 0)
			{
				entity.setMoveDirection(2); //go right
			}
			else if (direction < -pi / 2)
			{
				entity.setMoveDirection(0); //go left
			}
			x += (float) cos(direction) * maxSpeed;
			y += (float) sin(direction) * maxSpeed;
			position.setX(x);
			position.setY(y);
		}
		else // is Player
		{
			float moveX = 0;
			float moveY = 0;
			moveX += right ? maxSpeed : 0;
			moveX -= left ? maxSpeed : 0;
			moveY += up ? maxSpeed : 0;
			moveY -= down ? maxSpeed : 0;
			if (moveX != 0)
			{
				entity.setMoveDirection(1 + (int) moveX);
			}
			else
			{
				entity.setMoveDirection(1 + (int) moveY);
			}
			
			processMovementTo(position, x + moveX, y + moveY);
		}

		// Block entity to get beyond the border
		x = position.getX();
		y = position.getY();
		if (x > gameData.getMoveAreaWidthMax())
		{
			position.setX(gameData.getMoveAreaWidthMax());
		}
		if (x < gameData.getMoveAreaWidthMin())
		{
			position.setX(gameData.getMoveAreaWidthMin());
		}

		if (y > gameData.getMoveAreaHeightMax())
		{
			position.setY(gameData.getMoveAreaHeightMax());
		}
		if (y < gameData.getMoveAreaHeightMin())
		{
			position.setY(gameData.getMoveAreaHeightMin());
		}
	}

	/**
	 * Processes the movement of a Position towards a coordiante in a set given speed
	 * 
	 * @param position the position to be moved
	 * @param x the x coordinate the position wants to be in
	 * @param y  the y coordinate the position wants to be in
	 */
	private void processMovementTo(Position position, float x, float y)
	{
		if (position.getX() == x && position.getY() == y)
		{
			return;
		}

		float moveX = position.getX();
		float moveY = position.getY();
		float fixedX = x;
		float fixedY = y;

		double a = moveX - fixedX;
		double b = moveY - fixedY;
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		double angle_B = Math.atan(b / a);

		double pushPos_c = c - maxSpeed;
		double pushPos_b = (Math.sin(angle_B) * pushPos_c);
		double pushPos_a = (Math.cos(angle_B) * pushPos_c);

		double multiplier = 1;
		multiplier = moveX < fixedX ? -multiplier : multiplier;

		position.setX((float) (fixedX + pushPos_a * multiplier));
		position.setY((float) (fixedY + pushPos_b * multiplier));
	}

}
