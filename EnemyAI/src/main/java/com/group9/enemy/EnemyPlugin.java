

package com.group9.enemy;

import data.GameData;
import data.MovableEntity;
import data.World;
import java.util.ArrayList;
import java.util.List;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author Jorge Báez Garrido
 */
@ServiceProvider(service = iGamePluginServices.class)

public class EnemyPlugin implements iGamePluginServices {
	
	private MovableEntity enemy;
	private List<String> coordinateList = new ArrayList<>();
	private String coordinate;
	
	@Override
	public void start(GameData gameData, World world)
	{
		enemy = createEnemy(gameData);
		world.addEntity(enemy);
	}
	
	private MovableEntity createEnemy(GameData gamedata){
		
		//Spawn location of enemy/enemies
		float x =666, y=420;
		float maxSpeed = 4;
		createCoordinates(x, y);
		MovableEntity enemyCharacter = new Enemy();
		enemyCharacter.add(new Move(maxSpeed));
		enemyCharacter.add(new Position(x, y));
		return enemyCharacter;
	}
	
	@Override
	public void stop(GameData gameData, World world)
	{
        world.removeEntity(enemy);
	}
	
	private void createCoordinates(float x, float y){
		coordinateList.add(coordinate);
		String[] tempCoordinate = coordinate.split(";");
		String xCoordinate = tempCoordinate[0];
		String yCoordinate = tempCoordinate[1];
		x = Float.parseFloat(xCoordinate);
		y = Float.parseFloat(yCoordinate);
	}

}
