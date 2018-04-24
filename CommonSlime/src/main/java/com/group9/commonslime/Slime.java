/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonslime;

import data.MovableEntity;
import movableentityparts.Name;
import movableentityparts.Type;

/**
 *
 * @author jonas
 */
public class Slime extends MovableEntity{
    public Slime(){
        super.type = new Type();
        super.name = new Name();
        super.type.setTypeToEnemy();
        super.name.setNameToSlime();
    }
}
