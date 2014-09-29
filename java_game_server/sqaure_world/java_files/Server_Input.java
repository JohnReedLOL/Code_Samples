
package sqaure_world.java_files;

import java.io.*;
import java.net.*;

/**
 * Each instance of this class gets all input from one client.
 *
 * @author JohnReedLOL
 */
class Server_Input implements Runnable
{
    private final Player_Info my_info;
    
        /*
     * Tests to see if tile is passable and updates position.
     * The server should be doing this - the view is a container.
     */

    Server_Input(Player_Info player_info)
    {
        this.my_info = player_info;
    }

    @Override
    public void run()
    {
	my_info.player_thread = Thread.currentThread();


        //System.out.println("Thread for player " +  my_info.player_name + " starting.");
        try
        {

            while (true)
            {
                Object in_object = null;
		String string_object = null;
		String input_class = null;

                try
                {
                    //System.out.println("reading in object");
                    in_object = my_info.ois.readObject();
                    //System.out.println("Object read in.");
                }
                catch (Exception x)
                {
                    //System.out.println("This user has logged off.");
                    x.printStackTrace(System.out);
                    return;
                }
                if (in_object instanceof String)
                {
                    //System.out.println("Input object is a string.");
		    string_object = (String)in_object;
		    input_class = "String";
                    
                    //System.out.println("creating output thread");
                Thread output_thread = new Thread(new Server_Output(my_info, string_object));
                output_thread.start();
                //System.out.println("Output thread started.");
                }
                else if(in_object instanceof Move_Object)
                {
                    //System.out.println("creating output thread");
                    Move_Object my_move = (Move_Object)in_object;
                    
                    //System.out.println("x-pos: " + my_info.x_pos );  //9
                    //System.out.println("y-pos: " + my_info.y_pos );  //5
                    
                    if(this.my_info.x_pos + my_move.movex > 4 && this.my_info.x_pos + my_move.movex < 14
                            && this.my_info.y_pos + my_move.movey > 2 && this.my_info.y_pos + my_move.movey < 8)
                    {
                    this.my_info.update_Player_Position_and_View(my_move.movex, my_move.movey);
                    }
                    //this.my_info.p_view = new New_Player_View( THE_WORLD , this.my_info.p1)
                    
                    Thread t = new Thread(new Server_Output(this.my_info, this.my_info.p_view));
                    t.start();
                    
                    //below here was added
                    for(String player_name : Sqaure_World_Server.players.keySet())
                    {
                        Player_Info temp_info = Sqaure_World_Server.players.get(player_name);
                        if(temp_info != my_info && Math.abs(temp_info.x_pos - this.my_info.x_pos) <= 4 && Math.abs(temp_info.y_pos - this.my_info.y_pos) <= 2)
                        {
                            temp_info.update_Player_Position_and_View(0, 0);
                            Thread output_thread = new Thread(new Server_Output(temp_info, temp_info.p_view));
                            output_thread.start();
                        }
                    }
                    //up to here was added.
                            
                    //Playerview p = getPlayerView();
                    //Thread output_thread = new Thread(new Server_Output(playerView));
                   
                    //System.out.println("Output thread started.");
                    //}
                }
                else
                {
                    //System.out.println("Input object is not a string or Move_Object.");
		    //Cast in_object to something.
		    input_class = "Not String.";
                }
                
                
                
                }
            }//End while loop
        catch (Exception e)
        {
            //System.out.println("An Exception occured. Terminate #2.");
            e.printStackTrace(System.out);
            System.exit(1);
        }
    }//End run thread
}//End class

