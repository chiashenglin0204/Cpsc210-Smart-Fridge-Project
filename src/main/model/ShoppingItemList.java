package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingItemList {
    public List<FoodItem> shoppingItemList;

    // Effects: ShoppingItemList is empty
    public ShoppingItemList() {
        shoppingItemList = new ArrayList<>();
    }

    //Effect: add a Fooditem into the Shoppingitemlist
    public void addFoodItem(FoodItem foodItem) {
        shoppingItemList.add(foodItem);

    }

    //Required: ShoppingItemlist must contain the specific FoodItem initially
    //Effect: delete a Fooditem from the ShoppingItemlist
    public void deleteFoodItem(FoodItem foodItem) {
        shoppingItemList.remove(foodItem);
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
