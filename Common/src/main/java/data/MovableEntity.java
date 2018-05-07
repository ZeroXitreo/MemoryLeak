package data;

public class MovableEntity extends Entity {

    private float direction = 0;
    private boolean onlyOneWeapon = false;
    private boolean shoot;
    private int moveDirection;
	private double moveToX;
	private double moveToY;

    public void moveTowards(double x, double y) {
        moveToX = x;
        moveToY = y;
    }

    public double getMoveTowardsX() {
		return moveToX;
    }

    public double getMoveTowardsY() {
		return moveToY;
    }

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
    
    // 0 = left
    // 1 = stading still
    // 2 = right
    public void setMoveDirection(int moveDirection){
        this.moveDirection = moveDirection;
    }
    
    public int getMoveDirection(){
        return moveDirection;
    }
}
