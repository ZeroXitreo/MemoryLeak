/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;

import data.Entity;
import data.GameData;
import data.World;
import services.iGamePluginServices;

/**
 *
 * @author Christian
 */
public class PlayerPlugin implements iGamePluginServices
{
	private Entity player;
	
	@Override
	public void start(GameData gameData, World world)
	{
		player = createPlayer(gameData);
		world.addEntity(player);
	}
	private Entity createPlayer(GameData gameData){
		Entity playerCharacter = new Player();
		return playerCharacter;
	}
	@Override
	public void stop(GameData gameData, World world)
	{
		world.removeEntity(player);
	}
	
}
