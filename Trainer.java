import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class Trainer extends User implements Menu
{
    public ArrayList<Client> clientList = new ArrayList<>();
    public ArrayList<Workout> workoutList = new ArrayList<>();
    public ArrayList<Exercise> exerciseList = new ArrayList<>();

    public Trainer(String username, String password)
    {
        super(username, password);
        this.numMessages = 0;
        exerciseList.add(new Exercise("Bench press", "chest"));
        exerciseList.add(new Exercise("Bicep curls", "arms"));
        exerciseList.add(new Exercise("Tricep press-down", "arms"));
        exerciseList.add(new Exercise("Squats", "legs"));
        exerciseList.add(new Exercise("Pec flys", "chest"));
        exerciseList.add(new Exercise("Leg extensions", "legs"));
        exerciseList.add(new Exercise("Lat pulldowns", "back"));
        exerciseList.add(new Exercise("Rows", "back"));
    }

    public void viewMenu()
    {
        while (true)
        {
            System.out.println("\n******** Trainer Dashboard ********\n1- Add client\n2- Remove client\n3- View current clients\n4- Add exercise\n5- Remove exercise\n6- Create workout\n7- Track workout\n8- Build workout plan\n9- Generate session report\n10- Message a client\n11- Check your inbox " + numMessages + "\n12- Logout");
            Scanner inputScanner = new Scanner(System.in);   // Read in choice and call appropriate trainer function
            int trainerChoice = inputScanner.nextInt();
            System.out.println();

            if (trainerChoice == 1)
            {
                addClient();
            }
            else if (trainerChoice == 2)
            {
                removeClient();
            }
            else if (trainerChoice == 3)
            {
                viewClientList();
            }
            else if (trainerChoice == 4)
            {
                addExercise();
            }
            else if (trainerChoice == 5)
            {
                removeExercise();
            }
            else if (trainerChoice == 6)
            {
                createWorkout();
            }
            else if (trainerChoice ==7)
            {
                trackWorkout();
            }
            else if (trainerChoice == 8)
            {
                buildWorkoutPlan();
            }
            else if (trainerChoice == 9)
            {
                generateSessionReport();
            }
            else if (trainerChoice == 10)
            {
                sendMessage();
            }
            else if (trainerChoice == 11)
            {
                viewMessage();
            }
            else
            {
                System.out.println("Have a nice day!");
                break;
            }
        }
    }

    public void addClient()
    {
        System.out.println("******** Add a client ********");  // Add a client method allows the trainer to add clients
        System.out.print("Enter client name: ");
        Scanner inputScanner = new Scanner(System.in);
        String inputUsername = inputScanner.nextLine();
        System.out.print("Enter password: ");
        String inputPassword = inputScanner.nextLine();
        System.out.println();

        while (inputPassword.length() != 5)  // Password must be 5 characters long
        {
            System.out.println("Error: Password should be exactly 5 characters");
            System.out.println();
            System.out.print("Enter password: ");
            inputPassword = inputScanner.nextLine();
            System.out.println();
        }

        clientList.add(new Client(inputUsername, inputPassword, this));   // Add created client to clientList

        System.out.println("Success: Client added to client list");
    }

    public void removeClient()
    {
        System.out.println("******** Remove a client ********");
        System.out.print("Enter client name: ");
        Scanner inputScanner = new Scanner(System.in);
        String inputUsername = inputScanner.nextLine();
        boolean clientExists = false;
        
        for (int i=0; i < clientList.size(); i++)       // FOR loop to cycle through clientList to find the index
        {                                               // of the client to remove and verify they exist
            if (inputUsername.equals(clientList.get(i).getUsername()))
            {
                clientList.remove(i);                   // If found, remove and change clientExists to true
                clientExists = true;
            }
        }

        System.out.println();
        if (clientExists)        // Evaluate state of clientExists and display appropriate message
        {
            System.out.println("Success: Client removed from client list");
        }
        else
        {
            System.out.println("Error: Client not found");
        }
    }

    public void viewClientList()
    {
        if (clientList.size()==0)  // Ensure clients exist
        {
            System.out.println("No clients found - Add a client to view client list");
            return;
        }

        System.out.println("******** View current clients ********");
        for (int i=0; i<clientList.size(); i++)  // Print name, age, height, and weight of each client
        {                                                          
            System.out.println("Name: " + clientList.get(i).getUsername());
            System.out.println("Age: " + clientList.get(i).getAge());
            System.out.println("Height: " + clientList.get(i).getHeight()/12 + "ft " + clientList.get(i).getHeight()%12 + "in");
            System.out.println("Weight: " + clientList.get(i).getWeight() + " lbs");
            System.out.println();
        }
    }

    public void addExercise()
    {
        boolean exerciseAdded = true;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("******** Add exercise ********");
        System.out.print("Enter exercise name: ");
        String exerciseName = inputScanner.nextLine();
        System.out.print("Enter muscle group: ");
        String muscleGroup = inputScanner.nextLine();

        for (int i=0; i<exerciseList.size(); i++)  // FOR loop to ensure entered exercise doesn't already exist
        {
            if (exerciseList.get(i).getExerciseName().equals(exerciseName))
            {
                System.out.println("\nError: Exercise already exists");
                exerciseAdded = false;
                break;
            }
        }

        if (exerciseAdded) // If exerciseAdded is true, add the exercise and print a success message
        {
            exerciseList.add(new Exercise(exerciseName, muscleGroup));
            System.out.println("\nComplete: Exercise added successfully");
        }
    }

    public void removeExercise()
    {
        boolean exerciseRemoved = false;
        Scanner inputScanner = new Scanner(System.in);
        System.out.println("******** Remove exercise ********");
        System.out.print("Enter exercise name: ");
        String exerciseName = inputScanner.nextLine();

        for (int i=0; i<exerciseList.size(); i++)  // FOR loop to find exercise in list and remove
        {
            if (exerciseList.get(i).getExerciseName().equals(exerciseName))
            {
                exerciseList.remove(i);
                System.out.println("\nComplete: Exercise removed successfully");
                exerciseRemoved = true;
                break;
            }
        }

        if (!exerciseRemoved)  // If not removed, it must not exist
        {
            System.out.println("\nError: Exercise does not exist");
        }
    }

    public void createWorkout()
    {
        System.out.println("******** Create a workout ********");
        System.out.print("Enter workout name: ");
        Scanner inputScanner = new Scanner(System.in);
        String workoutName = inputScanner.nextLine();
        workoutList.add(new Workout(workoutName)); // Add a new instance of Workout class to workoutList
        System.out.println();

        this.exerciseList = workoutList.get(workoutList.size()-1).addExercises(exerciseList);  // Call addExercises method in workout class
        System.out.println("\nWorkout " + workoutName + " has been saved");
    }

    public void trackWorkout()
    {
        if (workoutList.size() == 0)  // Ensure workouts exist
        {
            System.out.println("Error: No workouts found - Create a workout before tracking");
            return;
        }
        else if (clientList.size() == 0)  // At least one client must exist to track a workout
        {
            System.out.println("Error: No clients found - Add clients before tracking a workout");
            return;
        }

        System.out.println("******** Track a workout ********");
        System.out.println("Which client completed the workout? ");
        showClientList();  // Show clients to choose from
        System.out.print("Enter client name: ");
        Scanner inputScanner = new Scanner(System.in);
        String currentClient = inputScanner.nextLine();

        boolean clientExists = false;
        int clientIndex = 0;
        for (int w=0; w<clientList.size(); w++)  // FOR loop to find client index
        {
            if (currentClient.equals(clientList.get(w).getUsername()))
            {
                clientExists = true;
                clientIndex = w;
            }
        }
        
        if (clientExists)
        {
            System.out.println("Select workout to track: ");
            for (int k=0; k<workoutList.size(); k++) // FOR loop to display workouts to choose from
            {
                workoutList.get(k).viewWorkout();
                System.out.println();
            }

            System.out.print("Enter workout name: ");
            String workoutCompleted = inputScanner.nextLine();
            boolean workoutExists = false;
            for (int j=0; j<workoutList.size(); j++)  // Ensure entered workout is a valid option
            {
                if (workoutCompleted.equals(workoutList.get(j).getWorkoutName()))
                {
                    workoutExists = true;
                }
            }
            // Add a new instance of CompletedWorkout to client's completed workout list
            if (workoutExists)
            {
                clientList.get(clientIndex).completedWorkouts.add(new CompletedWorkout(workoutCompleted));
            }
            else
            {
                System.out.println("Error: workout does not exist");
            }
            
        }
        else
        {
            System.out.println("Client does not exist");
        }
    }

    public void buildWorkoutPlan()
    {
        if (workoutList.size() == 0)  // Ensure at least one workout exists
        {
            System.out.println("Create at least one workout before making a workout plan");
            return;
        }
        else if (clientList.size() == 0)  // Workout plans must be assigned to clients
        {
            System.out.println("Error: Workout plans must be assigned to clients - No clients found");
            return;
        }

        System.out.println("******** Build a workout plan ********");
        System.out.print("Enter plan name: ");
        Scanner inputScanner = new Scanner(System.in);
        String planName = inputScanner.nextLine();

        WorkoutPlan newPlan = new WorkoutPlan(planName);  // Create a new workout plan
        newPlan.addWorkouts(workoutList);  // Call methods to add workouts to the new plan

        showClientList();
        System.out.print("\nWhich client should be assigned this plan: ");
        String assignedClient = inputScanner.nextLine();
        boolean clientExists = false;
        for (int j=0; j<clientList.size(); j++)  // Ensure client exists and set the plan as their current plan
        {
            if (assignedClient.equals(clientList.get(j).getUsername()))
            {
                clientList.get(j).setCurrentPlan(newPlan);
                clientExists = true;
                System.out.println("Complete: Workout plan has been assigned to " + assignedClient);
            }
        }

        if (!clientExists)
        {
            System.out.println("Error: Client " + assignedClient + " does not exist");
        }
    }

    public void generateSessionReport()
    {
        if (clientList.size() == 0)  // Ensure at least one client exists
        {
            System.out.println("Error: No clients found");
            return;
        }

        System.out.println("******** Generate Session Report ********");
        System.out.println("Which client completed the session?");
        showClientList();
        System.out.print("Enter client name: ");
        Scanner inputScanner = new Scanner(System.in);
        String enteredClient = inputScanner.nextLine();

        boolean clientExists = false;
        int clientIndex = 0;
        for (int j=0; j<clientList.size(); j++)  // Check if enteredClient exists and get index
        {
            if (enteredClient.equals(clientList.get(j).getUsername()))
            {
                clientIndex = j;
                clientExists = true;
                break;
            }
        }

        if (!clientExists)
        {
            System.out.println("Error: Client " + enteredClient + " does not exist");
            return;
        }
        else if (clientList.get(clientIndex).completedWorkouts.size() == 0)  // If the client has not completed any workouts,
        {                                                                    // a session report cannot be generated
            System.out.println("Client " + enteredClient + " has not completed any sessions");
            return;
        }

        System.out.print("Enter date of session (mm/dd/yy): ");
        String enteredDate = inputScanner.nextLine();
        boolean dateExists = false;
        for (int k=0; k<clientList.get(clientIndex).completedWorkouts.size(); k++)  // FOR loop to ensure the client worked out
        {                                                                           // on the entered day
            if (clientList.get(clientIndex).completedWorkouts.get(k).getDate().equals(enteredDate))
            {
                dateExists = true;
                break;
            }
        }

        if (!dateExists)
        {
            System.out.println("Client " + enteredClient + " did not completed a session on " + enteredDate);
            return;
        }

        SessionReport sessionReport = new SessionReport(clientList.get(clientIndex), enteredDate);  // Generate session report method called
        sessionReport.displayWorkout();  // Show the workout for that client on that date
    }

    public void sendMessage()
    {
        System.out.println("Which client would you like to message?");
        showClientList();
        System.out.print("Enter client name: ");
        Scanner inputScanner = new Scanner(System.in);
        String enteredClient = inputScanner.nextLine();

        boolean clientExists = false;
        int clientIndex = 0;
        for (int j=0; j<clientList.size(); j++)  // Ensure client exists and get index
        {
            if (enteredClient.equals(clientList.get(j).getUsername()))
            {
                clientIndex = j;
                clientExists = true;
                break;
            }
        }

        if (!clientExists)
        {
            System.out.println("Error: Client " + enteredClient + " does not exist");
            return;
        }

        System.out.println("\nType your message (press [ENTER] to send):");
        String messageToSend = inputScanner.nextLine();  // Read in message to be sent to the client
        Message message = new Message(clientList.get(clientIndex), messageToSend, false);  // Create a new message
        message.sendMessage();  // Call sendMessage method for the message
        clientList.get(clientIndex).numMessages++;  // Increment the number of messages for that client
    }

    public void viewMessage()
    {
        ArrayList<String> potentialFileNames = new ArrayList<>(); // ArrayList to store potential file names
        for (int i=0; i<clientList.size(); i++)
        {
            potentialFileNames.add(clientList.get(i).getUsername() + "TrainerMessage.txt");
        }

        boolean hasMessages = false;
        boolean clientMessage = false;
        for (int j=0; j<potentialFileNames.size(); j++)
        {
            File file = new File(potentialFileNames.get(j));

            try
            {
                if(file.createNewFile())  // If file is created, it did not previously exists,
                {                         // thus the message from the client does not exists
                    file.delete();
                }
                else  // If the file was not created, it must have already existed - Trainer has a message!
                {
                    hasMessages = true;
                    clientMessage = true;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            
            if (hasMessages)  // If message is found, read it and display it as well as its sender
            {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(potentialFileNames.get(j))))
                {
                    String line;
                    System.out.println("Message from " + clientList.get(j).getUsername() + ":");
                    while ((line = bufferedReader.readLine()) != null) 
                    {
                        System.out.println(line);
                    }
                    file.delete();  // Delete the message after reading
                    System.out.println();
                } 
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
            clientMessage = false;
        }

        if (!hasMessages)
        {
            System.out.println("\nNo new messages");
        }
        
        this.numMessages = 0;  // Reset number of messages to 0 after all messages are read
    }

    public void showClientList()  // Method to display the names of current clients
    {
        System.out.println("Current client list:");
        for (int i=0; i<clientList.size(); i++)
        {
            System.out.println(clientList.get(i).getUsername());
        }
        System.out.println();
    }
}