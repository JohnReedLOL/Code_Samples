package sqaure_world.java_files;

import java.awt.image.*;
import java.io.*;
import java.util.HashMap;
import javax.imageio.*;

//VolatileImage vImg = createVolatileImage(w, h);
public class Image_Repo {

    public static BufferedImage Generic_Person = null;
    public static BufferedImage Just_Grass = null;
    public static BufferedImage Wall = null;
    public static BufferedImage Female = null;
    public static BufferedImage Forest = null;
    public static BufferedImage Dirt = null;
    public static BufferedImage Gold_Key = null;
    public static BufferedImage Waves = null;
    public static BufferedImage Locked_Treasure_Box = null;

    public static HashMap<String, BufferedImage> images = new HashMap<String, BufferedImage>();

    public Image_Repo() {

        try {
            String name = "Wall";
            //Locked_Treasure_Box = ImageIO.read(new File("Locked_Treasure_Box.png"));
            Locked_Treasure_Box = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Locked_Treasure_Box == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Wall", Wall);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Locked_Treasure_Box";
            //Locked_Treasure_Box = ImageIO.read(new File("Locked_Treasure_Box.png"));
            Locked_Treasure_Box = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Locked_Treasure_Box == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Locked_Treasure_Box", Locked_Treasure_Box);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Waves";
            //Waves = ImageIO.read(new File("Waves.png"));
            Waves = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Waves == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Waves", Waves);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Generic_Person";
            //Generic_Person = ImageIO.read(new File("Generic_Person.png"));
            Generic_Person = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Generic_Person == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Generic_Person", Generic_Person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Gold_Key";

            //Gold_Key = ImageIO.read(new File("Gold_Key.png"));
            Gold_Key = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Gold_Key == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Gold_Key", Gold_Key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Dirt";

            //Dirt = ImageIO.read(new File("Dirt.png"));
            Dirt = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Dirt == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Dirt", Dirt);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Forest";

            //Forest = ImageIO.read(new File("Forest.png"));
            Forest = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Forest == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Forest", Forest);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Female";

            //Female = ImageIO.read(new File("Female.png"));
            Female = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Female == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("GENERIC PERSON NOT NULL");
            }
            images.put("Female", Female);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Just_Grass";
            //System.out.println("Reading in JUST GRASS");
            //Just_Grass = ImageIO.read(new File("Just_Grass.png"));
            Just_Grass = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Just_Grass == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            } else {
                //System.out.println("JUST GRASS NOT NULL");
            }
            images.put("Just_Grass", Just_Grass);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String name = "Wall";
            //Wall  = ImageIO.read(new File("Wall.png"));
            Wall = ImageIO.read(getClass().getResource("/sqaure_world/" + name + ".png"));
            if (Just_Grass == null) {
                //System.out.println("ERROR ERROR NULL IMAGE");
            }
            images.put("Wall", Wall);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
