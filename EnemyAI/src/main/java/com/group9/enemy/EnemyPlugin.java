

package com.group9.enemy;

import data.GameData;
import data.MovableEntity;
import data.World;
import services.iGamePluginServices;

/**
 *
 * @author Jorge Báez Garrido
 */
public class EnemyPlugin implements iGamePluginServices {
	
	private MovableEntity enemy;
	
	@Override
	public void start(GameData gameData, World world)
	{
		enemy = createEnemy(gameData);
		world.addEntity(enemy);
	}
	
	private MovableEntity createEnemy(GameData gamedata){
		MovableEntity enemyCharacter = new Enemy();
		return enemyCharacter;
	}
	
	@Override
	public void stop(GameData gameData, World world)
	{
        world.removeEntity(enemy);
	}

}
