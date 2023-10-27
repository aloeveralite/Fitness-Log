package persistence;

import model.GymLog;
import org.json.JSONObject;

import java.io.*;

// Represents a writer that writes JSON representation of gym log to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter printWriter;
    private String destination;

    // EFFECTS: constructs a writer that writes to the destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens the writer
    //          throws FileNotFoundException if destination file cannot be opened for writing
    public void openWriter() throws FileNotFoundException {
        printWriter = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of gym log to file
    public void writeGymLog(GymLog gl) {
        JSONObject json = gl.toJson();
        writeStringToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes the writer
    public void closeWriter() {
        printWriter.close();
    }

    // MODIFIES: this
    // EFFECTS: writes the string to the file
    private void writeStringToFile(String json) {
        printWriter.print(json);
    }

}

// Aid from: JsonSerializationDemo
// Class: JsonWriter