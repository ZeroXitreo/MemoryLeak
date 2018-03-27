package com.group9.enemy;

import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author Jorge Báez Garrido
 */
@ServiceProvider(service = iEntityProcessingService.class)


public class EnemyAI implements iEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity enemy : world.getMovableEntities(Enemy.class)) {
            Position position = enemy.getPart(Position.class);
            Move move = enemy.getPart(Move.class);
            HealthPart health = enemy.getPart(HealthPart.class);
            health.process(gameData, enemy);
            move.process(gameData, enemy);
            position.process(gameData, enemy);
            updateSprite(enemy);
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
