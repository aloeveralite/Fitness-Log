package persistence;

import model.GymExercise;
import model.GymLog;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads gym log from JSON data stored in file
public class JsonReader {
    private String fileSource;

    // EFFECTS: constructs a json reader to read from the source file
    public JsonReader(String fileSource) {
        this.fileSource = fileSource;
    }

    // EFFECTS: reads gym log from file and returns it
    // throws IOException if an error occurs reading data from file
    public GymLog readGymLog() throws IOException {
        String jsonData = readSourceFile(fileSource);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseGymLog(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readSourceFile(String source) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> stringBuilder.append(s));
        }

        return stringBuilder.toString();
    }

    // EFFECTS: parses gym log from JSON object and returns it
    private GymLog parseGymLog(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        GymLog gl = new GymLog(name);
        addGymExercises(gl, jsonObject);
        return gl;
    }

    // MODIFIES: gl
    // EFFECTS: parses gym exercises from JSON object and adds it to gym log
    private void addGymExercises(GymLog gl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("gym exercises");

        for (Object json : jsonArray) {
            JSONObject nextGymExercise = (JSONObject) json;
            addGymExercise(gl, nextGymExercise);
        }
    }

    // MODIFIES: gl
    // EFFECTS: parses gym exercise from JSON object and adds it to gym log
    private void addGymExercise(GymLog gl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String targetMuscle = jsonObject.getString("targetMuscle");
        Boolean hasAddedWeight = jsonObject.getBoolean("hasAddedWeight");
        int weight = jsonObject.getInt("weight");
        int sets = jsonObject.getInt("sets");
        int repetitions = jsonObject.getInt("repetitions");
        GymExercise gymExercise = new GymExercise(name, targetMuscle, hasAddedWeight);
        gymExercise.addWeight(weight);
        gymExercise.setSets(sets);
        gymExercise.setRepetitions(repetitions);
        gl.recordExercise(gymExercise);
    }

}

// Aid from: JsonSerializationDemo
// Class: JsonReader
