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

    public void add(EntityPart entityPart) {
        parts.put(entityPart.getClass(), entityPart);
    }

    public void remove(Class partClass) {
        parts.remove(partClass);
    }

    public String getID() {
        return ID.toString();
    }

    public <E extends EntityPart> E getPart(Class partClass) {
        return (E) parts.get(partClass);
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public float[] getShapeX() {
        return shapeX;
    }

    public void setShapeX(float[] shapeX) {
        this.shapeX = shapeX;
    }

    public float[] getShapeY() {
        return shapeY;
    }

    public void setShapeY(float[] shapeY) {
        this.shapeY = shapeY;
    }

    public Type getType() {
        return type;
    }
    
    public Name getNameInstance(){
        return name;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }

    public boolean getHit() {
        return isHit;
    }
    
}
