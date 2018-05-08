package com.group9.searchalgorithm;

import com.group9.commonplayer.Player;
import data.GameData;
import data.MovableEntity;
import data.World;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;
import movableentityparts.Position;
import org.openide.util.lookup.ServiceProvider;
import services.iEntityProcessingService;

@ServiceProvider(service = iEntityProcessingService.class)
public class SearchAlgorithm implements iEntityProcessingService
{
	int width;
	int height;
	int gridDensity = 16;

	static ArrayList<MovableEntity> enemies = new ArrayList();
	static HashMap<MovableEntity, ArrayList<Node>> enemyPaths = new HashMap();

	MovableEntity player;
	boolean[][] randomlyGenMatrix;

	@Override
	public void process(GameData gameData, World world)
	{
		// Set size of the screen
		width = gameData.getDisplayWidth();
		height = gameData.getDisplayHeight();

		// GenerateMatrix for walls
		if (randomlyGenMatrix == null)
		{
			randomlyGenMatrix = createGrid((int) Math.ceil((double) width / gridDensity), (int) Math.ceil((double) height / gridDensity));
		}

		for (MovableEntity player : world.getGameMovableEntities(Player.class))
		{
			this.player = player;
			for (MovableEntity enemy : world.getGameMovableEntities())
			{
				if(!enemy.getType().equalsEnemy())
				{
					continue;
				}
				
				enemies.add(enemy);
				if (!enemyPaths.containsKey(enemy))
				{
					enemyPaths.put(enemy, new ArrayList());
				}
			}
		}

		processSearch();
		// Clear
		player = null;
		enemies = new ArrayList();
	}

	public void processSearch()
	{
		enemies.stream().parallel().forEach(e ->
		{
			ArrayList<Node> pathList = enemyPaths.get(e);
			Position enemyPos = e.getPart(Position.class);
			int enemyPosX = (int) enemyPos.getX();
			int enemyPosY = (int) enemyPos.getY();
			processAStar(e, player, randomlyGenMatrix);

			Optional<Node> matchingObject = pathList.stream().
					filter(p -> p.y == enemyPosY / gridDensity && p.x == enemyPosX / gridDensity).
					findFirst();
			Node cell = matchingObject.orElse(null);

			// Direction
			if (cell != null && pathList.size() >= pathList.indexOf(cell) + 3)
			{
				Node nextNode = pathList.get(pathList.indexOf(cell) + 1);

				// Move direction
				float nodeX = nextNode.x * gridDensity + gridDensity / 2;
				float nodeY = nextNode.y * gridDensity + gridDensity / 2;
				float direction = (float) Math.atan2(nodeY - enemyPos.getY(), nodeX - enemyPos.getX());
				e.setDirection(direction);

				// Shooting direction
				Position playerPos = player.getPart(Position.class);
				int playerPosX = (int) playerPos.getX();
				int playerPosY = (int) playerPos.getY();
				float shootingDirection = (float) Math.atan2(playerPosY - enemyPos.getY(), playerPosX - enemyPos.getX());
				e.setShootingDirection(shootingDirection);
			}
		});
	}

	// return a createGrid N-by-N boolean matrix
	public boolean[][] createGrid(int width, int height)
	{
		boolean[][] a = new boolean[height][width];
		for (int y = 0; y < height; y++)
		{
			for (int x = 0; x < width; x++)
			{
				a[y][x] = true;
			}
		}
		
		// TODO: needs refactoring to walls
//		int posX = (int) (Math.random() * width);
//		int posY = (int) (Math.random() * height);
//		int r = (int) (Math.random() * 250);
//
//		a[posY / gridDensity][posX / gridDensity] = false;
//		int tempR = r + gridDensity / 2;
//		for (int i = tempR; i > 0; i -= gridDensity)
//		{
//			for (int j = 0; j < i; j++)
//			{
//				double cos = Math.cos(Math.toRadians(360 * j / i));
//				double sin = Math.sin(Math.toRadians(360 * j / i));
//
//				try
//				{
//					a[(int) (posY + cos * i) / gridDensity][(int) (posX + sin * i) / gridDensity] = false;
//				}
//				catch (IndexOutOfBoundsException e)
//				{
//				}
//			}
//		}
		
		return a;
	}

	public void processAStar(MovableEntity enemy, MovableEntity player, boolean[][] randomlyGenMatrix)
	{
		ArrayList<Node> pathList = enemyPaths.get(enemy);

		Position playerPos = player.getPart(Position.class);
		int playerPosX = (int) playerPos.getX();
		int playerPosY = (int) playerPos.getY();

		Position enemyPos = enemy.getPart(Position.class);
		int enemyPosX = (int) enemyPos.getX();
		int enemyPosY = (int) enemyPos.getY();

		Stream<Node> filter = pathList.stream().
				filter(p ->
                                        (p.y == playerPosY / gridDensity && p.x == playerPosX / gridDensity) ||
                                        p.y == enemyPosY / gridDensity && p.x == enemyPosX / gridDensity);

		if (filter.count() != 2) // Does the player and the enemy still operate inside the path grid generated?
		{
			pathList.clear(); // Clear the list
			generateHValue(randomlyGenMatrix, playerPosY / gridDensity, playerPosX / gridDensity, enemyPosY / gridDensity, enemyPosX / gridDensity, pathList); // Generate new path
		}
	}

	public void generateHValue(boolean grid[][], int ay, int ax, int by, int bx, ArrayList<Node> pathList)
	{
		//Creation of a Node type 2D array
		Node[][] cell = new Node[grid.length][grid[0].length];

		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[0].length; x++)
			{
				cell[y][x] = new Node(x, y);
				//Checks whether a cell is Blocked or Not by checking the boolean value
				cell[y][x].hValue = grid[y][x] ? Math.abs(y - by) + Math.abs(x - bx) : -1;
			}
		}
		generatePath(ax, ay, bx, by, pathList, cell);
	}

	/**
	 * @param Ay Starting point's x value
	 * @param Ax Starting point's y value
	 * @param By Ending point's x value
	 * @param Bx Ending point's y value
	 * @param pathList
	 * @param cell
	 */
	public void generatePath(int Ax, int Ay, int Bx, int By, ArrayList<Node> pathList, Node[][] cell)
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
		openList.add(cell[Ay][Ax]);

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
			if (node == cell[By][Bx])
			{
				closedList.add(node);
				break;
			}

			closedList.add(node);

			for (int y = -1; y <= 1; y++)
			{
				for (int x = -1; x <= 1; x++)
				{
					int a;
					if (x == 0 && y == 0) // Center
					{
						continue;
					}
					else if (Math.abs(x) == Math.abs(y)) // Corner
					{
						a = 14;
						//continue;
					}
					else // Side
					{
						a = 10;
					}

					try
					{
						if (cell[node.y + y][node.x + x].hValue != -1
								&& !openList.contains(cell[node.y + y][node.x + x])
								&& !closedList.contains(cell[node.y + y][node.x + x]))
						{
							double tCost = node.fValue + a;
							double cost = cell[node.y + y][node.x + x].hValue + tCost;
							if (cell[node.y + y][node.x + x].fValue > cost || !openList.contains(cell[node.y + y][node.x + x]))
							{
								cell[node.y + y][node.x + x].fValue = cost;
							}

							openList.add(cell[node.y + y][node.x + x]);
							cell[node.y + y][node.x + x].parent = node;
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

		pathList.add(cell[Ay][Ax]);
	}
}
