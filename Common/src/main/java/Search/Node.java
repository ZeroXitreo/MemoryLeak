/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.Map;

/**
 *
 * @author Christian
 */
public class Node<T>
{
	
	private T nodeId;
	private Map<T, Double> heuristic;
	
	//h = heuristic of destination. 
	//totaltCost = pathCost + h
	//pathCost = pathCost from source.
	private double h, totalCost, pathCost;
	
	
	
	public Node(T nodeId, Map<T,Double> heuristic ){
		this.nodeId = nodeId;
		this.heuristic = heuristic;
		this.pathCost = Double.MAX_VALUE;
}
	
	public T getNodeId(){
		return nodeId;
	}
	
	public double getH()
	{
		return h;
	}

	public double getTotalCost()
	{
		return totalCost;
	}

	public double getPathCost()
	{
		return pathCost;
	}

	public void setPathCost(double pathCost)
	{
		this.pathCost = pathCost;
	}
	
	public void calcTotalCost(T destination){
		this.h = heuristic.get(destination);
		this.totalCost = pathCost + h;
	}

	
}
