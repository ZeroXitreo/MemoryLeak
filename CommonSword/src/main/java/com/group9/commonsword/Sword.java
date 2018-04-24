/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonsword;

import data.MovableEntity;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author jonas
 */
public class Sword extends MovableEntity {

    public Sword(Boolean friendly) {
        super.type = new Type();
        super.name = new Name();
        super.name.setNameToSword();
        if (friendly) {
            super.type.setTypeToFriendlyBullet();
        } else {
            super.type.setTypeToEnemyBullet();
        }

    }
}
