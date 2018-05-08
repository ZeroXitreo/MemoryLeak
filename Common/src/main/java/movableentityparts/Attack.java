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
    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, MovableEntity entity) {
        if (entity.getType().equalsEnemy()) { //if the entity is an enemy
            entity.setShoot(true);
        } else if (entity.getType().equalsPlayer()) { //else if it is a player
            direction = 0;
            if (gameData.getKeys().isDown(LEFT)) {
                direction = (float) pi;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT)) {
                direction = 0f;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(UP)) {
                direction = (float) pi / 2;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(DOWN)) {
                direction = (float) pi + (float) pi / 2;
                entity.setShoot(true);
                newDirection = true;
            }

            if (gameData.getKeys().isDown(LEFT) && gameData.getKeys().isDown(UP)) {
                direction = (float) (3 * pi) / 4;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(LEFT) && gameData.getKeys().isDown(DOWN)) {
                direction = (float) (4 * pi) / 3;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT) && gameData.getKeys().isDown(UP)) {
                direction = (float) pi / 4;
                entity.setShoot(true);
                newDirection = true;
            }
            if (gameData.getKeys().isDown(RIGHT) && gameData.getKeys().isDown(DOWN)) {
                direction = (float) (7 * pi) / 4;
                entity.setShoot(true);
                newDirection = true;
            }

            if (newDirection) {
                entity.setShootingDirection(direction);
                newDirection = false;
            }
        }
    }
}
