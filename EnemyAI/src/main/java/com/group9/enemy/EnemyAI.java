package com.group9.enemy;

import com.group9.commonenemy.Enemy;
import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.Attack;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.WeaponPart;
import movableentityparts.iWeapon;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author Jorge Báez Garrido
 */
@ServiceProvider(service = iEntityProcessingService.class)

public class EnemyAI implements iEntityProcessingService {

    private float playerX;
    private float playerY;
    private float enemyX;
    private float enemyY;
    private iWeapon weapon;

    @Override
    public void process(GameData gameData, World world) {
        //playerX = gameData.getDisplayWidth() / 2;
        for (MovableEntity movableEntity : world.getMovableEntities(Enemy.class)) {

            Move move = movableEntity.getPart(Move.class);
            move.setDirection(movableEntity.getDirection());
            HealthPart health = movableEntity.getPart(HealthPart.class);
            Attack direction = movableEntity.getPart(Attack.class);
            Position position = movableEntity.getPart(Position.class);
            WeaponPart weaponPart = movableEntity.getPart(WeaponPart.class);
            if (!movableEntity.hasWeapon() && world.getWeapons() != null) {
                for (iWeapon currentWeapon : world.getWeapons()) {
                    if (currentWeapon.getType().equalsIgnoreCase("projectile")) {
                        this.weapon = currentWeapon;
                        movableEntity.setHasWeapon(true);
                        break;
                    }
                }
                weaponPart.setWeapon(weapon);
            }

            updateSprite(movableEntity);

            health.process(gameData, movableEntity);
            direction.process(gameData, movableEntity);
            move.process(gameData, movableEntity);
            position.process(gameData, movableEntity);
            weaponPart.process(gameData, movableEntity);

        }

    }

    private void updateSprite(MovableEntity movableEntity) {

        int numPoints = 12;
        float[] shapeX = new float[numPoints];
        float[] shapeY = new float[numPoints];

        Position position = movableEntity.getPart(Position.class);
        float radians = position.getRadians();
        float radius = movableEntity.getRadius();
        float x = position.getX();
        float y = position.getY();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapeX[i] = x + (float) Math.cos(angle + radians) * radius;
            shapeY[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numPoints;
        }
        movableEntity.setShapeX(shapeX);
        movableEntity.setShapeY(shapeY);
    }
}
