/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.List.ListStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.GdxRuntimeException;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
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
    private List<iGamePluginServices> gamePlugin = new CopyOnWriteArrayList<>();
    private AssetManager am;
    private Image menuBackground;
    private Skin buttonSkin;
    private Skin listSkin;
    private Skin scrollPaneSkin;
    private TextureAtlas listAtlas;
    private TextureAtlas lava;
    private TextureAtlas memoryLeakPack;
    private ListStyle listStyle;
    private BitmapFont font;
    private String scrollPaneSkinPath;
    private String atlasListSkinPath;
    private String skinListPath;
    private String assetPath;
    private String mainmenuPath;
    private String skinButtonPath;
    private String lavaPath;
    private String memoryleakGraphicPath;
    private String fontPath;
    private String soundPath;
    private String scrollAtlasPath;
    private Music music;
    private Boolean musicOnOff = false;

    private final LookupListener lookupListener = new LookupListener() {
        @Override
        public void resultChanged(LookupEvent le) {
            Collection<? extends iGamePluginServices> updated = getResult().allInstances();

            for (iGamePluginServices gs : updated) {
                if (!gamePlugin.contains(gs)) {
                    gs.start(getGameData(), getWorld());
                    getGamePlugin().add(gs);
                }
            }
            for (iGamePluginServices gs : gamePlugin) {
                if (!updated.contains(gs)) {
                    gs.stop(gameData, world);
                    gamePlugin.remove(gs);
                }
            }
        }
    };

    public ParentScreen(Game game, World world, GameData gameData, GameInputProcessor gip) throws GdxRuntimeException {
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
        skinButtonPath = assetPath + "/skin/memoryleakTextButton.json";
        skinListPath = assetPath + "/skin/listskin.json";
        lavaPath = assetPath + "/sprites/lava.pack";
        memoryleakGraphicPath = assetPath + "/sprites/memoryleak.pack";
        fontPath = assetPath + "/skin/MemoryLeakFont.fnt";
        atlasListSkinPath = assetPath + "/skin/listskin.atlas";
        soundPath = assetPath + "/sound/sound.mp3";
        scrollPaneSkinPath = assetPath + "/skin/srollpaneskin.json";
        scrollAtlasPath = assetPath + "/skin/srollpaneskin.atlas";

        //Load to assetManager
        am.load(mainmenuPath, Texture.class);
        am.load(skinButtonPath, Skin.class);
        am.load(skinListPath, Skin.class);
        am.load(lavaPath, TextureAtlas.class);
        am.load(memoryleakGraphicPath, TextureAtlas.class);
        am.load(fontPath, BitmapFont.class);
        am.load(atlasListSkinPath, TextureAtlas.class);
        am.load(soundPath, Music.class);
        am.load(scrollPaneSkinPath, Skin.class);
        am.load(scrollAtlasPath, TextureAtlas.class);
        am.finishLoading();

        //instanciate the image, button, lava, memoryLeakPack and font.
        menuBackground = new Image(am.get(mainmenuPath, Texture.class));
        buttonSkin = am.get(skinButtonPath, Skin.class);
        lava = am.get(lavaPath, TextureAtlas.class);
        memoryLeakPack = am.get(memoryleakGraphicPath, TextureAtlas.class);
        font = am.get(fontPath, BitmapFont.class);
        listAtlas = am.get(atlasListSkinPath, TextureAtlas.class);
        listSkin = am.get(skinListPath, Skin.class);
        listSkin.addRegions(listAtlas);
        music = am.get(soundPath, Music.class);
        music.setVolume(0.3f);
        music.setLooping(true);
        scrollPaneSkin = am.get(scrollPaneSkinPath, Skin.class);
        setResult();
    }

    /**
     * Singleton pattern, returns the instance.
     *
     * @return The ParentScreen singleton instance.
     */
    public static ParentScreen getInstance() {
        if (instance == null) {
            instance = new ParentScreen(getGame(), getWorld(), getGameData(), getGip());
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Singleton pattern, return the instance but used to create the first one.
     *
     * @param game The game, used to set the screens.
     * @param world The world, contains all of the entities.
     * @param gameData The game data, contains data of width, height, gamearea
     * and gamekeys.
     * @param gip The functionality for setting the gamekeys.
     * @return The ParentScreen singleton instance.
     */
    public static ParentScreen getInstance(Game game, World world, GameData gameData, GameInputProcessor gip) {
        if (instance == null) {
            instance = new ParentScreen(game, world, gameData, gip);
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * Gets a collection of the iGamePluginServices.
     *
     * @return the iGamePluginServices.
     */
    public Collection<? extends iGamePluginServices> getPluginServices() {
        return getLookup().lookupAll(iGamePluginServices.class);
    }

    /**
     * Gets a collection of the iEntityProcessingServices.
     *
     * @return the iEntityProcessingServices.
     */
    public Collection<? extends iEntityProcessingService> getEntityProcessingServices() {
        return getLookup().lookupAll(iEntityProcessingService.class);
    }

    /**
     * Gets a collection of the iPostEntityProcessingService
     *
     * @return The iPostEntityProcessingServices.
     */
    public Collection<? extends iPostEntityProcessingService> getPostEntityProcessingService() {
        return getLookup().lookupAll(iPostEntityProcessingService.class);
    }

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

    /**
     * Gets the MenuBackground
     *
     * @return Image used for the menu background.
     */
    public Image getMenuBackground() {
        return menuBackground;
    }

    /**
     * Gets the lava.
     *
     * @return TextureAtlas of the lava graphics.
     */
    public TextureAtlas getLava() {
        return lava;
    }

    /**
     * Gets the MemoryLeakPack.
     *
     * @return TextureAtlas of most of the memoryleak graphics.
     */
    public TextureAtlas getMemoryLeakPack() {
        return memoryLeakPack;
    }

    /**
     * Gets the button skin.
     *
     * @return Skin for Buttons.
     */
    public Skin getButtonSkin() {
        return buttonSkin;
    }

    /**
     * Gets the Font.
     *
     * @return BitmapFont used for text.
     */
    public BitmapFont getFont() {
        return font;
    }

    /**
     * Gets the ListSkin
     *
     * @return Skin for the List.
     */
    public Skin getListSkin() {
        return listSkin;
    }

    /**
     * Gets the ListAtlas.
     *
     * @return TextureArlas for the List.
     */
    public TextureAtlas getListAtlas() {
        return listAtlas;
    }

    /**
     * Gets the ListStyle.
     *
     * @return ListStyle.
     */
    public ListStyle getListStyle() {
        return listStyle;
    }

    /**
     * Gets the ScollPaneSkin.
     *
     * @return Skin - ScrollPane
     */
    public Skin getScrollPaneSkin() {
        return scrollPaneSkin;
    }

    /**
     * Return the path to the assets.
     *
     * @param path The path created from "new File("").getAbsolutePath();".
     * @return String path to the assets.
     */
    public String getAssetPath(String path) {
        int stopHere = 0;
        boolean oneMore = false;
        String[] section = path.split("\\\\");
        for (int i = section.length - 1; i >= 0; i--) {
            if (section[i].equals("MemoryLeak")) {
                stopHere = i;
            }
        }
        String truePath = "";
        for (int i = 0; i <= stopHere; i++) {
            truePath += section[i] + "/";
        }
        truePath += "Core/src/main/resources/assets";

        return truePath;
    }

    /**
     * Starts or Stops the music depending on if it is running.
     */
    public void startStopMusic() {
        if (!musicOnOff) {
            music.play();
            musicOnOff = true;
        } else {
            music.stop();
            musicOnOff = false;
        }
    }
}
