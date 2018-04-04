package com.group9.enemy;

import data.Entity;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

/**
 *
 * @author Jorge Báez Garrido
 */
@ServiceProvider(service = iEntityProcessingService.class)

public class EnemyAI implements iEntityProcessingService

{
	private float playerX;
	private float playerY;
	private float enemyX;
	private float enemyY;

	@Override
	public void process(GameData gameData, World world)
	{
		//playerX = gameData.getDisplayWidth() / 2;
		for(MovableEntity enemy : world.getMovableEntities())
		{
			//move(gameData, enemy, world);
			Position position = enemy.getPart(Position.class);
			if(enemy.getType().equalsIgnoreCase("player"))
			{
				playerX = position.getX();
				playerY = position.getY();
			}
			if(enemy.getType().equalsIgnoreCase("enemy"))
			{
				Move move = enemy.getPart(Move.class);
				move.process(gameData, enemy);
				HealthPart health = enemy.getPart(HealthPart.class);
				health.process(gameData, enemy);
				updateSprite(enemy);
				enemyX = position.getX();
				enemyY = position.getY();
				
				if(enemyX < playerX)
				{
					move.setLeft(false);
					move.setRight(true);

				}
				else if(enemyX > playerX)
				{
					move.setRight(false);
					move.setLeft(true);
				}
				else{
					move.setLeft(false);
					move.setRight(false);
				}
				
				if(enemyY < playerY){
					move.setUp(true);
					move.setDown(false);
				}
				else if(enemyY > playerY){
					move.setDown(true);
					move.setUp(false);
				}
				else{
					move.setDown(false);
					move.setUp(false);
				}
				

			}
			position.process(gameData, enemy);
		}

	}

	private void updateSprite(MovableEntity movableEntity)
	{

		int numPoints = 12;
		float[] shapeX = new float[numPoints];
		float[] shapeY = new float[numPoints];

		Position position = movableEntity.getPart(Position.class);
		float radians = position.getRadians();
		float radius = movableEntity.getRadius();
		float x = position.getX();
		float y = position.getY();

		float angle = 0;

		for(int i = 0; i < numPoints; i++)
		{
			shapeX[i] = x + (float) Math.cos(angle + radians) * radius;
			shapeY[i] = y + (float) Math.sin(angle + radians) * radius;
			angle += 2 * 3.1415f / numPoints;
		}
		movableEntity.setShapeX(shapeX);
		movableEntity.setShapeY(shapeY);
	}
}
