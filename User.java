import java.io.*;
import java.io.Serializable;

public class User implements Serializable
{
    String username;
    String password;
    int numMessages;

    public User(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public int getNumMessages()
    {
        return numMessages;
    }
}