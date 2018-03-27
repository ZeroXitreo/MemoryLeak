/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author Christian
 */
@ServiceProvider(service = iGamePluginServices.class)
public class PlayerPlugin implements iGamePluginServices
{
	private MovableEntity player;
	String test = "test";

	@Override
	public void start(GameData gameData, World world)
	{
		player = createPlayer(gameData);
		world.addMovableEntity(player);
		test = world.addMovableEntity(player);
	}
	
	public String getTest(){
		return this.test;
	}
	
	private MovableEntity createPlayer(GameData gameData)
	{

		float x = gameData.getDisplayWidth() / 2;
		float y = gameData.getDisplayHeight() / 2;
		float maxSpeed = 1;
		MovableEntity playerCharacter = new Player();
		playerCharacter.setRadius(20);
		playerCharacter.add(new Move(maxSpeed));
		playerCharacter.add(new Position(x, y));
                playerCharacter.add(new HealthPart(10));
		return playerCharacter;
	}

	@Override
	public void stop(GameData gameData, World world)
	{
		world.removeEntity(player);
	}

}
