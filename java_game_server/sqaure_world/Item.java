/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqaure_world;

/**
 *
 * @author VH
 */
public class Item extends TileComponent
{
	private static final long serialVersionUID = 100;
	
	/**
	 * The Item specific details
	 */
	protected String description;
	
	
	/**
	 * The constructors
	 */
	public Item (String name)
	{
		super (name);
	}
	
	public Item (String name, String description)
	{
		super (name);
                setDescription(description);
	}
	
	public Item (Item sourceObject) // This is a copy constructor. I needed it to deepcopy a Item object (in Tile.java).
	{
		super (sourceObject.getName ());
                setCustomDescription(sourceObject.description);
		// More needed
	}
	
	/**
	 * The getters
	 */
	
	// More needed
	
	/**
	 * The setters
	 */

	// More needed
}