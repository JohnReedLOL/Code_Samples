
package sqaure_world;

import java.io.*;
import java.net.*;

/**
 * Each instance of this class gets all input from one client.
 *
 * @author JohnReedLOL
 */
class Server_Input implements Runnable
{
    
        /*
     * Tests to see if tile is passable and updates position.
     * The server should be doing this - the view is a container.
     */
    public static void update_Player_Position (Player_Info p, int xChange, int yChange)
    {
        int xNew = p.x_pos + xChange;
        int yNew = p.y_pos + yChange;
        
        if (Sqaure_World_Server.map.getTile_At(xNew, yNew).getTerrain().getStatusEffectInt() != Terrain_Types.impassable)
        {
            Entity playerEntity = Sqaure_World_Server.map.tilesWorld[p.x_pos][p.y_pos].getEntity(p.player_name); // Saves the latest version of the player.
            Sqaure_World_Server.map.tilesWorld[p.x_pos][p.y_pos].removeEntity(p.player_name);
            Sqaure_World_Server.map.tilesWorld[xNew][yNew].addEntity(playerEntity); // Puts the playe back onto the latest tile.
        }
        else
        {
            xNew = p.x_pos;
            yNew = p.y_pos;
        }
        
        int xArray = xNew - World_Map.x_shift;
        int yArray = yNew - World_Map.y_shift;
        p.x_pos = xNew;
        p.y_pos = yNew;

        for (int i = 0; i < World_Map.xViewSize; i++)
        {
            for (int j = 0; j < World_Map.yViewSize; j++)
            {
              p.p_view.tilesInView[i][j] = Sqaure_World_Server.map.tilesWorld [xArray+i][yArray+j];
            }
        }
    }
    
    

    private final Player_Info my_info;

    Server_Input(Player_Info player_info)
    {
        this.my_info = player_info;
    }

    @Override
    public void run()
    {
	my_info.player_thread = Thread.currentThread();


        System.out.println("Thread for player " +  my_info.player_name + " starting.");
        try
        {

            while (true)
            {
                Object in_object = null;
		String string_object = null;
		String input_class = null;

                try
                {
                    System.out.println("reading in object");
                    in_object = my_info.ois.readObject();
                    System.out.println("Object read in.");
                }
                catch (Exception x)
                {
                    System.out.println("This user has logged off.");
                    x.printStackTrace(System.out);
                    return;
                }
                if (in_object instanceof String)
                {
                    System.out.println("Input object is a string.");
		    string_object = (String)in_object;
		    input_class = "String";
                    
                    System.out.println("creating output thread");
                Thread output_thread = new Thread(new Server_Output(my_info, string_object));
                output_thread.start();
                System.out.println("Output thread started.");
                }
                else if(in_object instanceof Move_Object)
                {
                    System.out.println("creating output thread");
                    Move_Object my_move = (Move_Object)in_object;
                    this.my_info.x_pos += my_move.movex;
                    this.my_info.y_pos += my_move.movey;
                    this.my_info.p1.setX(this.my_info.p1.getX() + my_move.movex);
                    this.my_info.p1.setY(this.my_info.p1.getY() + my_move.movey);
                    
                    for(String player_name : Sqaure_World_Server.players.keySet())
                    {
                        Player_Info temp_info = Sqaure_World_Server.players.get(player_name);
                        if(Math.abs(temp_info.x_pos - this.my_info.x_pos) <= 4 && Math.abs(temp_info.y_pos - this.my_info.y_pos) <= 2)
                        {
                            Player_View temp_view = new Player_View(Sqaure_World_Server.map, temp_info.p1);
                            Thread output_thread = new Thread(new Server_Output(temp_info, temp_view));
                            output_thread.start();
                        }
                            
                    //Playerview p = getPlayerView();
                    //Thread output_thread = new Thread(new Server_Output(playerView));
                   
                    System.out.println("Output thread started.");
                    }
                }
                else
                {
                    System.out.println("Input object is not a string.");
		    //Cast in_object to something.
		    input_class = "Not String.";
                }
                
                
                
                }
            }//End while loop
        catch (Exception e)
        {
            System.out.println("An Exception occured. Terminate #2.");
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }//End run thread
}//End class

