package test;

import model.EventLog;
import model.GymExercise;
import model.GymLog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// test cases for class GymLog
class GymLogTest {
    private GymLog testGymRepository;
    private GymExercise benchPress;
    private GymExercise inclineBench;
    private GymExercise squats;
    private GymExercise bulgarianSquat;
    private GymExercise bicepCurls;

    @BeforeEach
    void runBefore() {
        testGymRepository = new GymLog("Your gym log");
        benchPress = new GymExercise("bench-press", "chest", true);
        inclineBench = new GymExercise("incline-bench", "chest", true);
        squats = new GymExercise("squats", "legs", true);
        bulgarianSquat = new GymExercise("bulgarian-squats", "legs", true);
        bicepCurls = new GymExercise("bicep-curls", "arms", true);
        // Helper method at bottom of code
        helper(benchPress, 165, 3, 8);
        helper(inclineBench, 120, 3, 8);
        helper(squats, 205, 3, 8);
        helper(bulgarianSquat, 70, 3, 10);
        helper(bicepCurls, 50, 3, 10);
    }

    // tests the constructor
    @Test
    void testConstructor() {
        assertEquals("Your gym log", testGymRepository.getName());
        assertEquals(0, testGymRepository.totalGymExercisesRecorded());
    }

    // tests adding one exercise
    @Test
    void testAddExerciseOnce() {
        testGymRepository.recordExercise(benchPress);

        assertEquals(1, testGymRepository.totalGymExercisesRecorded());
        assertEquals(benchPress, testGymRepository.getGymExercises().get(0));
    }

    // tests adding two exercises
    @Test
    void testAddExerciseTwice() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bicepCurls);

        assertEquals(2, testGymRepository.totalGymExercisesRecorded());
        assertEquals(benchPress, testGymRepository.getGymExercises().get(0));
        assertEquals(bicepCurls, testGymRepository.getGymExercises().get(1));
    }

    // tests adding one exercise and removing it after
    @Test
    void testRemoveExerciseOnce() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.deleteExercise(benchPress);

        assertEquals(0, testGymRepository.totalGymExercisesRecorded());
    }

    // tests adding two exercises and removing them after
    @Test
    void testRemoveExerciseTwice() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bicepCurls);

        testGymRepository.deleteExercise(benchPress);
        testGymRepository.deleteExercise(bicepCurls);

        assertEquals(0, testGymRepository.totalGymExercisesRecorded());
    }

    // tests adding an exercise and viewing it
    @Test
    void testViewGymExercise() {
        testGymRepository.recordExercise(benchPress);

        assertEquals(
                "bench-press: chest 165lbs 3x8", testGymRepository.viewGymExercise(0));
    }

    // tests adding an exercise twice and viewing both
    @Test
    void testViewGymExerciseTwice() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bulgarianSquat);

        assertEquals(
                "bench-press: chest 165lbs 3x8", testGymRepository.viewGymExercise(0));
        assertEquals(
                "bulgarian-squats: legs 70lbs 3x10", testGymRepository.viewGymExercise(1));
    }

    // tests total number of gym exercises recorded
    @Test
    void testTotalGymExercisesRecordedNone() {
        assertEquals(0, testGymRepository.totalGymExercisesRecorded());
    }

    // tests total number of gym exercises recorded
    @Test
    void testTotalGymExercisesRecorded() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bulgarianSquat);
        testGymRepository.recordExercise(inclineBench);
        testGymRepository.deleteExercise(benchPress);

        assertEquals(2, testGymRepository.totalGymExercisesRecorded());
    }

    // tests adding only one exercise so no progress seen
    @Test
    void testTrackWeightProgressNone() {
        testGymRepository.recordExercise(bicepCurls);

        assertEquals(0, testGymRepository.trackWeightProgress("bicep-curls"));
    }

    // tests adding two of the same exercises and recording the progress
    @Test
    void testTrackWeightProgressTwice() {
        testGymRepository.recordExercise(squats);

        GymExercise squats2 = new GymExercise("squats", "legs", true);
        helper(squats2, 235, 3, 8);
        testGymRepository.recordExercise(squats2);

        assertEquals(14, testGymRepository.trackWeightProgress("squats"));
    }

    // tests adding multiple of the same exercises and recording the progress
    @Test
    void testTrackWeightProgressMultiple() {
        testGymRepository.recordExercise(squats);

        GymExercise squats2 = new GymExercise("squats", "legs", true);
        helper(squats2, 235, 3, 8);
        testGymRepository.recordExercise(squats2);

        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bicepCurls);

        GymExercise squats3 = new GymExercise("squats", "legs", true);
        helper(squats3, 265, 3, 8);
        testGymRepository.recordExercise(squats3);

        testGymRepository.recordExercise(bulgarianSquat);

        assertEquals(29, testGymRepository.trackWeightProgress("squats"));
    }

    // tests adding four exercises, two of which are the same muscle group, and
    // making a muscle group of that muscle containing the two exercises but doesn't
    // include the other two exercises added
    @Test
    void testMakeMuscleGroup() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bicepCurls);
        testGymRepository.recordExercise(inclineBench);
        testGymRepository.recordExercise(squats);

        assertEquals(2, testGymRepository.makeMuscleSpecialization("chest").size());
        assertEquals(benchPress, testGymRepository.makeMuscleSpecialization("chest").get(0));
        assertEquals(inclineBench, testGymRepository.makeMuscleSpecialization("chest").get(1));
    }

    // tests adding four exercises, but none are added to the muscle group
    // as the muscle group requires a muscle none of the other exercises target
    @Test
    void testMakeMuscleGroupNone() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(bicepCurls);
        testGymRepository.recordExercise(inclineBench);
        testGymRepository.recordExercise(squats);

        assertEquals(0, testGymRepository.makeMuscleSpecialization("shoulders").size());
    }

    // tests adding nothing and finding highest weighed exercise
    @Test
    void testHighestWeightedExerciseNull() {
        assertNull(testGymRepository.highestWeightedExercise());
    }

    // tests adding five exercises and finding which has highest weights
    @Test
    void testHighestWeightedExercise() {
        testGymRepository.recordExercise(benchPress);
        testGymRepository.recordExercise(inclineBench);
        testGymRepository.recordExercise(bicepCurls);
        testGymRepository.recordExercise(bulgarianSquat);
        testGymRepository.recordExercise(squats);

        assertEquals(squats, testGymRepository.highestWeightedExercise());
    }

    // tests two exercises being added with the same weight, but returning the first one added
    @Test
    void testHighestWeightedExerciseSameWeight() {
        GymExercise ShoulderPress = new GymExercise("shoulder-press", "shoulders",
                true);
        GymExercise ArnoldPress = new GymExercise("arnold-press", "shoulders", true);

        helper(ShoulderPress, 100, 3, 8);
        helper(ArnoldPress, 100, 3, 8);

        testGymRepository.recordExercise(ArnoldPress);
        testGymRepository.recordExercise(ShoulderPress);

        assertEquals(ArnoldPress, testGymRepository.highestWeightedExercise());
    }

    // Helper method to quickly apply weight, sets, and repetition for test gym exercises
    void helper(GymExercise gymExercise, int weight, int sets, int repetition) {
        gymExercise.addWeight(weight);
        gymExercise.setSets(sets);
        gymExercise.setRepetitions(repetition);
    }
}