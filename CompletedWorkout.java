import java.io.*;
import java.util.Scanner;
import java.io.Serializable;
import java.util.ArrayList;

public class CompletedWorkout extends Workout
{
    public String date;
    public ArrayList<CompletedExercise> completedExercises = new ArrayList<>();

    public CompletedWorkout(String workoutName)
    {
        super(workoutName);
        System.out.print("Enter date of completed workout (mm/dd/yy): ");
        Scanner inputScanner = new Scanner(System.in);
        this.date = inputScanner.nextLine();  // Read in date of workout
        System.out.println();
        addCompletedExercises();  // Call method to add completed exercises
    }

    public void addCompletedExercises()
    {
        String exerciseToAdd;
        
        while (true)  // Continue to prompt for exercises until the trainer indicates the workout has ended
        {
            System.out.print("\nEnter exercise completed (F to finish the workout): ");
            Scanner inputScanner = new Scanner(System.in);
            exerciseToAdd = inputScanner.nextLine();
            
            if (exerciseToAdd.equals("F"))
            {
                break;
            }

            System.out.print("Muscle group: ");
            String muscleGroup = inputScanner.nextLine();
            completedExercises.add(new CompletedExercise(exerciseToAdd, muscleGroup));  // Add a new completed exercise
        }

        System.out.println("Workout complete!");
    }

    public void viewCompletedExercises()
    {
        for (int i=0; i<completedExercises.size(); i++) // Print exercise name, sets, reps, and weight for each completed exercise
        {
            System.out.println("\t" + completedExercises.get(i).getExerciseName());
            System.out.println("\t\tSets: " + completedExercises.get(i).getSets());
            System.out.println("\t\tReps: " + completedExercises.get(i).getReps());
            System.out.println("\t\tWeight: " + completedExercises.get(i).getWeight() + " lbs\n");
        }
    }

    public String getDate()
    {
        return date;
    }
}