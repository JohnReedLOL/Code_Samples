
package sqaure_world.java_files;

import java.net.*;
import java.io.*;

/**
 *
 * @author JohnReedLOL
 */
public class Player_Info
{
    
    public void update_Player_Position_and_View(int xChange, int yChange) {
        
        
        
        int xNew = x_pos + xChange;
        int yNew = y_pos + yChange;

        String old_pos = New_World_Map.world[x_pos][y_pos].name;
        String new_pos = New_World_Map.world[x_pos + xChange][y_pos + yChange].name;

        New_World_Map.world[x_pos + xChange][y_pos + yChange].name = old_pos;
        New_World_Map.world[x_pos][y_pos].name = new_pos;

        x_pos += xChange;
        y_pos += yChange;
        this.p_view = new New_Player_View(player_name, x_pos, y_pos);
        
        
        
        //helper(xChange, yChange, this.x_pos, this.y_pos, this.player_name, this.p_view);
        
    }
    
    /*
    public static synchronized void helper(int xChange, int yChange, int my_xpos, int my_ypos, String my_name, New_Player_View my_pview)
    {
        int xNew = my_xpos + xChange;
        int yNew = my_ypos + yChange;

        String old_pos = New_World_Map.world[my_xpos][my_ypos].name;
        String new_pos = New_World_Map.world[my_xpos + xChange][my_ypos + yChange].name;

        New_World_Map.world[my_xpos + xChange][my_ypos + yChange].name = old_pos;
        New_World_Map.world[my_xpos][my_ypos].name = new_pos;

        my_xpos += xChange;
        my_ypos += yChange;
        my_pview = new New_Player_View(my_name, my_xpos, my_ypos);
        
        
    }
    */
    
    
    Player_Info(String p_name, String p_password, int p_number, Socket p_socket, 
                ObjectInputStream from_player, ObjectOutputStream to_player)
    {
        player_name = p_name;
        this.player_password = p_password;
        this.player_number = p_number;
	this.player_socket = p_socket;
        this.ois = from_player;
        this.oos = to_player;
        this.player_thread = null; //currentThread gets initialized later.
        this.ip = player_socket.getRemoteSocketAddress().toString();
	x_pos = 9;
	y_pos = 5;
        
        this.p_view = new New_Player_View(player_name, x_pos, y_pos); //Get view
        //System.out.println("!!! Player info: " + p_name + " "+ p_password + " " + p_number + "\n" + this.ip + " xpos:" + x_pos + " ypos:" + y_pos + "\n\n");
        
    }

    public volatile String player_name;
    public volatile String player_password;
    public volatile int player_number;
    public volatile Socket player_socket;
    public volatile ObjectInputStream ois;
    public volatile ObjectOutputStream oos;
    public volatile String ip;
    public volatile Thread player_thread;
    public volatile int x_pos = 0;
    public volatile int y_pos = 0;
    
    /*public volatile Entity y1;
    public volatile Item y2;
    public volatile Player y3;
    public volatile New_Player_View y4;
    public volatile Terrain y5;
    public volatile Terrain_Types y6;
    public volatile New_Tile y7;
    public volatile TileComponent y8;
    public volatile New_World_Map y9;*/
    public New_Player_View p_view;
}
