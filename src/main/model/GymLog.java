package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a gym exercises log that contains lists of gym exercises that can be modified, viewed, and tracked
public class GymLog implements Writable {
    private List<GymExercise> gymExercises;
    private String name;

    // EFFECTS: constructs a list of gym exercises
    public GymLog(String name) {
        this.gymExercises = new ArrayList<GymExercise>();
        this.name = name;
    }

    // EFFECTS: adds a gym exercise to the list of gym exercises
    public void recordExercise(GymExercise gymExercise) {
        this.gymExercises.add(gymExercise);
        EventLog.getInstance().logEvent(new Event("Added new exercise: " + gymExercise.getName()));
    }

    // EFFECTS: removes a gym exercise from the list of gym exercises
    public void deleteExercise(GymExercise gymExercise) {
        this.gymExercises.remove(gymExercise);
    }

    // EFFECTS: returns the gym exercise and its information at given index
    public String viewGymExercise(int index) {
        EventLog.getInstance().logEvent(
                new Event("Viewed exercise: " + this.gymExercises.get(index).getName()));
        GymExercise gymExerciseInfo = gymExercises.get(index);
        String information = gymExerciseInfo.getName() + ": " + gymExerciseInfo.getTargetMuscles()
                + " " + gymExerciseInfo.getWeight() + "lbs " + gymExerciseInfo.getSets() + "x"
                + gymExerciseInfo.getRepetitions();

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

    public String getName() {
        return this.name;
    }

    @Override
    // EFFECTS: converts a gym log object to a JSON Object with all the attributes of a gym log
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("gym exercises", gymExercisesToJson());
        return json;
    }

    // EFFECTS: returns every gym exercise in the gym log as a JSON array
    private JSONArray gymExercisesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GymExercise g : this.gymExercises) {
            jsonArray.put(g.toJson());
        }

        return jsonArray;
    }
}

// Aid from: JsonSerializationDemo
// Class: WorkRoom

// Aid from: AlarmSystem
// Package: model
// Class: AlarmCode