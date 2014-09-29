package sqaure_world.java_files;

import java.io.Serializable;

/**
 *
 * @author JMR
 */
public class Tile_View_Array implements Serializable {

    private static final long serialVersionUID = 20023435L;

    public final String target;
    public final New_Tile[][] tilesInView;


    //x and y refers to center of view with respect to whole map
    public Tile_View_Array(String s, New_Tile[][] t) {

        target = s;
        tilesInView = t;

    }
}
