package persistence;

import model.GymExercise;
import model.GymLog;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Represents test cases for JsonWriter
public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterFileInvalid() {
        try {
            GymLog gl = new GymLog("Your gym log");
            JsonWriter jsonWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriter.openWriter();
            fail("An IOException was expected but was not caught");
        } catch (IOException e) {
            // means that is has passed
        }
    }

    @Test
    void testWriterEmptyGymLog() {
        try {
            GymLog gl = new GymLog("Your gym log");
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyGymLog.json");
            jsonWriter.openWriter();
            jsonWriter.writeGymLog(gl);
            jsonWriter.closeWriter();

            JsonReader jsonReader = new JsonReader("./data/testWriterEmptyGymLog.json");
            gl = jsonReader.readGymLog();
            assertEquals("Your gym log", gl.getName());
            assertEquals(0, gl.totalGymExercisesRecorded());
        } catch (IOException e) {
            fail("An exception was thrown when it should not have");
        }
    }

    @Test
    void testWriterGeneralGymLog() {
        try {
            GymLog gl = new GymLog("Your gym log");
            gl.recordExercise(new GymExercise("barbell-rows", "back", true));
            gl.recordExercise(new GymExercise("pull-ups", "back", false));
            gl.recordExercise(new GymExercise("crunches", "core", false));
            gl.getGymExercises().get(0).addWeight(115);
            gl.getGymExercises().get(0).setSets(3);
            gl.getGymExercises().get(0).setRepetitions(10);
            gl.getGymExercises().get(1).setSets(3);
            gl.getGymExercises().get(1).setRepetitions(10);
            gl.getGymExercises().get(2).setSets(3);
            gl.getGymExercises().get(2).setRepetitions(20);

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralGymLog.json");
            jsonWriter.openWriter();
            jsonWriter.writeGymLog(gl);
            jsonWriter.closeWriter();

            JsonReader jsonReader = new JsonReader("./data/testWriterGeneralGymLog.json");
            gl = jsonReader.readGymLog();
            assertEquals("Your gym log", gl.getName());
            List<GymExercise> gymExercises = gl.getGymExercises();
            assertEquals(3, gymExercises.size());
            matchGymExercise("barbell-rows", "back", true, 115, 3,
                    10, gymExercises.get(0));
            matchGymExercise("pull-ups", "back", false, 0, 3,
                    10, gymExercises.get(1));
            matchGymExercise("crunches", "core", false, 0, 3,
                    20, gymExercises.get(2));
        } catch (IOException e) {
            fail("An exception was thrown when it should not have");
        }
    }
}

// Aid from: JsonSerializationDemo
// Class: JsonWriterTest
