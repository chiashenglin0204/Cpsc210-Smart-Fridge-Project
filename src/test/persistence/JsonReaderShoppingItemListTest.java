package persistence;
import model.FoodItem;
import model.ShoppingItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderShoppingItemListTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderShoppingItemList reader = new JsonReaderShoppingItemList("./data/noSuchFile.json");
        try {
            ShoppingItemList shoppingItemList = reader.readShoppingItemList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyShoppingItemList() {
        JsonReaderShoppingItemList reader = new JsonReaderShoppingItemList("./data/testReaderEmptyShoppingItemList.json");
        try {
            ShoppingItemList shoppingItemList = reader.readShoppingItemList();
            assertEquals(0, shoppingItemList.getShoppingItemList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralShoppingItemList() {
        JsonReaderShoppingItemList reader = new JsonReaderShoppingItemList("./data/testReaderGeneralShoppingItemList.json");
        try {
            ShoppingItemList shoppingItemList = reader.readShoppingItemList();
//            System.out.println(foodItemList);
            List<FoodItem> shoppingItems = shoppingItemList.getShoppingItemList();
//            System.out.println(foodItems);
            assertEquals(2, shoppingItemList.getShoppingItemList().size());
            checkFoodItem(
                    "ChiaSheng", 0,
                    0, shoppingItems.get(0));
            checkFoodItem(
                    "Taipei", 0,
                    0, shoppingItems.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}

