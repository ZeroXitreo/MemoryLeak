/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.collision;

import data.Entity;
import data.GameData;
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
        Collection<MovableEntity> movEntities = world.getMovableEntities();
        for (MovableEntity movEntity : movEntities) {
            CheckSingleCollision(movEntity, world);
        }
    }

    public void CheckSingleCollision(MovableEntity movEntity, World world) {
        Collection<MovableEntity> entities = world.getMovableEntities();
        for (MovableEntity entity : entities) {
            if (movEntity.equals(entity)) {
                //System.out.println("no");
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
                CircleCollisionPush(movEntity, entity, totalRadius);
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
    }

    private float getAverage(float x, float y) // Heh, why did I make this into a function?
    {
        return (x + y) / 2;
    }

    private void SquareCollisionPush(Entity movEntity, Entity entity, float averageWidth, float averageHeight) {
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

    private void CircleCollisionPush(MovableEntity pushedElement, Entity solidElement, float totalRadius) {
        CircleCollisionPush(pushedElement, solidElement, totalRadius, 0, 0);
    }

    private void CircleCollisionPush(MovableEntity pushedElement, Entity solidElement, float totalRadius, float solidOffsetX, float solidOffsetY) {
        CircleCollisionPush(pushedElement, solidElement, totalRadius, solidOffsetX, solidOffsetY, true);
    }

    private void CircleCollisionPush(MovableEntity movEntity, Entity entity, float totalRadius, float solidOffsetX, float solidOffsetY, boolean reverseOffset) {
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
            if (movEntity.getType().equalsIgnoreCase("Enemy")) { //change to bullet when made
                entity.setHit(true);
                movEntity.setHit(true);
            }
            else if (entity.getType().equalsIgnoreCase("Enemy")) { //change to bullet when made
                entity.setHit(true);
                movEntity.setHit(true);
            }
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
}
