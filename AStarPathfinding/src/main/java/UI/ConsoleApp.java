package UI;

import Logic.Helper;
import Logic.Map;
import Logic.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class ConsoleApp {
    private static Map map = new Map(5,3);
    public static void main(String[] args) {
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
