/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.player;


import data.MovableEntity;



/**
 *
 * @author Christian
 */
public class Player extends MovableEntity 
{
    @Override
    public String getType(){
        return "player";
    }
}
