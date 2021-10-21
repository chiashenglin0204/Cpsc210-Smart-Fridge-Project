package persistence;
import model.FoodItem;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkFoodItem(
            String foodItemName, long purchasedDateInMilli , long expiryDateInMilli, FoodItem foodItem) {
        assertEquals(foodItemName, foodItem.getFoodItemName());
        assertEquals(purchasedDateInMilli, foodItem.getPurchasedDateInMilli());
        assertEquals(expiryDateInMilli, foodItem.getExpiryDateInMilli());
    }

}
