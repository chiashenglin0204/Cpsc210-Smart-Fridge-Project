package persistence;

import model.FoodItemList;

import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

// Represents a writer that writes JSON representation of workroom to file
public class JsonWriterFoodItemList {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterFoodItemList(String destination) {
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


    public void write(FoodItemList foodItemList) {
        JSONObject json = new JSONObject();
        JSONArray array = new JSONArray();
        for (int i = 0; i < foodItemList.getFoodItemList().size(); i++) {
            JSONObject element = new JSONObject();
            element.put("foodItemName", foodItemList.getFoodItemList().get(i).getFoodItemName());
            element.put("expiryDate", foodItemList.getFoodItemList().get(i).getExpiryDate().getDateInString());
            element.put("purchasedDate",
                    foodItemList.getFoodItemList().get(i).getPurchasedDate().getDateInString());
            array.put(element);
        }

        json.put("foodItems", array);
        foodItemList.toJson();
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

