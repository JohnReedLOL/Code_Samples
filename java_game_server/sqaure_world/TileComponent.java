/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqaure_world;
import java.io.Serializable;
/**
 *
 * @author VH
 */
public class TileComponent implements Serializable
{
	protected static final long serialVersionUID = 100;
	protected String name;
	protected String fileName;
        protected String description;
	
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	public TileComponent (String name)
	{
		this.name = name;
		this.fileName = nameToPathString(name);
                generateAutomaticDescription();
	}
	
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The getters
	 */
	public long getSerialVersionUID () {return serialVersionUID;}
	public String getName () {return name;}
	public String getFileName () {return fileName;}
	public String nameToPathString (String name)
	{
		return "/sqaure_world/images/" + name + ".png"; // May need some tweaking. Yes the "au" of sqaure is right. Alternatively you can try "src/sqaure_world/". Or just "". Or "src/". Or etc.
	}
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The setters
         */
        protected String generateAutomaticDescription() {return "This is a " + name.toLowerCase() + ".";}
        public void setDescription (String description) {this.description = generateAutomaticDescription() + "\n" + description;} // This methof will append your description to the standard "This is a" line.
	public void setCustomDescription (String description) {this.description = description;} // Removes the standard description and replaces it with your own. Please only use for rare/special occasions, otherwise the tiles will not look consistent.
}