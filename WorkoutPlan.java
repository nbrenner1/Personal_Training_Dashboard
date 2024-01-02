import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class WorkoutPlan implements Serializable
{
    public ArrayList<Workout> workouts = new ArrayList<>();
    public String planName;

    public WorkoutPlan(String planName)
    {
        this.planName = planName;
    }

    public void addWorkouts(ArrayList<Workout> workoutList)
    {
        String workoutToAdd;
        
        while (true)  // Ask for workouts to add until the user chooses to quit
        {
            boolean workoutExists = false;
            System.out.println("Choose an workout to add (F to finish creating workout plan):");
            for (int i=0; i<workoutList.size()-1; i++)  // Print workouts to choose from
            {
                System.out.print(workoutList.get(i).getWorkoutName()+", ");
            }
            System.out.println(workoutList.get(workoutList.size()-1).getWorkoutName()+"\n");

            System.out.print("Workout name: ");
            Scanner inputScanner = new Scanner(System.in);
            workoutToAdd = inputScanner.nextLine();
            
            if (workoutToAdd.equals("F"))
            {
                break;
            }

            for (int i=0; i<workoutList.size(); i++)  // Check if the workout exists and add it to the workout if so
            {
                if (workoutToAdd.equals(workoutList.get(i).getWorkoutName()))
                {
                    workouts.add(workoutList.get(i));
                    workoutExists = true;
                    System.out.println("Complete: workout added successfully\n");
                }
            }

            if(!workoutExists)  // If the workout does not exists, output an error message
            {
                System.out.println("Error: Workout " + workoutToAdd + " does not exist\n");
            }
        }
    }
}