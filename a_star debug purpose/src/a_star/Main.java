package a_star;

import a_star.stdlib.StdRandom;
import a_star.stdlib.StdDraw;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Main
{
	static int width = 960;
	static int height = 540;
	static int gridDensity = 16 / 2;
	static int enemiesAmount = 1;
	static boolean playerMoveOnHit = true; // Debugging purpose only
	static boolean enemyPerfectMove = true; // Debugging purpose only
	static boolean moveDiagonal = true; // Debugging purpose only

	static ArrayList<Entity> enemies = new ArrayList();
	static HashMap<Entity, ArrayList<Node>> enemyPaths = new HashMap();

	static Player player;
	static boolean[][] randomlyGenMatrix;

	/**
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		setScreen();
		randomlyGenMatrix = createGrid((int) Math.ceil((double) height / gridDensity), (int) Math.ceil((double) width / gridDensity), 0.9);

		//renderWalls();
		// Add player and enemies
		player = new Player(width, height);
		for (int i = 0; i < enemiesAmount; i++)
		{
			Enemy enemy = new Enemy(width, height);
			enemies.add(enemy);
			enemyPaths.put(enemy, new ArrayList());
		}

		drawGrid(randomlyGenMatrix);

		player.draw();
		while (true)
		{
			enemies.stream().parallel().forEach(e ->
			{
				processAStar(e, player, randomlyGenMatrix);
				ArrayList<Node> pathList = enemyPaths.get(e);

				Optional<Node> matchingObject = pathList.stream().
						filter(p -> p.x == e.y / gridDensity && p.y == e.x / gridDensity).
						findFirst();
				Node cell = matchingObject.orElse(null);

				// For movement and beyond (And drawingz)
				if (cell != null && pathList.size() >= pathList.indexOf(cell) + 3)
				{
					Node nextNode = pathList.get(pathList.indexOf(cell) + 1);
					if (enemyPerfectMove)
					{
						e.x = nextNode.y * gridDensity + gridDensity / 2;
						e.y = nextNode.x * gridDensity + gridDensity / 2;
					}
					else
					{
						e.moveTowards(nextNode.y * gridDensity + gridDensity / 2, nextNode.x * gridDensity + gridDensity / 2);
					}

					e.draw();
				}
				else if (cell != null && playerMoveOnHit)
				{
					player = new Player(width, height);
					player.draw();
				}
			});
		}
	}

	private static void renderWalls()
	{
		int posX = (int) (Math.random() * width);
		int posY = (int) (Math.random() * height);
		int r = (int) (Math.random() * 250);

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

	private static void setScreen()
	{
		// Draw defaults
		StdDraw.setCanvasSize(width, height);
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(height, 0);
	}

	private static void drawGrid(boolean[][] a)
	{
		StdDraw.setPenColor(StdDraw.BLACK);
		for (int i = 0; i < a.length; i++)
		{
			for (int j = 0; j < a[i].length; j++)
			{
				if (a[i][j] != true)
				{
					StdDraw.filledSquare(j * gridDensity + gridDensity / 2, i * gridDensity + gridDensity / 2, gridDensity / 2);
				}
			}
		}
	}

	/**
	 *
	 * @param y
	 * @param x
	 * @param p
	 * @return a createGrid N-by-N boolean matrix
	 */
	public static boolean[][] createGrid(int y, int x, double p)
	{
		boolean[][] a = new boolean[y][x];
		for (int i = 0; i < y; i++)
		{
			for (int j = 0; j < x; j++)
			{
				a[i][j] = StdRandom.bernoulli(p);
				//a[i][j] = true;
			}
		}
		return a;
	}

	/**
	 *
	 * @param enemy
	 * @param player
	 * @param randomlyGenMatrix
	 */
	public static void processAStar(Entity enemy, Entity player, boolean[][] randomlyGenMatrix)
	{
		ArrayList<Node> pathList = enemyPaths.get(enemy);
		Stream<Node> filter = pathList.stream().
				filter(p -> (p.x == player.y / gridDensity && p.y == player.x / gridDensity) || p.x == enemy.y / gridDensity && p.y == enemy.x / gridDensity);

		if (filter.count() != 2) // Has the player or the enemy moved from the original path?
		{
			pathList.clear();
			generateHValue(randomlyGenMatrix, player.y / gridDensity, player.x / gridDensity, enemy.y / gridDensity, enemy.x / gridDensity, pathList);
		}
	}

	/**
	 * @param grid The boolean grid
	 * @param ax
	 * @param ay
	 * @param bx Ending point's x value
	 * @param by Ending point's y value
	 * @param pathList
	 */
	public static void generateHValue(boolean grid[][], int ax, int ay, int bx, int by, ArrayList<Node> pathList)
	{
		//Creation of a Node type 2D array
		Node[][] cell = new Node[grid.length][grid[0].length];

		for (int x = 0; x < grid.length; x++)
		{
			for (int y = 0; y < grid[0].length; y++)
			{
				//Creating a new Node object for each and every Cell of the Grid (Matrix)
				cell[x][y] = new Node(x, y);
				//Checks whether a cell is Blocked or Not by checking the boolean value
				cell[x][y].hValue = grid[x][y] ? Math.abs(x - bx) + Math.abs(y - by) : -1;
			}
		}
		generatePath(ax, ay, bx, by, pathList, cell);
	}

	/**
	 * @param Ai Starting point's y value
	 * @param Aj Starting point's x value
	 * @param Bi Ending point's y value
	 * @param Bj Ending point's x value
	 * @param pathList
	 * @param cell
	 */
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
					int a;
					if (x == 0 && y == 0) // Center
					{
						continue;
					}
					else if (Math.abs(x) == Math.abs(y)) // Corner
					{
						a = 14;
						if (!moveDiagonal)
						{
							continue;
						}
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
