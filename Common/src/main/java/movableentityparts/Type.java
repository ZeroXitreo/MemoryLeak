/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

/**
 *
 * @author jonas
 */
public class Type {

    private String theTypeString;

    public void setTypeToPlayer() {
        theTypeString = "player";
    }

    public void setTypeToFriendlyBullet() {
        theTypeString = "friendlyBullet";
    }

    public void setTypeToEnemy() {
        theTypeString = "enemy";
    }

    public void setTypeToEnemyBullet() {
        theTypeString = "enemyBullet";
    }

    public void setTypeToWall() {
        theTypeString = "wall";
    }

    public String getType() {
        return theTypeString;
    }

    public boolean equals(Type anotherType) {
        if (anotherType.getType().equals(theTypeString)) {
            return true;
        }
        return false;
    }

    public boolean equalsEnemy() {
        if (theTypeString.equals("enemy")) {
            return true;
        }
        return false;
    }
    
    public boolean equalsEnemyBullet(){
        if (theTypeString.equals("enemyBullet")) {
            return true;
        }
        return false;
    }
    
    public boolean equalsFriendlyBullet(){
        if (theTypeString.equals("friendlyBullet")) {
            return true;
        }
        return false;
    }
    
    public boolean equalsPlayer(){
        if (theTypeString.equals("player")) {
            return true;
        }
        return false;
    }
    
    public boolean equalsWalls(){
        if (theTypeString.equals("wall")) {
            return true;
        }
        return false;
    }
}
