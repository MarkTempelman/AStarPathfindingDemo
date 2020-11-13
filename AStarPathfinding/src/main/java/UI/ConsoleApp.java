package UI;

import Logic.Map;

import java.util.Arrays;

public class ConsoleApp {
    private static Map map = new Map(5,5);
    public static void main(String[] args) {

        for(String[] row : map.getDisplayMap()){
            System.out.println(Arrays.toString(row));
        }
    }
}
