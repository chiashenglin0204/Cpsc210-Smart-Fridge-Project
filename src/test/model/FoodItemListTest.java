package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class FoodItemListTest {
    private FoodItemList foodItemList;
    private DateFoodItem expiryDate = new DateFoodItem();
    private FoodItem banana = new FoodItem("banana",
            new DateFoodItem().dateMilliToString(1011) ,
            new DateFoodItem().dateMilliToString(1021));
    private FoodItem apple = new FoodItem("apple",
            new DateFoodItem().dateMilliToString(1021) ,
            new DateFoodItem().dateMilliToString(1017));
    private FoodItem melon = new FoodItem("melon",
            new DateFoodItem().dateMilliToString(1021),
            expiryDate.dateMilliToString(new FoodItem("shdfushdf", new DateFoodItem(), new DateFoodItem()).getLocalTime()+30));


    @BeforeEach
    public void runBefore(){
        foodItemList = new FoodItemList();
    }

    @Test
    public void testAddFoodItem() {
        foodItemList.addFoodItem(banana);
        foodItemList.addFoodItem(apple);
        assertEquals(2, foodItemList.getFoodItemList().size());
        }

    @Test
    public void testDeleteFoodItem() {
        for(int i =0; i<5; i++){
            foodItemList.addFoodItem(banana);
        }
        foodItemList.deleteFoodItem(banana);
        assertEquals(4,foodItemList.getFoodItemList().size());

    }

    @Test
    public void testContainInFoodItemList(){
        assertFalse(foodItemList.containInFoodItemList("anana"));
        foodItemList.addFoodItem(banana);
        foodItemList.addFoodItem(apple);
        assertTrue(foodItemList.containInFoodItemList("banana"));
        assertFalse(foodItemList.containInFoodItemList("anana"));

    }
    @Test
    public void TestReturnExpiryFoodItem() {
        assertEquals(0,foodItemList.returnExpiryFoodItem().getFoodItemList().size());
        foodItemList.addFoodItem(banana);
        foodItemList.addFoodItem(melon);
        assertEquals(1, foodItemList.returnExpiryFoodItem().getFoodItemList().size());

    }

    /*public FoodItemList returnExpiryFoodItem() {
        FoodItemList expiredFoodItemList = new FoodItemList();
        for (FoodItem foodItem : foodItemList) {
            foodItem.markExpiryFoodItem();
            if (foodItem.isExpired()) {
                expiredFoodItemList.addFoodItem(foodItem);
            }
        }

        return expiredFoodItemList;

    }*/




}
