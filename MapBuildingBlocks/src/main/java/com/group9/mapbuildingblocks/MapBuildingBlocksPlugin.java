

package com.group9.mapbuildingblocks;

import data.GameData;
import data.ImmovableEntity;
import data.World;
import services.iGamePluginServices;

/**
 *
 * @author Jorge Báez Garrido
 */
public class MapBuildingBlocksPlugin implements iGamePluginServices {
	
	private ImmovableEntity buildingBlocks;
	
	@Override
	public void start(GameData gameData, World world)
	{
		buildingBlocks = createBuildingBlocks(gameData);
		world.addImmovableEntity(buildingBlocks);
	}

	@Override
	public void stop(GameData gameData, World world)
	{
		world.removeImmovableEntity(buildingBlocks);
	}

	private ImmovableEntity createBuildingBlocks(GameData gameData)
	{
		ImmovableEntity newBuildingBlock = new MapBuildingBlocks();
		return newBuildingBlock;
		}

}
