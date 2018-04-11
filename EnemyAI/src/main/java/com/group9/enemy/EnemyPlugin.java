package com.group9.enemy;

import com.group9.commonenemy.Enemy;
import data.GameData;
import data.MovableEntity;
import data.World;
import java.util.ArrayList;
import java.util.List;
import movableentityparts.Attack;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.WeaponPart;
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
    public void start(GameData gameData, World world) {
        enemy = createEnemy(gameData);
        world.addMovableEntity(enemy);
    }

    private MovableEntity createEnemy(GameData gameData) {

        //Spawn location of enemy/enemies
        float x = 100;
        float y = 420;
        float maxSpeed = 1;
        MovableEntity enemyCharacter = new Enemy();
        enemyCharacter.setRadius(10);
        Move move = new Move(maxSpeed);
        move.setUseDirection(true);
        enemyCharacter.add(move);
        enemyCharacter.add(new Position(x, y));
        enemyCharacter.add(new HealthPart(1));
        enemyCharacter.add(new WeaponPart());
        
        enemyCharacter.add(new Attack());
        
        enemyCharacter.setHasWeapon(false);
        return enemyCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
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
