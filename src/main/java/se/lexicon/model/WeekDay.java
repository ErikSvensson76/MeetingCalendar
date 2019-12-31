package se.lexicon.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

public class WeekDay {

    private final String weekDayId;
    private final LocalDate date;
    private Event[] events;

    public WeekDay(String weekDayId, LocalDate date, Event[] events) {
        this.weekDayId = weekDayId;
        this.date = date;
        if(events.length > 0){
            this.events = new Event[0];
            for(Event event : events){
                boolean added = addEvent(event);
                if(!added){
                    throw new IllegalArgumentException("Invalid Event[] events in constructor, could not fit all events. Event was " + event);
                }
            }
        }else {
            this.events = events;
        }
    }

    public WeekDay(LocalDate date) {
        this(UUID.randomUUID().toString(),date, new Event[0]);
    }

    public String getWeekDayId() {
        return weekDayId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Event[] getEvents() {
        return events;
    }

    public boolean addEvent(Event event){
        boolean added = false;

        if(canFit(event.getStart(), event.getEnd()) || this.events.length == 0){
            addToArray(event);
            added = true;
        }else if((event.getEnd().isBefore(events[0].getStart()) || event.getEnd().equals(events[0].getStart()))){
            addToArray(event);
            added = true;
        }

        return added;
    }

    private void addToArray(Event event){
        int writeIndex = events.length;
        events = Arrays.copyOf(events, events.length + 1);
        events[writeIndex] = event;
        Arrays.sort(events);
    }

    private boolean canFit(LocalTime start, LocalTime end){

        boolean[] fits = new boolean[events.length];
        for(int i=0; i<events.length; i++){
            Event current = events[i];
            Optional<Event> optionalNext = i==events.length-1 ? Optional.empty() : Optional.of(events[i+1]);
            fits[i] = canFit(start,end,current, optionalNext);
        }

        for(boolean fit : fits){
            if(fit){
                return true;
            }
        }
        return false;
    }

    private boolean canFit(LocalTime start, LocalTime end, Event current, Optional<Event> optionalNext){
        if(optionalNext.isPresent()){
            Event next = optionalNext.get();
            return (current.getEnd().isBefore(start) || current.getEnd().equals(start)) && (next.getStart().isAfter(end) || next.getStart().equals(end));
        }else{
            return current.getEnd().isBefore(start) || current.getEnd().equals(start);
        }
    }
}
