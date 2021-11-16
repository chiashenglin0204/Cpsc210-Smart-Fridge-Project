package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;


public class DateFoodItemTest {
    private DateFoodItem date;
    private String myBirthday = "2021/02/04";
    private long myBirthdayInMilli = 1612425600000L;

    @BeforeEach
    public void runBefore() {
        date = new DateFoodItem();
    }

    @Test
    public void testConstructor() {
        assertEquals("", date.getDateInString());
        assertEquals(0, date.getDateInMilli());

    }

    @Test
    public void testDateMilliToString() {

        date.dateMilliToString(myBirthdayInMilli);
        assertEquals(myBirthdayInMilli, date.getDateInMilli());
        assertEquals(myBirthday, date.getDateInString());
        assertEquals(date.dateStringToMilli(myBirthday), date.dateMilliToString(myBirthdayInMilli));

    }

    @Test
    public void testDateStringToMilli() {
        date.dateStringToMilli(myBirthday);
        assertEquals(myBirthdayInMilli, date.getDateInMilli());
        assertEquals(myBirthday, date.getDateInString());
        assertEquals(date.dateStringToMilli(myBirthday), date.dateMilliToString(myBirthdayInMilli));
    }




}
