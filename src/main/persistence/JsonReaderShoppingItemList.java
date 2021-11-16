package persistence;

import model.DateFoodItem;
import model.FoodItem;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.ShoppingItemList;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReaderShoppingItemList {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderShoppingItemList(String source) {
        this.source = source;
    }

    // EFFECTS: reads shoppingItemList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingItemList readShoppingItemList() throws IOException {
        String jsonData = readFile(source);
        //System.out.println("your mom");
        System.out.println(jsonData);

        JSONObject jsonObject = new JSONObject(jsonData);
        return parseShoppingItemList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses shoppingItemList from JSON object and returns it
    private ShoppingItemList parseShoppingItemList(JSONObject jsonObject) {
        ShoppingItemList shoppingItemList = new ShoppingItemList();
        addFoodItemsToShoppingItemList(shoppingItemList, jsonObject);
        return shoppingItemList;
    }

    // MODIFIES: shoppingItemList
    // EFFECTS: parses shoppingItems from JSON object and adds them to shoppingItemList
    private void addFoodItemsToShoppingItemList(ShoppingItemList shoppingItemList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("shoppingItems");
        for (Object json : jsonArray) {
            JSONObject nextFoodItem = (JSONObject) json;
            addFoodItemToShoppingItemList(shoppingItemList, nextFoodItem);
        }
    }

    // MODIFIES: shoppingItemList
    // EFFECTS: parses foodItem from JSON object and adds it to shoppingItemList
    private void addFoodItemToShoppingItemList(ShoppingItemList shoppingItemList, JSONObject jsonObject) {
        String foodItemName = jsonObject.getString("foodItemName");

        FoodItem foodItem = new FoodItem(foodItemName, new DateFoodItem(),new DateFoodItem());
        shoppingItemList.addFoodItem(foodItem);
    }

}
