package persistence;

import model.DateFoodItem;
import model.FoodItem;
import model.FoodItemList;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderFoodItemList {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderFoodItemList(String source) {
        this.source = source;
    }

    // EFFECTS: reads foodItemList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public FoodItemList readFoodItemList() throws IOException {
        String jsonData = readFile(source);
        //System.out.println("your mom");
        System.out.println(jsonData);

        JSONObject jsonObject = new JSONObject(jsonData);
        return parseFoodItemList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses foodItemList from JSON object and returns it
    private FoodItemList parseFoodItemList(JSONObject jsonObject) {
        FoodItemList foodItemList = new FoodItemList();
        addFoodItemsToFoodItemList(foodItemList, jsonObject);
        return foodItemList;
    }

    // MODIFIES: foodItemList
    // EFFECTS: parses foodItems from JSON object and adds them to foodItemList
    private void addFoodItemsToFoodItemList(FoodItemList foodItemList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("foodItems");
        for (Object json : jsonArray) {
            JSONObject nextFoodItem = (JSONObject) json;
            addFoodItemToFoodItemList(foodItemList, nextFoodItem);
        }
    }

    // MODIFIES: foodItemList
    // EFFECTS: parses foodItem from JSON object and adds it to foodItemList
    private void addFoodItemToFoodItemList(FoodItemList foodItemList, JSONObject jsonObject) {
        String foodItemName = jsonObject.getString("foodItemName");
        DateFoodItem purchasedDate = new DateFoodItem();
        purchasedDate.dateStringToMilli(jsonObject.get("purchasedDate").toString());

        DateFoodItem expiryDate = new DateFoodItem();
        expiryDate.dateStringToMilli(jsonObject.get("expiryDate").toString());

        FoodItem foodItem = new FoodItem(foodItemName, purchasedDate, expiryDate);
        foodItemList.addFoodItem(foodItem);
    }

}
