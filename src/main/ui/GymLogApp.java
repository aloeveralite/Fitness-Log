package ui;

import model.GymExercise;
import model.GymLog;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Gym Log application
public class GymLogApp {
    private static final String JSON_STORAGE = "./data/gymlog.json";
    private GymLog gymLog;
    private GymExercise gymExercise;
    private Scanner input;
    private String userName;
    private String userTargetMuscle;
    private Boolean userHasWeights;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: starts the gym log
    public GymLogApp() throws FileNotFoundException {
        jsonReader = new JsonReader(JSON_STORAGE);
        jsonWriter = new JsonWriter(JSON_STORAGE);
        startGymLog();
    }

    // MODIFIES: this
    // EFFECTS: registers user input
    private void startGymLog() {
        boolean stillRunning = true;
        String userCommand = null;

        initializer();

        while (stillRunning)  {
            mainMenu();
            userCommand = input.next();
            userCommand  = userCommand.toLowerCase();

            if (userCommand.equals("q")) {
                stillRunning = false;
            } else {
                registerUserCommand(userCommand);
            }
        }
        System.out.println("\nClosing gym log... See you next time!");
    }

    // EFFECTS: initializes gym log
    private void initializer() {
        gymLog = new GymLog("Your personal gym log");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays the main menu of options for user to enter
    private void mainMenu() {
        System.out.println("\nWelcome to " + gymLog.getName() + ".");
        System.out.println("\nTotal exercises added: " + gymLog.totalGymExercisesRecorded());

        System.out.println("\tr -> record exercise");
        System.out.println("\td -> delete exercise");
        System.out.println("\te -> select exercise");
        System.out.println("\tp -> weight progress on exercise");
        System.out.println("\tm -> make a specialized muscle workout");
        System.out.println("\th -> view exercise with heaviest weight");
        System.out.println("\ts -> save gym log to file");
        System.out.println("\tl -> load gym log from file");
        System.out.println("\tq -> quit");
    }


    // MODIFIES: this
    // EFFECTS: registers user command
    private void registerUserCommand(String userCommand) {
        if (userCommand.equals("r")) {
            userRecordExercise();
        } else if (userCommand.equals("d")) {
            userDeleteExercise();
        } else if (userCommand.equals("e")) {
            userSelectExercises();
        } else if (userCommand.equals("p")) {
            userProgress();
        } else if (userCommand.equals("m")) {
            userMuscleGroup();
        } else if (userCommand.equals("h")) {
            userStrongestExercise();
        } else if (userCommand.equals("s")) {
            userSaveGymLog();
        } else if (userCommand.equals("l")) {
            userLoadGymLog();
        } else {
            System.out.println("Invalid selection...");
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to specify the exercise they are recording
    private void userRecordExercise() {
        referenceList();

        System.out.println("\nnote: use a dash '-' instead of a space in-between words for consistency");
        System.out.println("\tWhat is the name of the exercise? ");
        userName = input.next();

        System.out.println("\tWhat body part does this exercise target? ");
        userTargetMuscle = input.next();

        System.out.println("\tDoes this exercise have weights? (y/n) ");

        if (input.next().equals("y")) {
            userHasWeights = true;
        } else {
            userHasWeights = false;
        }

        gymExercise = new GymExercise(userName, userTargetMuscle, userHasWeights);

        if (userHasWeights) {
            System.out.println("\tHow much weight (in lbs) did you lift with this exercise? ");
            gymExercise.addWeight(input.nextInt());
        }

        System.out.println("\tHow many sets? ");
        gymExercise.setSets(input.nextInt());

        System.out.println("\tHow many repetitions? ");
        gymExercise.setRepetitions(input.nextInt());

        gymLog.recordExercise(gymExercise);
    }

    // MODIFIES: this
    // EFFECTS: removes the exercise from the list of exercises
    private void userDeleteExercise() {
        referenceList();

        if (gymLog.totalGymExercisesRecorded() > 0) {
            System.out.println("\n\tWhich exercise at which index starting 0 would you like to delete? ");
            int deleteAtIndex = input.nextInt();
            gymLog.deleteExercise(gymLog.getGymExercises().get(deleteAtIndex));
            System.out.println("\n\tExercise successfully deleted!");
        } else {
            System.out.println("You have not recorded any exercises yet!");
        }
    }

    // EFFECTS: shows all the exercises recorded, how many had been added, and user may select an exercise
    //          and view an exercise in detail
    private void userSelectExercises() {
        referenceList();

        if (gymLog.totalGymExercisesRecorded() > 0) {
            System.out.println("Select an exercise by index starting from 0: ");
            System.out.println(gymLog.viewGymExercise(input.nextInt()));
        } else {
            System.out.println("You have not recorded any exercises yet!");
        }
    }

    // MODIFIES: this
    // EFFECTS: shows user's progress on an exercise
    private void userProgress() {
        referenceList();

        if (gymLog.totalGymExercisesRecorded() > 0) {
            System.out.println("Enter the name of the exercise you want to see the progress of: ");
            double improvementPercent = gymLog.trackWeightProgress(input.next());
            System.out.println("\tYou have improved by " + improvementPercent + "%");

            if (improvementPercent > 0) {
                System.out.println("Congrats! Keep up the good work.");
            } else {
                System.out.println("Trust the process, never give up!");
            }
        } else {
            System.out.println("You have not recorded any exercises yet!");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates a specialized workout for the user
    private void userMuscleGroup() {
        if (gymLog.totalGymExercisesRecorded() > 0) {
            System.out.println("\nHere is a list of target body muscles to choose from:");
            List<String> muscleGroups = new ArrayList<>();
            for (GymExercise gm : gymLog.getGymExercises()) {
                muscleGroups.add(gm.getTargetMuscles());
            }

            System.out.println(muscleGroups);

            System.out.println("\n\tEnter the target body muscles of the exercise you want to see the progress of: ");
            String newWorkout = input.next();
            List<GymExercise> specializedGroup = gymLog.makeMuscleSpecialization(newWorkout);
            List<String> specializedGroupNames = new ArrayList<>();

            for (GymExercise gm : specializedGroup) {
                if (!(specializedGroupNames.contains(gm.getName()))) {
                    specializedGroupNames.add(gm.getName());
                }
            }

            System.out.println("\n\tComplete! Here's a new " + newWorkout + " workout: ");
            System.out.println(specializedGroupNames);
        } else {
            System.out.println("You have not recorded any exercises yet!");
        }
    }

    // EFFECTS: finds user's strongest exercise
    private void userStrongestExercise() {
        System.out.println("Your strongest exercise is... " + gymLog.highestWeightedExercise().getName() + "!");
    }

    // EFFECTS: saves the gym log to file
    private void userSaveGymLog() {
        try {
            jsonWriter.openWriter();
            jsonWriter.writeGymLog(gymLog);
            jsonWriter.closeWriter();
            System.out.println("Saving " + gymLog.getName() + " to " + JSON_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Not able to write to file: " + JSON_STORAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the gym log from file
    private void userLoadGymLog() {
        try {
            gymLog = jsonReader.readGymLog();
            System.out.println("Loading " + gymLog.getName() + " from " + JSON_STORAGE);
        } catch (IOException e) {
            System.out.println("Not able to read from file: " + JSON_STORAGE);
        }
    }

    // EFFECTS: displays a list of the names of each exercise for reference
    private void referenceList() {
        System.out.println("\nFor reference, all exercises recorded so far: ");
        System.out.println(getNamesOfExercises());
    }

    // EFFECTS: gets the name of each exercise in list
    private List<String> getNamesOfExercises() {
        List<String> exerciseNames = new ArrayList<>();
        for (GymExercise gm : gymLog.getGymExercises()) {
            exerciseNames.add(gm.getName());
        }
        return exerciseNames;
    }
}

// Aid from: AccountNotRobust
// Class: TellerApp
// Aid from: JsonSerializationDemo
// Class: WorkRoomApp