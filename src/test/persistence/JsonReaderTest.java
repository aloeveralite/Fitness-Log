package persistence;

import model.GymExercise;
import model.GymLog;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Represents test cases for JsonReader
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderFileNonExistent() {
        JsonReader jsonReader = new JsonReader("./data/noSuchFile.json");

        try {
            GymLog gl = jsonReader.readGymLog();
            fail("An IOException was expected but was not caught");
        } catch (IOException e){
            // means that it has passed
        }
    }

    @Test
    void testReaderEmptyGymLog() {
        JsonReader jsonReader = new JsonReader("./data/testReaderEmptyGymLog.json");

        try {
            GymLog gl = jsonReader.readGymLog();
            assertEquals("Your gym log", gl.getName());
            assertEquals(0, gl.totalGymExercisesRecorded());
        } catch (IOException e) {
            fail("Sorry, the file could not be read");
        }
    }

    @Test
    void testReaderGeneralGymLog() {
        JsonReader jsonReader = new JsonReader("./data/testReaderGeneralGymLog.json");

        try {
            GymLog gl = jsonReader.readGymLog();
            assertEquals("Your gym log", gl.getName());
            List<GymExercise> gymExercises = gl.getGymExercises();
            assertEquals(3, gymExercises.size());
            matchGymExercise("push-ups", "chest", false, 0, 3,
                    20, gymExercises.get(0));
            matchGymExercise("squats", "legs", true, 205, 3,
                    8, gymExercises.get(1));
            matchGymExercise("bicep-curls", "arms", true, 50, 3,
                    10, gymExercises.get(2));
        } catch (IOException e) {
            fail("Sorry, the file could not be read");
        }
    }
}

// Aid from: JsonSerializationDemo
// Class: JsonReaderTest
