package com.group9.searchalgorithm;

import java.awt.Color;

abstract public class Entity
{
	protected Color drawColor = Color.gray;
	int x;
	int y;
	int speed = 5;

	public Entity(int x, int y)
	{
		this.x = (int) (Math.random() * x);
		this.y = (int) (Math.random() * y);
	}

	public void moveTowards(int x, int y)
	{
		if(this.x == x && this.y == y)
		{
			return;
		}
		
		double a = this.x - x;
		double b = this.y - y;
		double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
		double angle_B = Math.atan(b / a);

		double pushPos_c = c - speed;
		double pushPos_b = Math.sin(angle_B) * pushPos_c;
		double pushPos_a = Math.cos(angle_B) * pushPos_c;

		double multiplier = 1;
		multiplier = this.x < x ? -multiplier : multiplier;

		this.x = (int) Math.round(x + pushPos_a * multiplier);
		this.y = (int) Math.round(y + pushPos_b * multiplier);
	}
}
