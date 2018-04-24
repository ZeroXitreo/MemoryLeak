/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.slime;

import com.group9.commonslime.Slime;
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
 * @author jonas
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class SlimeAI implements iEntityProcessingService {

    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity movableEntity : world.getMovableEntities(Slime.class)) {
            Move move = movableEntity.getPart(Move.class);
            move.setDirection(movableEntity.getDirection());
            HealthPart health = movableEntity.getPart(HealthPart.class);
            Position position = movableEntity.getPart(Position.class);
            updateSprite(movableEntity);
            health.process(gameData, movableEntity);
            move.process(gameData, movableEntity);
            position.process(gameData, movableEntity);
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
