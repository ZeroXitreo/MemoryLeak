

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
		System.out.println("Hej1");
		enemy = createEnemy(gameData);
		world.addMovableEntity(enemy);
	}
	
	private MovableEntity createEnemy(GameData gameData){
		
		//Spawn location of enemy/enemies
//		float x =100, y=420;
		float x = gameData.getDisplayWidth()/2;
		float y = gameData.getDisplayHeight()/2;
		float maxSpeed = 4;
		MovableEntity enemyCharacter = new Enemy();
		enemyCharacter.setRadius(10);
		enemyCharacter.add(new Move(maxSpeed));
		enemyCharacter.add(new Position(x, y));
		System.out.println("Hej");
		return enemyCharacter;
	}
	
	@Override
	public void stop(GameData gameData, World world)
	{
        world.removeEntity(enemy);
	}
	
//	private void createCoordinates(float x, float y){
//		coordinateList.add(coordinate);
//		String[] tempCoordinate = coordinate.split(";");
//		String xCoordinate = tempCoordinate[0];
//		String yCoordinate = tempCoordinate[1];
//		x = Float.parseFloat(xCoordinate);
//		y = Float.parseFloat(yCoordinate);
//	}

}
