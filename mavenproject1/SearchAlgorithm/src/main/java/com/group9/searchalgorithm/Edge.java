/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.searchalgorithm;

/**
 *
 * @author Christian
 */
public class Edge	
{
	private float cost;
    public Node target;
	
	public Edge(float cost, Node target){
		this.cost = cost;
		this.target = target; 
	}

	public float getCost()
	{
		return cost;
	}

	public void setCost(float cost)
	{
		this.cost = cost;
	}


	
}
