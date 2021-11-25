package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemList implements Writable {
    private List<FoodItem> shoppingItemList;

    public List<FoodItem> getShoppingItemList() {
        return shoppingItemList;
    }

    /*public void setShoppingItemList(List<FoodItem> shoppingItemList) {
        this.shoppingItemList = shoppingItemList;
    }*/

    // Effects: ShoppingItemList is empty
    public ShoppingItemList() {
        shoppingItemList = new ArrayList<>();
    }

    //Effect: add a Fooditem into the Shoppingitemlist
    public void addFoodItem(FoodItem foodItem) {
        EventLog.getInstance().logEvent(new Event("add " + foodItem.getFoodItemName() + " in your shoppingItem List"));
        shoppingItemList.add(foodItem);

    }

    //Required: ShoppingItemlist must contain the specific FoodItem initially
    //Effect: delete a Fooditem from the ShoppingItemlist
    public void deleteFoodItem(FoodItem foodItem) {
        EventLog.getInstance().logEvent(
                new Event(
                        "delete " + foodItem.getFoodItemName() + " in your shoppingItem List"));
        shoppingItemList.remove(foodItem);
    }

    public boolean containInShoppingItemList(String s) {
        for (FoodItem foodItem : shoppingItemList) {
            if (foodItem.getFoodItemName().equals(s)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("shoppingItems", shoppingItemsToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray shoppingItemsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (FoodItem foodItem : shoppingItemList) {
            jsonArray.put(foodItem.toJson());
        }

        return jsonArray;
    }


    /*public ShoppingItemList returnUnpurchasedShoppingItem() {
        ShoppingItemList unpurchasedShoppingItem = new ShoppingItemList();
        for (FoodItem foodItem : shoppingItemList) {
            if (foodItem.status == FoodItem.Status.Unpurchased) {
                unpurchasedShoppingItem.addFoodItem(foodItem);
            }
        }
        return unpurchasedShoppingItem;
    }*/


}
