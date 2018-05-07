/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.wall;

import com.group9.commonwall.Wall;
import data.GameData;
import data.ImmovableEntity;
import data.World;
import java.util.Random;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iGamePluginServices.class)
public class WallPlugin implements iGamePluginServices {

    private ImmovableEntity wall;
    private Random random;
    private int amountOfWalls;

    @Override
    public void start(GameData gameData, World world) {
        random = new Random();
        amountOfWalls = 4;
        for (int i = 0; i < amountOfWalls; i++) {
            wall = createWall(gameData);
            world.addImmovableEntity(wall);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        for (ImmovableEntity wall : world.getImmovableEntities(Wall.class)) {
            world.removeImmovableEntity(wall);
        }
        for (ImmovableEntity wall : world.getGameImmovableEntities(Wall.class)) {
            world.removeGameImmovableEntity(wall);
        }
    }

    private ImmovableEntity createWall(GameData gameData) {
        float x = gameData.getMoveAreaWidthMin() + random.nextFloat() * (gameData.getMoveAreaWidthMax() - gameData.getMoveAreaWidthMin());;
        float y = gameData.getMoveAreaHeightMin() + random.nextFloat() * (gameData.getMoveAreaHeightMax() - gameData.getMoveAreaHeightMin());
        ImmovableEntity wallEntity = new Wall();
        wallEntity.setRadius(32);
        wallEntity.add(new Position(x, y));
        return wallEntity;
    }

}
