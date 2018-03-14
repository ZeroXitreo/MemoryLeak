package services;

import data.GameData;
import data.World;

/**
 *
 * @author Jorge Báez Garrido
 */
public interface iGamePluginServices
{
	void start(GameData gameData, World world);

	void stop(GameData gameData, World world);

}
