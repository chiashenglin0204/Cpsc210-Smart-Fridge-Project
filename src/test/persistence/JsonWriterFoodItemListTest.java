package persistence;


import model.DateFoodItem;
import model.FoodItem;
import model.FoodItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
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
            DateFoodItem purchaseDate = new DateFoodItem();
            DateFoodItem expiryDate = new DateFoodItem();
            DateFoodItem purchaseDate1 = new DateFoodItem();
            DateFoodItem expiryDate1 = new DateFoodItem();
            foodItemList.addFoodItem(new FoodItem("ChiaSheng",
                    purchaseDate.dateStringToMilli("2021/02/04"),
                    expiryDate.dateStringToMilli("2021/02/05")));
            foodItemList.addFoodItem(new FoodItem("Taipei",
                    purchaseDate1.dateStringToMilli("2020/02/06"),
                    expiryDate1.dateStringToMilli("2020/02/08")));
            JsonWriterFoodItemList writer = new JsonWriterFoodItemList("./data/testWriterGeneralFoodItemList.json");
            writer.open();
            writer.write(foodItemList);
            writer.close();

            JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/testWriterGeneralFoodItemList.json");
            foodItemList = reader.readFoodItemList();
            List<FoodItem> foodItems = foodItemList.getFoodItemList();
            assertEquals(2, foodItems.size());
            checkFoodItem("ChiaSheng",
                    "2021/02/04",
                    "2021/02/05", foodItems.get(0)); //todo here
            checkFoodItem("Taipei",
                    "2020/02/06",
                    "2020/02/08", foodItems.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }

    }
}
