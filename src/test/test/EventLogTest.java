package test;

import model.Event;
import model.EventLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// represents test cases for class EventLog
public class EventLogTest {
    private Event testLog1;
    private Event testLog2;
    private Event testLog3;
    private Event testLog4;

    @BeforeEach
    public void loadEvents() {
        testLog1 = new Event("Exercise 1");
        testLog2 = new Event("Exercise 2");
        testLog3 = new Event("Exercise 3");
        testLog4 = new Event("Exercise 4");
        EventLog testEventLog = EventLog.getInstance();
        testEventLog.logEvent(testLog1);
        testEventLog.logEvent(testLog2);
        testEventLog.logEvent(testLog3);
        testEventLog.logEvent(testLog4);
    }

    @Test
    public void testLogEvent() {
        List<Event> events = new ArrayList<>();

        EventLog eventLog = EventLog.getInstance();
        for (Event event : eventLog) {
            events.add(event);
        }

        assertTrue(events.contains(testLog1));
        assertTrue(events.contains(testLog2));
        assertTrue(events.contains(testLog3));
    }

    @Test
    public void testClear() {
        EventLog eventLog = EventLog.getInstance();

        eventLog.clear();

        Iterator<Event> iterator = eventLog.iterator();

        assertTrue(iterator.hasNext());
        assertEquals("Clearing the event log.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }
}

// Aid from: AlarmSystem
// Package: test
// Class: EventLogTest
