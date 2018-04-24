/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonfireball;

import data.MovableEntity;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author jonas
 */
public class Fireball extends MovableEntity {

    public Fireball(Boolean friendly) {
        super.type = new Type();
        super.name = new Name();
        super.name.setNameToFireball();
        if (friendly) {
            super.type.setTypeToFriendlyBullet();
        } else {
            super.type.setTypeToEnemyBullet();
        }
    }

}
