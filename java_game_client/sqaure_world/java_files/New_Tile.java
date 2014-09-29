package sqaure_world.java_files;

//import java.awt.*;

import java.io.Serializable;

//import java.awt.image.*;
/**
 *
 * @author VH
 */
public class New_Tile  implements Serializable {

    private static final long serialVersionUID = 24563435L;
    public String name;

    	//protected ArrayList <Item> items = new ArrayList <Item> ();
	//protected ArrayList <Entity> entities = new ArrayList <Entity> ();
    
    public New_Tile() {
        
        name = "Empty";
        //this->isImpassable = my_isImpassable;
    }

    public New_Tile(String my_name) {
        
        name = my_name;
        //this->isImpassable = my_isImpassable;
    }

}
