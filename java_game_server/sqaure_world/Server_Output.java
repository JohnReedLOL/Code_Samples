/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqaure_world;

/**
 *
 * @author JohnReedLOL
 */
public class Server_Output implements Runnable
{

    private final Player_Info my_info;
    //private final Object in_object;
    private final Object object;
    //private final Player_View ;

    Server_Output(Player_Info player_info, Object obj)
    {
        this.my_info = player_info;
        this.object = obj;
    }

    @Override
    public void run()
    {
        try {

            if ((object instanceof Player_View)) {
                Player_View player_view_object = (Player_View) object;
                String player_name = player_view_object.playerName; // Make public


                Player_Info temp_info = Sqaure_World_Server.players.get(player_name);

                if (temp_info.player_socket != null
                        && temp_info.oos != null
                        && temp_info.player_socket.isConnected()) {

                    //send display to player using outgoing stream from server to client.
                    //If person disconnected (computer went to sleep for a while), remove them.
                    try {
                        temp_info.oos.writeObject(player_view_object);
                    }
                    catch (java.net.SocketException u) {
                        System.out.println(temp_info.player_name + "is disconnected and connect recieve the signal");
                        try {
                            temp_info.player_socket.close();
                        }
                        catch (Exception o) {
                            System.out.println("To " + temp_info.player_name + ": Failed Socket already closed");
                            o.printStackTrace();
                        }

                        temp_info.oos = null;
                        temp_info.ois = null;
                        temp_info.player_socket = null;
                        u.printStackTrace();
                    }
                    //Note that this is NO LONGER the blocking, way to do IO.

                    temp_info.oos.flush();
                    System.out.println("Player " + temp_info.player_name + " was sent a display.");
                }
                else {
                    System.out.println("Player " + player_name + " is not online to recieve display.");
                }


            }
            //}
            //catch (Exception oo) {
            //    oo.printStackTrace();
            //}
            //Send message to friend X by typing "X: Message_to_X."
            if ((object instanceof String) && ((String) object).contains(":")) {
                System.out.println("PRIVATE MESSAGE");
                //get friend X's username
                String string_object = (String) object;
                String friends_name = string_object.substring(0, string_object.indexOf(':'));

                Player_Info friends_info = null;

                //get friend X's Player_Info
                friends_info = Sqaure_World_Server.players.get(friends_name);

                if (friends_info != null) {
                    if (friends_info.player_socket != null
                            && friends_info.player_socket.isConnected()
                            && friends_info.oos != null) {
                        System.out.println("About to send private message.");
                        //send message to friend using outgoing stream from server to client.
                        friends_info.oos.writeObject(this.my_info.player_name + ": " + string_object);
                        //Note that this is the wrong, potentially blocking, way to do IO.
                        friends_info.oos.flush();
                    }

                }
                else {

                    System.out.println("The player doesn't exist.");
                    String message = "You are trying to send a message to player \"" + friends_name + "\".";
                    //Warning. Doing it this way will cause bugs. 
                    my_info.oos.writeObject(message);
                    my_info.oos.flush();
                    //Fix later.
                    //continue;
                }

            }
            else if (object instanceof String) {
                String string_object = (String) object;
                boolean kill = false;
                if (string_object.contentEquals("logout" + "\n")) {

                    try {
                        System.out.println("User logging out. Closing thread.");
                        my_info.player_socket.close();
                        System.out.println("Cleaning up.");
                        my_info.player_socket = null;
                        my_info.oos = null;
                        my_info.ois = null;
                        my_info.player_thread = null;
                        kill = true; //End thread.
                    }
                    catch (Exception e) {
                        //Socket is already closed by the other side.
                        System.out.println("Socket already closed by client. Cleaning up.");
                        my_info.player_socket = null;
                        my_info.oos = null;
                        my_info.ois = null;
                        my_info.player_thread = null;
                        kill = true; //End thread.
                    }

                }

                //SIGNAL EVERYONE.
                System.out.println("Signalling everyone");
                for (String player_name : Sqaure_World_Server.players.keySet()) {
                    System.out.println("Signalling someone");
                    Player_Info temp_info = null;
                    temp_info = Sqaure_World_Server.players.get(player_name);

                    if (temp_info.player_socket != null
                            && temp_info.oos != null
                            && temp_info.player_socket.isConnected()) {

                        //send message to friend using outgoing stream from server to client.
                        //If person disconnected (computer went to sleep for a while), remove them.
                        try {
                            temp_info.oos.writeObject(this.my_info.player_name + ": " + string_object);
                        }
                        catch (java.net.SocketException u) {
                            System.out.println(temp_info.player_name + "is disconnected and connect recieve the signal");
                            try {
                                temp_info.player_socket.close();
                            }
                            catch (Exception o) {
                                System.out.println("To " + temp_info.player_name + ": Failed Socket already closed");
                                o.printStackTrace();
                            }

                            temp_info.oos = null;
                            temp_info.ois = null;
                            temp_info.player_socket = null;
                            u.printStackTrace();
                        }
                        //Note that this is the wrong, potentially blocking, way to do IO.

                        temp_info.oos.flush();
                        System.out.println("Player " + temp_info.player_name + " was sent a global chat line.");
                    }
                    else {
                        System.out.println("Player " + player_name + " is not online for global chat.");
                    }

                }
                if (kill == true) {
                    return;
                }
            }//End outermost if
            return;
        }
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}
