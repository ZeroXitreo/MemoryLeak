package a_star;

public class Node
{

	int x;
	int y;
	double hValue;
	double fValue;
	Node parent;

	public Node(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
}
