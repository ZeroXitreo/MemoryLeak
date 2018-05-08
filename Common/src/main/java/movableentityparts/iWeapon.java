/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;


import data.MovableEntity;

/**
 *
 * @author jonas
 */
public interface iWeapon {
    /**
     * creates a projectile.
     * @param shooter The MovableEntity that creates the projectile.
     */
    void createProjectile(MovableEntity shooter);
    /**
     * @return long cooldown when the weapon can fire again.
     */
    long getCooldown();
    /**
     * @return String name of weapon.
     */
    String getWeaponName();
}
