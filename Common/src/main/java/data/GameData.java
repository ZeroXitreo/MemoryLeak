package data;



/**
 *
 * @author Jorge Báez Garrido
 */
public class GameData
{
	private int displayWidth;
	private int displayHeight;
	private GameKeys gameKeys = new GameKeys();
	
	
	public void setDisplayWidth(int width)
	{
		this.displayWidth = width;
	}

	public int getDisplayWidth()
	{
		return displayWidth;
	}

	public GameKeys getKeys()
	{
		return gameKeys;
	}

	/**
	 * @return the displayHeight
	 */
	public int getDisplayHeight()
	{
		return displayHeight;
	}

	/**
	 * @param displayHeight the displayHeight to set
	 */
	public void setDisplayHeight(int displayHeight)
	{
		this.displayHeight = displayHeight;
	}
}
