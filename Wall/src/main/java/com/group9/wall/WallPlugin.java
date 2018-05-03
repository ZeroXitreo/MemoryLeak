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

    @Override
    public void start(GameData gameData, World world) {
        random = new Random();
        for (int i = 0; i < 4; i++) {
            wall = createWall(gameData);
            world.addImmovableEntity(wall);
        }

    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeImmovableEntity(wall);
        world.removeGameImmovableEntity(wall);
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
