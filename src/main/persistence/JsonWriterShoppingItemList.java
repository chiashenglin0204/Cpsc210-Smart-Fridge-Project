package persistence;

import model.DateFoodItem;
import model.FoodItemList;
import model.ShoppingItemList;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriterShoppingItemList {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterShoppingItemList(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of workroom to file
    public void write(ShoppingItemList shoppingItemList) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < shoppingItemList.getShoppingItemList().size(); i++) {
            JSONObject element = new JSONObject();
            element.put("foodItemName", shoppingItemList.getShoppingItemList().get(i).getFoodItemName());
            element.put("expiryDate", shoppingItemList.getShoppingItemList().get(i).getExpiryDate().getDateInString());
            element.put("purchasedDate",
                    shoppingItemList.getShoppingItemList().get(i).getPurchasedDate().getDateInString());
            array.put(element);
        }

        json.put("shoppingItems", array);
        shoppingItemList.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }
}

