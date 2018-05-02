package com.group9.searchalgorithm;

import java.awt.Color;
import java.util.ArrayList;

public class Enemy extends Entity
{
	ArrayList<Node> pathList = new ArrayList<>();
	
	public Enemy(int x, int y)
	{
		super(x, y);
		
		drawColor = Color.red;
	}
}
