/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.enemyradar;

import com.group9.commonplayer.Player;
import com.group9.commonslime.Slime;
import com.group9.commonspaceslime.SpaceSlime;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class RadarProcessing implements iEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity player : world.getGameMovableEntities(Player.class)) {
            Position playerPosition = player.getPart(Position.class);
            double playerX = playerPosition.getX();
            double playerY = playerPosition.getY();
            for (MovableEntity enemy : world.getGameMovableEntities(Slime.class)) {
                Position enemyPosition = enemy.getPart(Position.class);
                double enemyX = enemyPosition.getX();
                double enemyY = enemyPosition.getY();
                double direction = Math.atan2(playerY - enemyY, playerX - enemyX);
                float directionf = (float) direction;
                enemy.setDirection(directionf);
            }
            for (MovableEntity enemy : world.getGameMovableEntities(SpaceSlime.class)) {
                Position enemyPosition = enemy.getPart(Position.class);
                double enemyX = enemyPosition.getX();
                double enemyY = enemyPosition.getY();
                double direction = Math.atan2(playerY - enemyY, playerX - enemyX);
                float directionf = (float) direction;
                enemy.setDirection(directionf);
            }
        }
    }

}
