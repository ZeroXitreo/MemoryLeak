/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.searchalgorithm;

import data.MovableEntity;
import data.World;
import movableentityparts.Position;

/**
 *
 * @author Christian
 */
public class AStar
{
	World world;
	public void calcDistance(MovableEntity player, MovableEntity enemy){
		Position playerposition = player.getPart(Position.class);
		Position enemyposition = enemy.getPart(Position.class);
		
		playerposition.getX();
		playerposition.getY();
		enemyposition.getX();
		enemyposition.getY();
		
	}
	
	
}
