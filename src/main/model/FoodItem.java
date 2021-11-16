package model;


import org.json.JSONObject;
import persistence.Writable;

import java.util.Calendar;
import java.util.Date;

public class FoodItem implements Writable {
    private String foodItemName;
    private DateFoodItem purchasedDate;
    private DateFoodItem expiryDate;


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

    public DateFoodItem getPurchasedDate() {
        return purchasedDate;
    }

    public DateFoodItem getExpiryDate() {
        return expiryDate;
    }

    public boolean isExpired() {
        return expired;
    }

    public long getLocalTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    public FoodItem(String foodItemName, DateFoodItem purchasedDate, DateFoodItem expiryDate) {

        this.foodItemName = foodItemName;
        this.purchasedDate = purchasedDate;
        this.expiryDate = expiryDate;
        this.expired = false;
        this.status = Status.New;
    }

    //Require: Only input status "Used" "OutOfStock"
    //Mark a foodItem's Status as "Used" or "OutOfStock"
    public void markFoodItemStatus(Status s) {
        status = s;
    }

    //Effect: Change expired to true if current time is bigger than expiryDate
    public void checkExpiryFoodItem() {
        if (getLocalTime() > this.expiryDate.getDateInMilli()) {
            expired = true;
        }
    }

    // Effect: Convert data from Millisecond to date format
//    public Date milliSecondToDate(long milisecond) {
//        //DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//        Date result = new Date(milisecond);
//        return result;
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodItemName", foodItemName);
        json.put("purchasedDate", purchasedDate);
        json.put("expiryDate", expiryDate);
        return json;
    }

}
