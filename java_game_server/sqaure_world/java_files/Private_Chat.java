/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package sqaure_world.java_files;

import java.io.Serializable;
import java.text.StringCharacterIterator;
import java.util.*;
import java.io.*;

/**
 *
 * @author johnmichaelreed
 */
public class Private_Chat implements Serializable
{
    private static final long serialVersionUID = 7526471155622776147L;
    
    String reciever;
    String sender;
    String word;
    
   /**
   * Always treat de-serialization as a full-blown constructor, by
   * validating the final state of the de-serialized object.
   */
   private void readObject(
     ObjectInputStream aInputStream
   ) throws ClassNotFoundException, IOException {
     //always perform the default de-serialization first
     aInputStream.defaultReadObject();

     //ensure that object state has not been corrupted or tampered with maliciously
     //validateState();
  }

    /**
    * This is the default implementation of writeObject.
    * Customise if necessary.
    */
    private void writeObject(
      ObjectOutputStream aOutputStream
    ) throws IOException {
      //perform the default serialization for all non-transient, non-static fields
      aOutputStream.defaultWriteObject();
    }
    
}
