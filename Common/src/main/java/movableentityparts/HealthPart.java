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
    private int maxHealth;

    public HealthPart(int health) {
        this.health = health;
        this.maxHealth = health;
    }

    public int getMaxHealth() {
        return maxHealth;
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
    public void process(GameData gameData, MovableEntity entity) {
        
        if (entity.getHit()){
            health--;
            entity.setHit(false);
        }
        if(health <= 0){
            dead = true;
        }
        
    }

}
