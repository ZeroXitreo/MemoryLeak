package com.group9.searchalgorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class SearchAlgorithm
{
	static int width;
	static int height;
	static int gridDensity = 16 / 2;

	static ArrayList<Enemy> enemies = new ArrayList();

	static Player player;
	static boolean[][] randomlyGenMatrix;

	public SearchAlgorithm()
	{
		// Set size of the screen
		width = 960;
		height = 540;

		// Add player and enemies
		player = new Player(width, height);
		for (int i = 0; i < 1; i++)
		{
			enemies.add(new Enemy(width, height));
		}

		// GenerateMatrix for walls
		randomlyGenMatrix = createGrid((int) Math.ceil((double) height / gridDensity), (int) Math.ceil((double) width / gridDensity), 0.9);
	}

	public void processSearch()
	{
		enemies.stream().parallel().forEach(e ->
		{
			processAStar(e, player, randomlyGenMatrix);

			Optional<Node> matchingObject = e.pathList.stream().
					filter(p -> p.x == e.y / gridDensity && p.y == e.x / gridDensity).
					findFirst();
			Node cell = matchingObject.orElse(null);

			// For movement and beyond (And drawingz)
			if (cell != null && e.pathList != null && e.pathList.size() >= e.pathList.indexOf(cell) + 3)
			{
				Node nextNode = e.pathList.get(e.pathList.indexOf(cell) + 1);
				e.moveTowards(nextNode.y * gridDensity + gridDensity / 2, nextNode.x * gridDensity + gridDensity / 2);
			}
		});
	}

	// return a createGrid N-by-N boolean matrix, where each entry is
	// true with probability p
	public static boolean[][] createGrid(int y, int x, double p)
	{
		boolean[][] a = new boolean[y][x];
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				a[i][j] = true;
			}
		}
		return a;
	}

	public static void processAStar(Enemy enemy, Entity player, boolean[][] randomlyGenMatrix)
	{
		Stream<Node> filter = enemy.pathList.stream().
				filter(p -> (p.x == player.y / gridDensity && p.y == player.x / gridDensity) || p.x == enemy.y / gridDensity && p.y == enemy.x / gridDensity);

		ArrayList<Node> pathList = enemy.pathList;

		if (filter.count() != 2) // Does the player and the enemy still operate inside the path generated?
		{
			pathList.clear(); // Clear the list
			generateHValue(randomlyGenMatrix, player.y / gridDensity, player.x / gridDensity, enemy.y / gridDensity, enemy.x / gridDensity, pathList); // Generate new path
		}
	}

	public static void generateHValue(boolean grid[][], int ax, int ay, int bx, int by, ArrayList<Node> pathList)
	{
		//Creation of a Node type 2D array
		Node[][] cell = new Node[grid.length][grid[0].length];

		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid[0].length; y++)
			{
				cell[x][y] = new Node(x, y);
				//Checks whether a cell is Blocked or Not by checking the boolean value
				cell[x][y].hValue = grid[x][y] ? Math.abs(x - bx) + Math.abs(y - by) : -1;
			}
		}
		generatePath(ax, ay, bx, by, pathList, cell);
	}

	public static void generatePath(int Ai, int Aj, int Bi, int Bj, ArrayList<Node> pathList, Node[][] cell)
	{
		ArrayList<Node> closedList = new ArrayList();

		//Creation of a PriorityQueue and the declaration of the Comparator
		PriorityQueue<Node> openList = new PriorityQueue(1, new Comparator()
		{
			@Override
			//Compares 2 Node objects stored in the PriorityQueue and Reorders the Queue according to the object which has the lowest fValue
			public int compare(Object cell1, Object cell2)
			{
				return ((Node) cell1).fValue < ((Node) cell2).fValue ? -1 : ((Node) cell1).fValue > ((Node) cell2).fValue ? 1 : 0;
			}
		});

		//Adds the Starting cell inside the openList
		openList.add(cell[Ai][Aj]);

		//Executes the rest if there are objects left inside the PriorityQueue
		while (true)
		{
			//Gets and removes the objects that's stored on the top of the openList and saves it inside node
			Node node = openList.poll();

			//Checks if whether node is empty and f it is then breaks the while loop
			if (node == null)
			{
				break;
			}

			// Checks if whether the node returned is having the same node object values of the ending point
			// If it des then stores that inside the closedList and breaks the while loop
			if (node == cell[Bi][Bj])
			{
				closedList.add(node);
				break;
			}

			closedList.add(node);

			for (int y = -1; y <= 1; y++)
			{
				for (int x = -1; x <= 1; x++)
				{
					int a = 0;
					if (x == 0 && y == 0) // Center
					{
						continue;
					}
					else if (Math.abs(x) == Math.abs(y)) // Corner
					{
						a = 14;
						continue;
					}
					else // Side
					{
						a = 10;
					}

					try
					{
						if (cell[node.x + y][node.y + x].hValue != -1
								&& !openList.contains(cell[node.x + y][node.y + x])
								&& !closedList.contains(cell[node.x + y][node.y + x]))
						{
							double tCost = node.fValue + a;
							double cost = cell[node.x + y][node.y + x].hValue + tCost;
							if (cell[node.x + y][node.y + x].fValue > cost || !openList.contains(cell[node.x + y][node.y + x]))
							{
								cell[node.x + y][node.y + x].fValue = cost;
							}

							openList.add(cell[node.x + y][node.y + x]);
							cell[node.x + y][node.y + x].parent = node;
						}
					}
					catch (IndexOutOfBoundsException e)
					{
					}
				}
			}
		}

		//Assigns the last Object in the closedList to the endNode variable
		Node endNode = closedList.get(closedList.size() - 1);

		//Checks if whether the endNode variable currently has a parent Node. if it doesn't then stops moving forward.
		//Stores each parent Node to the PathList so it is easier to trace back the final path
		while (endNode.parent != null)
		{
			Node currentNode = endNode;
			pathList.add(currentNode);
			endNode = endNode.parent;
		}

		pathList.add(cell[Ai][Aj]);
	}
}
