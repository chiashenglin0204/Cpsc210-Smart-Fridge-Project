package persistence;


import model.FoodItem;
import model.FoodItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterFoodItemListTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            FoodItemList foodItemList = new FoodItemList();
            JsonWriterFoodItemList writer = new JsonWriterFoodItemList("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyFoodItemList() {
        try {
            FoodItemList foodItemList = new FoodItemList();
            JsonWriterFoodItemList writer = new JsonWriterFoodItemList("./data/testWriterEmptyFoodItemList.json");
            writer.open();
            writer.write(foodItemList);
            writer.close();

            JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/testWriterEmptyFoodItemList.json");
            foodItemList = reader.readFoodItemList();

            assertEquals(0, foodItemList.getFoodItemList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralFoodItemList() {
        try {
            FoodItemList foodItemList = new FoodItemList();
            foodItemList.addFoodItem(new FoodItem("ChiaSheng" , 1611993600000L, 1612080000000L));
            foodItemList.addFoodItem(new FoodItem("Taipei" , 1634626800000L, 1634799600000L));
            JsonWriterFoodItemList writer = new JsonWriterFoodItemList("./data/testWriterGeneralFoodItemList.json");
            writer.open();
            writer.write(foodItemList);
            writer.close();

            JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/testWriterGeneralFoodItemList.json");
            foodItemList = reader.readFoodItemList();
            List<FoodItem> foodItems = foodItemList.getFoodItemList();
            assertEquals(2, foodItems.size());
            checkFoodItem("ChiaSheng", 1611993600000L, 1612080000000L, foodItems.get(0)); //todo here
            checkFoodItem("Taipei", 1634626800000L, 1634799600000L, foodItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }}
