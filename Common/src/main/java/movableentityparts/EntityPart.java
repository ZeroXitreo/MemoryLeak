

package movableentityparts;

import data.Entity;
import data.GameData;

/**
 *
 * @author Jorge Báez Garrido
 */
public interface EntityPart {
	
	public void process(GameData gameData, Entity entity);

}
