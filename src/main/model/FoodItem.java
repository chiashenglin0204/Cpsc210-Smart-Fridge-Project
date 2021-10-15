package model;


import java.util.Date;

public class FoodItem {
    private String foodItemName;
    private long purchasedDateinMilli;
    private long expiryDateInMilli;

    private Status status;
    private boolean expired;


    public enum Status {
        New,
        Used,
        OutOfStock,
    }

    public String getFoodItemName() {
        return foodItemName;
    }

    public Status getStatus() {
        return status;
    }

    public long getPurchasedDateinMilli() {
        return purchasedDateinMilli;
    }

    public long getExpiryDateInMilli() {
        return expiryDateInMilli;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setFoodItemName(String foodItemName) {
        this.foodItemName = foodItemName;
    }

    public void setPurchasedDateinMilli(long purchasedDateinMilli) {
        this.purchasedDateinMilli = purchasedDateinMilli;
    }

    public void setExpiryDateInMilli(long expiryDateInMilli) {
        this.expiryDateInMilli = expiryDateInMilli;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
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
