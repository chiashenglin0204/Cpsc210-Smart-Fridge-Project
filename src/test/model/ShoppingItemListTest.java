package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingItemListTest {
    private ShoppingItemList shoppingItemList;
    private FoodItem banana = new FoodItem("banana", new DateFoodItem(), new DateFoodItem());
    private FoodItem apple = new FoodItem("apple", new DateFoodItem(), new DateFoodItem());

    @BeforeEach
    public void runBefore(){
        shoppingItemList = new ShoppingItemList();
    }

    @Test
    public void testAddFoodItem(){
        shoppingItemList.addFoodItem(banana);
        shoppingItemList.addFoodItem(apple);
        assertEquals(2, shoppingItemList.getShoppingItemList().size());
    }


    @Test
    public void testDeleteFoodItem(){
        for(int i =0; i<5; i++){
            shoppingItemList.addFoodItem(banana);
        }
        shoppingItemList.deleteFoodItem(banana);
        assertEquals(4,shoppingItemList.getShoppingItemList().size());


    }

    @Test
    public void testContainInFoodItemList(){
        shoppingItemList.addFoodItem(banana);
        shoppingItemList.addFoodItem(apple);
        assertTrue(shoppingItemList.containInShoppingItemList("banana"));
        assertFalse(shoppingItemList.containInShoppingItemList("anana"));

    }


}
