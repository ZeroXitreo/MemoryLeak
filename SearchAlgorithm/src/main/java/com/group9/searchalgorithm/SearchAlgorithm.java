package com.group9.searchalgorithm;

import com.group9.commonplayer.Player;
import data.GameData;
import data.ImmovableEntity;
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
	private int width;
	private int height;
	private int gridDensity = 16;

	private static ArrayList<MovableEntity> enemies = new ArrayList();
	private static HashMap<MovableEntity, ArrayList<Node>> enemyPaths = new HashMap();

	private MovableEntity player;
	private boolean[][] randomlyGenMatrix;

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
			generateWalls(world);
		}

		for (MovableEntity player : world.getGameMovableEntities(Player.class))
		{
			this.player = player;
			for (MovableEntity enemy : world.getGameMovableEntities())
			{
				if (!enemy.getType().equalsEnemy())
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

	/**
	 * Processes the search algorithm
	 */
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
					filter(p -> p.getY() == enemyPosY / gridDensity && p.getX() == enemyPosX / gridDensity).
					findFirst();
			Node cell = matchingObject.orElse(null);

			// Direction
			if (cell != null && pathList.size() >= pathList.indexOf(cell) + 3)
			{
				Node nextNode = pathList.get(pathList.indexOf(cell) + 1);

				// Move direction
				float nodeX = nextNode.getX() * gridDensity + gridDensity / 2;
				float nodeY = nextNode.getY() * gridDensity + gridDensity / 2;
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

	/**
	 * Generates a grid which allows path finding to be done in a grid based manner
	 * 
	 * @param width of the map the grid is to be generated on
	 * @param height of the map the grid is to be generated on
	 * @return boolean matrix
	 */
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

		return a;
	}

	/**
	 * Generates the walls in the grid based system, allowing the pathing to detect immovable walls
	 * 
	 * @param world the world which the walls are in
	 */
	private void generateWalls(World world)
	{
		for (ImmovableEntity wall : world.getGameImmovableEntities())
		{
			Position pos = wall.getPart(Position.class);
			int posX = (int) (pos.getX());
			int posY = (int) (pos.getY());
			int r = (int) wall.getRadius();

			randomlyGenMatrix[posY / gridDensity][posX / gridDensity] = false;
			int tempR = r + gridDensity / 2;
			for (int i = tempR; i > 0; i -= gridDensity)
			{
				for (int j = 0; j < i; j++)
				{
					double cos = Math.cos(Math.toRadians(360 * j / i));
					double sin = Math.sin(Math.toRadians(360 * j / i));

					try
					{
						randomlyGenMatrix[(int) (posY + cos * i) / gridDensity][(int) (posX + sin * i) / gridDensity] = false;
					}
					catch (IndexOutOfBoundsException e)
					{
					}
				}
			}
		}
	}

	/**
	 * Processes the AStar algorithm which allows a path to be generated for an enemy
	 * 
	 * @param enemy A MovableEntity which needs to move towards player
	 * @param player A MovableEntity which the enemy moves towards
	 * @param randomlyGenMatrix The grid which has the data of where walls exist
	 */
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
				filter(p
						-> (p.getY() == playerPosY / gridDensity && p.getX() == playerPosX / gridDensity)
				|| p.getY() == enemyPosY / gridDensity && p.getX() == enemyPosX / gridDensity);

		if (filter.count() != 2) // Does the player and the enemy still operate inside the path grid generated?
		{
			pathList.clear(); // Clear the list
			generateHValue(randomlyGenMatrix, playerPosY / gridDensity, playerPosX / gridDensity, enemyPosY / gridDensity, enemyPosX / gridDensity, pathList); // Generate new path
		}
	}

	/**
	 * Generates the HValue of the grid which
	 *
	 * @param grid parses the grid which will be generated HValues from
	 * @param startY Starting point's x value
	 * @param startX Starting point's y value
	 * @param endY Ending point's x value
	 * @param endX Ending point's y value
	 * @param pathList to be parsed to another method, allowing the path to be
	 * generated and stores in pathList
	 */
	public void generateHValue(boolean[][] grid, int startY, int startX, int endY, int endX, ArrayList<Node> pathList)
	{
		//Creation of a Node type 2D array
		Node[][] cell = new Node[grid.length][grid[0].length];

		for (int y = 0; y < grid.length; y++)
		{
			for (int x = 0; x < grid[0].length; x++)
			{
				cell[y][x] = new Node(x, y);
				//Checks whether a cell is Blocked or Not by checking the boolean value
				cell[y][x].setHValue(grid[y][x] ? Math.abs(y - endY) + Math.abs(x - endX) : -1);
			}
		}
		generatePath(startX, startY, endX, endY, pathList, cell);
	}

	/**
	 * Generate the path for the given pathList
	 *
	 * @param startY Starting point's x value
	 * @param startX Starting point's y value
	 * @param endY Ending point's x value
	 * @param endX Ending point's y value
	 * @param pathList The pathlist which is to be filled
	 * @param cell the cells which are to be searched in order to generate a
	 * path
	 */
	public void generatePath(int startX, int startY, int endX, int endY, ArrayList<Node> pathList, Node[][] cell)
	{
		ArrayList<Node> closedList = new ArrayList();

		//Creation of a PriorityQueue and the declaration of the Comparator
		PriorityQueue<Node> openList = new PriorityQueue(1, new Comparator()
		{
			@Override
			//Compares 2 Node objects stored in the PriorityQueue and Reorders the Queue according to the object which has the lowest fValue
			public int compare(Object cell1, Object cell2)
			{
				return ((Node) cell1).getFValue() < ((Node) cell2).getFValue() ? -1 : ((Node) cell1).getFValue() > ((Node) cell2).getFValue() ? 1 : 0;
			}
		});

		//Adds the Starting cell inside the openList
		openList.add(cell[startY][startX]);

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
			if (node == cell[endY][endX])
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
						if (cell[node.getY() + y][node.getX() + x].getHValue() != -1
								&& !openList.contains(cell[node.getY() + y][node.getX() + x])
								&& !closedList.contains(cell[node.getY() + y][node.getX() + x]))
						{
							double tCost = node.getFValue() + a;
							double cost = cell[node.getY() + y][node.getX() + x].getHValue() + tCost;
							if (cell[node.getY() + y][node.getX() + x].getFValue() > cost || !openList.contains(cell[node.getY() + y][node.getX() + x]))
							{
								cell[node.getY() + y][node.getX() + x].setFValue(cost);
							}

							openList.add(cell[node.getY() + y][node.getX() + x]);
							cell[node.getY() + y][node.getX() + x].setParent(node);
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
		while (endNode.getParent() != null)
		{
			Node currentNode = endNode;
			pathList.add(currentNode);
			endNode = endNode.getParent();
		}

		pathList.add(cell[startY][startX]);
	}
}
