package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {
    private FoodItem foodItem;

    public long getLocalTime() {
        return Calendar.getInstance().getTimeInMillis();
    }

    @BeforeEach
    public void runBefore() {
        DateFoodItem purchasedDate = new DateFoodItem().dateStringToMilli("2021/01/30");
        DateFoodItem expiryDate = new DateFoodItem().dateStringToMilli("2021/02/04");
        foodItem = new FoodItem("banana", purchasedDate, expiryDate);
    }


    @Test
    public void testGetLocalTime() {
        assertEquals(Calendar.getInstance().getTimeInMillis(), getLocalTime());
    }

    @Test
    public void testMarkFoodItemStatus() {
        foodItem.markFoodItemStatus(FoodItem.Status.Used);
        assertEquals(FoodItem.Status.Used, foodItem.getStatus());
        assertEquals("banana", foodItem.getFoodItemName());


        foodItem.markFoodItemStatus(FoodItem.Status.OutOfStock);
        assertEquals(FoodItem.Status.OutOfStock, foodItem.getStatus());


    }

    @Test
    public void testMarkExpiryFoodItem() {
        assertEquals("2021/02/04",foodItem.getExpiryDate().getDateInString());
        DateFoodItem purchaseDate1 = new DateFoodItem();
        DateFoodItem expiryDate1 = new DateFoodItem();

        purchaseDate1.dateMilliToString(202020);
        expiryDate1.dateMilliToString(getLocalTime()+20);
        FoodItem banana = new FoodItem("banana",purchaseDate1,
                expiryDate1);
        banana.checkExpiryFoodItem();
        assertFalse(banana.isExpired());

        DateFoodItem purchaseDate2 = new DateFoodItem();
        DateFoodItem expiryDate2 = new DateFoodItem();
        purchaseDate2.dateMilliToString(202020);
        expiryDate2.dateMilliToString(getLocalTime()-20);
        FoodItem apple = new FoodItem("apple",purchaseDate2,
                expiryDate2);
        apple.checkExpiryFoodItem();

        assertTrue(apple.isExpired());
    }




//    @Test
//    public void testMilliSecondToDate()  {
//
//        assertEquals(new Date(foodItem.getPurchasedDateInMilli()), foodItem.milliSecondToDate(foodItem.getPurchasedDateInMilli()));
//    }

}
