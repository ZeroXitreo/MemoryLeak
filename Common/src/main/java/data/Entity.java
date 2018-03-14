

package data;

import java.io.Serializable;
import java.util.UUID;

/**
 *
 * @author Jorge Báez Garrido
 */
public class Entity implements Serializable {
	private final UUID ID = UUID.randomUUID();
	
	public Entity(){
		
	}
	
	public String getID(){
		return ID.toString();
	}
}
