package com.group9.collision;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author Nicolai Matthiesen
 */
@ServiceProvider(service = iGamePluginServices.class)
public class CollisionPlugin implements iGamePluginServices {

    private Collision collision;

    @Override
    public void start(GameData gameData, World world) {
        collision = new Collision();
    }

    @Override
    public void stop(GameData gameData, World world) {
        
    }

}
