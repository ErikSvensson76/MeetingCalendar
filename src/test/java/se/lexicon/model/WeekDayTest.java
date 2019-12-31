package se.lexicon.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

public class WeekDayTest {
    private WeekDay testObject;

    private Event[] eventData(){
        Event[] events = {
            new Event(LocalTime.parse("08:00"), LocalTime.parse("10:00"), "First event"),
            new Event(LocalTime.parse("11:00"), LocalTime.parse("12:00"), "Second event"),
            new Event(LocalTime.parse("12:00"), LocalTime.parse("13:00"), "Third event"),
            new Event(LocalTime.parse("14:00"), LocalTime.parse("15:00"), "Fourth event"),
            new Event(LocalTime.parse("16:00"), LocalTime.parse("17:00"), "Fifth event")
        };
        return events;
    }

    @BeforeEach
    void setUp() {
        testObject = new WeekDay("test_id", LocalDate.parse("2019-09-19"), eventData());
    }

    @Test
    public void testObject_successfully_created(){
        assertEquals("test_id", testObject.getWeekDayId());
        assertEquals(LocalDate.parse("2019-09-19"), testObject.getDate());
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_no_id_and_no_events_should_be_successfully_created(){
        WeekDay testObject = new WeekDay(LocalDate.parse("2019-09-19"));
        assertNotNull(testObject.getWeekDayId());
        assertEquals(LocalDate.parse("2019-09-19"), testObject.getDate());
        assertEquals(0, testObject.getEvents().length);
    }

    @Test
    public void given_event_8_00_to_10_00_return_false(){
        Event event = new Event(LocalTime.parse("08:00"), LocalTime.parse("10:00"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5,testObject.getEvents().length);
    }

    @Test
    public void given_event_7_30_to_17_30_return_false(){
        Event event = new Event(LocalTime.parse("07:30"), LocalTime.parse("17:30"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5,testObject.getEvents().length);
    }

    @Test
    public void given_event_15_30_to_17_05_return_false(){
        Event event = new Event(LocalTime.parse("15:30"), LocalTime.parse("17:05"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5,testObject.getEvents().length);
    }

    @Test
    public void given_event_9_59_to_11_01_return_false(){
        Event event = new Event(LocalTime.parse("09:59"), LocalTime.parse("11:01"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_starts_at_8_00_return_false(){
        Event event = new Event(LocalTime.parse("08:00"), 30, "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_starts_at_9_00_to_11_00_return_false(){
        Event event = new Event(LocalTime.parse("09:00"), LocalTime.parse("11:00"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_starts_at_7_59_to_09_59_return_false(){
        Event event = new Event(LocalTime.parse("07:59"), LocalTime.parse("09:59"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_starts_at_9_59_to_10_59_return_false(){
        Event event = new Event(LocalTime.parse("09:59"), LocalTime.parse("10:59"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_starts_at_10_01_to_11_01_return_false(){
        Event event = new Event(LocalTime.parse("10:01"), LocalTime.parse("11:01"), "Test");
        assertFalse(testObject.addEvent(event));
        assertEquals(5, testObject.getEvents().length);
    }

    @Test
    public void given_event_start_at_10_00_to_11_00_return_true(){
        Event event = new Event(LocalTime.parse("10:00"), LocalTime.parse("11:00"), "Test");
        assertTrue(testObject.addEvent(event));
        assertEquals(6, testObject.getEvents().length);
    }

    @Test
    public void given_4_events_at_range_10_00_to_11_00_all_return_true(){
        Event first = new Event(LocalTime.parse("10:00"), LocalTime.parse("10:05"), "Test");
        Event second = new Event(LocalTime.parse("10:05"), LocalTime.parse("10:30"), "Test");
        Event third = new Event(LocalTime.parse("10:30"), LocalTime.parse("10:50"), "Test");
        Event fourth = new Event(LocalTime.parse("10:50"), LocalTime.parse("11:00"), "Test");



        assertTrue(testObject.addEvent(second));
        assertTrue(testObject.addEvent(fourth));
        assertTrue(testObject.addEvent(first));
        assertTrue(testObject.addEvent(third));
        for(Event event : testObject.getEvents()){
            System.out.println(event);
        }
        assertEquals(9, testObject.getEvents().length);
    }

    @Test
    public void given_event_start_at_07_30_to_08_00_addEvent_should_return_true(){
        Event event = new Event(LocalTime.parse("07:30"), LocalTime.parse("08:00"), "Test");
        assertTrue(testObject.addEvent(event));
        assertEquals(6, testObject.getEvents().length);
    }

    @Test
    public void given_event_start_at_17_00_to_18_30_addEvent_should_return_true(){
        Event event = new Event(LocalTime.parse("17:00"), LocalTime.parse("18:30"), "Test");
        assertTrue(testObject.addEvent(event));
        assertEquals(6, testObject.getEvents().length);
    }

}
