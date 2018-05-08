package data;

import java.io.Serializable;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import movableentityparts.EntityPart;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author Jorge Báez Garrido
 */
public abstract class Entity implements Serializable {

    private final UUID ID = UUID.randomUUID();
    private Map<Class, EntityPart> parts;
    private boolean isHit = false;
    private float[] shapeX = new float[4];
    private float[] shapeY = new float[4];
    private float radius;
    protected Type type;
    protected Name name;

    public Entity() {
        parts = new ConcurrentHashMap<>();
    }

    /**
     * Adds an EntityPart to the Entity.
     * @param entityPart The entitypart to be added.
     */
    public void add(EntityPart entityPart) {
        parts.put(entityPart.getClass(), entityPart);
    }

    /**
     * Removes an entitypart
     * @param partClass the entityparts class to be removed.
     */
    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    /**
     * Gets the ID of the entity
     * @return String id
     */
    public String getID() {
        return ID.toString();
    }

    /**
     * Gets the part of the entity
     * @param <E> The generic type.
     * @param partClass The class of the entitypart the entity needs to return
     * @return The entitypart of the specified class.
     */
    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    /**
     * Gets the radius of the entity.
     * @return float radius.
     */
    public float getRadius() {
        return radius;
    }

    /**
     * Sets the radius of the entity.
     * @param radius float radius of the entity.
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * Gets an array of floats used to draw the entity
     * @return float array of x-values
     */
    public float[] getShapeX() {
        return shapeX;
    }

    /**
     * Sets an array of floats used to draw the entity.
     * @param shapeX float array of x-values.
     */
    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    /**
     * Gets an array of floats used to draw the entity.
     * @return float array of y-values.
     */
    public float[] getShapeY() {
        return shapeY;
    }

    /**
     * Sets an array of floats used to draw the entity.
     * @param shapeY float array of y-values.
     */
    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    /**
     * Gets the Type instance of the entity.
     * used to know how to handle the entity.
     * @return Type used to indicate which entity type it is.
     */
    public Type getType() {
        return type;
    }
    
    /**
     * Returns the Name instance of the entity (used to know what sprite to pick)
     * @return Name used to indicate what the name of the entity is.
     */
    public Name getNameInstance(){
        return name;
    }

    /**
     * Sets the boolean hit to whatever value it receives.
     * True = Entity is hit.
     * False = Entity isn't hit.
     * @param hit Boolean indicating if hit.
     */
    public void setHit(boolean hit) {
        isHit = hit;
    }

    /**
     * Gets the boolean if it is hit.
     * True = Entity is hit.
     * False = Entity isn't hit.
     * @return Boolean indicating if hit.
     */
    public boolean getHit() {
        return isHit;
    }
}
