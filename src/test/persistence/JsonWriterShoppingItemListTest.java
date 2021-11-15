package persistence;


import model.DateFoodItem;
import model.FoodItem;
import model.FoodItemList;
import model.ShoppingItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterShoppingItemListTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            ShoppingItemList shoppingItemList = new ShoppingItemList();
            JsonWriterShoppingItemList writer = new JsonWriterShoppingItemList("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyShoppingItemList() {
        try {
            ShoppingItemList shoppingItemList = new ShoppingItemList();
            JsonWriterShoppingItemList writer = new JsonWriterShoppingItemList("./data/testWriterEmptyFoodItemList.json");
            writer.open();
            writer.write(shoppingItemList);
            writer.close();

            JsonReaderShoppingItemList reader = new JsonReaderShoppingItemList("./data/testWriterEmptyFoodItemList.json");
            shoppingItemList = reader.readShoppingItemList();

            assertEquals(0, shoppingItemList.getShoppingItemList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingItemList() {
        try {
            ShoppingItemList shoppingItemList = new ShoppingItemList();
            DateFoodItem emptyPurchaseDate = new DateFoodItem();
            DateFoodItem emptyExpiryDate = new DateFoodItem();
            shoppingItemList.addFoodItem(new FoodItem("ChiaSheng", emptyPurchaseDate, emptyExpiryDate));
            shoppingItemList.addFoodItem(new FoodItem("Taipei", emptyPurchaseDate, emptyExpiryDate));
            JsonWriterShoppingItemList writer = new JsonWriterShoppingItemList("./data/testWriterGeneralShoppingItemList.json");
            writer.open();
            writer.write(shoppingItemList);
            writer.close();

            JsonReaderShoppingItemList reader = new JsonReaderShoppingItemList("./data/testWriterGeneralShoppingItemList.json");
            shoppingItemList = reader.readShoppingItemList();
            List<FoodItem> shoppingItems = shoppingItemList.getShoppingItemList();
            assertEquals(2, shoppingItems.size());
            checkFoodItem("ChiaSheng", "", "", shoppingItems.get(0)); //todo here
            checkFoodItem("Taipei", "", "", shoppingItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}

