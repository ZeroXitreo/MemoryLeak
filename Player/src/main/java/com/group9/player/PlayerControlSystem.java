/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import data.Entity;
import data.GameData;
import data.GameKeys;
import data.MovableEntity;
import data.World;
import movableentityparts.Move;
import services.iEntityProcessingService;
import movableentityparts.Position;

/**
 *
 * @author Christian
 */
public class PlayerControlSystem implements iEntityProcessingService
{
	@Override
	public void process(GameData gameData, World world)
	{
		GameKeys gameKeys = new GameKeys(gameData);
		for(Entity player : world.getEntities(Player.class)){
			Position position = player.getPart(Position.class);
			Move move = player.getPart(Move.class);
			
			gameKeys.keyPress();
//			move.setDown(gameKeys.keyPress());
//			move.setLeft(true);
//			move.setRight(true);
//		if(Gdx.input.isKeyPressed(Keys.W)){
//			System.out.println("hello");
//			move.setUp(true);
//			System.out.println("up");
//		}
			
			
		}
//		Entity player = new Entity();
//		updateSprite(player);
	}
	private void updateSprite(MovableEntity movableEntity){
		
	}
}
