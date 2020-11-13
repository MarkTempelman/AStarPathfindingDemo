package Logic;

public class Helper {
    public static String getIconFromTileType(TileType type){
        switch (type){
            case Start:
                return "s";
            case End:
                return "e";
            case Empty:
                return "o";
            case Wall:
                return "x";
        }
        return null;
    }
}
