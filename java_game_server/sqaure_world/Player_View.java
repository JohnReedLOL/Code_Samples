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
public class Player_View implements Serializable
{
    protected static final long serialVersionUID = 100;
    
    public Tile [][] tilesInView = new Tile [World_Map.xViewSize][World_Map.yViewSize]; // Here for efficiency
    World_Map map;
    
    String playerName;
    
    public Player_View (World_Map map, Player player)
    {
        this.map = map;
        playerName = player.getName();
    }
    

    
    public boolean keepMoving = true; // Should be used in an if-stement in Square_World_GUI.java
    /*
     * Allows character motion.
     */
    public void startMoving () {keepMoving = true;} // 
     
    /*
     * Interupts character motion.
     */
    public void stopMoving () {keepMoving = false;} // 
     
    /*
     * Returns tiles in view.
     */
    public Tile [][] getVisibleTiles () {return tilesInView;}
    
}

