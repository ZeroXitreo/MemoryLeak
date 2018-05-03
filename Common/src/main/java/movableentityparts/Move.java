package movableentityparts;

import data.GameData;
import data.MovableEntity;
import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Move implements EntityPart {

    private float maxSpeed, direction, wasd;
    private boolean left, up, right, down, useDirection, moveEntity;
    private double pi = Math.PI;

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

    public float getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public void process(GameData gameData, MovableEntity entity) {
        direction = (float)(direction%(2*pi));
        entity.setMoveDirection(1);
        Position position = entity.getPart(Position.class);
        float x = position.getX();
        float y = position.getY();
        if (useDirection) {
            if(direction >= 0f && direction <= pi/2f){
                entity.setMoveDirection(2); //go right
            } else if(direction > pi/2 && direction <= pi){
                entity.setMoveDirection(0); //go left
            } else if(direction >= -(pi/2) && direction < 0){
                entity.setMoveDirection(2); //go right
            } else if(direction < -1/2){
                entity.setMoveDirection(0); //go left
            }
            x += (float) cos(direction) * maxSpeed;
            y += (float) sin(direction) * maxSpeed;

        } else {
            moveEntity = false;
            wasd = 0;
            
            if (left) {
                wasd = (float) pi;
                moveEntity = true;
            }
            if (right) {
                wasd = (float) (2 * pi);
                moveEntity = true;
            }
            if (up) {
                wasd = (float) (pi / 2);
                moveEntity = true;
            }
            if (down) {
                wasd = (float) pi + (float) (pi / 2);
                moveEntity = true;
            }
            if (left && up) {
                wasd = (float) (3 * pi) / 4;
                moveEntity = true;
            }
            if (left && down) {
                wasd = (float) (4 * pi) / 3;
                moveEntity = true;
            }
            if (right && up) {
                wasd = (float) (pi / 4);
                moveEntity = true;
            }
            if (right && down) {
                wasd = (float) (7 * pi) / 4;
                moveEntity = true;
            }
            if (wasd >= pi / 2 && wasd <= (3 * pi) / 2) {
                entity.setMoveDirection(0);
            } else if (wasd > 0 && wasd < pi / 2 || wasd > (3 * pi / 2)) {
                entity.setMoveDirection(2);
            }
            if (moveEntity) {
                x += (float) cos(wasd) * maxSpeed;
                y += (float) sin(wasd) * maxSpeed;
            }

        }

        if (x > gameData.getMoveAreaWidthMax()) {
            x = gameData.getMoveAreaWidthMax();
        } else if (x < gameData.getMoveAreaWidthMin()) {
            x = gameData.getMoveAreaWidthMin();
        }

        if (y > gameData.getMoveAreaHeightMax()) {
            y = gameData.getMoveAreaHeightMax();
        } else if (y < gameData.getMoveAreaHeightMin()) {
            y = gameData.getMoveAreaHeightMin();
        }

        position.setX(x);
        position.setY(y);

    }

}
