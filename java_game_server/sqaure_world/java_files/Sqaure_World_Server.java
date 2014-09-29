package sqaure_world.java_files;

//import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.net.*;
import java.util.Random;

/*
 * @author John-Michael Reed
 */
class Sqaure_World_Server {
    //This player data lookup map is shared between all threads.

    public static volatile HashMap<String, Player_Info> players = new HashMap<String, Player_Info>();
    //public static New_World_Map map = new New_World_Map();

    public static void main(String argv[]) throws Exception {

        //create a new starting person.
        final ServerSocket serversocket = new ServerSocket();

        //move it back out
        serversocket.setReuseAddress(true);
        serversocket.setPerformancePreferences(0, 1, 0);
        serversocket.bind(new InetSocketAddress(5010));

        while (true) {

            //Random r = new Random();
            //int next_rand = r.nextInt(2);
            //if (next_rand == 0) {
                New_World_Map.world[9][5].name = "Female";
            //} else {
            //    New_World_Map.world[9][5].name = "Generic_Person";
            //}

            //System.out.println("server listening");

            //serversocket.
            final Socket socket = serversocket.accept();
            //System.out.println("server accepted.");

            ObjectOutputStream outputToClient = new ObjectOutputStream(
                    (socket.getOutputStream()));
            outputToClient.flush();
            //System.out.println("Made output stream.");

            ObjectInputStream inputFromClient = new ObjectInputStream(
                    (socket.getInputStream()));
            //System.out.println("Made input stream.");

            String username = null;
            String password = null;
            try {
                Object temp_obj = inputFromClient.readObject();
                //username = (String) inputFromClient.readObject();
                //System.out.println("First object recieved.");
                username = (String) temp_obj;

                //Just make the username into the global ip address.
                //username = socket.getRemoteSocketAddress().toString() + "p" + Sqaure_World_Server.players.size();
                //username = "Player " + Integer.toString(Sqaure_World_Server.players.size() );
                //No, make it the hostname because that changes less.
                //username = 
                //System.out.println(socket.getInetAddress().getCanonicalHostName());
                //System.out.println("Username object successfully class casted.");
                Object temp2 = inputFromClient.readObject();
                //System.out.println("Second object recieved.");
                password = (String) temp2;
                //password = Integer.toString(Sqaure_World_Server.players.size()); //socket.getRemoteSocketAddress().toString();

                //System.out.println("Password object successfully class casted.");

                if (username == null || password == null) {
                    //System.out.println("Null user/pass error.");
                    System.exit(1);
                }
            } catch (ClassNotFoundException e) {
                //System.out.println("String class for username/pass not found. Fatal error.");
                e.printStackTrace(System.out);
                System.exit(1);
            }

            //Check if player's account already exists.
            Player_Info player_info = players.get(username);

            //If player_info != null, then the player already exists in hash table players.
            if (player_info != null) {
                //Check password match
                if (player_info.player_password.contentEquals(password)) {
                    //System.out.println("Password matches.");

                    //Pass the player's info to a new thread dedicated to serving that player.
                    player_info.player_thread = new Thread(new Server_Input(player_info));

                    //player_info = new Player_Info(username, 
                    //password, Sqaure_World_Server.players.size(), socket, inputFromClient, outputToClient);
                    player_info.player_socket = socket;
                    player_info.ois = inputFromClient;
                    player_info.oos = outputToClient;

                    player_info.player_thread.start();

                    continue; //Go to top of while(true) loop. The socket will be handled by the new thread.
                } else {
                    //System.out.println("Password does not match.");

                    //Do something about it.
                    Runtime.getRuntime().exit(1);
                }
            } //If player_info == null, then this new player must be added to hash table players.
            else {
                //System.out.println("New account being made.");
                player_info = new Player_Info(username, password, Sqaure_World_Server.players.size(), socket, inputFromClient, outputToClient);

                //System.out.println("Adding one new player to the hash table.");
                players.put(username, player_info);

                //System.out.println("Creating a thread for new player.");
                player_info.player_thread = new Thread(new Server_Input(player_info));
                player_info.player_thread.start();
                //System.out.println("Thread created and started.");

                //player_info = null; //This thread is no longer using this.
                /*
                 for(int i = 0; i < 10; ++i)
                 {
                 Thread.sleep(4000);
                
                 Move_Object in_objectf = new Move_Object(0, 0);

                 //System.out.println("creating first display");
                 Move_Object my_move2 = (Move_Object) in_objectf;
                
                 player_info.update_Player_Position_and_View(my_move2.movex, my_move2.movey);

                 Thread t2 = new Thread(new Server_Output(player_info, player_info.p_view));
                 t2.start();
                
                
                 Thread.sleep(4000);
                 in_objectf = new Move_Object(1, 1);
                 //System.out.println("creating second display");
                 Move_Object my_move3 = (Move_Object) in_objectf;
                 player_info.update_Player_Position_and_View(my_move3.movex, my_move3.movey);
                 t2 = new Thread(new Server_Output(player_info, player_info.p_view));
                 t2.start();
                 }    
                 */
                continue; //Go to top of while(true) loop.
            }

        }
    }
}
