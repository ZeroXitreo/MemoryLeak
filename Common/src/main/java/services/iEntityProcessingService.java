/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import data.GameData;
import data.World;

/**
 *
 * @author Jorge Báez Garrido
 */
public interface iEntityProcessingService {
    public void process(GameData gameData, World world);
}
