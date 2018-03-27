/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import data.GameData;
import static data.GameKeys.*;
import data.MovableEntity;
import data.World;
import movableentityparts.Move;
import services.iEntityProcessingService;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Christian
 */

    @ServiceProvider (service = iEntityProcessingService.class)
public class PlayerControlSystem implements iEntityProcessingService
{
		PlayerPlugin pp;
	@Override
	public void process(GameData gameData, World world)
	{
		String playerID;
		float x , y;
		for(MovableEntity player : world.getMovableEntities(Player.class)){
			playerID = world.addMovableEntity(player);
			Position position = player.getPart(Position.class);
			Move move = player.getPart(Move.class);
			
                        move.setUp(gameData.getKeys().isDown(W));
                        move.setLeft(gameData.getKeys().isDown(A));
                        move.setDown(gameData.getKeys().isDown(S));
                        move.setRight(gameData.getKeys().isDown(D));
                        move.process(gameData, player);
                        updateSpriteSquare(player);
                        
//						x = position.getX();
//						y = position.getY();
//						position.setPosition(x, y);
						//player.add(new Position(x, y));
						position.process(gameData, player);
						pp.getTest();
						//updateSpriteCircle(player);
	//		gameKeys.keyPress();
//			move.setDown(gameKeys.keyPress());
//			move.setLeft(true);
//			move.setRight(true);
//		if(Gdx.input.isKeyPressed(Keys.W)){
//			System.out.println("hello");
//			move.setUp(true);
//			System.out.println("up");
//		}
			
			
		}
	}
	private void updateSpriteCircle(MovableEntity entity){
		int numPoints = 12;
		float [] shapeX = new float[numPoints];
		float [] shapeY = new float[numPoints];
		
		Position position = entity.getPart(Position.class);
		float radians = position.getRadians();
		float radius = entity.getRadius();
		float x = position.getX();
		float y = position.getY();
		
		float angle = 0;
		
		for(int i = 0; i < numPoints; i++)
		{
			shapeX[i] = x +(float) Math.cos(angle + radians) * radius;
			shapeY[i] = y + (float) Math.sin(angle + radians) * radius;
			angle += 2 * 3.1415f / numPoints;
		}
		entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
	}
	
	private void updateSpriteSquare(MovableEntity entity){
		float [] shapeX =  entity.getShapeX();
		float [] shapeY = entity.getShapeY();
		
		Position position = entity.getPart(Position.class);
		float radians = position.getRadians();
		double pi = Math.PI;
		//float radius = entity.getRadius();
		float x = position.getX();
		float y = position.getY();
		
		shapeX[0] = x + (float) Math.cos(radians - 3 * pi / 4) *20;
		shapeY[0] = y + (float) Math.sin(radians - 3 * pi / 4) *20;
		
		shapeX[3] = x + (float) Math.cos(radians - 5 * pi /4) * 20;
		shapeY[3] = y + (float) Math.sin(radians - 5 * pi /4) * 20;
		
		shapeX[1] = x + (float) Math.cos(radians + 7 * pi / 4) *20;
		shapeY[1] = y + (float) Math.sin(radians + 7 * pi / 4) *20;
		
		shapeX[2] = x + (float) Math.cos(radians + pi /4) *     20;
		shapeY[2] = y + (float) Math.sin(radians + pi /4) *     20;
		
		entity.setShapeX(shapeX);
        entity.setShapeY(shapeY);
	}
}
