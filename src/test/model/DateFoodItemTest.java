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
        try {
            date.dateMilliToString(myBirthdayInMilli);
            assertEquals(myBirthdayInMilli, date.getDateInMilli());
            assertEquals(myBirthday, date.getDateInString());
            assertEquals(date.dateStringToMilli(myBirthday), date.dateMilliToString(myBirthdayInMilli));
        } catch (ParseException exception) {
            fail("didn't expect parse exception");
        }
    }

    @Test
    public void testDateStringToMilli() {
        try {
        date.dateStringToMilli(myBirthday);
        assertEquals(myBirthdayInMilli, date.getDateInMilli());
        assertEquals(myBirthday, date.getDateInString());
        assertEquals(date.dateStringToMilli(myBirthday), date.dateMilliToString(myBirthdayInMilli));}
        catch (ParseException exception) {
            fail("didn't expect parse exception");
        }
    }

    @Test
    public void testWrongDateFormat() {
         String s = "202022";
         try {
             date.dateStringToMilli(s);
             fail("we expect fail here");
         }catch (ParseException exception) {
             //we pass here
         }
    }


}
