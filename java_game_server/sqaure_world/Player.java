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
public class Player extends Entity
{
    protected String fileName;
    protected static final long serialVersionUID = 100;
    
    public Player (String name, int xInit, int yInit)
    {
            super (name, xInit, xInit);
            fileName = nameToPathString("Generic_Person");
    }
    
    @Override
    public String getFileName()
    {
        return fileName;
    }
}
