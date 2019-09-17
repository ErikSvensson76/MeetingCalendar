package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    public static final LocalTime START = LocalTime.of(10, 00);
    public static final LocalTime END = LocalTime.of(11, 00);
    public static final String EVENT_DESCRIPTION = "Test Description";
    private Event testObject;



    @BeforeEach
    public void setUp(){
        int durationInMinutes = 60;
        testObject = new Event(START,durationInMinutes, EVENT_DESCRIPTION);
    }


    @Test
    public void test_event_successfully_created(){
        assertNotNull(testObject.getEventId());
        assertEquals(START, testObject.getStart());
        assertEquals(END, testObject.getEnd());
        assertEquals(EVENT_DESCRIPTION, testObject.getEventDescription());
        String toString = testObject.toString();
        assertTrue(
                toString.contains(START.toString())
                        && toString.contains(END.toString())
                        && toString.contains(testObject.getEventId())
                        && toString.contains(EVENT_DESCRIPTION)
                );
    }

   @Test
    public void set_start_to_after_end_throws_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                () -> testObject.setStart(LocalTime.of(11,01)));

        assertThrows(IllegalArgumentException.class,
                ()-> testObject.setStart(END));
    }

    @Test
    public void set_end_to_before_start_throws_IllegalArgumentException(){
        assertThrows(IllegalArgumentException.class,
                () -> testObject.setEnd(LocalTime.of(9,59)));

        assertThrows(IllegalArgumentException.class,
                () -> testObject.setEnd(START));
    }

    @Test
    public void set_end_and_start_to_valid_LocalTime_not_throws_IllegalArgumentException(){
        assertDoesNotThrow(() -> testObject.setStart(LocalTime.of(8,00)));
        assertDoesNotThrow(() -> testObject.setEnd(LocalTime.of(9, 00)));
    }
}
