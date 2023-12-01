package model;

import java.util.Calendar;
import java.util.Date;

// Represents a gym log event
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date eventDate;
    private String eventDescription;

    // EFFECTS: constructs an event with a description and current date/time stamp with
    //          parenthesis string of description of event
    public Event(String description) {
        eventDate = Calendar.getInstance().getTime();
        this.eventDescription = description;
    }

    // EFFECTS: returns the date of the event with time
    public Date getDate() {
        return eventDate;
    }

    // EFFECTS: returns the description of the event
    public String getDescription() {
        return eventDescription;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Event otherEvent = (Event) other;

        return (this.eventDate.equals(otherEvent.eventDate)
                && this.eventDescription.equals(otherEvent.eventDescription));
    }

    @Override
    public int hashCode() {
        return (HASH_CONSTANT * eventDate.hashCode() + eventDescription.hashCode());
    }

    @Override
    public String toString() {
        return eventDate.toString() + "\n" + eventDescription;
    }
}

// Aid from: AlarmSystem
// Package: model
// Class: Event