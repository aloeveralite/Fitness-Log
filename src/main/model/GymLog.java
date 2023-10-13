package model;

import java.util.ArrayList;
import java.util.List;

// a gym exercises log that contains lists of gym exercises that can be modified, viewed, and tracked
public class GymLog {
    private List<GymExercise> gymExercises;

    // EFFECTS: constructs a list of gym exercises
    public GymLog() {
        this.gymExercises = new ArrayList<GymExercise>();
    }

    // EFFECTS: adds a gym exercise to the list of gym exercises
    public void recordExercise(GymExercise gymExercise) {
        this.gymExercises.add(gymExercise);
    }

    // EFFECTS: removes a gym exercise from the list of gym exercises
    public void deleteExercise(GymExercise gymExercise) {
        this.gymExercises.remove(gymExercise);
    }

    // EFFECTS: returns the gym exercise and its information at given index
    public String viewGymExercise(int index) {
        GymExercise gymExerciseInfo = gymExercises.get(index);
        String information = gymExerciseInfo.getName() + ": " + gymExerciseInfo.getTargetMuscles()
                + " " + gymExerciseInfo.getWeight() + "lbs " + gymExerciseInfo.getSets() + "x"
                + gymExerciseInfo.getRepetition();

        return information;
    }

    // EFFECTS: returns the number of gym exercises recorded
    public int totalGymExercisesRecorded() {
        return this.gymExercises.size();
    }

    // REQUIRES: gymExercise.getWeight() > 0
    // MODIFIES: this
    // EFFECTS: returns the percentage of weight improvement between gym exercises with the same name
    //              added more than once
    public int trackWeightProgress(String gymExerciseName) {
        List<GymExercise> recurringExercises = new ArrayList<>();

        for (GymExercise gm : this.gymExercises) {
            if (gm.getName().equals(gymExerciseName)) {
                recurringExercises.add(gm);
            }
        }

        GymExercise firstExercise = recurringExercises.get(0);
        GymExercise lastExercise = recurringExercises.get(recurringExercises.size() - 1);
        int improvementPercent = (int)((((double)lastExercise.getWeight() - (double)firstExercise.getWeight())
                / (double)(firstExercise.getWeight()))
                * 100);

        return improvementPercent;
    }

    // REQUIRES: totalGymExercisesRecorded() > 0
    // MODIFIES: this
    // EFFECTS: returns a list of exercises that target a specific muscle
    public List<GymExercise> makeMuscleSpecialization(String targetMuscles) {
        List<GymExercise> muscleSpecialization = new ArrayList<>();

        for (GymExercise gm : this.gymExercises) {
            if (gm.getTargetMuscles().equals(targetMuscles)) {
                muscleSpecialization.add(gm);
            }
        }
        return muscleSpecialization;
    }

    // MODIFIES: this
    // EFFECTS: returns the exercise with the greatest weight
    //              returns null if list is empty
    //              returns the first one added if two exercises have same weight
    public GymExercise highestWeightedExercise() {
        if (this.gymExercises.isEmpty()) {
            return null;
        } else {
            GymExercise strongestExercise = this.gymExercises.get(0);

            for (GymExercise gm : this.gymExercises) {
                if (gm.getWeight() > strongestExercise.getWeight()) {
                    strongestExercise = gm;
                }
            }
            return strongestExercise;
        }
    }

    public List<GymExercise> getGymExercises() {
        return this.gymExercises;
    }
}
