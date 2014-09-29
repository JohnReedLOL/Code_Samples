
package sqaure_world;

import java.net.*;
import java.io.*;
import static sqaure_world.Sqaure_World_Server.map;

/**
 *
 * @author JohnReedLOL
 */
public class Player_Info
{
    Player_Info(String p_name, String p_password, int p_number, Socket p_socket, 
                ObjectInputStream from_player, ObjectOutputStream to_player)
    {
        this.player_name = p_name;
        this.player_password = p_password;
        this.player_number = p_number;
	this.player_socket = p_socket;
        this.ois = from_player;
        this.oos = to_player;
        this.player_thread = null; //currentThread gets initialized later.
        this.ip = player_socket.getRemoteSocketAddress().toString();
	this.x_pos = 0;
	this.y_pos = 0;
        
        //Player not necessary.
        this.p1 = new Player(this.player_name, this.x_pos, this.y_pos);
        this.p_view = new Player_View(Sqaure_World_Server.map, p1); //Get view
        System.out.println("!!! Player info: " + p_name + " "+ p_password + " " + p_number + 
        "\n" + this.ip + " xpos:" + this.x_pos + " ypos:" + this.y_pos + "\n\n");
        
    }

    Player_Info(String p_name, String p_password, int p_number, Socket p_socket, 
                ObjectInputStream from_player, ObjectOutputStream to_player, int xx, int yy)
    {
        this.player_name = p_name;
        this.player_password = p_password;
        this.player_number = p_number;
	this.player_socket = p_socket;
        this.ois = from_player;
        this.oos = to_player;
        this.player_thread = null; //currentThread gets initialized later.
        this.ip = player_socket.getRemoteSocketAddress().toString();
	this.x_pos = xx;
	this.y_pos = yy;
        
        //Player not necessary.
        this.p1 = new Player(this.player_name, this.x_pos, this.y_pos);
        
        map.addEntity(this.p1, this.x_pos, this.y_pos);
        
        this.p_view = new Player_View(Sqaure_World_Server.map, p1); //Get view
        System.out.println("!!! Player info: " + p_name + " "+ p_password + " " + p_number + 
        "\n" + this.ip + " xpos:" + this.x_pos + " ypos:" + this.y_pos + "\n\n");
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
    public volatile Player_View y4;
    public volatile Terrain y5;
    public volatile Terrain_Types y6;
    public volatile Tile y7;
    public volatile TileComponent y8;
    public volatile World_Map y9;*/
    public volatile Player p1;
    public volatile Player_View p_view;
}
