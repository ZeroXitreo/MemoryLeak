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
public class Timer implements EntityPart{

    private float lifeTime;
    private long start;
    private long stop;
    private boolean remove;
    
    public Timer(float lifeTimeInSeconds){
        remove = false;
        start = System.currentTimeMillis();
        this.lifeTime = lifeTimeInSeconds*1000;
    }
    
    @Override
    public void process(GameData gameData, MovableEntity entity) {
        stop = System.currentTimeMillis();
        if(stop-start >= lifeTime){
            remove = true;
        }
    }

    public boolean isRemove() {
        return remove;
    }
    
}
