// ***********************************************************
// Title: CSCI 240 Final Project - Personal Training database
// Author: Nick Brenner
// ***********************************************************

import java.io.*;
import java.util.Scanner;

public class Main
{
    public static void main(String args[])
    {
        System.out.println("**** Welcome to the Personal Training Log ****\nHow would you like to login?\n1- Trainer\n2- Existing client\n3- New client");
        Scanner inputScanner = new Scanner(System.in);
        int userType = inputScanner.nextInt();
        while (!(userType==1 || userType==2 || userType==3)) // Validate user choice
        {
            System.out.println();
            System.out.println("Invalid selection\nHow would you like to login?\n1- Trainer\n2- Client");
            userType = inputScanner.nextInt();
        }

        System.out.println();
        if (userType==3)  // If the user is a new client, call method to register them
        {
            registerUser();
        }
        else  // If they are an existing client or the trainer, call the login method
        {
            login();
        }
    }

    private static void login()
    {
        boolean verifiedUser = false;
        System.out.print("Username: ");
        Scanner inputScanner = new Scanner(System.in);
        String inputUsername = inputScanner.nextLine();
        System.out.print("Password: ");
        String inputPassword = inputScanner.nextLine();

        Trainer trainer = (Trainer) FileManager.loadObject("trainer.ser");  // Load in stored trainer
        if (trainer == null)  // If no stored trainer, create a new instance
        {
            trainer = new Trainer(inputUsername, inputPassword);
        }

        if ((inputUsername.equals("trainer")) && (inputPassword.equals("12345")))  // If user passes this check, they are a trainer
        {
            trainer.viewMenu();
            verifiedUser = true;
        }
        else
        {
            for (int i=0; i < trainer.clientList.size(); i++)         // FOR loop to cycle through clientList checking if this username and password exist and are correct
            {
                if ((inputUsername.equals(trainer.clientList.get(i).getUsername())) && (inputPassword.equals(trainer.clientList.get(i).getPassword())))
                {
                    trainer.clientList.get(i).viewMenu();
                    verifiedUser = true;
                }
            }
        }
        
        if(!verifiedUser)
        {
            System.out.println("Login failed - Incorrect username or password");
        }
        else  // Save data at the end of the program run
        {
            FileManager.saveData("trainer.ser", trainer);
        }
    }

    private static void registerUser()
    {
        Trainer trainer = (Trainer) FileManager.loadObject("trainer.ser");  // Load in stored trainer
        if (trainer == null)
        {
            trainer = new Trainer("trainer", "12345");
        }

        System.out.print("Enter name: ");
        Scanner inputScanner = new Scanner(System.in);
        String newUsername = inputScanner.nextLine();
        System.out.print("Create password: ");
        String newPassword = inputScanner.nextLine();

        while (newPassword.length() != 5)       // User must enter a password of length 5
        {
            System.out.println("Error: Password should be exactly 5 characters");
            System.out.println();
            System.out.print("Enter password: ");
            newPassword = inputScanner.nextLine();
        }
        
        trainer.clientList.add(new Client(newUsername, newPassword, trainer));   // Once username and password are entered, add user to the clientList
        trainer.clientList.get(trainer.clientList.size()-1).viewMenu();  // View the menu for that specific client
        FileManager.saveData("trainer.ser", trainer);  // Save data at the end of the program run
    }
}
