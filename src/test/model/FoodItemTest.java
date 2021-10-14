package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class FoodItemTest {
    private FoodItem foodItem;

    @BeforeEach
    public void runBefore() {
        foodItem = new FoodItem("banana", 234762, 444332);
    }


    @Test
    public void testMarkFoodItemStatus() {
        foodItem.markFoodItemStatus(FoodItem.Status.Used);
        assertEquals(FoodItem.Status.Used, foodItem.status);

        foodItem.markFoodItemStatus(FoodItem.Status.OutOfStock);
        assertEquals(FoodItem.Status.OutOfStock, foodItem.status);


    }

    @Test
    public void testMarkExpiryFoodItem() {
        FoodItem banana = new FoodItem("banana", 2020,
                FoodItem.getLocalTime() + 20);
        banana.markExpiryFoodItem();
        assertFalse(banana.expired);

        FoodItem apple = new FoodItem("apple", 2020,
                FoodItem.getLocalTime() - 20);
        apple.markExpiryFoodItem();
        assertTrue(apple.expired);

    }

    @Test
    public void testMilliSecondToDate()  {

        assertEquals(new Date(foodItem.purchasedDateinMilli), foodItem.milliSecondToDate(foodItem.purchasedDateinMilli));
    }

}
