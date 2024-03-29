package group9.core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.utils.GdxRuntimeException;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import group9.screens.MenuScreen;
import group9.screens.ParentScreen;

/**
 *
 * @author Jorge Báez Garrido
 */
public class MemoryLeak extends Game {

    private GameData gameData = new GameData();
    private World world = new World();
    private GameInputProcessor gip;

    @Override
    public void create() {
        gip = new GameInputProcessor(gameData);
        gameData.setDisplayHeight(540);
        gameData.setDisplayWidth(960);
        gameData.setMoveAreaWidthMax(gameData.getDisplayWidth() - 85);
        gameData.setMoveAreaWidthMin(68);
        gameData.setMoveAreaHeightMin(64);
        gameData.setMoveAreaHeightMax(gameData.getDisplayHeight() - 45);
        try {
            ParentScreen.getInstance(this, world, gameData, gip);
            this.setScreen(new MenuScreen());
        } catch (GdxRuntimeException e) {
            System.out.println(e);
        }

    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void render() {
        super.render();

    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
