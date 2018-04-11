/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

import data.GameData;
import static data.GameKeys.DOWN;
import static data.GameKeys.LEFT;
import static data.GameKeys.RIGHT;
import static data.GameKeys.UP;
import data.MovableEntity;

/**
 *
 * @author jonas
 */
public class Attack implements EntityPart {

    private float direction;
    private boolean newDirection;

    @Override
    public void process(GameData gameData, MovableEntity entity) {
        if (entity.getType().equalsIgnoreCase("enemy")) {
            entity.setShoot(true);
        }

        if (entity.getType().equalsIgnoreCase("player")) {
            direction = 0;
            if (gameData.getKeys().isDown(LEFT)) {
                direction = 3.14f;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT)) {
                direction = 0f;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(UP)) {
                direction = 3.14f / 2;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(DOWN)) {
                direction = 3.14f + 3.14f / 2;
                entity.setShoot(true);
                newDirection = true;
            }
            
            if (gameData.getKeys().isDown(LEFT) && gameData.getKeys().isDown(UP)) {
                direction = (3 * 3.14f)/4;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(LEFT) && gameData.getKeys().isDown(DOWN)) {
                direction = (4 * 3.14f)/3;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT) && gameData.getKeys().isDown(UP)) {
                direction = 3.14f /4;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT) && gameData.getKeys().isDown(DOWN)) {
                direction = (7* 3.14f)/4;
                entity.setShoot(true);
                newDirection = true;
            }
            
            if(newDirection){
                entity.setDirection(direction);
                newDirection = false;
            }
        }
    }
}
