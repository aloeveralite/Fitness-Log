package model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

// Represents a log of gym log events using the Singleton Design Pattern ensuring only one
// EventLog instantiated in the system and that it can be accessed globally in the system
// as a single instance
public class EventLog implements Iterable<Event> {
    private static EventLog eventLog;
    private Collection<Event> events;

    // EFFECTS: constructs a singleton design pattern that prevents external construction
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // EFFECTS: returns the same instance of EventLog
    //          if it did not exist it is instantiated
    public static EventLog getInstance() {
        if (eventLog == null) {
            eventLog = new EventLog();
        }

        return eventLog;
    }

    // EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECTS: clears the event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Clearing the event log."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}

// Aid from: AlarmSystem
// Package: model
// Class: EventLog