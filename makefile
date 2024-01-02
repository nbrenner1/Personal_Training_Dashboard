#makefile for Workout Log

all: Workout

Workout:
	javac Client.java CompletedExercise.java CompletedWorkout.java Exercise.java FileManager.java Menu.java SessionReport.java Trainer.java User.java Workout.java WorkoutPlan.java Main.java

run:
	java Main

clean:
	rm *.class

jar: all
	jar cfm Training.jar manifest.txt *.class
	java -jar Training.jar