/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import data.GameData;
import static data.GameKeys.*;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import services.iEntityProcessingService;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Christian
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class PlayerControlSystem implements iEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity player : world.getMovableEntities(Player.class)) {
            Position position = player.getPart(Position.class);
            HealthPart healthPart = player.getPart(HealthPart.class);
            Move move = player.getPart(Move.class);
            move.setUp(gameData.getKeys().isDown(W));
            move.setLeft(gameData.getKeys().isDown(A));
            move.setDown(gameData.getKeys().isDown(S));
            move.setRight(gameData.getKeys().isDown(D));
            healthPart.process(gameData, player);
            move.process(gameData, player);
            position.process(gameData, player);
            updateSprite(player);
            //		gameKeys.keyPress();
//			move.setDown(gameKeys.keyPress());
//			move.setLeft(true);
//			move.setRight(true);
//		if(Gdx.input.isKeyPressed(Keys.W)){
//			System.out.println("hello");
//			move.setUp(true);
//			System.out.println("up");
//		}

        }
    }

    private void updateSprite(MovableEntity entity) {
        int numPoints = 12;
        float[] shapeX = new float[numPoints];
        float[] shapeY = new float[numPoints];

        Position position = entity.getPart(Position.class);
        float radians = position.getRadians();
        float radius = entity.getRadius();
        float x = position.getX();
        float y = position.getY();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapeX[i] = x + (float) Math.cos(angle + radians) * radius;
            shapeY[i] = y + (float) Math.sin(angle + radians) * radius;
            angle += 2 * 3.1415f / numPoints;
        }
        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}
