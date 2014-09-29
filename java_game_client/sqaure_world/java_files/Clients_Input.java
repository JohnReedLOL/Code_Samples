/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqaure_world.java_files;

/**
 *
 * @author johnmichaelreed2
 */
import static sqaure_world.java_files.GUI.*;

public class Clients_Input extends Thread{
    
    private javax.swing.JTextArea my_global_chat;
    
    Clients_Input(javax.swing.JTextArea p_chat)
    {
        this.my_global_chat = p_chat;
    }

    //public void getInput() throws Exception {
    public void run() {
        //javax.swing.JTextArea 
        if (my_global_chat == null) {
            //System.out.println("In Input, my_global_chat = null");
        } else {
            //System.out.println("In Input, my_global_chat != null");
        }
        try {
            while (true) {
                Object in_object;
                String string_object;
                New_Tile tile_object;
                String input_class;

                //System.out.println("reading in object");
                in_object = ois.readObject();
                //System.out.println("Object read in.");

                if (in_object instanceof String) {
                    //System.out.println("Input object is a string.");
                    string_object = (String) in_object;
                    input_class = "String";

                    //if (!string_object.contains(":"))
                    //{
                    my_global_chat.append(string_object);
                    my_global_chat.setCaretPosition(my_global_chat.getDocument().getLength());
                    //}

                } else if (in_object instanceof Tile_View_Array) {

                    //System.out.println("Input object is a view.");
                    GUI.yourView = (Tile_View_Array) in_object;

                    // yourView = (Player_View) in_object;
                    ((GUI) gameWindow).update_The_Map_Desplay();

                } else {
                    //System.out.println("Input object is not a string or a Player_View.");
                    //Cast in_object to something.
                    input_class = "Not String.";
                }

            }
        } catch (Exception xxx) {
            //System.out.println("Fatal exception in input thread.");
            xxx.printStackTrace();
            try {
                socket.close();
            } catch (Exception e) {
            }
            System.exit(1);
            //return;
            //Input thread cannot handle exceptions.
        }
    }
}
