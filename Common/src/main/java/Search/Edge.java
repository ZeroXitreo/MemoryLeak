/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Search;

import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;


/**
 *
 * @author Christian
 */
public class Edge <T> implements Iterable<T>
{
	private final Map<T, Map<Node<T>, Double>> graph;
	
	private final Map<T, Map<T, Double>> heuristicMap;
	
	private final Map<T, Node<T>> nodeIdNode;
	
	public Edge(Map<T, Map<T, Double>> heuristicMap){
		if(heuristicMap == null) {
			throw new NullPointerException("The heuristic map should not be null");}
		
		graph = new HashMap<T, Map< Node<T>, Double>>();
		nodeIdNode = new HashMap<T, Node<T>>();
		this.heuristicMap = heuristicMap;
	}
	
	public void addNode(T nodeId){
		if (nodeId == null) throw new NullPointerException("The node can't be null");
		if (!heuristicMap.containsKey(nodeId)) throw new NoSuchElementException("this node is not a part of heuristicMap");
	
		graph.put(nodeId, new HashMap<Node<T>, Double>());
		nodeIdNode.put(nodeId, new Node<T>(nodeId, heuristicMap.get(nodeId)));
	}
	
	public void addEdge(T nodeIdFirst, T nodeIdSecond, double length){
		if (nodeIdFirst == null || null == nodeIdSecond) throw new NullPointerException("The first nor second node should be null");
		
		if (heuristicMap.containsKey(nodeIdFirst) || !heuristicMap.containsKey(nodeIdSecond)){
			throw new NoSuchElementException("Source and destination should be part of the heuristicMap");
		}
		if (!graph.containsKey(nodeIdFirst) || !graph.containsKey(nodeIdSecond)){
			throw new NoSuchElementException("Source and destination should both be part of the graph");
		}		
		
		graph.get(nodeIdFirst).put(nodeIdNode.get(nodeIdSecond), length);
		graph.get(nodeIdSecond).put(nodeIdNode.get(nodeIdFirst), length);
	}
	
	public Map<Node<T>, Double> edgesFrom (T nodeId){
		if (nodeId == null) throw new NullPointerException("The input node should not be null.");
		if(!heuristicMap.containsKey(nodeId)) throw new NoSuchElementException("This node is not a part of the heuristic map.");
		if(!graph.containsKey(nodeId)) throw new NoSuchElementException("The node should not be null.");
		return Collections.unmodifiableMap(graph.get(nodeId));
	}
	
	public Node<T> getNode(T nodeId) {
		if(nodeId == null) { throw new NullPointerException("The nodeid should not be empty.");
				}
		if(!nodeIdNode.containsKey(nodeId)){
			throw new NoSuchElementException("The nodeid does not exist");
		}
		return nodeIdNode.get(nodeId);
	}
	
	@Override
	public Iterator<T> iterator()
	{
		return graph.keySet().iterator();
	}
	


	
}
