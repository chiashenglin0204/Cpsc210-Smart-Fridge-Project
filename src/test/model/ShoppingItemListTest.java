package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ShoppingItemListTest {
    private ShoppingItemList shoppingItemList;
    private FoodItem banana = new FoodItem("banana", 0, 0);
    private FoodItem apple = new FoodItem("apple", 1021, 1017);

    @BeforeEach
    public void runBefore(){
        shoppingItemList = new ShoppingItemList();
    }

    @Test
    public void testAddFoodItem(){
        shoppingItemList.addFoodItem(banana);
        shoppingItemList.addFoodItem(apple);
        assertEquals(2, shoppingItemList.shoppingItemList.size());
    }


    @Test
    public void testDeleteFoodItem(){
        for(int i =0; i<5; i++){
            shoppingItemList.addFoodItem(banana);
        }
        shoppingItemList.deleteFoodItem(banana);
        assertEquals(4,shoppingItemList.shoppingItemList.size());


    }


}
