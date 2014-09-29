package sqaure_world;

//import java.util.ArrayList;
import java.util.HashMap;
import java.io.*;
import java.net.*;
/*
 * @author John-Michael Reed
 */

class Sqaure_World_Server
{
    //This player data lookup map is shared between all threads.

    public static volatile HashMap<String, Player_Info> players = new HashMap<String, Player_Info>();
    public static World_Map map = new World_Map();

    public static void main(String argv[]) throws Exception
    {

            final ServerSocket serversocket = new ServerSocket();

            //move it back out
            serversocket.setReuseAddress(true);
            serversocket.setPerformancePreferences(0, 1, 0);
            serversocket.bind(new InetSocketAddress(5010));
            
        while (true) {
            System.out.println("server listening");



            //serversocket.
            final Socket socket = serversocket.accept();
            System.out.println("server accepted.");

            ObjectInputStream inputFromClient = new ObjectInputStream(
                    (socket.getInputStream()));
            System.out.println("Made input stream.");

            ObjectOutputStream outputToClient = new ObjectOutputStream(
                    (socket.getOutputStream()));
            outputToClient.flush();
            System.out.println("Made output stream.");

            String username = null;
            String password = null;
            try {
                Object temp_obj = inputFromClient.readObject();
                //username = (String) inputFromClient.readObject();
                System.out.println("First object recieved.");
                username = (String) temp_obj;

                //Just make the username into the global ip address.
                //username = socket.getRemoteSocketAddress().toString() + "p" + Sqaure_World_Server.players.size();
                //username = "Player " + Integer.toString(Sqaure_World_Server.players.size() );

                //No, make it the hostname because that changes less.
                //username = 
                System.out.println(socket.getInetAddress().getCanonicalHostName());
                System.out.println("Username object successfully class casted.");
                Object temp2 = inputFromClient.readObject();
                System.out.println("Second object recieved.");
                password = (String) temp2;
                //password = Integer.toString(Sqaure_World_Server.players.size()); //socket.getRemoteSocketAddress().toString();

                System.out.println("Password object successfully class casted.");

                if (username == null || password == null) {
                    System.out.println("Null user/pass error.");
                    System.exit(1);
                }
            }
            catch (ClassNotFoundException e) {
                System.out.println("String class for username/pass not found. Fatal error.");
                e.printStackTrace(System.out);
                System.exit(1);
            }

            //Check if player's account already exists.
            Player_Info player_info = players.get(username);

            //If player_info != null, then the player already exists in hash table players.
            if (player_info != null) {
                //Check password match
                if (player_info.player_password.contentEquals(password)) {
                    System.out.println("Password matches.");

                    //Pass the player's info to a new thread dedicated to serving that player.

                    player_info.player_thread = new Thread(new Server_Input(player_info));

                    //player_info = new Player_Info(username, 
                    //password, Sqaure_World_Server.players.size(), socket, inputFromClient, outputToClient);
                    player_info.player_socket = socket;
                    player_info.ois = inputFromClient;
                    player_info.oos = outputToClient;

                    player_info.player_thread.start();

                    continue; //Go to top of while(true) loop. The socket will be handled by the new thread.
                }
                else {
                    System.out.println("Password does not match.");

                    //Do something about it.

                    Runtime.getRuntime().exit(1);
                }
            }
            //If player_info == null, then this new player must be added to hash table players.
            else {
                System.out.println("New account being made.");
                player_info = new Player_Info(username, password, Sqaure_World_Server.players.size(), socket, inputFromClient, outputToClient, map.xWorldSize / 2 + 1, map.yWorldSize / 2 + 1);

                System.out.println("Adding one new player to the hash table.");
                players.put(username, player_info);

                System.out.println("Creating a thread for new player.");
                player_info.player_thread = new Thread(new Server_Input(player_info));
                player_info.player_thread.start();
                System.out.println("Thread created and started.");
                player_info = null; //This thread is no longer using this.
                continue; //Go to top of while(true) loop.
            }

        }
    }
}
