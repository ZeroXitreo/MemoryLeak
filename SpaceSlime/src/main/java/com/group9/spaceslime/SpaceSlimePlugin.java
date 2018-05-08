package com.group9.spaceslime;

import com.group9.commonspaceslime.SpaceSlime;
import data.GameData;
import data.MovableEntity;
import data.World;
import java.util.Random;
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

    private MovableEntity spaceSlime;
    private Random random;
    private int amountOfEnemies;

    @Override
    public void start(GameData gameData, World world) {
        random = new Random();
        amountOfEnemies = 2;
        for (int i = 0; i < amountOfEnemies; i++) {
            spaceSlime = createSpaceSlime();
            world.addMovableEntity(spaceSlime);
        }
    }

    /**
     * Creates a SpaceSlime
     * @return SpaceSlime MovableEntity
     */
    private MovableEntity createSpaceSlime() {
        //Spawn location of enemy/enemies
        float x = 100 + random.nextFloat();
        float y = 420 + random.nextFloat();
        float maxSpeed = 1;
        MovableEntity spaceSlime = new SpaceSlime();
        spaceSlime.setRadius(15);
        Move move = new Move(maxSpeed);
        move.setUseDirection(true);
        spaceSlime.add(move);
        spaceSlime.add(new Position(x, y));
        spaceSlime.add(new HealthPart(1));
        spaceSlime.add(new WeaponPart());
        spaceSlime.add(new Attack());
        return spaceSlime;
    }

    @Override
    public void stop(GameData gameData, World world) {
        for (MovableEntity spaceSlime : world.getMovableEntities(SpaceSlime.class)) {
            world.removeMovableEntity(spaceSlime);
        }

        for (MovableEntity spaceSlime : world.getGameMovableEntities(SpaceSlime.class)) {
            world.removeGameMovableEntity(spaceSlime);
        }

    }
}
