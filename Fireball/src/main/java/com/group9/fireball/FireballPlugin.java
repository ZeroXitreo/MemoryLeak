/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.fireball;

import com.group9.commonfireball.Fireball;
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
public class FireballPlugin implements iWeapon, iGamePluginServices {

    private MovableEntity fireball;
    private long cooldown = 500;
    private World world;

    @Override
    public void start(GameData gameData, World world) {
        world.addWeapon(this);
        this.world = world;
    }

    @Override
    public void stop(GameData gameData, World world) {
        world.removeWeapon(getWeaponName());
    }

    @Override
    public void createProjectile(MovableEntity shooter) {
        Position shooterPos = shooter.getPart(Position.class);
        float shooterX = shooterPos.getX();
        float shooterY = shooterPos.getY();
        float radians = shooter.getShootingDirection();
        float speed = 3;

        fireball = new Fireball(shooter.getType().equalsIgnoreCase("player"));
        fireball.setRadius(6);
        fireball.setDirection(radians);

        float bulletX = (float) cos(radians) * (shooter.getRadius() + fireball.getRadius());
        float bulletY = (float) sin(radians) * (shooter.getRadius() + fireball.getRadius());

        fireball.add(new Position(bulletX + shooterX + 4, bulletY + shooterY + 4));
        fireball.add(new Timer(1.5f));
        fireball.add(new HealthPart(1));
        fireball.add(new Move(speed));
        fireball.setShapeX(new float[2]);
        fireball.setShapeY(new float[2]);
        fireball.setDirection(radians);
        world.addMovableEntity(fireball);
    }

    @Override
    public long getCooldown() {
        return cooldown;
    }

    @Override
    public String getWeaponName() {
        return "fireball";
    }

}
