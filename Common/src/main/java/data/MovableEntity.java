package data;

import movableentityparts.Type;

/**
 *
 * @author Jorge Báez Garrido
 */
public abstract class MovableEntity extends Entity {

    private float direction = 0;
    private float shootingDirection = 0;
    private boolean onlyOneWeapon = false;
    private boolean shoot;
    private int moveDirection;

    /**
     * Gets the direction the entity is moving in using radians float.
     * @return float in which direction the entity is moving in radians.
     */
    public float getDirection() {
        return direction;
    }

    /**
     * Sets the direction the entity is moving in using radians float.
     * @param direction float in which direction the entity is moving in radians.
     */
    public void setDirection(float direction) {
        this.direction = direction;
    }

    /**
     * Get the shooting direction in radians.
     * @return float radins of the shooting direction.
     */
    public float getShootingDirection() {
        return shootingDirection;
    }

    /**
     * Sets the shooting direction in radians.
     * Where the entity wants to shoot/fire their weapon.
     * @param shootingDirection float indicating in radians where to shoot.
     */
    public void setShootingDirection(float shootingDirection) {
        this.shootingDirection = shootingDirection;
    }

    /**
     * If the entity has a weapon.
     * @return boolean if the enemy already has a weapon.
     */
    public boolean hasWeapon() {
        return onlyOneWeapon;
    }

    /**
     * Each entity should only have one weapon.
     * True = the entity has a weapon.
     * False = the entity doesn't have a weapon.
     * @param onlyOneWeapon boolean if the entity has a weapon.
     */
    public void setHasWeapon(boolean onlyOneWeapon) {
        this.onlyOneWeapon = onlyOneWeapon;
    }

    /**
     * boolean if the entity is shooting.
     * True = it is shooting.
     * False = it isn't shooting.
     * @return boolean if the entity is shooting.
     */
    public boolean getShoot() {
        return shoot;
    }

    /**
     * Used to indicate if the entity is shooting.
     * True = Shooting.
     * False = Not Shooting.
     * @param shoot boolean if the enemy is shooting.
     */
    public void setShoot(boolean shoot) {
        this.shoot = shoot;
    }

    /**
     * 0 = Left, 1 = Stading Still and 2 = Right
     *
     * @param moveDirection int from 0 - 2
     */
    public void setMoveDirection(int moveDirection) {
        if (moveDirection < 0 || moveDirection > 2) {
            moveDirection = 1;
        } else {
            this.moveDirection = moveDirection;
        }
    }

    /**
     * Returns the move direction, where 0 = left, 1 = not moving and 2 = Right.
     * @return int from 0 - 2.
     */
    public int getMoveDirection() {
        return moveDirection;
    }
}
