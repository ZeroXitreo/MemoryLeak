/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonspaceslime;

import data.MovableEntity;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author jonas
 */
public class SpaceSlime extends MovableEntity {

    public SpaceSlime() {
        super.type = new Type();
        super.name = new Name();
        super.type.setTypeToEnemy();
        super.name.setNameToSpaceSlime();
    }
}
