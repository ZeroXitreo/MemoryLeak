/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import java.io.File;
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
    private AssetManager am;
    private Image menuBackground;
    private Skin buttonSkin;
    private TextureAtlas lava;
    private TextureAtlas memoryLeakPack;
    private BitmapFont font;
    private String assetPath;
    private String mainmenuPath;
    private String skinPath;
    private String lavaPath;
    private String memoryleakGraphicPath;
    private String fontPath;

    public ParentScreen(Game game, World world, GameData gameData, GameInputProcessor gip) {
        this.game = game;
        this.world = world;
        this.gameData = gameData;
        this.gip = gip;
        am = new AssetManager();

        //where we wanna go (example):   C:\\Users\jonas\OneDrive\Dokumenter\MemoryLeak\Core\target
        String path = new File("").getAbsolutePath(); //C:\Users\jonas\OneDrive\Dokumenter\MemoryLeak\application\target\memoryleak
        assetPath = getAssetPath(path);

        //The right paths
        mainmenuPath = assetPath + "/background/mainmenu.png";
        skinPath = assetPath + "/skin/memoryleakTextButton.json";
        lavaPath = assetPath + "/sprites/lava.pack";
        memoryleakGraphicPath = assetPath + "/sprites/memoryleak.pack";
        fontPath = assetPath + "/skin/MemoryLeakFont.fnt";

        //Load to assetManager
        am.load(mainmenuPath, Texture.class);
        am.load(skinPath, Skin.class);
        am.load(lavaPath, TextureAtlas.class);
        am.load(memoryleakGraphicPath, TextureAtlas.class);
        am.load(fontPath, BitmapFont.class);
        am.finishLoading();

        //instanciate the image, button, lava, memoryLeakPack and font.
        menuBackground = new Image(am.get(mainmenuPath, Texture.class));
        buttonSkin = am.get(skinPath, Skin.class);
        lava = am.get(lavaPath, TextureAtlas.class);
        memoryLeakPack = am.get(memoryleakGraphicPath, TextureAtlas.class);
        font = am.get(fontPath, BitmapFont.class);
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
    
    /**
     * finds all the iGamePluginServices and starts them.
     */
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

    public Image getMenuBackground() {
        return menuBackground;
    }

    public TextureAtlas getLava() {
        return lava;
    }

    public TextureAtlas getMemoryLeakPack() {
        return memoryLeakPack;
    }

    public Skin getButtonSkin() {
        return buttonSkin;
    }

    public BitmapFont getFont() {
        return font;
    }
    
    /**
     * @param path the autogenerated path from the "new File("").getAbsolutePath()"
     * @return the path to core's asset
     */
    public String getAssetPath(String path) {
        int stopHere = 0;
        boolean oneMore = false;
        System.out.println(path);
        String[] section = path.split("\\\\");
        for (int i = section.length -1; i >= 0; i--) {
            System.out.println(section[i]);
            if (section[i].equalsIgnoreCase("MemoryLeak")) {
                if (oneMore) {
                    stopHere = i;
                    break;
                }
                oneMore = true;
            }
        }
        String truePath = "";
        for (int i = 0; i <= stopHere; i++) {
            truePath += section[i] + "/";
        }
        truePath += "Core/src/main/resources/assets";

        return truePath;
    }

}
