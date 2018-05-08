package movableentityparts;

import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;

/**
 *
 * @author Jorge Báez Garrido
 */
public interface EntityPart {

    /**
     * @param gameData Data of the game.
     * @param entity The MovableEntity to process.
     */
    public void process(GameData gameData, MovableEntity entity);

}
