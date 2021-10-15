package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class FoodItemListTest {
    private FoodItemList foodItemList;
    private FoodItem banana = new FoodItem("banana", 1011, 1021);
    private FoodItem apple = new FoodItem("apple", 1021, 1017);

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
        foodItemList.addFoodItem(banana);
        foodItemList.addFoodItem(apple);
        assertTrue(foodItemList.containInFoodItemList("banana"));
        assertFalse(foodItemList.containInFoodItemList("anana"));

    }




}
