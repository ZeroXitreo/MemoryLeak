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

    private final Map<String, Entity> entityMap = new ConcurrentHashMap<>();
    private final Map<String, MovableEntity> mEntityMap = new ConcurrentHashMap<>();
    private final Map<String, iWeapon> weaponMap = new ConcurrentHashMap<>();
    private final Map<String, MovableEntity> enemyMap = new ConcurrentHashMap<>();


    public String addEntity(Entity entity) {
        entityMap.put(entity.getID(), entity);
        return entity.getID();
    }

    public String addMovableEntity(MovableEntity entity) {
        mEntityMap.put(entity.getID(), entity);
        return entity.getID();
    }
    
    public void addWeapon(iWeapon weapon) {
        weaponMap.put(weapon.getWeaponName(), weapon);
    }

    public void addEnemyEntity(MovableEntity entity){
        addMovableEntity(entity);
        enemyMap.put(entity.getID(), entity);
    }
    
    public void removeEntity(String entityID) {
        entityMap.remove(entityID);
    }

    public void removeMovableEntity(String entityID) {
        mEntityMap.remove(entityID);
    }

    public void removeWeapon(String entityID) {
        weaponMap.remove(entityID);
    }
    
    public void removeEnemyEntity(String entityID){
        enemyMap.remove(entityID);
    }

    public void removeEntity(Entity entity) {
        entityMap.remove(entity.getID());
    }

    public void removeMovableEntity(MovableEntity movableEntity) {
        mEntityMap.remove(movableEntity.getID());
    }
    
    public void removeWeapon(iWeapon weapon){
        weaponMap.remove(weapon);
    }

    public Collection<Entity> getEntities() {
        return entityMap.values();
    }

    public Collection<MovableEntity> getMovableEntities() {
        return mEntityMap.values();
    }

    public Collection<iWeapon> getWeapons() {
        return weaponMap.values();
    }
    
    public Collection<MovableEntity> getEnemyEntities(){
        return enemyMap.values();
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

    public Entity getEntity(String ID) {
        return entityMap.get(ID);
    }

    public MovableEntity getMovableEntity(String ID) {
        return mEntityMap.get(ID);
    }

}
