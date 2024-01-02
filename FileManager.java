import java.io.*;
import java.util.ArrayList;

public class FileManager
{
    public static void saveData(String trainerFilepath, Object trainer)
    {  // Call saveObject method to save each of the desired states
        saveObject(trainerFilepath, trainer);
    }

    public static Object loadObject(String filepath)
    {
        Object loadedObject = null;

        try
        {
            FileInputStream file = new FileInputStream(filepath);  // Read in from filepath
            ObjectInputStream in = new ObjectInputStream(file);
            loadedObject = in.readObject();
            in.close();
            file.close();
        }
        catch (FileNotFoundException e)
        {
            
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return loadedObject;
    }

    private static void saveObject(String filepath, Object o)
    {
        try
        {
            FileOutputStream file = new FileOutputStream(filepath);   // Save object to filepath
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(o);
            out.close();
            file.close();
        }
        catch(IOException e)
        {
            System.out.println("Error saving data to " + filepath);
            e.printStackTrace();
        }
    }
}