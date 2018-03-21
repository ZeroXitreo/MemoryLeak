/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;


import data.GameData;
import data.MovableEntity;
import data.World;
import services.iGamePluginServices;

/**
 *
 * @author Christian
 */
public class PlayerPlugin implements iGamePluginServices
{
	private MovableEntity player;
	
	@Override
	public void start(GameData gameData, World world)
	{
		player = createPlayer(gameData);
		world.addEntity(player);
	}
	private MovableEntity createPlayer(GameData gameData){
		
		float x = gameData.getDisplayWidth() / 2;
		float y = gameData.getDisplayHeight() /2;
		
		MovableEntity playerCharacter = new Player();
		return playerCharacter;
	}
	@Override
	public void stop(GameData gameData, World world)
	{
		world.removeEntity(player);
	}
	
}
