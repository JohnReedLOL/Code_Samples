/*
 * To change this license header, chSquare_World_GUI.oose License Headers in Project Properties.
 * To change this template file, chSquare_World_GUI.oose Tools | Templates
 * and open the template in the editor.
 */
package sqaure_world.java_files;

import sqaure_world.java_files.GUI;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author JohnReedLOL
 */
public class Clients_Output implements Runnable
{

    private final Object my_object;

    Clients_Output(Object other_object)
    {
        this.my_object = other_object;
    }
    
    @Override
    public void run()
    {
        try
        {
        do_struff();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void do_struff() throws Exception
    {
        
        if(my_object instanceof String)
        {
            String string_object = (String) my_object;
            //System.out.println("String_Object to send.");
            GUI.oos.writeObject(string_object);
            GUI.oos.flush();
        }
        else if(my_object instanceof Move_Object)
        {
            Move_Object move_object = (Move_Object) my_object;
            //System.out.println("Move_Object to send.");
            GUI.oos.writeObject(move_object);
            GUI.oos.flush();
        }

        //System.out.println("Output sent.");
    }
    
    
    public synchronized static void log_off()
    {
        try
        {
            String logout = "logout" + "\n";
            //System.out.println("Socket is preparing to close in log_off.");

            GUI.oos.writeObject(logout);

            GUI.oos.flush();
            GUI.socket.close();
            System.exit(0);
        }
        catch (Exception e)
        {
            ////System.out.println("IOException in log_off.");
            //e.printStackTrace(System.out);
            System.exit(0);
        }
    }
}

    /*
    public class chat_Key_Listener extends java.awt.event.KeyAdapter
    {

        @Override
        public void keyPressed(java.awt.event.KeyEvent evt)
        {
            //System.out.println("A key was pressed.");
            if (evt.getKeyCode() == KeyEvent.VK_ENTER) // Enter key pressed
            {
                //System.out.println("Enter key was pressed.");
                String to_send = jTextField_Outgoing.getText() + "\n";
                //System.out.println(":" + to_send + ":");
                try
                {
                    GUI.oos.writeObject(to_send);
                    if(to_send.contentEquals("logout" + "\n")) System.exit(1);
                }
                catch (IOException ee)
                {
                    //System.out.println("Fatal exception in ");
                    System.exit(1);
                }
                jTextField_Outgoing.setText(""); // Resets text to empty.
                //textArea.append(text + newline);
                //textField.selectAll();
                //jTextField_Outgoing.setCa
                //Make sure the new text is visible, even if there
                //was a selection in the text area.
                //textArea.setCaretPosition(textArea.getDocument().getLength());
            }
        }
    }
    */
