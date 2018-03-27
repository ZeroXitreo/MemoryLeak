/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;
import services.iEntityProcessingService;
import services.iGamePluginServices;
import services.iPostEntityProcessingService;

/**
 *
 * @author jonas
 */
public class ParentScreen {

    private static Game game;
    private static GameInputProcessor gip;
    private static World world;
    private static GameData gameData;
    private static ParentScreen instance = null;
    private Lookup lookup = Lookup.getDefault();
    private Lookup.Result<iGamePluginServices> result;
    private List<iGamePluginServices> gamePlugin = new ArrayList<>();

    public ParentScreen(Game game, World world, GameData gameData, GameInputProcessor gip) {
        this.game = game;
        this.world = world;
        this.gameData = gameData;
        this.gip = gip;
    }

    public static ParentScreen getInstance() {
        if (instance == null) {
            instance = new ParentScreen(getGame(), getWorld(), getGameData(), getGip());
            return instance;
        } else {
            return instance;
        }
    }

    public Collection<? extends iGamePluginServices> getPluginServices() {
        return getLookup().lookupAll(iGamePluginServices.class);
    }

    public Collection<? extends iEntityProcessingService> getEntityProcessingServices() {
        return getLookup().lookupAll(iEntityProcessingService.class);
    }

    public Collection<? extends iPostEntityProcessingService> getPostEntityProcessingService() {
        return getLookup().lookupAll(iPostEntityProcessingService.class);
    }

    private final LookupListener lookupListener = new LookupListener() {

        @Override
        public void resultChanged(LookupEvent le) {
            Collection<? extends iGamePluginServices> updated = getResult().allInstances();

            for (iGamePluginServices gs : updated) {
                if (!gamePlugin.contains(gs)) {
                    gs.start(getGameData(), getWorld());
                    getGamePlugin().remove(gs);
                }
            }
        }
    };

    /**
     * @return the game
     */
    public static Game getGame() {
        return game;
    }

    /**
     * @param aGame the game to set
     */
    public static void setGame(Game aGame) {
        game = aGame;
    }

    /**
     * @return the gip
     */
    public static GameInputProcessor getGip() {
        return gip;
    }

    /**
     * @param aGip the gip to set
     */
    public static void setGip(GameInputProcessor aGip) {
        gip = aGip;
    }

    /**
     * @return the world
     */
    public static World getWorld() {
        return world;
    }

    /**
     * @param aWorld the world to set
     */
    public static void setWorld(World aWorld) {
        world = aWorld;
    }

    /**
     * @return the gameData
     */
    public static GameData getGameData() {
        return gameData;
    }

    /**
     * @param aGameData the gameData to set
     */
    public static void setGameData(GameData aGameData) {
        gameData = aGameData;
    }

    /**
     * @param aInstance the instance to set
     */
    public static void setInstance(ParentScreen aInstance) {
        instance = aInstance;
    }

    /**
     * @return the lookup
     */
    public Lookup getLookup() {
        return lookup;
    }

    /**
     * @param lookup the lookup to set
     */
    public void setLookup(Lookup lookup) {
        this.lookup = lookup;
    }

    /**
     * @return the result
     */
    public Lookup.Result<iGamePluginServices> getResult() {
        return result;
    }

    public void setResult() {
        result = lookup.lookupResult(iGamePluginServices.class);
        result.addLookupListener(lookupListener);
        result.allItems();
        for (iGamePluginServices plugin : result.allInstances()) {
            plugin.start(gameData, world);
            gamePlugin.add(plugin);
        }
    }

    /**
     * @return the gamePlugin
     */
    public List<iGamePluginServices> getGamePlugin() {
        return gamePlugin;
    }

    /**
     * @param gamePlugin the gamePlugin to set
     */
    public void setGamePlugin(List<iGamePluginServices> gamePlugin) {
        this.gamePlugin = gamePlugin;
    }

}
