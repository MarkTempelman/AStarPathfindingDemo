package Logic;

public class Map {
    Tile[][] tiles;

    public Map(int width, int height) {
        tiles = new Tile[height][width];
        fillMapWithWalls();
    }

    private void fillMapWithWalls(){
        int rowNr = 0;
        for(Tile[] row : tiles){
            int tileNr = 0;
            for(Tile tile : row){
                tiles[rowNr][tileNr] = new Tile(TileType.Wall);
                tileNr++;
            }
            rowNr++;
        }
    }

    public String[][] getDisplayMap(){
        int height = tiles.length;
        int width = tiles[0].length;
        String[][] results = new String[height][width];
        int rowNr = 0;
        for(Tile[] row : tiles){
            int tileNr = 0;
            for(Tile tile : row){
                results[rowNr][tileNr] = Helper.getIconFromTileType(tiles[rowNr][tileNr].getType());
                tileNr++;
            }
            rowNr++;
        }

        return results;
    }
}
