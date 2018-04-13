/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.healthsystem;

import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import services.iPostEntityProcessingService;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Mikkel and Jonas
 */
@ServiceProvider(service = iPostEntityProcessingService.class)

public class Life implements iPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        for (MovableEntity movableEntity : world.getMovableEntities()) {
            if (movableEntity.getPart(HealthPart.class) != null) {
                HealthPart current = movableEntity.getPart(HealthPart.class);
                if (current.isDead()) {
                    world.removeMovableEntity(movableEntity.getID());
                }
            }

        }
    }
}
