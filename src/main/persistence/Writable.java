package persistence;

import org.json.JSONObject;

// Represents an interface for converting objects to JSON objects
public interface Writable {
    // EFFECTS: returns the object at hand as a JSON object
    JSONObject toJson();
}

// Aid from: JsonSerializationDemo
// Class: Writable
