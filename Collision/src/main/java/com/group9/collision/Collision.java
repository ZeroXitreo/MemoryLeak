/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.collision;

import data.Entity;
import data.GameData;
import data.ImmovableEntity;
import data.MovableEntity;
import data.World;
import java.util.Collection;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iPostEntityProcessingService;

/**
 *
 * @author ZeroXitreo
 */
@ServiceProvider(service = iPostEntityProcessingService.class)
public class Collision implements iPostEntityProcessingService {

    public Collision() {
    }

    // public void Collision()
    @Override
    public void process(GameData gameData, World world) {
        Collection<MovableEntity> movEntities = world.getGameMovableEntities();
        for (MovableEntity movEntity : movEntities) {
            checkSingleCollision(movEntity, world);
        }
    }

    public void checkSingleCollision(MovableEntity movEntity, World world) {
        Collection<MovableEntity> movableEntities = world.getGameMovableEntities();
        Collection<ImmovableEntity> immovableEntities = world.getGameImmovableEntities();
        for (MovableEntity entity : movableEntities) {
            if (movEntity.equals(entity)) {
                continue;
            }

            Position movEntityPos = movEntity.getPart(Position.class);
            Position entityPos = movEntity.getPart(Position.class);

            /*if (!movableEl['isCircle'] && !solidEl['isCircle']) // is both squares?
				{
					var averageWidth = getAverage(movableEl['width'], solidEl['width']);
					var averageHeight = getAverage(movableEl['height'], solidEl['height']);

					SquareCollisionPush(movableEl, solidEl, averageWidth, averageHeight);
				}/**/
            if (true) //if (movableEl['isCircle'] && solidEl['isCircle']) // is both circles?
            {
                float totalRadius = movEntity.getRadius() + entity.getRadius();
                //  System.out.println(totalRadius);
                circleCollisionPush(movEntity, entity, totalRadius);
            }

            /*if (movableEl['isCircle'] && !solidEl['isCircle'])
				{
					var absDisX = Math.abs(movableEl['x'] - solidEl['x']);
					var absDisY = Math.abs(movableEl['y'] - solidEl['y']);

					if (absDisX <= solidEl['width']/2 || absDisY <= solidEl['height']/2)
					{
						var averageWidth = getAverage(movableEl['r']*2, solidEl['width']);
						var averageHeight = getAverage(movableEl['r']*2, solidEl['height']);

						SquareCollisionPush(movableEl, solidEl, averageWidth, averageHeight);
					}
					else
					{
						var solidOffsetX = (movableEl['x'] > solidEl['x'] ? solidEl['width'] : -solidEl['width'])/2;
						var solidOffsetY = (movableEl['y'] > solidEl['y'] ? solidEl['height'] : -solidEl['height'])/2;

						CircleCollisionPush(movableEl, solidEl, movableEl['r'], solidOffsetX, solidOffsetY);
					}
				}

				if (!movableEl['isCircle'] && solidEl['isCircle'])
				{
					float absDisX = Math.abs(movableEl['x'] - solidEl['x']);
					float absDisY = Math.abs(movableEl['y'] - solidEl['y']);

					if (absDisX <= movableEl['width']/2 || absDisY <= movableEl['height']/2)
					{
						var averageWidth = getAverage(solidEl['r']*2, movableEl['width']);
						var averageHeight = getAverage(solidEl['r']*2, movableEl['height']);

						SquareCollisionPush(movableEl, solidEl, averageWidth, averageHeight);
					}
					else
					{
						var movableOffsetX = (solidEl['x'] > movableEl['x'] ? movableEl['width'] : -movableEl['width'])/2;
						var movableOffsetY = (solidEl['y'] > movableEl['y'] ? movableEl['height'] : -movableEl['height'])/2;

						CircleCollisionPush(movableEl, solidEl, solidEl['r'], movableOffsetX, movableOffsetY, true);
					}
				}/**/
        }
        for(ImmovableEntity immovableEntity : immovableEntities){
            if (true) //if (movableEl['isCircle'] && solidEl['isCircle']) // is both circles?
            {
                float totalRadius = movEntity.getRadius() + immovableEntity.getRadius();
                circleCollisionPush(movEntity, immovableEntity, totalRadius);
            }
        }
    }

    private float getAverage(float x, float y) // Heh, why did I make this into a function?
    {
        return (x + y) / 2;
    }

    private void squareCollisionPush(Entity movEntity, Entity entity, float averageWidth, float averageHeight) {
        Position movEntityPos = movEntity.getPart(Position.class);
        Position entityPos = movEntity.getPart(Position.class);

        float disX = movEntityPos.getX() - entityPos.getX();
        float disY = movEntityPos.getY() - entityPos.getY();

        float absDisX = Math.abs(disX);
        float absDisY = Math.abs(disY);

        if (absDisX < averageWidth && absDisY < averageHeight) // does it collide?
        {
            float collisionX = averageWidth - absDisX;
            float collisionY = averageHeight - absDisY;

            if (collisionX < collisionY) // Is collisionY bigger than collisionX?
            {
                movEntityPos.setX(movEntityPos.getX() + (disX == absDisX ? averageWidth : -averageWidth) - disX);
            } else {
                movEntityPos.setY(movEntityPos.getY() + (disY == absDisY ? averageHeight : -averageHeight) - disY);
            }
        }
    }

    private void circleCollisionPush(MovableEntity pushedElement, Entity solidElement, float totalRadius) {
        circleCollisionPush(pushedElement, solidElement, totalRadius, 0, 0);
    }

    private void circleCollisionPush(MovableEntity pushedElement, Entity solidElement, float totalRadius, float solidOffsetX, float solidOffsetY) {
        circleCollisionPush(pushedElement, solidElement, totalRadius, solidOffsetX, solidOffsetY, true);
    }

    private void circleCollisionPush(MovableEntity movEntity, Entity entity, float totalRadius, float solidOffsetX, float solidOffsetY, boolean reverseOffset) {
        Position movEntityPos = movEntity.getPart(Position.class);
        Position entityPos = entity.getPart(Position.class);

        if (movEntityPos.getX() == entityPos.getX() && movEntityPos.getY() == entityPos.getY()) {
            return;
        }

        float movablePosX = movEntityPos.getX();
        float movablePosY = movEntityPos.getY();
        float solidPosX = entityPos.getX();
        float solidPosY = entityPos.getY();

        if (!reverseOffset) {
            solidPosX += solidOffsetX;
            solidPosY += solidOffsetY;
        } else {
            movablePosX += solidOffsetX;
            movablePosY += solidOffsetY;
        }

        float disX = movablePosX - solidPosX;
        float disY = movablePosY - solidPosY;

        float disPoints = (float) Math.sqrt(Math.pow(disX, 2) + Math.pow(disY, 2));
        if (totalRadius > disPoints) {
            collisionDMG(movEntity, entity);
            float angle_B = (float) Math.atan(disY / disX);
//            if(angle_B <=0){
//                angle_B = 1f;
//            }
            float pushPos_b = (float) (Math.sin(angle_B) * (totalRadius - disPoints));
            float pushPos_a = (float) (Math.cos(angle_B) * (totalRadius - disPoints));

            movEntityPos.setX(movEntityPos.getX() + (movablePosX < solidPosX ? -pushPos_a : pushPos_a));
            movEntityPos.setY(movEntityPos.getY() + (movablePosX < solidPosX ? -pushPos_b : pushPos_b));
        }
    }

    /**
     * Checks if the entities needs to take damage.
     * @param entity1 MovableEntity colliding with entity2.
     * @param entity2 Entity colliding with eneity1.
     */
    private void collisionDMG(MovableEntity entity1, Entity entity2) {
        //if one of the entities is an enemy and the other one isn't an enemy
        //or an enemyBullet.
        if (entity1.getType().equalsEnemy()
                && !entity2.getType().equalsEnemy()
                && !entity2.getType().equalsEnemyBullet()) {
            entity2.setHit(true);
        } else if (entity2.getType().equalsEnemy()
                && !entity1.getType().equalsEnemy()
                && !entity1.getType().equalsEnemyBullet()) {
            entity1.setHit(true);
        }
        //if one of the entities is a friendlyBullet and the other one
        //isn't a player or a bullet.
        if (entity1.getType().equalsFriendlyBullet()
                && !entity2.getType().equalsFriendlyBullet()
                && !entity2.getType().equalsPlayer()
                && !entity2.getType().equalsEnemyBullet()) {
            entity2.setHit(true);
        } else if (entity2.getType().equalsFriendlyBullet()
                && !entity1.getType().equalsFriendlyBullet()
                && !entity1.getType().equalsPlayer()
                && !entity1.getType().equalsEnemyBullet()) {
            entity1.setHit(true);
        }
        //if one of the entities is an enemyBullet and the other one is player.
        //if one of the entities is a bullet and the other one
        //isn't an enemy or a bullet.
        if (entity1.getType().equalsEnemyBullet()
                && entity2.getType().equalsPlayer()) {
            entity1.setHit(true);
            entity2.setHit(true);
        } else if (entity2.getType().equalsEnemyBullet()
                && entity1.getType().equalsPlayer()){
            entity1.setHit(true);
            entity2.setHit(true);
        } else if (entity1.getType().equalsEnemyBullet()
                && !entity2.getType().equalsEnemyBullet()
                && !entity2.getType().equalsEnemy()
                && !entity2.getType().equalsFriendlyBullet()) {
            entity2.setHit(true);
        } else if (entity2.getType().equalsEnemyBullet()
                && !entity1.getType().equalsEnemyBullet()
                && !entity1.getType().equalsEnemy()
                && !entity1.getType().equalsFriendlyBullet()) {
            entity1.setHit(true);
        }
    }
}
