package Logic;

public class Tile {
    private TileType type;
    private int xPos;
    private int yPos;
    private int totalCost;
    private int distanceFromStart;
    private Tile parent;

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

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public void setDistanceFromStart(int distanceFromStart) {
        this.distanceFromStart = distanceFromStart;
    }

    public Tile getParent() {
        return parent;
    }

    public void setParent(Tile parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o){
        if(o == this){
            return true;
        }

        if(!(o instanceof Tile)){
            return false;
        }

        Tile t = (Tile) o;
        return xPos == t.xPos && yPos == t.yPos;
    }
}
