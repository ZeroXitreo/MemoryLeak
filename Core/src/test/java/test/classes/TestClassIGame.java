/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.classes;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iGamePluginServices.class)
public class TestClassIGame implements iGamePluginServices{

    @Override
    public void start(GameData gameData, World world) {
        StaticValues.setIGameStarted(true);
    }

    @Override
    public void stop(GameData gameData, World world) {
        StaticValues.setIGameStopped(true);
    }
    
}
