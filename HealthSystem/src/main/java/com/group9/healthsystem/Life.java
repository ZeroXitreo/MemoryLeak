/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.healthsystem;

import data.GameData;
import data.World;
import services.iEntityProcessingService;

/**
 *
 * @author Christian
 */
public class Life implements iEntityProcessingService
{
	private int health;
	private boolean dead = false;
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int health){
		this.health = health;
	}
	
	public void setDead(boolean dead){
		this.dead = dead;
	}
	
	public boolean isDead(){
		return dead;
	}

	@Override
	public void process(GameData gameData, World world)
	{
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
