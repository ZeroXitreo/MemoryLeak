/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package group9.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;
import group9.core.MemoryLeak;
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
public class PlayScreen implements Screen {

    private GameData gamedata;
    private Stage stage;
    private Game game;
    private List<iGamePluginServices> gamePlugin = new ArrayList<>();
    private Lookup lookup = Lookup.getDefault();
    private Lookup.Result<iGamePluginServices> result;
    private World world;
    private GameInputProcessor gip;
    private ShapeRenderer sr;
    public static final int GAME_RUNNING = 1;
    public static final int GAME_OVER = 0;
    private int state;
    long time;
    long time2;
    private SpriteBatch batch;
    private BitmapFont font;

    public PlayScreen(Game aGame, World aWorld, GameData aGameData, GameInputProcessor aGip) {
        stage = new Stage(new ScreenViewport());
        game = aGame;
        world = aWorld;
        gamedata = aGameData;
        sr = new ShapeRenderer();
        gip = aGip;
        result = lookup.lookupResult(iGamePluginServices.class);
        result.addLookupListener(lookupListener);
        result.allItems();
        for (iGamePluginServices plugin : result.allInstances()) {
            plugin.start(gamedata, world);
            gamePlugin.add(plugin);
        }
        state = 1;
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.setColor(Color.WHITE);
        time = java.lang.System.currentTimeMillis();
        font.setScale(2);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    private void draw() {
        for (MovableEntity movableEntity : world.getMovableEntities()) {
            sr.setColor(1, 1, 1, 1);
            sr.begin(ShapeRenderer.ShapeType.Line);
            float[] shapeX = movableEntity.getShapeX();
            float[] shapeY = movableEntity.getShapeY();
            for (int i = 0, j = shapeX.length - 1;
                    i < shapeX.length;
                    j = i++) {
                sr.line(shapeX[i], shapeY[i], shapeX[j], shapeY[j]);
            }
            sr.end();
        }

    }

    @Override
    public void render(float f) {
        if(world.getMovableEntities().contains(this)) {
            
        }
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Clear screen
        time2 = java.lang.System.currentTimeMillis();

//        if (time2 - time > 2500) {
//            state = 0;
//        }

        switch (state) {
            case GAME_RUNNING:
                stage.act();
                update();
                gip.keyPress();
                draw();
                break;
            case GAME_OVER:
                
                draw();
                Gdx.gl.glEnable(GL20.GL_BLEND);
                Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
                sr.begin(ShapeRenderer.ShapeType.Filled);
                sr.setColor(new Color(0.5f, 0, 0, 0.7f));
                sr.rect(0, 0, gamedata.getDisplayWidth(), gamedata.getDisplayHeight());
                sr.end();
                Gdx.gl.glDisable(GL20.GL_BLEND);
                batch.begin();
                font.draw(batch, "U DED", gamedata.getDisplayWidth()/2, gamedata.getDisplayHeight()/2);
                batch.end();
                break;
        }

        //stage.draw();
        //stage.clear();
    }

    @Override
    public void resize(int i, int i1) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        stage.dispose();
    }

    private void update() {
        for (iEntityProcessingService entityProcessor : getEntityProcessingServices()) {
            entityProcessor.process(gamedata, world);
        }
        for (iPostEntityProcessingService postEntityProcessor : getPostEntityProcessingService()) {
            postEntityProcessor.process(gamedata, world);
        }
    }

    private Collection<? extends iGamePluginServices> getPluginServices() {
        return lookup.lookupAll(iGamePluginServices.class);
    }

    private Collection<? extends iEntityProcessingService> getEntityProcessingServices() {
        return lookup.lookupAll(iEntityProcessingService.class);
    }

    private Collection<? extends iPostEntityProcessingService> getPostEntityProcessingService() {
        return lookup.lookupAll(iPostEntityProcessingService.class);
    }

    private final LookupListener lookupListener = new LookupListener() {

        @Override
        public void resultChanged(LookupEvent le) {
            Collection<? extends iGamePluginServices> updated = result.allInstances();

            for (iGamePluginServices gs : updated) {
                if (!gamePlugin.contains(gs)) {
                    gs.start(gamedata, world);
                    gamePlugin.remove(gs);
                }
            }
        }
    };

}
