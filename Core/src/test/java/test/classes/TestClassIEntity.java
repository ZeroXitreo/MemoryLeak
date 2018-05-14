/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.classes;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iEntityProcessingService.class)
public class TestClassIEntity implements iEntityProcessingService{

    @Override
    public void process(GameData gameData, World world) {
        StaticValues.setIEntity(true);
    }
    
}
