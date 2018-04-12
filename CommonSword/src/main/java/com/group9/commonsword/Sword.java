/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonsword;

import data.MovableEntity;

/**
 *
 * @author jonas
 */
public class Sword extends MovableEntity{

    private String type;

    public Sword(Boolean friendly) {
        if (friendly) {
            type = "friendlyBullet";
        } else {
            type = "enemyBullet";
        }
    }

    @Override
    public String getType() {
        return type;
    }

}