package com.group9.searchalgorithm;

public class Node
{
	private final int X;
	private final int Y;
	private double hValue;
	private double fValue;
	private Node parent;

	/**
	 * Constructor.
	 *
	 * @param x sets the node's x position
	 * @param y sets the node's y position
	 */
	protected Node(int x, int y)
	{
		this.X = x;
		this.Y = y;
	}

	/**
	 * @return the x coordinate
	 */
	protected int getX()
	{
		return X;
	}

	/**
	 * @return the y coordinate
	 */
	protected int getY()
	{
		return Y;
	}

	/**
	 * @param hValue to set the hValue in the node
	 */
	protected void setHValue(double hValue)
	{
		this.hValue = hValue;
	}

	/**
	 * @return hValue from the node
	 */
	protected double getHValue()
	{
		return hValue;
	}

	/**
	 * @param fValue to set the fValue in the node
	 */
	protected void setFValue(double fValue)
	{
		this.fValue = fValue;
	}

	/**
	 * @return fValue from the node
	 */
	protected double getFValue()
	{
		return fValue;
	}

	/**
	 * @param parent to set the parent of the node
	 */
	protected void setParent(Node parent)
	{
		this.parent = parent;
	}

	/**
	 * @return parent of the node
	 */
	protected Node getParent()
	{
		return parent;
	}
}
