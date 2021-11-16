package persistence;
import model.FoodItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFoodItem(
            String foodItemName, String purchasedDate, String expiryDate, FoodItem foodItem) {
        assertEquals(foodItemName, foodItem.getFoodItemName());
        assertEquals(purchasedDate, foodItem.getPurchasedDate().getDateInString());
        assertEquals(expiryDate, foodItem.getExpiryDate().getDateInString());
    }

}
