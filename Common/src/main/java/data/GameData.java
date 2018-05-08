package data;

public class GameData
{
	private int displayWidth;
	private int displayHeight;
	private GameKeys gameKeys = new GameKeys();
	private int moveAreaWidthMin;
        private int moveAreaWidthMax;
        private int moveAreaHeightMin;
        private int moveAreaHeightMax;
	
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

    public int getMoveAreaWidthMin() {
        return moveAreaWidthMin;
    }

    public int getMoveAreaWidthMax() {
        return moveAreaWidthMax;
    }

    public int getMoveAreaHeightMin() {
        return moveAreaHeightMin;
    }

    public int getMoveAreaHeightMax() {
        return moveAreaHeightMax;
    }

    public void setMoveAreaWidthMin(int moveAreaWidthMin) {
        this.moveAreaWidthMin = moveAreaWidthMin;
    }

    public void setMoveAreaWidthMax(int moveAreaWidthMax) {
        this.moveAreaWidthMax = moveAreaWidthMax;
    }

    public void setMoveAreaHeightMin(int moveAreaHeightMin) {
        this.moveAreaHeightMin = moveAreaHeightMin;
    }

    public void setMoveAreaHeightMax(int moveAreaHeightMax) {
        this.moveAreaHeightMax = moveAreaHeightMax;
    }
    
    
}
