package Logic;

import java.util.ArrayList;

public class Map {
    private int width;
    private int height;
    private ArrayList<Tile> tiles = new ArrayList<>();

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        fillMapWithWalls();
    }

    private void fillMapWithWalls(){
        for (int y = 0; y < height; y++){
            for(int x = 0; x < width; x++){
                tiles.add(new Tile(TileType.Wall, x, y));
            }
        }
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
