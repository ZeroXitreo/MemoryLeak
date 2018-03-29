/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

import data.GameData;
import data.MovableEntity;
import data.World;

/**
 *
 * @author jonas
 */
public class HealthPart implements EntityPart {
    
    private int health;
    private boolean dead = false;
    private boolean isHit = false;
    long time = System.currentTimeMillis();
    long time2;

    public HealthPart(int health) {
        this.health = health;
    }


    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }
    
    @Override
    public void process(GameData gameData, MovableEntity entity, World world) {
        
//        time2 = System.currentTimeMillis();
//        if(time2 - time > 2500){
//            isHit = true;
//            time = System.currentTimeMillis();
//        }
//        
//        if (isHit){
//            health--;
//            isHit = false;
//        }
//        if(health <= 0){
//            dead = true;
//        }
        
    }

}
