/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data.GameData;
import data.World;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Lookup;
import services.iEntityProcessingService;
import services.iGamePluginServices;
import services.iPostEntityProcessingService;
import test.classes.StaticValues;

/**
 *
 * @author Forberg
 */
public class InterfaceTest {

    World world;
    GameData gameData;
    Lookup lookup;

    public InterfaceTest() {
        world = new World();
        gameData = new GameData();
        lookup = Lookup.getDefault();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void iGamePluginTest() {

        //Check if the lookup can find the service
        assertTrue(!lookup.lookupAll(iGamePluginServices.class).isEmpty());

        //Starts and Stops all the iGamePluginServices.
        for (iGamePluginServices plugin : lookup.lookupAll(iGamePluginServices.class)) {
            plugin.start(gameData, world);
            plugin.stop(gameData, world);
        }

        //Check if the calls have been succesful
        assertTrue(StaticValues.isIGameStarted());
        assertTrue(StaticValues.isIGameStopCalled());
    }

    @Test
    public void iEntityTest() {
        //Check if the lookup can find the service
        assertTrue(!lookup.lookupAll(iEntityProcessingService.class).isEmpty());

        //Process all the iEntityProcessingService
        for (iEntityProcessingService plugin : lookup.lookupAll(iEntityProcessingService.class)) {
            plugin.process(gameData, world);
        }

        //Check if the call have been succesful
        assertTrue(StaticValues.isEntityCalled());
    }

    @Test
    public void IPostEntity() {
        //Check if the lookup can find the services
        assertTrue(!lookup.lookupAll(iPostEntityProcessingService.class).isEmpty());

        //Process all the iPostEntityProcessingService
        for (iPostEntityProcessingService plugin : lookup.lookupAll(iPostEntityProcessingService.class)) {
            plugin.process(gameData, world);
        }

        //Check if the calls have been succesful
        assertTrue(StaticValues.isPostEntityCalled());
    }

}
