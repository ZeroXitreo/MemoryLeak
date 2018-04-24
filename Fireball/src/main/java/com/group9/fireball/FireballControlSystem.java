/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.fireball;

import com.group9.commonfireball.Fireball;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.Timer;
import services.iEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class FireballControlSystem implements iEntityProcessingService {

    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity bullet : world.getMovableEntities(Fireball.class)) {
            Position position = bullet.getPart(Position.class);
            HealthPart healthPart = bullet.getPart(HealthPart.class);
            Timer timer = bullet.getPart(Timer.class);
            Move move = bullet.getPart(Move.class);
            move.setUseDirection(true);
            move.setDirection(bullet.getDirection());
            move.process(gameData, bullet);
            healthPart.process(gameData, bullet);
            timer.process(gameData, bullet);
            if (timer.isRemove()) {
                world.removeMovableEntity(bullet);
            }
            position.process(gameData, bullet);
            updateSpriteCircle(bullet);
        }
    }

    private void updateSpriteCircle(MovableEntity movableEntity) {
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
