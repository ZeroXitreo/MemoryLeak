/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.projectile;

import data.GameData;
import data.MovableEntity;
import data.World;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import movableentityparts.HealthPart;
import movableentityparts.Move;
import movableentityparts.Position;
import movableentityparts.Timer;
import org.openide.util.lookup.ServiceProvider;
import services.iGamePluginServices;
import movableentityparts.iWeapon;

/**
 *
 * @author jonas
 */
@ServiceProvider(service = iGamePluginServices.class)
public class ProjectilePlugin implements iWeapon, iGamePluginServices {

    private MovableEntity bullet;
    private long cooldown = 500;
    private World world;

    @Override
    public void start(GameData gameData, World world) {
        world.addWeapon(getType(), this);
        this.world = world;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeWeapon(getType());
    }

    @Override
    public void createProjectile(MovableEntity shooter) {
        Position shooterPos = shooter.getPart(Position.class);
        float shooterX = shooterPos.getX();
        float shooterY = shooterPos.getY();
        float radians = shooter.getDirection();
        float speed = 3;

        bullet = new Projectile(shooter.getType().equalsIgnoreCase("player"));
        bullet.setRadius(3);
        bullet.setDirection(radians);

        float bulletX = (float) cos(radians) * (shooter.getRadius() + bullet.getRadius());
        float bulletY = (float) sin(radians) * (shooter.getRadius() + bullet.getRadius());

        bullet.add(new Position(bulletX + shooterX + 4, bulletY + shooterY + 4));
        bullet.add(new Timer(1.5f));
        bullet.add(new HealthPart(1));
        bullet.add(new Move(speed));
        bullet.setShapeX(new float[2]);
        bullet.setShapeY(new float[2]);
        bullet.setDirection(radians);
        world.addMovableEntity(bullet);
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public String getType() {
        return "projectile";
    }

}
