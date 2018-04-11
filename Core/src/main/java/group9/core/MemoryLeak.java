package group9.core;

import com.badlogic.gdx.Game;
import data.GameData;
import data.World;
import group9.manager.GameInputProcessor;
import group9.screens.MenuScreen;
import group9.screens.ParentScreen;
import group9.screens.PlayScreen;

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
        new ParentScreen(this, world, gameData, gip);
        this.setScreen(new MenuScreen());
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
