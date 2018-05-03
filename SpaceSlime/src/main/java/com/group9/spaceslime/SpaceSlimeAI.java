package com.group9.spaceslime;

import com.group9.commonspaceslime.SpaceSlime;
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

public class SpaceSlimeAI implements iEntityProcessingService {

    private iWeapon weapon;
    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity movableEntity : world.getGameMovableEntities(SpaceSlime.class)) {
            Move move = movableEntity.getPart(Move.class);
            move.setDirection(movableEntity.getDirection());
            HealthPart health = movableEntity.getPart(HealthPart.class);
            Attack direction = movableEntity.getPart(Attack.class);
            Position position = movableEntity.getPart(Position.class);
            WeaponPart weaponPart = movableEntity.getPart(WeaponPart.class);
            if (!movableEntity.hasWeapon() && world.getWeaponEntities() != null) {
                for (iWeapon currentWeapon : world.getWeaponEntities()) {
                    if (currentWeapon.getWeaponName().equalsIgnoreCase("fireball")) {
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
            angle += 2 * pi / numPoints;
        }
        movableEntity.setShapeX(shapeX);
        movableEntity.setShapeY(shapeY);
    }
}
