package Logic;

public class Tile {
    private TileType type;
    private int xPos;
    private int yPos;

    public Tile(TileType type, int x, int y) {
        this.type = type;
        xPos = x;
        yPos = y;
    }

    public TileType getType() {
        return type;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
