package UI;

import Logic.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleApp {
    private static Map map = new Map(5,5);
    private static Pathfinder pathfinder = new Pathfinder();
    public static void main(String[] args) {
        map.setTile(0, 0, TileType.Start);
        map.setTile(4, 4, TileType.End);
        map.setTile(0, 1, TileType.Wall);
        map.setTile(1, 1, TileType.Wall);
        map.setTile(3, 0, TileType.Wall);
        map.setTile(3, 1, TileType.Wall);
        ArrayList<Tile> tiles = map.getTiles();
        System.out.println("Map:");
        printTiles(tiles, map.getWidth(), map.getHeight());
        for (Tile tile: pathfinder.generatePath(tiles)) {
            map.setTile(tile.getXPos(), tile.getYPos(), TileType.Path);
        }
        System.out.println("Path:");
        printTiles(map.getTiles(), map.getWidth(), map.getHeight());
    }

    private static void printTiles(ArrayList<Tile> tiles, int width, int height){
        for(int y = 0; y < height; y++){
            int finalY = y;
            ArrayList<Tile> row = (ArrayList<Tile>) tiles.stream().filter(tile -> tile.getYPos() == finalY).collect(Collectors.toList());
            String rowString = "|";
            for( int x = 0; x < width; x++){
                int finalX = x;
                rowString += Helper.getIconFromTileType(row.stream().filter(tile -> tile.getXPos() == finalX).findFirst().get().getType());
                rowString += "|";
            }
            System.out.println(rowString);
        }
    }
}
