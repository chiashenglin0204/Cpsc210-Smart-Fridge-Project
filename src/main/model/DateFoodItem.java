package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;


public class DateFoodItem {
    private String dateInString;
    private long dateInMilli;
    private String datePattern = "yyyy/MM/dd";
    private String timezoneCode = "UTC";

    public DateFoodItem() {
        this.dateInString = "";
        this.dateInMilli = 0;
    }

    public long getLocalTimeInMilli() {
        Date date = new Date();
        long timeMilli = date.getTime();
        return timeMilli;
    }

    public void dateMilliToString(long dateInMilli) {
        this.dateInMilli = dateInMilli;
        Date date = new Date(dateInMilli);
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setTimeZone(TimeZone.getTimeZone(timezoneCode));
        String formattedDate = sdf.format(date);
        this.dateInString = formattedDate;
    }

    public void dateStringToMilli(String dateInString) {
        try {
            this.dateInString = dateInString;
            SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
            java.util.Date date = null;
            date = sdf.parse(dateInString);
            dateInMilli = date.getTime();
        } catch (ParseException e) {
            fail("invalid date input");
        }
    }

    public String getDateInString() {
        return dateInString;
    }

    public long getDateInMilli() {
        return dateInMilli;
    }
}
