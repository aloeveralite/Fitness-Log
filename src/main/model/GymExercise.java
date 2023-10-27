package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a gym exercise that has its name, target muscles, ability to add weight, weight, and number of sets
// and repetitions
public class GymExercise implements Writable {
    private String name;
    private String targetMuscle;
    private Boolean hasAddedWeight;
    private int weight;
    private int sets;
    private int repetitions;

    // EFFECTS: constructs a gym exercise with name
    public GymExercise(String name, String targetMuscle, Boolean hasAddedWeight) {
        this.name = name;
        this.targetMuscle = targetMuscle;
        this.hasAddedWeight = hasAddedWeight;
        this.weight = 0;
        this.sets = 0;
        this.repetitions = 0;
    }

    // REQUIRES: exercise must have weight
    // MODIFIES: this
    // EFFECTS: if the gym exercise has weight then adds the parameter value to weight
    public boolean addWeight(int weight) {
        if (canAddWeight()) {
            this.weight += weight;
            return true;
        } else {
            return false;
        }
    }

    // setters

    public void setSets(int sets) {
        this.sets += sets;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions += repetitions;
    }

    // getters

    public String getName() {
        return this.name;
    }

    public String getTargetMuscles() {
        return this.targetMuscle;
    }

    public boolean canAddWeight() {
        return this.hasAddedWeight;
    }

    public int getWeight() {
        return this.weight;
    }

    public int getSets() {
        return this.sets;
    }

    public int getRepetitions() {
        return this.repetitions;
    }

    @Override
    // EFFECTS: converts gym exercise to a JSON object with all the attributes of a gym exercise
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("targetMuscle", targetMuscle);
        json.put("hasAddedWeight", hasAddedWeight);
        json.put("weight", weight);
        json.put("sets", sets);
        json.put("repetitions", repetitions);
        return json;
    }
}

// Aid from: JsonSerializationDemo
// Class: Thingy
