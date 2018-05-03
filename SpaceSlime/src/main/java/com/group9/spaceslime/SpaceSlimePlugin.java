package com.group9.spaceslime;

import com.group9.commonspaceslime.SpaceSlime;
import data.GameData;
import data.MovableEntity;
import data.World;
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

public class SpaceSlimePlugin implements iGamePluginServices {

    private MovableEntity enemy;

    @Override
    public void start(GameData gameData, World world) {
            enemy = createEnemy();
            world.addMovableEntity(enemy);
    }

    private MovableEntity createEnemy() {
        //Spawn location of enemy/enemies
        float x = 100;
        float y = 420;
        float maxSpeed = 1;
        MovableEntity enemyCharacter = new SpaceSlime();
        enemyCharacter.setRadius(15);
        Move move = new Move(maxSpeed);
        move.setUseDirection(true);
        enemyCharacter.add(move);
        enemyCharacter.add(new Position(x, y));
        enemyCharacter.add(new HealthPart(1));
        enemyCharacter.add(new WeaponPart());
        enemyCharacter.add(new Attack());
        return enemyCharacter;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMovableEntity(enemy);
        world.removeGameMovableEntity(enemy);
    }
}
