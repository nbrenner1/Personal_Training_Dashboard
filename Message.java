import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class Message implements Serializable
{
    public Client client;
    public String message;
    public boolean toTrainer;

    public Message(Client client, String message, boolean toTrainer)
    {
        this.client = client;
        this.message = message;
        this.toTrainer = toTrainer;
    }

    public void sendMessage()
    {
        String clientName = client.getUsername();
        String fileName;
        if (toTrainer)  // If the message is to a trainer, create a file that reflects such
        {
            fileName = clientName + "TrainerMessage.txt";
        }
        else
        {
            int messageNumber = client.getNumMessages() + 1;
            fileName = clientName + messageNumber + "Message.txt";  // If to a client, add a number to allow for multiple messages
        }

        try 
        {
            File file = new File(fileName);
            if(!file.createNewFile())
            {
                if (!toTrainer)  // Check for full inbox
                {
                    System.out.println("\n" + clientName + " has not opened your previous message - Wait to send another message");
                }
                else
                {
                    System.out.println("\nTrainer has not opened your previous message - Wait to send another message");
                }
                return;
            }
            FileWriter writer = new FileWriter(file);
            writer.write(message);  // Write message to file
            writer.close();
            System.out.println("\nMessage sent successfully");
        } 
        catch (IOException e) 
        {
            System.out.println("\nError: Message could not be sent");
            e.printStackTrace();
        }
    }
}