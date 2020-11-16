package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Pathfinder {
    private ArrayList<Tile> openTiles = new ArrayList<>();
    private ArrayList<Tile> closedTiles = new ArrayList<>();

    //stop using 2d array remove it everywhere and replace with a list or a list of lists.
    public ArrayList<Tile> generatePath(ArrayList<Tile> tiles){
        if(!tilesAreValid(tiles)){
            return null;
        }
        return tiles;
    }

    private boolean tilesAreValid(ArrayList<Tile> tiles){
        return tiles.stream().filter(tile -> tile.getType() == TileType.Start).collect(Collectors.toList()).toArray().length == 1 &&
                tiles.stream().filter(tile -> tile.getType() == TileType.End).collect(Collectors.toList()).toArray().length == 1;
    }
}
