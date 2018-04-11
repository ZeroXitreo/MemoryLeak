/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.sword;

import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.Timer;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class SwordControlSystem implements iEntityProcessingService{

    public void process(GameData gameData, World world) {
        for (MovableEntity sword : world.getMovableEntities(Sword.class)) {
            Position position = sword.getPart(Position.class);
            Timer timer = sword.getPart(Timer.class);
            timer.process(gameData, sword);
            if(timer.isRemove()){
                world.removeMovableEntity(sword);
            }
            position.process(gameData, sword);
            updateSpriteCircle(sword);
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
            angle += 2 * 3.1415f / numPoints;
        }
        movableEntity.setShapeX(shapeX);
        movableEntity.setShapeY(shapeY);
    }
}
