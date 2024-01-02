import java.io.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Exercise implements Serializable
{
    public String exerciseName;
    public String muscleGroup;
    public int sets;
    public int reps;

    public Exercise(String exerciseName, String muscleGroup)
    {
        this.exerciseName = exerciseName;
        this.muscleGroup = muscleGroup;
    }

    public String getExerciseName()
    {
        return exerciseName;
    }

    public String getExerciseMuscleGroup()
    {
        return muscleGroup;
    }

    public void setReps(int reps)
    {
        this.reps = reps;
    }

    public void setSets(int sets)
    {
        this.sets = sets;
    }
}