/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.commonenemy;

import data.MovableEntity;

/**
 *
 * @author jonas
 */
public class Enemy extends MovableEntity{
    @Override
    public String getType() {
        return "enemy";
    }
}
