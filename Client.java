import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class Client extends User implements Menu
{
    WorkoutPlan currentPlan;
    ArrayList<CompletedWorkout> completedWorkouts = new ArrayList<>();
    public int age;
    public int height;
    public float weight;
    public Trainer trainer;

    public Client(String username, String password, Trainer trainer)
    {
        super(username, password);
        this.trainer = trainer;
        Scanner inputScanner = new Scanner(System.in);  // Read in and set client-specific data
        System.out.print("Please enter age: ");
        this.age = inputScanner.nextInt();
        System.out.print("Please enter height (inches): ");
        this.height = inputScanner.nextInt();
        System.out.print("Please enter weight (lbs): ");
        this.weight = inputScanner.nextFloat();
        this.numMessages = 0;
    }

    public void viewMenu()
    {
        while (true)
        {
            System.out.println("\n******** Client dashboard ********\n1- View current training plan\n2- View completed workouts\n3- View progress\n4- Get an exercise suggestion\n5- Message your trainer\n6- Check your inbox " + numMessages + "\n7- Logout");
            Scanner inputScanner = new Scanner(System.in);   // Read in choice and call appropriate client function
            int clientChoice = inputScanner.nextInt();
            System.out.println();

            if (clientChoice == 1)
            {
                viewCurrentPlan();
            }
            else if (clientChoice == 2)
            {
                viewCompletedWorkouts();
            }
            else if (clientChoice == 3)
            {
                viewProgress();
            }
            else if (clientChoice == 4)
            {
                getExerciseSuggestion();
            }
            else if (clientChoice == 5)
            {
                sendMessage();
            }
            else if (clientChoice == 6)
            {
                viewMessage();
            }
            else
            {
                System.out.println("Have a nice day!");
                break;  // Stop displaying the menu after this selection
            }
        }
    }

    public int getAge()
    {
        return age;
    }

    public int getHeight()
    {
        return height;
    }

    public double getWeight()
    {
        return weight;
    }

    public void setCurrentPlan(WorkoutPlan newPlan)
    {
        this.currentPlan = newPlan;  // Set plan sent by trainer as the plan for this client
    }

    public void viewCurrentPlan()
    {
        if (currentPlan == null)  // Ensure the client has a plan assigned
        {
            System.out.println("No plan currently assigned");
            return;
        }

        System.out.println("\nCurrent training plan:");
        for (int i=0; i<currentPlan.workouts.size(); i++)
        {
            System.out.println(currentPlan.workouts.get(i).getWorkoutName());  // Print each workout name
            for (int j=0; j<currentPlan.workouts.get(i).exercises.size(); j++)  // For each workout, print the exercises
            {
                System.out.println("\t"+currentPlan.workouts.get(i).exercises.get(j).getExerciseName());
            }
            System.out.println();
        }
    }

    public void viewCompletedWorkouts()
    {
        if (completedWorkouts.size()==0)  // Check for completed workouts
        {
            System.out.println("No workouts completed - Head to the gym!");
            return;
        }

        System.out.println("\n******** Completed workouts ********");
        for (int i=0; i<completedWorkouts.size(); i++)  // For each workout, print the date, workout name, and completed exercises
        {
            System.out.println(completedWorkouts.get(i).getDate() + ": " + completedWorkouts.get(i).getWorkoutName() + "\n");
            completedWorkouts.get(i).viewCompletedExercises();
            System.out.println();
        }
    }

    public void viewProgress()
    {
        if (completedWorkouts.size() == 0)  // Check for completed workouts
        {
            System.out.println("No completed exercises - Must have at least one tracked workout to view progress");
            return;
        }

        System.out.println("\n******** View progress ********");
        System.out.print("Enter exercise you would like to see your progress in: ");  // Ask user for their desired exercise
        Scanner inputScanner = new Scanner(System.in);
        String progressExercise = inputScanner.nextLine();

        for (int i=0; i<completedWorkouts.size(); i++)
        {
            for (int j=0; j<completedWorkouts.get(i).completedExercises.size(); j++)  // For the exercise selected, print the date(s) completed
            {                                                                         // as well as the sets, reps, and weight
                if (progressExercise.equals(completedWorkouts.get(i).completedExercises.get(j).getExerciseName()))
                {
                    System.out.println(completedWorkouts.get(i).getDate() + ": ");
                    System.out.println("\tSets: " + completedWorkouts.get(i).completedExercises.get(j).getSets());
                    System.out.println("\tReps: " + completedWorkouts.get(i).completedExercises.get(j).getReps());
                    System.out.println("\tWeight: " + completedWorkouts.get(i).completedExercises.get(j).getWeight() + " lbs\n");
                }
            }
        }
    }

    public void getExerciseSuggestion()
    {
        System.out.println("\n******** Get an exercise suggestion ********");
        System.out.print("Which muscle group would you like to workout (chest, back, arms, legs): ");
        Scanner inputScanner = new Scanner(System.in);
        String desiredMuscleGroup = inputScanner.nextLine();

        boolean muscleGroupExists = false;
        System.out.println("\nPotential exercises:");
        for (int i=0; i<trainer.exerciseList.size(); i++)  // Find exercises of that muscle group and display
        {
            if (desiredMuscleGroup.equals(trainer.exerciseList.get(i).getExerciseMuscleGroup()))
            {
                muscleGroupExists = true;
                System.out.println(trainer.exerciseList.get(i).getExerciseName());
            }
        }

        if (!muscleGroupExists)  // Output if no exercises for that muscle group are found
        {
            System.out.println("Sorry: No exercises available for that muscle group");
        }
    }

    public void sendMessage()
    {
        System.out.println("******** Message your trainer ********");
        System.out.println("Type your message (press [ENTER] to send):"); // Prompt for message
        Scanner inputScanner = new Scanner(System.in);
        String messageToSend = inputScanner.nextLine();
        Message message = new Message(this, messageToSend, true);  // Create new instance of a message
        message.sendMessage();
        trainer.numMessages++;  // Increment number of messages for the trainer
    }

    public void viewMessage()
    {
        if (numMessages == 0)  // Check for messages
        {
            System.out.println("\nNo new messages");
            return;
        }

        String fileName;
        File file;
        for (int i=1; i<=numMessages; i++)
        {
            fileName = username + i + "Message.txt";  // Get file name
            file = new File(fileName);
            try
            {
                if(file.createNewFile()) // If the file was created, the message doesn't already exist
                {
                    file.delete();
                    return;
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) 
            {
                String line;
                System.out.println("Message from your trainer:");
                while ((line = bufferedReader.readLine()) != null)  // Read message from fileName
                {
                    System.out.println(line);
                }
                System.out.println();
                file.delete();  // Delete the file once it has been read
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
        

        this.numMessages = 0;  // Reset number of messages to 0 after reading all messages
    }
}