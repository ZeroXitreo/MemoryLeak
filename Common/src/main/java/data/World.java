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

    /**
     * Adds an ImmovableEntity to the immovableEntityMap.
     * @param immovableEntity The ImmovableEntity to be added.
     */
    public void addImmovableEntity(ImmovableEntity immovableEntity) {
        immovableEntityMap.put(immovableEntity.getID(), immovableEntity);
    }

    /**
     * Adds a MovableEntity to the movableEntityMap.
     * @param movableEntity The MovableEntity to be added.
     */
    public void addMovableEntity(MovableEntity movableEntity) {
        movableEntityMap.put(movableEntity.getID(), movableEntity);
    }

    /**
     * Adds a class with the implemented interface iWeapon and puts it in the
     * weaponMap.
     * @param weapon The Weapon to be added.
     */
    public void addWeapon(iWeapon weapon) {
        weaponMap.put(weapon.getWeaponName(), weapon);
    }

    /**
     * Adds a MovableEntity to the game.
     * @param movableEntity The Entity to be added to the game.
     */
    public void addGameMovableEntity(MovableEntity movableEntity) {
        gameMovableEntities.put(movableEntity.getID(), movableEntity);
    }

    /**
     * Adds an ImmovableEntity to the game.
     * @param immovableEntity The Entity to be added to the game.
     */
    public void addGameImmovableEntity(ImmovableEntity immovableEntity) {
        gameImmovableEntities.put(immovableEntity.getID(), immovableEntity);
    }

    /**
     * Removes the ImmovableEntity from the map.
     * @param immovableEntity The entity to be removed.
     */
    public void removeImmovableEntity(ImmovableEntity immovableEntity) {
        immovableEntityMap.remove(immovableEntity.getID());
    }

    /**
     * Removes the MovableEntity from the map.
     * @param movableEntity The entity to be removed.
     */
    public void removeMovableEntity(MovableEntity movableEntity) {
        movableEntityMap.remove(movableEntity.getID());
    }

    /**
     * Removes the iWeapon from the map.
     * @param weapon The weapon to be removed.
     */
    public void removeWeapon(iWeapon weapon) {
        weaponMap.remove(weapon.getWeaponName());
    }
    
    /**
     * Removes the MovableEntity from the game.
     * @param movableEntity The entity to be removed from the game.
     */
    public void removeGameMovableEntity(MovableEntity movableEntity) {
        gameMovableEntities.remove(movableEntity.getID());
    }

    /**
     * Removes the ImmovableEntity from the game.
     * @param immovableEntity The entity to be removed from the game.
     */
    public void removeGameImmovableEntity(ImmovableEntity immovableEntity) {
        gameImmovableEntities.remove(immovableEntity.getID());
    }

    /**
     * Get a collection of the immovableEntities.
     * @return Collection of immovableEntities.
     */
    public Collection<ImmovableEntity> getImmovableEntities() {
        return immovableEntityMap.values();
    }

    /**
     * Get a collection of the movableEntities.
     * @return Collection of movableEntities.
     */
    public Collection<MovableEntity> getMovableEntities() {
        return movableEntityMap.values();
    }

    /**
     * Get a collection of the weapons.
     * @return Collection of weapons.
     */
    public Collection<iWeapon> getWeapons() {
        return weaponMap.values();
    }

    /**
     * Get a collection of the movableEntities in the game.
     * @return Collection of game movableEntities.
     */
    public Collection<MovableEntity> getGameMovableEntities() {
        return gameMovableEntities.values();
    }

    /**
     * Get a collection of the immovableEntities in the game.
     * @return Collection of game ImmovableEntities.
     */
    public Collection<ImmovableEntity> getGameImmovableEntities() {
        return gameImmovableEntities.values();
    }

    /**
     * Get all the immovableEntities of a specific type.
     * @param <E> The generic type.
     * @param immovableEntityTypes Class that extends immovableEntity.
     * @return The ImmovableEntities of the type.
     */
    public <E extends ImmovableEntity> List<ImmovableEntity> getImmovableEntities(Class<E>... immovableEntityTypes) {
        List<ImmovableEntity> r = new ArrayList<>();
        for (ImmovableEntity e : World.this.getImmovableEntities()) {
            for (Class<E> entityType : immovableEntityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }

    /**
     * Get all the movableEntities of a specific type.
     * @param <E> The generic type.
     * @param movableEntityTypes Class that extends movableEntity.
     * @return The MovableEntities of the given type.
     */
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
    
    /**
     * Get all the movableEntities of the game of a specific type.
     * @param <E> The generic type.
     * @param movableEntityTypes Class that extends movableEntity.
     * @return The MovableEntities of the given type from game entities.
     */
    public <E extends MovableEntity> List<MovableEntity> getGameMovableEntities(Class<E>... movableEntityTypes) {
        List<MovableEntity> r = new ArrayList<>();
        for (MovableEntity e : World.this.getGameMovableEntities()) {
            for (Class<E> entityType : movableEntityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
    
    /**
     * Get all the immovableEntities of the game of a specific type.
     * @param <E> The generic type.
     * @param immovableEntityTypes Class that extends immovableEntity.
     * @return The MovableEntities of the given type from game entities.
     */
    public <E extends ImmovableEntity> List<ImmovableEntity> getGameImmovableEntities(Class<E>... immovableEntityTypes) {
        List<ImmovableEntity> r = new ArrayList<>();
        for (ImmovableEntity e : World.this.getGameImmovableEntities()) {
            for (Class<E> entityType : immovableEntityTypes) {
                if (entityType.equals(e.getClass())) {
                    r.add(e);
                }
            }
        }
        return r;
    }
}
