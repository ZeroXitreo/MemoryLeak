/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

import data.GameData;
import data.MovableEntity;

/**
 *
 * @author jonas
 */
public class Type {
    private String theType;
    
    public void setTypeToPlayer(){
        theType = "player";
    }
    
    public void setTypeToFriendlyBullet(){
        theType = "friendlyBullet";
    }
    
    public void setTypeToEnemy(){
        theType = "enemy";
    }
    
    public void setTypeToEnemyBullet(){
        theType = "enemyBullet";
    }
    
    public void setTypeToWall(){
        theType = "wall";
    }
    
    public String getType(){
        return theType;
    }

}
