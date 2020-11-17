package Logic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Pathfinder {
    private ArrayList<Tile> openTiles = new ArrayList<>();
    private ArrayList<Tile> closedTiles = new ArrayList<>();
    private ArrayList<Tile> allTiles = new ArrayList<>();
    private ArrayList<Tile> path = new ArrayList<>();
    private int endX;
    private int endY;

    //stop using 2d array remove it everywhere and replace with a list or a list of lists.
    public ArrayList<Tile> generatePath(ArrayList<Tile> tiles){
        allTiles = tiles;
        if(!tilesAreValid()){
            return null;
        }
        initializeOpenList();
        setEndCoordinates();

        while(openTiles.size() > 0){

            Tile currentTile = openTiles.stream().min(Comparator.comparing(Tile::getTotalCost)).orElse(null);

            if(currentTile.getType() == TileType.End){
                break;
            }

            openTiles.remove(currentTile);
            closedTiles.add(currentTile);

            ArrayList<Tile> neighbouringTiles = getNeighbouringTiles(currentTile);

            for (Tile neighbour: neighbouringTiles) {
                int cost = currentTile.getTotalCost() + 1;
                if(neighbour.getType() == TileType.Wall){
                    continue;
                }
                removeTileFromOpenTilesIfNecessary(neighbour, cost);
                removeTileFromClosedTilesIfNecessary(neighbour, cost);
                tryAddNeighbourToOpenTiles(neighbour, cost, currentTile);
            }
        }

        addEndToPath();
        addOthersToPath();
        return path;
    }

    private void removeTileFromOpenTilesIfNecessary(Tile neighbour, int cost){
        Tile otherTile = openTiles.stream().filter(t -> t.equals(neighbour)).findFirst().orElse(null);
        if(otherTile != null && otherTile.getDistanceFromStart() > cost){
            openTiles.remove(otherTile);
        }
    }

    private void removeTileFromClosedTilesIfNecessary(Tile neighbour, int cost){
        Tile otherTile = closedTiles.stream().filter(t -> t.equals(neighbour)).findFirst().orElse(null);
        if(otherTile != null && otherTile.getDistanceFromStart() > cost){
            closedTiles.remove(otherTile);
        }
    }

    private void tryAddNeighbourToOpenTiles(Tile neighbour, int cost, Tile currentTile){
        if(!openTiles.stream().anyMatch(t -> t.equals(neighbour)) && !closedTiles.stream().anyMatch(t -> t.equals(neighbour))){
            setNeighbourVariables(neighbour, cost, currentTile);
            openTiles.add(neighbour);
        }
    }

    private void setNeighbourVariables(Tile neighbour, int cost, Tile parent){
        neighbour.setDistanceFromStart(cost);
        neighbour.setTotalCost(cost + getDistanceToEnd(neighbour));
        neighbour.setParent(parent);
    }

    private void addEndToPath(){
        path.add(openTiles.stream().filter(tile -> tile.getType() == TileType.End).findFirst().orElse(null));
    }

    private void addOthersToPath(){
        Tile tileToAdd = path.get(path.size() - 1).getParent();
        path.add(tileToAdd);
        if(tileToAdd.getParent() != null){
            addOthersToPath();
        }
    }

    private boolean tilesAreValid(){
        return allTiles.stream().filter(tile -> tile.getType() == TileType.Start).collect(Collectors.toList()).toArray().length == 1 &&
                allTiles.stream().filter(tile -> tile.getType() == TileType.End).collect(Collectors.toList()).toArray().length == 1;

    }

    private Tile getStartNode(){
        return allTiles.stream().filter(tile -> tile.getType() == TileType.Start).findFirst().orElse(null);
    }

    private void initializeOpenList(){
        Tile tile = getStartNode();
        tile.setTotalCost(0);
        openTiles.add(tile);
    }

    private void setEndCoordinates(){
        Tile end = allTiles.stream().filter(tile -> tile.getType() == TileType.End).findFirst().orElse(null);
        endX = end.getXPos();
        endY = end.getYPos();
    }

    private ArrayList<Tile> getNeighbouringTiles(Tile tile){
        ArrayList<Tile> tiles = new ArrayList<>();
        addNeighbour(tiles, tile, 1, 0);
        addNeighbour(tiles, tile, -1, 0);
        addNeighbour(tiles, tile, 0, 1);
        addNeighbour(tiles, tile, 0, -1);
        return tiles;
    }

    private void addNeighbour(ArrayList<Tile> tiles, Tile tile, int xModifier, int yModifier){
        Tile currentTile = allTiles.stream().filter(t -> t.getXPos() == tile.getXPos() + xModifier && t.getYPos() == tile.getYPos() + yModifier).findFirst().orElse(null);
        if(currentTile != null){
            tiles.add(currentTile);
        }
    }

    private int getDistanceToEnd(Tile currentTile){
        return Math.abs(currentTile.getXPos() - endX) + Math.abs(currentTile.getYPos() - endY);
    }
}
