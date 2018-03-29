

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
	
	public void process(GameData gameData, MovableEntity entity);

}
