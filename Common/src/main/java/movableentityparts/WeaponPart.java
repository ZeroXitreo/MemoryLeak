/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movableentityparts;

import data.GameData;
import data.MovableEntity;

/**
 *
 * @author jonas
 */
public class WeaponPart implements EntityPart {

    private iWeapon weapon;
    private long cooldown = 0;
    private long startTime = System.currentTimeMillis();
    private long endTime = System.currentTimeMillis();

    public WeaponPart() {
    }

    @Override
    public void process(GameData gameData, MovableEntity entity) {

        endTime = System.currentTimeMillis();
        if (endTime - startTime >= cooldown && weapon != null) { //check for cooldown and if there is a weapon
            
            cooldown = weapon.getCooldown();

            if (entity.getShoot()) {                
                weapon.createProjectile(entity);
            }
            entity.setShoot(false);
            startTime = System.currentTimeMillis();
        }

    }

    /**
     * @param weapon iWeapon to be set as the weapon.
     */
    public void setWeapon(iWeapon weapon) {
        this.weapon = weapon;
    }

    /**
     * removes the weapon.
     */
    public void removeWeapon() {
        weapon = null;
    }

}
