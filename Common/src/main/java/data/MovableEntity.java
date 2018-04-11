package data;

/**
 *
 * @author Jorge Báez Garrido
 */
public class MovableEntity extends Entity {

    private float direction = 0;
    private boolean onlyOneWeapon;
    private boolean shoot;

    public float getDirection() {
        return direction;
    }

    public void setDirection(float direction) {
        this.direction = direction;
    }

    public boolean hasWeapon() {
        return onlyOneWeapon;
    }

    public void setHasWeapon(boolean onlyOneWeapon) {
        this.onlyOneWeapon = onlyOneWeapon;
    }

    public boolean getShoot() {
        return shoot;
    }

    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }
    
    
    
}
