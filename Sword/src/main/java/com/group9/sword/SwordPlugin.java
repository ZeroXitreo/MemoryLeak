/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.sword;

import com.group9.commonsword.Sword;
import data.GameData;
import data.MovableEntity;
import data.World;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.Timer;
import movableentityparts.WeaponPart;
import movableentityparts.iWeapon;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iGamePluginServices.class)
public class SwordPlugin implements iWeapon, iGamePluginServices {

    private long cooldown = 200;
    private World world;
    private Sword sword;

    @Override
    public void createProjectile(MovableEntity shooter) {
        Position shooterPos = shooter.getPart(Position.class);
        float shooterX = shooterPos.getX();
        float shooterY = shooterPos.getY();
        float radians = shooter.getShootingDirection();

        sword = new Sword(shooter.getType().equalsPlayer());
        sword.setRadius(7);

        float bulletX = (float) cos(radians) * (shooter.getRadius() + sword.getRadius() + 6);
        float bulletY = (float) sin(radians) * (shooter.getRadius() + sword.getRadius() + 6);

        sword.add(new Timer(0.1f));
        sword.add(new HealthPart(2));
        sword.add(new Position(bulletX + shooterX, bulletY + shooterY));
        Move move = shooter.getPart(Move.class);
        sword.add(move);
        sword.setDirection(radians);
        sword.setShapeX(new float[2]);
        sword.setShapeY(new float[2]);
        world.addGameMovableEntity(sword);
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public String getWeaponName() {
        return "Flail";
    }

    @Override
    public void start(GameData gameData, World world) {
        world.addWeapon(this);
        this.world = world;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeWeapon(this);
        for (MovableEntity entity : world.getGameMovableEntities()) {
            WeaponPart weapon = entity.getPart(WeaponPart.class);
            try {
                weapon.removeWeapon("Flail");
            } catch (NullPointerException e) {

            }

        }
        for (MovableEntity flail : world.getGameMovableEntities(Sword.class)) {
            world.removeMovableEntity(flail);
        }

    }

}
