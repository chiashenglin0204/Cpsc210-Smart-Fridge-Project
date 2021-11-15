package persistence;

import model.DateFoodItem;
import model.FoodItem;
import model.FoodItemList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;


public class JsonReaderFoodItemListTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/noSuchFile.json");
        try {
            FoodItemList foodItemList = reader.readFoodItemList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFoodItemList() {
        JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/testReaderEmptyFoodItemList1.json");
        try {
            FoodItemList foodItemList = reader.readFoodItemList();
            assertEquals(0, foodItemList.getFoodItemList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFoodItemList() {
        JsonReaderFoodItemList reader = new JsonReaderFoodItemList("./data/testReaderGeneralFoodItemList1.json");
        try {
            FoodItemList foodItemList = reader.readFoodItemList();
//            System.out.println(foodItemList);
            List<FoodItem> foodItems = foodItemList.getFoodItemList();
//            System.out.println(foodItems);
            assertEquals(2, foodItemList.getFoodItemList().size());
            checkFoodItem(
                    "ChiaSheng", "2021/02/04",
                    "2021/02/05", foodItems.get(0));
            checkFoodItem(
                    "Taipei", "2020/02/06",
                    "2020/02/08", foodItems.get(1));

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
