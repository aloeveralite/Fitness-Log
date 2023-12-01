package test;


import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;

// Represents tests for the event class
public class EventTest {
    private Date testDate;
    private Event testEvent;

    @BeforeEach
    void runBefore() {
        testEvent = new Event("Add exercise to log");
        testDate = Calendar.getInstance().getTime();
    }

    @Test
    void testConstructor() {
        assertEquals("Add exercise to log", testEvent.getDescription());
        assertEquals(testDate, testEvent.getDate());
    }

    @Test
    void testToString() {
        assertEquals(testDate.toString() + "\n" + "Add exercise to log", testEvent.toString());
    }
}

// Aid from: AlarmSystem
// Package: test
// Class: EventTest