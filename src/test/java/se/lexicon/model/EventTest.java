package se.lexicon.model;

import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {


    @Test
    public void test_event_successfully_created(){
        LocalTime start = LocalTime.of(10,00);
        int durationInMinutes = 60;
        String eventDescription = "Test Description";
        LocalTime expectedEnd = LocalTime.of(11,00);
        Event testObject = new Event(start,durationInMinutes,eventDescription);

        assertNotNull(testObject.getEventId());
        assertEquals(start, testObject.getStart());
        assertEquals(expectedEnd, testObject.getEnd());
        assertEquals(eventDescription, testObject.getEventDescription());
        String toString = testObject.toString();
        assertTrue(
                toString.contains(start.toString())
                        && toString.contains(expectedEnd.toString())
                        && toString.contains(testObject.getEventId())
                        && toString.contains(eventDescription)
                );
    }


}
