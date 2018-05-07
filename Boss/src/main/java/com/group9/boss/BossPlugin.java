

package com.group9.boss;

import data.GameData;
import data.MovableEntity;
import data.World;
import services.iGamePluginServices;

/**
 *
 * @author Jorge Báez Garrido
 */
public class BossPlugin implements iGamePluginServices {
	
	private MovableEntity boss;
	@Override
	public void start(GameData gameData, World world)
	{
		boss = createBoss(gameData);
		world.addMovableEntity(boss);
	}

	@Override
	public void stop(GameData gameData, World world)
	{
		world.removeMovableEntity(boss);
	}

	private MovableEntity createBoss(GameData gameData)
	{
		MovableEntity bossCharacter = new Boss();
		return bossCharacter;
	}

}
