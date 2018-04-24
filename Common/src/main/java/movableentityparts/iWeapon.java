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
    void createProjectile(MovableEntity shooter);
    long getCooldown();
    String getWeaponName();
}
