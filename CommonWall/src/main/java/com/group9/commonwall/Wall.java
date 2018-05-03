/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonwall;

import data.ImmovableEntity;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author jonas
 */
public class Wall extends ImmovableEntity {

    public Wall() {
        super.type = new Type();
        super.name = new Name();
        super.type.setTypeToWall();
        super.name.setNameToWall();
    }

}
