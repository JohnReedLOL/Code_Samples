package sqaure_world.java_files;

import java.io.Serializable;

/**
 *
 * @author JMR
 */
public class New_Player_View implements Serializable {

    protected static final long serialVersionUID = 10009935L;

    String target;
    New_Tile[][] tilesInView = new New_Tile[9][5];

    //x and y refers to center of view with respect to whole map
    public New_Player_View(String my_target, int x, int y) {

        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 5; ++j) {
                tilesInView[i][j] = new New_Tile("Uninitialized");
            }
        }

        target = my_target;
        for (int xi = -4; xi <= 4; ++xi) {
            for (int yi = -2; yi <= 2; ++yi) {
                ////System.out.println(tilesInView[xi + 4][yi + 2].name);
                ////System.out.println(New_World_Map.world[x + xi][y + yi].name);
                tilesInView[xi + 4][yi + 2].name = New_World_Map.world[x + xi][y + yi].name;
            }
        }
    }
}
