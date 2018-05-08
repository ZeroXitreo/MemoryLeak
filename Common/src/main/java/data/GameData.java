package data;

public class GameData {

    private int displayWidth;
    private int displayHeight;
    private GameKeys gameKeys = new GameKeys();
    private int moveAreaWidthMin;
    private int moveAreaWidthMax;
    private int moveAreaHeightMin;
    private int moveAreaHeightMax;

    /**
     * Sets the width of the display screen.
     * @param width int of the screen witdh.
     */
    public void setDisplayWidth(int width) {
        this.displayWidth = width;
    }

    /**
     * Gets the witdh of the display screen.
     * @return int of the screen witdh.
     */
    public int getDisplayWidth() {
        return displayWidth;
    }

    /**
     * Gets the GameKeys.
     * @return GameKeys holds the values of the keys used in the game.
     */
    public GameKeys getKeys() {
        return gameKeys;
    }

    /**
     * @return the displayHeight
     */
    public int getDisplayHeight() {
        return displayHeight;
    }

    /**
     * @param displayHeight the displayHeight to set
     */
    public void setDisplayHeight(int displayHeight) {
        this.displayHeight = displayHeight;
    }

    /**
     * @return int minimum width to be.
     */
    public int getMoveAreaWidthMin() {
        return moveAreaWidthMin;
    }

    /**
     * @return int maximum width to be.
     */
    public int getMoveAreaWidthMax() {
        return moveAreaWidthMax;
    }

    /**
     * @return int minimum height to be.
     */
    public int getMoveAreaHeightMin() {
        return moveAreaHeightMin;
    }

    /**
     * @return int maximum height to be.
     */
    public int getMoveAreaHeightMax() {
        return moveAreaHeightMax;
    }

    /**
     * @param moveAreaWidthMin int minimum width to be.
     */
    public void setMoveAreaWidthMin(int moveAreaWidthMin) {
        this.moveAreaWidthMin = moveAreaWidthMin;
    }

    /**
     * @param moveAreaWidthMax int maximum width to be.
     */
    public void setMoveAreaWidthMax(int moveAreaWidthMax) {
        this.moveAreaWidthMax = moveAreaWidthMax;
    }

    /**
     * @param moveAreaHeightMin int minimum height to be.
     */
    public void setMoveAreaHeightMin(int moveAreaHeightMin) {
        this.moveAreaHeightMin = moveAreaHeightMin;
    }

    /**
     * @param moveAreaHeightMax int maximum height to be.
     */
    public void setMoveAreaHeightMax(int moveAreaHeightMax) {
        this.moveAreaHeightMax = moveAreaHeightMax;
    }

}
