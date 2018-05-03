package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import movableentityparts.iWeapon;

/**
 *
 * @author Jorge Báez Garrido
 */
public class World {

    private final Map<String, ImmovableEntity> immovableEntityMap = new ConcurrentHashMap<>();
    private final Map<String, MovableEntity> movableEntityMap = new ConcurrentHashMap<>();
    private final Map<String, iWeapon> weaponMap = new ConcurrentHashMap<>();
    private final Map<String, MovableEntity> gameMovableEntities = new ConcurrentHashMap<>();
    private final Map<String, ImmovableEntity> gameImmovableEntities = new ConcurrentHashMap<>();

    //Add entities to the different maps
    public void addImmovableEntity(ImmovableEntity entity) {
        immovableEntityMap.put(entity.getID(), entity);
    }

    public void addMovableEntity(MovableEntity entity) {
        movableEntityMap.put(entity.getID(), entity);
    }

    public void addWeapon(iWeapon weapon) {
        weaponMap.put(weapon.getWeaponName(), weapon);
    }

    public void addGameMovableEntity(MovableEntity entity) {
        gameMovableEntities.put(entity.getID(), entity);
    }

    public void addGameImmovableEntity(ImmovableEntity entity) {
        gameImmovableEntities.put(entity.getID(), entity);
    }

//    public void removeImmovableEntity(String entityID) {
//        entityMap.remove(entityID);
//    }
//
//    public void removeMovableEntity(String entityID) {
//        mEntityMap.remove(entityID);
//    }
//    public void removeGameMovableEntity(String entityID){
//        gameEntities.remove(entityID);
//    }
    //Remove the Entites from their map
    public void removeImmovableEntity(ImmovableEntity entity) {
        immovableEntityMap.remove(entity.getID());
    }

    public void removeMovableEntity(MovableEntity movableEntity) {
        movableEntityMap.remove(movableEntity.getID());
    }

    public void removeWeapon(MovableEntity entity) {
        weaponMap.remove(entity.getID());
    }

    public void removeGameMovableEntity(MovableEntity entity) {
        gameMovableEntities.remove(entity.getID());
    }

    public void removeGameImmovableEntity(ImmovableEntity entity) {
        gameImmovableEntities.remove(entity.getID());
    }

    //Get all the entities from their map
    public Collection<ImmovableEntity> getImmovableEntities() {
        return immovableEntityMap.values();
    }

    public Collection<MovableEntity> getMovableEntities() {
        return movableEntityMap.values();
    }

    public Collection<iWeapon> getWeaponEntities() {
        return weaponMap.values();
    }

    public Collection<MovableEntity> getGameMovableEntities() {
        return gameMovableEntities.values();
    }

    public Collection<ImmovableEntity> getGameImmovableEntities() {
        return gameImmovableEntities.values();
    }

    //Get all the entities of a specific class from their map
    public <E extends ImmovableEntity> List<ImmovableEntity> getImmovableEntities(Class<E>... entityTypes) {
        List<ImmovableEntity> r = new ArrayList<>();
        for (ImmovableEntity e : World.this.getImmovableEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    public <E extends MovableEntity> List<MovableEntity> getMovableEntities(Class<E>... movableEntityTypes) {
        List<MovableEntity> list = new ArrayList<>();
        for (MovableEntity movableEntity : getMovableEntities()) {
            for (Class<E> movableEntityType : movableEntityTypes) {
                if (movableEntityType.equals(movableEntity.getClass())) {
                    list.add(movableEntity);
                }
            }
        }
        return list;
    }
    
    public <E extends MovableEntity> List<MovableEntity> getGameMovableEntities(Class<E>... entityTypes) {
        List<MovableEntity> r = new ArrayList<>();
        for (MovableEntity e : World.this.getGameMovableEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
    
    public <E extends ImmovableEntity> List<ImmovableEntity> getGameImmovableEntities(Class<E>... entityTypes) {
        List<ImmovableEntity> r = new ArrayList<>();
        for (ImmovableEntity e : World.this.getGameImmovableEntities()) {
            for (Class<E> entityType : entityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    //Get entity based on their ID
    public Entity getImmovableEntity(String ID) {
        return immovableEntityMap.get(ID);
    }

    public MovableEntity getMovableEntity(String ID) {
        return movableEntityMap.get(ID);
    }

    public ImmovableEntity getGameImmovableEntity(String ID) {
        return gameImmovableEntities.get(ID);
    }
}
