/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.classes;

import data.GameData;
import data.World;
import org.openide.util.lookup.ServiceProvider;
import services.iPostEntityProcessingService;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iPostEntityProcessingService.class)
public class TestClassIPostEntity implements iPostEntityProcessingService {

    @Override
    public void process(GameData gameData, World world) {
        StaticValues.setIPostEntity(true);
    }

}
