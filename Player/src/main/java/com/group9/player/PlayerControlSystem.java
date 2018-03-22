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
	@Override
	public void process(GameData gameData, World world)
	{
		for(Entity player : world.getEntities(Player.class)){
			Position position = player.getPart(Position.class);
			Move move = player.getPart(Move.class);
			
                        move.setUp(gameData.getKeys().isDown(W));
                        move.setLeft(gameData.getKeys().isDown(A));
                        move.setDown(gameData.getKeys().isDown(S));
                        move.setRight(gameData.getKeys().isDown(D));
                        move.process(gameData, player);
                        position.process(gameData, player);
                        updateSprite(player);
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
	private void updateSprite(Entity entity){
		
	}
}
