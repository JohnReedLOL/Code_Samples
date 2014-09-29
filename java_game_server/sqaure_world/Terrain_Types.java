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
public class Terrain_Types
{
    //{"harmless", "sacred", "marshy", "poisonous"/*, etc*/};
    
    public static final int harmless = 0;
    public static final int sacred = 1;
    public static final int marchy = 2;
    public static final int poisonous = 3;
    public static final int impassable = 4;
    // public static final int ... = ...;
    
    
    
    public static Terrain Dirt_with_Grass (int status_effect)
    {                                      // fire,  water, wood
        return new Terrain ("Dirt_with_Grass",false, false, true,status_effect);
    }
    
    
    
    public static Terrain DungeonCave (int status_effect)
    {                                  // fire, water, wood
        return new Terrain ("DungeonCave",true, true,  false,status_effect);
    }
    
    
    
    public static Terrain Forest (int status_effect)
    {                             // fire,  water, wood
        return new Terrain ("Forest",false, false, true,status_effect);
    }
    
    
    
    public static Terrain Just_Grass (int status_effect)
    {                                 // fire, water, wood
        return new Terrain ("Just_Grass",false, true, true,status_effect);
    }
    
    
    
    public static Terrain Off_World_Stars ()
    {                                      // fire,  water, wood
        return new Terrain ("Off_World_Stars",false, false, false,impassable);
    }
    
    
    
    public static Terrain Paved_Roman_Road (int status_effect)
    {                                       // fire, water, wood
        return new Terrain ("Paved_Roman_Road",true, false, true,status_effect);
    }
    
    
    
    public static Terrain Water_Waves (int status_effect)
    {                                  // fire,  water, wood
        return new Terrain ("Water_Waves",false, true,  false,status_effect);
    }
}
