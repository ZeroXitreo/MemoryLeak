/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.collision;

import com.group9.commonplayer.Player;
import com.group9.commonslime.Slime;
import com.group9.commonwall.Wall;
import data.GameData;
import data.ImmovableEntity;
import data.MovableEntity;
import data.World;
import movableentityparts.Position;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openide.util.Lookup;

/**
 *
 * @author Forberg
 */
public class CollisionTest {

    Collision instance;
    GameData gameData;
    World world;

    MovableEntity player;
    MovableEntity enemy;
    ImmovableEntity wall;

    Lookup lookup;

    public CollisionTest() {
        lookup = Lookup.getDefault();

        instance = new Collision();
        gameData = new GameData();
        world = new World();

        gameData.setDisplayHeight(540);
        gameData.setDisplayWidth(960);
        //gameData.setMoveAreaHeightMax(gameData.getDisplayHeight());
        //gameData.setMoveAreaHeightMin(0);
        //gameData.setMoveAreaWidthMax(gameData.getDisplayWidth());
        //gameData.setMoveAreaWidthMin(0);

        player = new Player();
        enemy = new Slime();
        wall = new Wall();

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of checkSingleCollision method, of class Collision.
     */
    @Test
    public void testCheckSingleCollision() {
        //Set up player
        float playerX = 100f;
        float playerY = 100f;
        player.setRadius(32f);

        //Set up wall
        float wallX = 105f;
        float wallY = 105f;
        wall.setRadius(32f);

        //Set up enemy
        float enemyX = 110f;
        float enemyY = 110f;
        enemy.setRadius(32f);
        //Add positions to the entities
        player.add(new Position(playerX, playerY));
        enemy.add(new Position(enemyX, enemyY));
        wall.add(new Position(wallX, wallY));

        //Add entities to world
        world.addGameMovableEntity(player);
        world.addGameMovableEntity(enemy);
        world.addGameImmovableEntity(wall);

        //Test player has been relocated
        instance.checkSingleCollision(player, world);
        Position playerposition = player.getPart(Position.class);

        float playerNewX = playerposition.getX();
        float playerNewY = playerposition.getY();
        
        System.out.println("TESTING PLAYER RELOCATION");
        System.out.println("OLD X: " + playerX + "\t\tOLD Y: " + playerY + "\nNEW X: " + playerNewX + "\tNEW Y: " + playerNewY);
        assertTrue(playerX != playerNewX && playerY != playerNewY);

        //Test enemy has been relocated
        instance.checkSingleCollision(enemy, world);
        Position enemyposition = enemy.getPart(Position.class);

        float enemyNewX = enemyposition.getX();
        float enemyNewY = enemyposition.getY();
        System.out.println("TESTING ENEMY RELOCATION");
        System.out.println("OLD X: " + enemyX + "\t\tOLD Y: " + enemyY + "\nNEW X: " + enemyNewX + "\tNEW Y: " + enemyNewY);
        assertTrue(enemyX != enemyNewX && enemyY != enemyNewY);

    }

}
