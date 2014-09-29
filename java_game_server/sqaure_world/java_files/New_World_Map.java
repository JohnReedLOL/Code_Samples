package sqaure_world.java_files;

import sqaure_world.java_files.New_Tile;

//import java.awt.*;
//import java.awt.image.*;
/**
 *
 * @author VH
 */
public class New_World_Map {

    public static final int xWorldSize = 20;
    public static final int yWorldSize = 12;

    public static New_Tile[][] world = new New_Tile[xWorldSize][yWorldSize]; //
    public static New_World_Map map = new New_World_Map();

    public New_World_Map() {
        generateTiles();
    }

    private void generateTiles() {
        //Inside of the world ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
        //Just list image names / attributes

        for (int i = 0; i < xWorldSize; ++i) {
            for (int j = 6; j < yWorldSize; ++j) {
                world[i][j] = new New_Tile("Forest");
            }
        }

        for (int i = 0; i < xWorldSize; ++i) {
            world[i][5] = new New_Tile("Dirt");
        }

        for (int i = 0; i < xWorldSize; ++i) {
            world[i][4] = new New_Tile("Just_Grass");
        }

        world[10][5].name = "Generic_Person";

        world[13][5].name = "Gold_Key";
        world[13][4].name = "Locked_Treasure_Box";

        for (int i = 0; i < xWorldSize; ++i) {
            for (int j = 0; j <= 3; ++j) {
                world[i][j] = new New_Tile("Waves");
            }
        }

        for (int i = 0; i < xWorldSize; ++i) {
            world[i][2] = new New_Tile("Wall");
        }

        for (int i = 0; i < xWorldSize; ++i) {
            world[i][8] = new New_Tile("Wall");
        }

        for (int j = 0; j < yWorldSize; ++j) {
            world[14][j] = new New_Tile("Wall");
        }

        for (int j = 0; j < yWorldSize; ++j) {
            world[4][j] = new New_Tile("Wall");
        }

        // Edge of the world ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
    }
}
