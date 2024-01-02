import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class SessionReport implements Serializable
{
    public Client client;
    public String date;

    public SessionReport(Client client, String date)
    {
        this.client = client;
        this.date = date;
    }

    public void displayWorkout()
    {
        System.out.println("\nPersonal training session report: " + date + "\nClient name: " + client.getUsername() + "\n");
        int workoutIndex = 0;
        for (int i=0; i<client.completedWorkouts.size(); i++)
        {
            if (client.completedWorkouts.get(i).getDate().equals(date))  // Display workout for date entered
            {
                System.out.println(client.completedWorkouts.get(i).getWorkoutName());
                client.completedWorkouts.get(i).viewCompletedExercises();
                workoutIndex = i;  // Save the workout index for that date
                break;
            }
        }

        writeToFile(workoutIndex);  // Call writeToFile method for the workout found
    }

    public void writeToFile(int workoutIndex)
    {
        String clientName = client.getUsername();
        String fileName = clientName + "SessionReport.txt"; // Create file name for the session report

        try 
        {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(generateFileContent(workoutIndex)); // Call method to generate the file content and write it to the file
            writer.close();
            System.out.println("Success: Session report file created");
        } 
        catch (IOException e) 
        {
            System.out.println("Error: Session report could not be generated");
        }
    }

    public String generateFileContent(int workoutIndex)
    {
        StringBuilder fileContent = new StringBuilder();  // Use string builder to get a string will all the info to write to the file
        fileContent.append("Personal training session report: " + date + "\nClient name: " + client.getUsername() + "\n");  // Append header info
        fileContent.append("\n" + client.completedWorkouts.get(workoutIndex).getWorkoutName());  // Append workout name
        for (int i=0; i<client.completedWorkouts.get(workoutIndex).completedExercises.size(); i++)  // Add exercise name, sets, reps, and weight
        {
            fileContent.append("\n\t" + client.completedWorkouts.get(workoutIndex).completedExercises.get(i).getExerciseName());
            fileContent.append("\n\t\tSets: " + client.completedWorkouts.get(workoutIndex).completedExercises.get(i).getSets());
            fileContent.append("\n\t\tReps: " + client.completedWorkouts.get(workoutIndex).completedExercises.get(i).getReps());
            fileContent.append("\n\t\tWeight: " + client.completedWorkouts.get(workoutIndex).completedExercises.get(i).getWeight() + " lbs\n");
        }
        return fileContent.toString();  // Return completed string to be written to the file
    }
}