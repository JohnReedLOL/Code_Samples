/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqaure_world.java_files;

import sqaure_world.java_files.Clients_Output;
import sqaure_world.java_files.GUI;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;

import static sqaure_world.java_files.GUI.*;

/**
 *
 * @author johnmichaelreed2
 */
public class Main {
    
    GUI myGUI;

    public Main() {

        try {
            myGUI = new GUI();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        //mainPane = new javax.swing.JPanel();
        //mainPane.add(myGUI);
    }

    public static Image_Repo image_repo;

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        image_repo = new Image_Repo();

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //System.out.println("Starting");
        String yourName = System.getProperty("user.name");
        //String yourPass = Runtime.getRuntime().exec("hostname");

        String yourPass = "Host_name";

        try {
            InetAddress addr;
            addr = InetAddress.getLocalHost();
            yourPass = addr.getHostName();
        } catch (UnknownHostException ex) {
            //System.out.println("Hostname can not be resolved");
            ex.printStackTrace(System.out);
            return;
        }

        String MAC_Username = getMacUsername();
        //System.out.println("Your MAC address username is: " + MAC_Username);

        // create a TCP socket connection.
        socket = new Socket();
        socket.setTcpNoDelay(true); //send all objects immediarely.
        socket.setReuseAddress(true); //This connection is reusable.
        //System.out.println("Preparing to connect");
        try {
            socket.connect(new InetSocketAddress("jmrsserver.publicvm.com", 5010));
            //socket.connect(new InetSocketAddress("98.70.32.93", 5010));

            //socket.connect(new InetSocketAddress("localhost", 5010));
            //System.out.println("Connection made to jmrsserver.publicvm.com.");
        } catch (java.net.ConnectException error) {
            //System.out.println("\n\nConnection to JMRsServer failed.\nGame quitting.\n");
            error.printStackTrace();
            javax.swing.JOptionPane.showConfirmDialog(gameWindow, "Server connection filed.\nPlease try again later.", "Quitting", javax.swing.JOptionPane.OK_CANCEL_OPTION, javax.swing.JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
        //System.out.println("client accepted.");

        oos = new ObjectOutputStream(
                (socket.getOutputStream()));
        oos.flush();
        //System.out.println("Made output stream (client side).");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                try {
                    GUI.oos.writeObject("logout" + "\n");
                    GUI.oos.flush();
                    GUI.socket.close();
                } catch (Exception e) {
                    //e.printStackTrace();
                }
            }
        });
        //System.out.println("Shutdown hook added.");

        ois = new ObjectInputStream(
                (socket.getInputStream()));
        //System.out.println("Made input stream (client side).");

        oos.writeObject(MAC_Username);
        //System.out.println("name almost almost sent");
        oos.flush();
        //System.out.println("name flushed to server");
        oos.writeObject("yourPass");
        //System.out.println("pass almost sent");
        oos.flush();
        //System.out.println("pass flushed to server.");

        /* Create and display the form BEFORE*/
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {

                try {
                    //new GUI().setVisible(true);
                    createAndShowGUI();
                } catch (Exception E) {
                    E.printStackTrace();
                }

                //new GUI().setVisible(true);
            } //End of thread
        });
    }

    private static void createAndShowGUI() {

        //TAKEN OUT OF GUI
        /* 
         setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
         setResizable(false);
         addWindowListener(new java.awt.event.WindowAdapter() {
         public void windowClosing(java.awt.event.WindowEvent evt) {
         formWindowClosing(evt);
         }
         });
         */
        //Create and set up the content pane.
        Main main = new Main();
        
        main.myGUI.gameWindow.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        
        main.myGUI.gameWindow.setFocusable(true);
        main.myGUI.gameWindow.requestFocusInWindow();
      
        frame.setVisible(true);
    }

    private static String getMacUsername() throws Exception {

        //Get MAC address
        String MAC_Username = "";
        InetAddress ip = InetAddress.getLocalHost();

        Enumeration e = NetworkInterface.getNetworkInterfaces();

        while (e.hasMoreElements()) {

            NetworkInterface n = (NetworkInterface) e.nextElement();
            Enumeration<InetAddress> ee = n.getInetAddresses();
            while (ee.hasMoreElements()) {
                InetAddress i = (InetAddress) ee.nextElement();
                if (!i.isLoopbackAddress() && !i.isLinkLocalAddress() && i.isSiteLocalAddress()) {
                    ip = i;
                }
            }
        }

        NetworkInterface network = NetworkInterface.getByInetAddress(ip);
        byte[] mac_byte = network.getHardwareAddress();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < mac_byte.length; i++) {
            sb.append(String.format("%02X%s", mac_byte[i], (i < mac_byte.length - 1) ? "-" : ""));
        }
        return sb.toString();
    }
    
        //vlad please adf arrow keys. Thank you!
    private static void formKeyPressed(java.awt.event.KeyEvent evt) {
        //System.out.println("A key was pressed");
        if (evt.getKeyCode() == 38) {
            //System.out.println("up was pressed");
            Move_Object m = new Move_Object(0, 1);
            output_thread = new Thread(new Clients_Output(m));
            output_thread.start();
        } // Up
        else if (evt.getKeyCode() == 40) {
            //System.out.println("down was pressed");
            Move_Object m = new Move_Object(0, -1);
            output_thread = new Thread(new Clients_Output(m));
            output_thread.start();
        }// Left
        else if (evt.getKeyCode() == 37) {
            //System.out.println("left was pressed");
            Move_Object m = new Move_Object(-1, 0);
            output_thread = new Thread(new Clients_Output(m));
            output_thread.start();
        }// Left
        else if (evt.getKeyCode() == 39) {
            //System.out.println("right was pressed");
            Move_Object m = new Move_Object(1, 0);
            output_thread = new Thread(new Clients_Output(m));
            output_thread.start();
        }// Left
    }

}
