/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import com.group9.commonplayer.Player;
import data.GameData;
import static data.GameKeys.*;
import data.MovableEntity;
import data.World;
import movableentityparts.Attack;
import movableentityparts.WeaponPart;
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

    private double pi = Math.PI;

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity player : world.getGameMovableEntities(Player.class)) {
            Position position = player.getPart(Position.class);
            HealthPart healthPart = player.getPart(HealthPart.class);
            Attack direction = player.getPart(Attack.class);
            Move move = player.getPart(Move.class);
            move.setUp(gameData.getKeys().isDown(W));
            move.setLeft(gameData.getKeys().isDown(A));
            move.setDown(gameData.getKeys().isDown(S));
            move.setRight(gameData.getKeys().isDown(D));
            WeaponPart weaponPart = player.getPart(WeaponPart.class);
            healthPart.process(gameData, player);
            move.process(gameData, player);
            position.process(gameData, player);
            updateSpriteCircle(player);
            direction.process(gameData, player);
            weaponPart.process(gameData, player);
        }
    }

    /**
     * Updates the sprite of the MovableEntity
     * @param entity The entity which sprite needs to be updated.
     */
    private void updateSpriteCircle(MovableEntity entity) {
        int numPoints = 12;
        float[] shapeX = new float[numPoints];
        float[] shapeY = new float[numPoints];

        Position position = entity.getPart(Position.class);
        float radius = entity.getRadius();
        float x = position.getX();
        float y = position.getY();

        float angle = 0;

        for (int i = 0; i < numPoints; i++) {
            shapeX[i] = x + (float) Math.cos(angle) * radius;
            shapeY[i] = y + (float) Math.sin(angle) * radius;
            angle += 2 * pi / numPoints;
        }
        entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
    }
}
