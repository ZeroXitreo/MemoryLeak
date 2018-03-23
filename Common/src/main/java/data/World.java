

package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Jorge Báez Garrido
 */


public class World {
	private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
	private final Map<String, MovableEntity> mEntityMap = new ConcurrentHashMap<>();
	public String addEntity(Entity entity){
		entityMap.put(entity.getID(), entity);
		return entity.getID();
	}
	public String addMovableEntity(MovableEntity entity){
		mEntityMap.put(entity.getID(), entity);
		return entity.getID();
	}
	
	public void removeEntity(String entityID){
		entityMap.remove(entityID);
	}
	
	/**
	 *
	 * @param entityID
	 */
	public void removeMovableEntity(String entityID){
		mEntityMap.remove(entityID);
	}
	
	public void removeEntity(Entity entity){
		entityMap.remove(entity.getID());
	}
	
	public void removeMovableEntity(MovableEntity movableEntity){
		mEntityMap.remove(movableEntity.getID());
	}
	
	public Collection<Entity> getEntities(){
		return entityMap.values();
	}
	
	public Collection<MovableEntity> getMovableEntities(){
		return mEntityMap.values();
	}
	 
	public <E extends Entity> List<Entity> getEntities(Class<E>... entityTypes) {
            List<Entity> r = new ArrayList<>();
            for (Entity e : getEntities()) {
                for (Class<E> entityType : entityTypes) {
                    if (entityType.equals(e.getClass())) {
                        r.add(e);
                    }
                }
            }
            return r;
    }
	
	public <E extends MovableEntity> List<MovableEntity> getMovableEntities(Class<E>... movableEntityTypes){
		List<MovableEntity> list = new ArrayList<>();
		for(MovableEntity movableEntity : getMovableEntities()){
			for(Class<E> movableEntityType: movableEntityTypes){
				if (movableEntityType.equals(movableEntity.getClass())){
					list.add(movableEntity);
				}
			}
		}
		return list;
	}
	
	public Entity getEntity(String ID){
		return entityMap.get(ID);
	}



}
