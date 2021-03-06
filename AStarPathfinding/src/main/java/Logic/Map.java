package Logic;

import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        fillMapWithEmpty();
    }

    private void fillMapWithEmpty(){
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles.add(new Tile(TileType.Empty, x, y));
            }
        }
    }

    public void setTile(int x, int y, TileType type){
        Tile currentTileOnCoordinates = tiles.stream().filter(tile -> tile.getYPos() == y && tile.getXPos() == x).findFirst().orElse(null);
        if(currentTileOnCoordinates != null){
            tiles.remove(currentTileOnCoordinates);
        }
        tiles.add(new Tile(type, x, y));
    }

    public ArrayList<Tile> getTiles(){
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
