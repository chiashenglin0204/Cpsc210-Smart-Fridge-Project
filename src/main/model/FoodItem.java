package model;


import java.util.Date;

public class FoodItem {
    public String foodItemName;
    public long purchasedDateinMilli;
    public long expiryDateInMilli;

    public Status status;
    public boolean expired;


    public enum Status {
        New,
        Used,
        OutOfStock,
    }

    // Effect: get the local time now
    public static long getLocalTime() {
        Date date = new Date();
        long timeMilli = date.getTime();
        return timeMilli;
    }

    public FoodItem(String foodItemName, long purchasedDate, long expiryDate) {
        this.foodItemName = foodItemName;
        this.purchasedDateinMilli = purchasedDate;
        this.expiryDateInMilli = expiryDate;

        this.expired = false;
        this.status = Status.New;

    }

    //Require: Only input status "Used" "OutOfStock"
    //Mark a foodItem's Status as "Used" or "OutOfStock"
    public void markFoodItemStatus(Status s) {
        status = s;
    }

    //Effect: Change expired to true if current time is bigger than expiryDate
    public void markExpiryFoodItem() {
        if (getLocalTime() > expiryDateInMilli) {
            expired = true;
        }
    }

    // Effect: Convert data from Millisecond to date format
    public Date milliSecondToDate(long milisecond) {
        //DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date result = new Date(milisecond);
        return result;
    }


}
