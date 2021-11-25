package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


public class FoodItemList implements Writable {

    private List<FoodItem> foodItemList;

    public List<FoodItem> getFoodItemList() {
        return foodItemList;
    }

    /*public void setFoodItemList(List<FoodItem> foodItemList) {
        this.foodItemList = foodItemList;
    }*/

    // Effects: FoodItemList is empty
    public FoodItemList() {
        foodItemList = new ArrayList<>();

    }


    //Effect: add a Fooditem into the Fooditemlist
    public void addFoodItem(FoodItem foodItem) {
        EventLog.getInstance().logEvent(new Event(
                "add " + foodItem.getFoodItemName() + " in your foodItem List with purchase date "
                        + foodItem.getPurchasedDate().getDateInString()
                        + " expiry date " + foodItem.getExpiryDate().getDateInString()));
        foodItemList.add(foodItem);

    }

    //Required: FoodItemlist must contain the specific FoodItem initially
    //Effect: delete a Fooditem from the Fooditemlist
    public void deleteFoodItem(FoodItem foodItem) {
        EventLog.getInstance().logEvent(new Event(
                "delete " + foodItem.getFoodItemName() + " in your foodItem List with purchase date "
                        + foodItem.getPurchasedDate().getDateInString()
                        + " expiry date " + foodItem.getExpiryDate().getDateInString()));
        foodItemList.remove(foodItem);

    }

    //Effect: return the expired FoodItemList which contain expired foodItem
    public FoodItemList returnExpiryFoodItem() {
        FoodItemList expiredFoodItemList = new FoodItemList();
        EventLog.getInstance().logEvent(new Event("return expiry FoodItems in your foodItem List"));
        for (FoodItem foodItem : foodItemList) {
            foodItem.checkExpiryFoodItem();
            if (foodItem.isExpired()) {
                expiredFoodItemList.addFoodItem(foodItem);
            }
        }

        return expiredFoodItemList;

    }

    public boolean containInFoodItemList(String s) {
        for (FoodItem foodItem : foodItemList) {
            if (foodItem.getFoodItemName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("foodItems", foodItemsToJson());
        return json;
    }

    // EFFECTS: returns things in this foodItemList as a JSON array
    private JSONArray foodItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodItem foodItem : foodItemList) {
            jsonArray.put(foodItem.toJson());
        }

        return jsonArray;
    }


}
