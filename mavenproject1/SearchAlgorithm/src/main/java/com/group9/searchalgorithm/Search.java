/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.searchalgorithm;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Christian
 */
public class Search
{
	Map<String, Map<String, Double>> heuristic = new HashMap<String, Map<String, Double>>();
	
	Map<String, Double> mapA = new HashMap<>();
	
	public void createMaps(){
		mapA.put("A", 0.0);
	}
}
