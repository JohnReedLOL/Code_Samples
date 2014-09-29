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
import java.io.*;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

class Tile implements Serializable
{
	protected static final long serialVersionUID = 100;
	
	protected Terrain terrain = null;
	protected ArrayList <Item> items = new ArrayList <Item> ();
	protected ArrayList <Entity> entities = new ArrayList <Entity> ();
	
	/**
	 * The constructors
	 */
	public Tile (Terrain terrain)
	{
		setTerrain(terrain);
	}
	public Tile (Terrain terrain, ArrayList <Item> inputItems, ArrayList <Entity> inputEntities)
	{
		setTerrain(terrain);
		for (int i = 0; i < inputItems.size(); i++)
			addItem (inputItems.get(i));
		for (int i = 0; i < inputEntities.size(); i++)
			addEntity (inputEntities.get(i));
	}
	public Tile (Tile sourceTile) // The copy constructor. Prevents the shallow copy issue.
	{
		setTerrain(new Terrain (sourceTile.getTerrain()));
		for (int i = 0; i < sourceTile.getItems_ALL().size(); i++)
			addItem (new Item(sourceTile.getItem(i)));
		for (int i = 0; i < sourceTile.getEntities_ALL().size(); i++)
			addEntity (new Entity (sourceTile.getEntity(i)));
	}
	
	
	
	
	
	
	
	/**
	 * The supporting method(s)
	 */
	private String getFileNameFromTyleComponent (TileComponent TC)
	{
		return TC.getFileName();
	}
	private ImageIcon getScaledImage (String fileName)
	{
		return new javax.swing.ImageIcon( getClass().getResource(fileName));
	}
	
	
	
	
	
	
	
	/**
	 * The getters
	 */
	
	public String getTileImagePath ()
	{
		String imagePath;
		
		// Entities have the priority
		if (entities.size()>0)		imagePath = entities.get(entities.size()-1).getFileName();
 		
		// then the items
		else if (items.size()>0)	imagePath = items.get(items.size()-1).getFileName();
		
		// and finally just the terrain
 		else 						imagePath = terrain.getFileName();
 		
		return imagePath;
	}
	
	
	// Get terrain methods
	public Terrain getTerrain ()
	{
		return terrain;
	}
	// Get items methods
	public ArrayList <Item> getItems_ALL () // Returns the whole ArrayList.
	{
		return items;
	}
	public Item getItem (int index) // Return a single item at the given index.
	{
		return items.get(index);
	}
	public Item getItem (String itemName)
	{
		for (int i=0; i < items.size(); i++)
			if ((items.get(i).getName()).compareToIgnoreCase(itemName)==0)
				return items.get(i);
		throw new IndexOutOfBoundsException("the item " + itemName.toLowerCase() + " is not on this tile.");
	}
        public Item getItem_LastAdded () // Return the last added item.
	{
		return items.get(items.size()-1);
	}
        
        
        
	// Get entities methods
	public ArrayList <Entity> getEntities_ALL () // Returns the whole ArrayList.
	{
		return entities;
	}
	public Entity getEntity (int index) // Returns a single entity at the give index.
	{
		return entities.get(index);
	}
	public Entity getEntity (String entityName)
	{
		for (int i=0; i < entities.size(); i++)
			if ((entities.get(i).getName()).compareToIgnoreCase(entityName)==0)
				return entities.get(i);
		throw new IndexOutOfBoundsException("the item " + entityName.toLowerCase() + " is not on this tile.");
	}
	public Entity getEntity_LastAdded () // Returns the last added entity.
	{
		return entities.get(entities.size()-1);
	}
	
	
	
	
	/**
	 * The setters
	 */
	// Terrain methods
	protected void setTerrain (Terrain newTerrain)
	{
		changeTerrain (newTerrain); // Simply calls the changeTerrain method.
	}
	public void changeTerrain (Terrain newTerrain)
	{
		terrain = newTerrain;
	}
	
	
	
	
	
	
	// Item methods
	// Add item
	public void addItem (Item newItem)
	{
		items.add(newItem);
	}
	// Remove item
	public void removeItem (int index)
	{
		items.remove(index);
	}
	public void removeItem (String itemName)
	{
		for (int i=0; i < items.size(); i++)
		{
			if ((items.get(i).getName()).compareToIgnoreCase(itemName)==0)
			{
				items.remove(i);
				break; // Ensures that only one item is removed.
			}
		}
	}
	public void removeItem_LastAdded ()
	{
		items.remove(items.size()-1);
	}
	public void removeItem_ALL ()
	{
		items = new ArrayList <Item> (); // I think this is fine because Java handles garbage collection. But in C++ we would need a delete.
	}
	public void removeItem_ALL (String itemName) // All with the given name.
	{
		for (int i=items.size()-1; i >= 0; i--) // Must be --, or will cause repated off by one bugs.
		{
			if ((items.get(i).getName()).compareToIgnoreCase(itemName)==0)
			{
				items.remove(i);
				// No break statement so that all items of this name are removed.
			}
		}
	}
	
	
	
	
	
	// Entity methods
	// Add entity
	public void addEntity (Entity newEntity)
	{
		entities.add(newEntity);
	}
	// Remove entity
	public void removeEntity (int index)
	{
		entities.remove(index);
	}
	public void removeEntity (String entityName)
	{
		for (int i=0; i < entities.size(); i++)
		{
			if ((entities.get(i).getName()).compareToIgnoreCase(entityName)==0)
			{
				entities.remove(i);
				break; // Ensures that only one entity is removed.
			}
		}
	}
	
	public void removeEntity_LastAdded ()
	{
		entities.remove(entities.size()-1);
	}
	public void removeEntity_ALL ()
	{
		entities = new ArrayList <Entity> (); // I think this is fine because Java handles garbage collection. But in C++ we would need a delete.
	}
        public void removeEntity_ALL (String entityName) // Removes all with the given name.
	{
		for (int i=entities.size()-1; i >= 0 ; i--) // Must be --, or will cause repated off by one bugs.
		{
			if ((entities.get(i).getName()).compareToIgnoreCase(entityName)==0)
			{
				entities.remove(i);
				// No break statement so that all entities of this name are removed.
			}
		}
	}
}