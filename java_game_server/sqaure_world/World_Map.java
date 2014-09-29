/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sqaure_world;

import java.io.Serializable;
import sqaure_world.Entity;
import sqaure_world.Terrain;
import sqaure_world.Terrain_Types;
import sqaure_world.Tile;

/**
 *
 * @author VH
 */
public class World_Map implements Serializable
{
    protected static final long serialVersionUID = 100;
    public static int xWorldSize = 101;
    public static int yWorldSize = 101;
    
    public static final int xViewSize = 9;
    public static final int yViewSize = 5;
    public static final int x_shift = xViewSize/2;
    public static final int y_shift = yViewSize/2;
    
    public static Tile [][] tilesWorld;
    public static World_Map map = new World_Map();
    
    public World_Map ()
    {
        generateTiles ();
    }
    public World_Map (int xSize, int ySize)
    {
        if (xSize<xViewSize || ySize<yViewSize)
        {
            System.out.println ("The game field must be no less than " + xViewSize + " by " + yViewSize + " tiles.\nReverting to default values (101 by 101).");
        }
        else
        {
            if (Math.abs(xSize)%2==0)
            {
                xWorldSize=xSize+1;
                System.out.println ("The x size was autoadjusted by +1 (game field must be odd by odd).");
            }
            else
            {
                xWorldSize=xSize;
            }
            if (Math.abs(ySize)%2==0)
            {
                yWorldSize=ySize+1;
                System.out.println ("The y size was autoadjusted by +1 (game field must be odd by odd).");
            }
            else
            {
                yWorldSize=ySize;
            }
        }
        generateTiles ();
    }
    
    private void generateTiles ()
    {
        tilesWorld = new Tile [xWorldSize][yWorldSize];
        
        Terrain tmpTerrain = Terrain_Types.Water_Waves (Terrain_Types.harmless);
        Terrain offWorldTerrain = Terrain_Types.Off_World_Stars ();
        
        for (int i = x_shift; i < xWorldSize-x_shift; i++)
        {
            for (int j = y_shift; j < yWorldSize-y_shift; j++)
            {
                tilesWorld[i][j] = new Tile (tmpTerrain);
            }
        }
        
        // For testing ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
        for (int i = 0; i < xWorldSize; i+=4)
        {
            for (int j = 0; j < yWorldSize; j++)
            {
                tilesWorld[i][j] = new Tile (offWorldTerrain);
            }
        }
        for (int i = 0; i < xWorldSize; i++)
        {
            for (int j = 0; j < yWorldSize; j+=5)
            {
                tilesWorld[i][j] = new Tile (Terrain_Types.Forest (Terrain_Types.harmless));
            }
        }
        
        
        
        // Edge of the world ,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,
        for (int i = 0; i < xWorldSize; i++)
        {
            for (int j = 0; j < y_shift; j++)
            {
                tilesWorld[i][j] = new Tile (offWorldTerrain);
            }
            for (int j = yWorldSize - y_shift; j < yWorldSize; j++)
            {
                tilesWorld[i][j] = new Tile (offWorldTerrain);
            }
        }
        for (int j = 0; j < yWorldSize; j++)
        {
            for (int i = 0; i < x_shift; i++)
            {
                tilesWorld[i][j] = new Tile (offWorldTerrain);
            }
            for (int i = xWorldSize - x_shift; i < xWorldSize; i++)
            {
                tilesWorld[i][j] = new Tile (offWorldTerrain);
            }
        }
    }
    
    public Tile getTile_At (int x, int y)
    {
        return tilesWorld [x][y];
    }
    
    public void addEntity (Entity newEntity, int xInit, int yInit)
    {
        tilesWorld[xInit][yInit].addEntity(newEntity);
    }
}
