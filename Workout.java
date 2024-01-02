import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Workout implements Serializable
{
    public ArrayList<Exercise> exercises = new ArrayList<>();
    public String workoutName;

    public Workout(String workoutName)
    {
        this.workoutName = workoutName;
    }

    public ArrayList<Exercise> addExercises(ArrayList<Exercise> exerciseList)
    {
        String exerciseToAdd;
        
        while (true)  // Ask for exercises to add until the user chooses to quit
        {
            boolean exerciseExists = false;

            if (exerciseList.size() != 0)
            {
                System.out.println("Choose an exercise to add (F to finish creating workout):");
                for (int i=0; i<exerciseList.size()-1; i++)  // Display exercises to choose from
                {
                    System.out.print(exerciseList.get(i).getExerciseName()+", ");
                }
                System.out.println(exerciseList.get(exerciseList.size()-1).getExerciseName()+"\n");
            }

            System.out.print("Exercise name: ");
            Scanner inputScanner = new Scanner(System.in);
            exerciseToAdd = inputScanner.nextLine();
            
            if (exerciseToAdd.equals("F"))
            {
                break;
            }

            for (int i=0; i<exerciseList.size(); i++)  // Ensure exercise exists and add it to workout if so
            {
                if (exerciseToAdd.equals(exerciseList.get(i).getExerciseName()))
                {
                    exercises.add(exerciseList.get(i));
                    exerciseExists = true;
                }
            }

            if (!exerciseExists)  // If it doesn't, allow the user to use it as a newly created exercise
            {
                System.out.print("Muscle group: ");
                String muscleGroup = inputScanner.nextLine();
                exercises.add(new Exercise(exerciseToAdd, muscleGroup));  // Add new exercise to list of exercises
                exerciseList.add(new Exercise(exerciseToAdd, muscleGroup));  // Add new exercise to workout
            }
            
            System.out.println("Exercise added successfully\n");
        }

        return exerciseList;  // Return updated exercise list, as new exercises may have been added
    }

    public void viewWorkout()
    {
        System.out.println("Workout name: " + workoutName);
        for (int i=0; i<exercises.size(); i++)  // FOR loop to print the exercises in the workout
        {
            System.out.println("\t"+exercises.get(i).getExerciseName());
        }
    }

    public String getWorkoutName()
    {
        return workoutName;
    }
}