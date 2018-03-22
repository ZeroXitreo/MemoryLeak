

package data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import movableentityparts.EntityPart;

/**
 *
 * @author Jorge Báez Garrido
 */
public abstract class Entity implements Serializable {
	private final UUID ID = UUID.randomUUID();
	private Map<Class, EntityPart> parts;
	
	public Entity(){
		parts = new ConcurrentHashMap<>();
	}
	
	public void add(EntityPart entityPart){
		parts.put(entityPart.getClass(), entityPart);
	}
	
	public void remove(Class partClass){
		parts.remove(partClass);
	}
	
	public String getID(){
		return ID.toString();
	}
	
	public <E extends EntityPart> E getPart(Class partClass){
		return (E) parts.get(partClass);
	}
}
