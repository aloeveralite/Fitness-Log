package persistence;

import model.GymExercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Represents a test for json in relation to the gym exercise class
public class JsonTest {
    protected void matchGymExercise(String name, String targetMuscle, Boolean hasAddedWeight, int weight, int sets,
                                    int repetitions, GymExercise gymExercise) {
        assertEquals(name, gymExercise.getName());
        assertEquals(targetMuscle, gymExercise.getTargetMuscles());
        assertEquals(hasAddedWeight, gymExercise.canAddWeight());
        assertEquals(weight, gymExercise.getWeight());
        assertEquals(sets, gymExercise.getSets());
        assertEquals(repetitions, gymExercise.getRepetitions());
    }
}

// Aid from: JsonSerializationDemo
// Class: JsonTest


