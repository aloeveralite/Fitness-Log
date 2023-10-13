package test;

import model.GymExercise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test cases for class GymExercise
class GymExerciseTest {
    private GymExercise testGymExercise1;
    private GymExercise testGymExercise2;
    private GymExercise testGymExercise3;
    private GymExercise testGymExercise4;

    @BeforeEach
    void runBefore() {
        testGymExercise1 = new GymExercise("bench-press", "chest", true);
        testGymExercise2 = new GymExercise("push-up", "chest", false);
        testGymExercise3 = new GymExercise("pull-up", "back", false);
        testGymExercise4 = new GymExercise("Shoulder-press", "shoulders", true);
    }

    // tests the constructor
    @Test
    void testConstructor() {
        GymExercise testGymExercise = new GymExercise("sit-up", "core", false);

        assertEquals("sit-up", testGymExercise.getName());
        assertEquals("core", testGymExercise.getTargetMuscles());
        assertFalse(testGymExercise.canAddWeight());
        assertEquals(0, testGymExercise.getWeight());
        assertEquals(0, testGymExercise.getSets());
        assertEquals(0, testGymExercise.getRepetition());
    }

    // tests if some gym exercises can have weights added
    @Test
    void testAddWeight() {
        assertTrue(testGymExercise1.addWeight(165));
        assertFalse(testGymExercise2.addWeight(25));
        assertFalse(testGymExercise3.addWeight(25));
        assertTrue(testGymExercise4.addWeight(100));
    }

    // tests setting sets for a gym exercise
    @Test
    void testSetSets() {
        testGymExercise1.setSets(3);
        testGymExercise2.setSets(0);
        assertEquals(3, testGymExercise1.getSets());
        assertEquals(0, testGymExercise2.getSets());
    }

    // tests setting repetition for a gym exercise
    @Test
    void testSetRepetition() {
        testGymExercise1.setRepetition(8);
        testGymExercise2.setRepetition(0);
        assertEquals(8, testGymExercise1.getRepetition());
        assertEquals(0, testGymExercise2.getRepetition());
    }
}
