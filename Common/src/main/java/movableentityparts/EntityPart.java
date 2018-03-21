

package movableentityparts;

import data.GameData;
import data.MovableEntity;

/**
 *
 * @author Jorge Báez Garrido
 */
public interface EntityPart {
	
	public void process(GameData gameData, MovableEntity movableEntity);

}
