/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group9.searchalgorithm;


import Search.Edge;
import Search.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author Christian
 */
public class AStar<T> {
	private final Edge<T> edge;
	
	public AStar(Edge<T> edge){
		this.edge = edge;
	}
	
	public class NodeComparator implements Comparator<Node<T>>{
		public int compare(Node<T> nodeFirst, Node<T> nodeSecond){
			if(nodeFirst.getTotalCost() > nodeSecond.getTotalCost()) return 1;
			if(nodeSecond.getTotalCost() > nodeFirst.getTotalCost()) return -1;
			return 0;
	}
	}
	
	public List<T> aStar(T source, T destination){
		final Queue<Node<T>> openQueue = new PriorityQueue<Node<T>>(11, new NodeComparator());
		
		Node<T> sourceNode = edge.getNode(source);
		sourceNode.setPathCost(0);
		sourceNode.calcTotalCost(destination);
		openQueue.add(sourceNode);
		
		final Map<T, T> path = new HashMap<>();
		final Set<Node<T>> closedList = new HashSet<>();
		
		while(!openQueue.isEmpty()){
			final Node<T> node = openQueue.poll();
			
			if(node.getNodeId().equals(destination)){
				return path(path, destination);
			}
			closedList.add(node);
			
			for(Entry<Node<T>, Double> neighborEntry : edge.edgesFrom(node.getNodeId()).entrySet()){
				Node<T> neighbor = neighborEntry.getKey();
				
				if(closedList.contains(neighbor)) continue;
				
				double distanceBetweenTwoNodes = neighborEntry.getValue();
				double tentativeG = distanceBetweenTwoNodes + node.getPathCost();
				
				if(tentativeG < neighbor.getPathCost()){
					neighbor.setPathCost(tentativeG);
					neighbor.calcTotalCost(destination);
					
					path.put(neighbor.getNodeId(), node.getNodeId());
					if(!openQueue.contains(neighbor)){
						openQueue.add(neighbor);
					}
				}
		}
		}
		return null;
	}
	
	private List<T> path(Map<T, T> path, T destination){
		assert path != null;
		assert destination != null;
		
		final List<T> pathList = new ArrayList<>();
		pathList.add(destination);
		while(path.containsKey(destination)){
			destination = path.get(destination);
			pathList.add(destination);
		}
		Collections.reverse(pathList);
		return pathList;
	}
	
}
