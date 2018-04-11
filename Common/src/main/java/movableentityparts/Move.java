package movableentityparts;

import data.Entity;
import data.GameData;
import static data.GameKeys.D;
import data.MovableEntity;
import data.World;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Move implements EntityPart {

    private float maxSpeed, direction;
    private boolean left, up, right, down, useDirection;

    public Move(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setUseDirection(boolean useDirection) {
        this.useDirection = useDirection;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    @Override
    public void process(GameData gameData, MovableEntity entity) {

        entity.setMoveDirection(1);
        Position position = entity.getPart(Position.class);
        float x = position.getX();
        float y = position.getY();
        if (useDirection) {
            if (direction >= 3.14f / 2 && direction <= (3 * 3.14f) / 2) {
                entity.setMoveDirection(0);
            } else if (direction > 0 && direction < 3.14f / 2 || direction > (3 * 3.14f / 2)) {
                entity.setMoveDirection(2);
            }
            x += (float) cos(direction) * maxSpeed;
            y += (float) sin(direction) * maxSpeed;

        } else {

            if (left) {
                x -= maxSpeed;
                entity.setMoveDirection(0);
            }

            if (right) {
                x += maxSpeed;
                entity.setMoveDirection(2);
            }

            if (up) {
                y += maxSpeed;
            }

            if (down) {
                y -= maxSpeed;
            }

        }

        if (x > gameData.getDisplayWidth()) {
            x = gameData.getDisplayWidth();
        } else if (x < 0) {
            x = 0;
        }

        if (y > gameData.getDisplayHeight()) {
            y = gameData.getDisplayHeight();
        } else if (y < 0) {
            y = 0;
        }

        position.setX(x);
        position.setY(y);

    }

}
