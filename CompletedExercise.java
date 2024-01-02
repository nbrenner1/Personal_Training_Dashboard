import java.io.*;
import java.io.Serializable;
import java.util.Scanner;

public class CompletedExercise extends Exercise
{
    public int weight;
    public int sets;
    public int reps;

    public CompletedExercise(String exerciseName, String muscleGroup)
    {
        super(exerciseName, muscleGroup);
        Scanner inputScanner = new Scanner(System.in);  // Read in sets, reps, and weight completed
        System.out.print("Enter sets completed: ");
        this.sets = inputScanner.nextInt();
        System.out.print("Enter reps per set: ");
        this.reps = inputScanner.nextInt();
        System.out.print("Enter weight: ");
        this.weight = inputScanner.nextInt();
    }

    public int getWeight()
    {
        return weight;
    }

    public int getSets()
    {
        return sets;
    }

    public int getReps()
    {
        return reps;
    }
}
