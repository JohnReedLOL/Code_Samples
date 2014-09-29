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
public class Terrain extends TileComponent
{
	private static final long serialVersionUID = 100;
	
	/**
	 * The Terrain specific details
	 */
	protected boolean affinityFire;
	protected boolean affinityWater;
	protected boolean affinityWood;
	protected int statusEffect;
	protected static String [] statusEffectNames = {"harmless", "sacred", "marshy", "poisonous", "deadly"/*, etc*/};
	protected String description;
	
	
	
	
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The constructors
	 */
	public Terrain (String name, boolean affinityFire, boolean affinityWater, boolean affinityWood, int statusEffect)
	{
		super (name);
		this.affinityFire = affinityFire;
		this.affinityWater = affinityWater;
		this.affinityWood = affinityWood;
		this.statusEffect = statusEffect;
		this.description = generateAutomaticDescription(); // This method is pretty cool. Check it out. :)
		// Please use the methods setDescription() and setCustomDescription if you want to use your one descriptions.
		   // More details about these methods can be found below (in the Setters group).
	}
	
	public Terrain (Terrain sourceObject) // This is a copy constructor. I needed it to deepcopy a Terrain object (in Tile.java).
	{
		super (sourceObject.getName ());
		
		this.fileName = sourceObject.getFileName ();
		this.affinityFire = sourceObject.getAffinityFire ();
		this.affinityWater = sourceObject.getAffinityWater ();
		this.affinityWood = sourceObject.getAffinityWood ();
		this.statusEffect = sourceObject.getStatusEffectInt();
		this.description = sourceObject.getDescription();
	}
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The supporting method(s)
	 */
	protected String generateAutomaticDescription ()
	{
		char tmpLetter = statusEffectNames[statusEffect].charAt(0);
		String article;
		if (tmpLetter=='a'||tmpLetter=='e'||tmpLetter=='u'||tmpLetter=='i'||tmpLetter=='o')
			article = "an";
		else
			article = "a";
		
		return "This is " + article + " " + statusEffectNames[statusEffect] + " " + name.toLowerCase() + ".";
		// Sample output: This is a safe forest.
	}
	
	
	
	
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The getters
	 */
	public boolean getAffinityFire () {return affinityFire;}
	public boolean getAffinityWater () {return affinityWater;}
	public boolean getAffinityWood () {return affinityWood;}
	public int getStatusEffectInt () {return statusEffect;}
	public String getStatusEffectString () {return statusEffectNames[statusEffect];}
	public String getDescription () {return description;}
	public String inspect () {return description;} //////////////////////////////////// This is redundant, but it may be needed for some other files. I would prefer to keep the "getDescription ()" for naming consistency.
	
	
	
	
	
	
	// ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
	/**
	 * The setters
	 */
	public void changeAffinityFire (boolean affinityFire) {this.affinityFire = affinityFire;}
	public void changeAffinityWater (boolean affinityWater) {this.affinityWater = affinityWater;}
	public void changeAffinityWood (boolean affinityWood) {this.affinityWood = affinityWood;}
	public void setStatusEffectInt (int statusEffect) {this.statusEffect = statusEffect;}
	public void setDescription (String description) {this.description = generateAutomaticDescription() + "\n" + description;} // Will append your description to the standard "This is a" line.
	public void setCustomDescription (String description) {this.description = description;} // Removes the standard description and replaces it with your own. Please only use for rare/special terrain types, otherwise the Tiles will not look consistent.
}