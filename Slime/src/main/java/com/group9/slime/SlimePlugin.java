/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.slime;

import com.group9.commonslime.Slime;
import data.GameData;
import data.MovableEntity;
import data.World;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iGamePluginServices.class)
public class SlimePlugin implements iGamePluginServices {

    private MovableEntity enemy;

    @Override
    public void start(GameData gameData, World world) {
        enemy = createEnemy();
        world.addEnemyEntity(enemy);
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeMovableEntity(enemy);
    }

    private MovableEntity createEnemy(){
        //Spawn location of enemy/enemies
        float x = 100;
        float y = 100;
        float maxSpeed = 1;
        MovableEntity enemyCharacter = new Slime();
        enemyCharacter.setRadius(15);
        Move move = new Move(maxSpeed);
        move.setUseDirection(true);
        enemyCharacter.add(move);
        enemyCharacter.add(new Position(x, y));
        enemyCharacter.add(new HealthPart(5));
        return enemyCharacter;
    }
}
