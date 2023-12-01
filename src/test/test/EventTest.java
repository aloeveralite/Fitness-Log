package test;


import model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static junit.framework.TestCase.*;

// Represents tests for the event class
public class EventTest {
    private Date testDate;
    private Event testEvent;
    private Event testEvent2;

    @BeforeEach
    void runBefore() {
        testEvent = new Event("Added exercise");
        testEvent2 = new Event("Selected an exercise");
        testDate = Calendar.getInstance().getTime();
    }

    @Test
    void testConstructor() {
        assertEquals("Added exercise", testEvent.getDescription());
        assertEquals(testDate, testEvent.getDate());
    }

    @Test
    void testEqualsNull() {
        assertFalse(testEvent.equals(null));
    }

    @Test
    void testEqualsDifferentClass() {
        assertFalse(testEvent.equals(testDate));
    }

    @Test
    void testEqualsMismatch() {
        assertFalse(testEvent.equals(testEvent2));
    }

    @Test
    void testEquals() {
        assertTrue(testEvent.equals(testEvent));
        assertTrue(testEvent2.equals(testEvent2));
    }

    @Test
    void testHashCode() {
        assertEquals((13 * testEvent.getDate().hashCode()
                + testEvent.getDescription().hashCode()), testEvent.hashCode());
        assertEquals((13 * testEvent2.getDate().hashCode()
                + testEvent2.getDescription().hashCode()), testEvent2.hashCode());
    }

    @Test
    void testToString() {
        assertEquals(testDate.toString() + "\n" + "Added exercise", testEvent.toString());
    }
}

// Aid from: AlarmSystem
// Package: test
// Class: EventTest