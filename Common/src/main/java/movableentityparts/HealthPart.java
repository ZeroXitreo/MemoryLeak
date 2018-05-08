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

    /**
     * @return int indicating max health of the entity.
     */
    public int getMaxHealth() {
        return maxHealth;
    }

    /**
     * @return int indicating current health of entity.
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health int indicating how much health the entity should have.
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param dead boolean if the entity is dead.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    /**
     * @return boolean if the entity is dead.
     */
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
