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
public class Node
{
	private float x, y, distance;
	private Node parent;
	public Edge[] adjacencies;
	
	
	public Node(float x, float y, float distance){
		this.x = x;
		this.y = y;
		this.distance = distance;
}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getDistance()
	{
		return distance;
	}

	public void setDistance(float distance)
	{
		this.distance = distance;
	}

	public Node getParent()
	{
		return parent;
	}

	public void setParent(Node parent)
	{
		this.parent = parent;
	}
	
}
