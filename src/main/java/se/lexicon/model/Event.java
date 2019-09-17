package se.lexicon.model;

import java.time.LocalTime;
import java.util.UUID;

public class Event {

    private String eventId;
    private LocalTime start;
    private LocalTime end;
    private String eventDescription;

    public Event(String eventId, LocalTime start, LocalTime end, String eventDescription) {
        this.eventId = eventId;
        setStart(start);
        setEnd(end);
        setEventDescription(eventDescription);
    }

    public Event(LocalTime start, LocalTime end, String eventDescription) {
        this(UUID.randomUUID().toString(), start, end, eventDescription);
    }

    public Event(LocalTime start, int durationInMinutes, String eventDescription) {
        this(start, start.plusMinutes(durationInMinutes), eventDescription);
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public String getEventId(){
        return this.eventId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        return eventId.equals(event.eventId);
    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Event{");
        sb.append("eventId='").append(eventId).append('\'');
        sb.append(", start=").append(start);
        sb.append(", end=").append(end);
        sb.append(", eventDescription='").append(eventDescription).append('\'');
        sb.append('}');
        return sb.toString();
    }
}