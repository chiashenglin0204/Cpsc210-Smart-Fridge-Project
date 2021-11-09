package ui;

import model.FoodItem;
import model.FoodItemList;
import model.ShoppingItemList;
import persistence.JsonReaderShoppingItemList;
import persistence.JsonReaderFoodItemList;
import persistence.JsonWriterFoodItemList;
import persistence.JsonWriterShoppingItemList;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SmartFridgeApp {
    private static final String JSON_STORE_FoodItemList = "./data/foodItem.json";
    private static final String JSON_STORE_ShoppingItemList = "./data/shoppingItem.json";
    private FoodItemList foodItemList = new FoodItemList();
    private ShoppingItemList shoppingItemList = new ShoppingItemList();
    private FoodItemList expiryFoodItemList = new FoodItemList();
    private Scanner input;
    private JsonWriterFoodItemList jsonWriterFoodItemList;
    private JsonWriterShoppingItemList jsonWriterShoppingItemList;
    private JsonReaderFoodItemList jsonReaderFoodItemList;
    private JsonReaderShoppingItemList jsonReaderShoppingItemList;

    public static final int frameWidth = 400;
    public static final int frameHeight = 400;

    public static final int buttonWidth = 135;
    public static final int buttonHeight = 30;


    public SmartFridgeApp() throws ParseException, FileNotFoundException {
        jsonWriterFoodItemList = new JsonWriterFoodItemList(JSON_STORE_FoodItemList);
        jsonWriterShoppingItemList = new JsonWriterShoppingItemList(JSON_STORE_ShoppingItemList);
        jsonReaderShoppingItemList = new JsonReaderShoppingItemList(JSON_STORE_ShoppingItemList);
        jsonReaderFoodItemList = new JsonReaderFoodItemList(JSON_STORE_FoodItemList);


        JFrame frame = new JFrame("Smart Fridge");
        JPanel panel = new JPanel();
        panel.setSize(frameWidth, frameHeight);
        panel.setBackground(Color.gray);



        JButton button = new JButton("clicker here");
        button.setVisible(true);
        button.setBounds(50, 100, buttonWidth, buttonHeight);
        Color backgroundColor = new Color(4, 99, 7);
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        frame.add(panel, BorderLayout.CENTER);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JLabel label = new JLabel("next performed");
                label.setBounds(50,50, 100,30);
                panel.add(label, BorderLayout.CENTER);
                final JTextField textField = new JTextField();
                textField.setBounds(50, 50, 150, 20);
                textField.setText("Welcome to Javatpoint.");
                textField.setBackground(Color.WHITE);
                panel.add(textField);
            }
        });

        panel.add(button, BorderLayout.CENTER);
        panel.setVisible(true);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);


        runSmartFridge();
    }

    private void runSmartFridge() throws ParseException {
        boolean keepgoing = true;
        String command = null;
        init();
        while (keepgoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepgoing = false;
            } else {
                processCommand(command);
            }
        }


    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws ParseException {
        if (command.equals("1")) {
            doAddFoodItem();
        } else if (command.equals("2")) {
            doAddShoppingItem();
        } else if (command.equals("3")) {
            doDeleteFoodItem();
        } else if (command.equals("4")) {
            doDeleteShoppingItem();
        } else if (command.equals("5")) {
            doViewFoodItemList();
        } else if (command.equals("6")) {
            doViewShoppingItemList();
        } else if (command.equals("7")) {
            doViewExpiryItem();
        } else if (command.equals("8")) {
            doMarkFoodItem();
        } else if (command.equals("s")) {
            save();
        } else if (command.equals("l")) {
            load();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    private void save() {
        saveFoodItemList();
        saveShoppingItemList();
    }

    private void load() {
        loadFoodItemList();
        loadShoppingItemList();
    }

    private void init() {
        shoppingItemList = new ShoppingItemList();
        foodItemList = new FoodItemList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        expiryFoodItemList = new FoodItemList();
    }

    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\t1 -> Add a food Item in your FoodItem List");
        System.out.println("\t2 -> Add a food item in your ShoppingItem List");
        System.out.println("\t3 -> Delete a food item in your FoodItem List");
        System.out.println("\t4 -> Delete a food item in your ShoppingItem List");
        System.out.println("\t5 -> view your FoodItem List");
        System.out.println("\t6 -> view your ShoppingItem List");
        System.out.println("\t7 -> view your Expiry FoodItems");
        System.out.println("\t8 -> Mark your FoodItem in FoodItem List");
        System.out.println("\ts -> Save your file");
        System.out.println("\tl -> Load your file");
        System.out.println("\tq -> quit");
    }

    private void doAddFoodItem() throws ParseException {
        boolean notEnd = true;
        while (notEnd) {
            System.out.println("\nPlease enter FoodItem's Name");
            String foodItemName = input.nextLine();

            while (foodItemName.length() < 1) {
                foodItemName = input.nextLine();
                if (foodItemName.length() >= 1) {
                    System.out.println("you entered: " + foodItemName);
                }
            }

            FoodItem foodItem1 = new FoodItem(foodItemName, askForPurchasedDate(), askForExpiryDate());
            foodItemList.addFoodItem(foodItem1);
            System.out.println(foodItemList.getFoodItemList().get(0).getFoodItemName());
            System.out.println(
                    "Purchased date: " + foodItemList.getFoodItemList().get(0).milliSecondToDate(
                            foodItem1.getPurchasedDateInMilli())
            );
            System.out.println(
                    "Expiry date: "
                            + foodItemList.getFoodItemList().get(0).milliSecondToDate(
                            foodItem1.getExpiryDateInMilli()));
            notEnd = askForRepeat();
        }

    }

    private boolean askForRepeat() {
        while (true) {
            System.out.println("Want to keep doing?, type y for yes, n for no");
            String choiceInputTF = input.nextLine();
            if (choiceInputTF.length() >= 1) {
                System.out.println("you entered:" + choiceInputTF);
            }

            if (choiceInputTF.equals("y")) {
                return true;
            } else if (choiceInputTF.equals("n")) {
                return false;
            } else {
                System.out.println("invalid input, please type again");
            }
        }
    }

    private long askForExpiryDate() throws ParseException {
        System.out.println("\nPlease enter FoodItem's Expiry Date in yyyy/mm/dd");
        String expiryDate;
        // wait for the users to enter something

        expiryDate = input.nextLine();
        if (expiryDate.length() >= 1) {
            System.out.println("you entered:" + expiryDate);
        }

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = sdf1.parse(expiryDate);
        long millis1 = date1.getTime();
        return millis1;

    }

    private long askForPurchasedDate() throws ParseException {
        System.out.println("\nPlease enter FoodItem's Purchased Date in yyyy/mm/dd");
        String purchasedDate = input.nextLine();


        if (purchasedDate.length() >= 1) {
            System.out.println("you entered: " + purchasedDate);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = sdf.parse(purchasedDate);
        long millis = date.getTime();
        return millis;
    }

    private void doDeleteFoodItem() {
        boolean onGoing;
        String foodItemNameInput;
        do {
            foodItemNameInput = "";
            System.out.println("\nPlease enter FoodItem's Name you want to delete in your FoodItem List");
            while (foodItemNameInput.length() < 1) {
                foodItemNameInput = input.nextLine();
            }
            if (foodItemNameInput.length() >= 1) {
                System.out.println("you entered: " + foodItemNameInput);
            }
            tryToDeleteFoodItemFromFoodItemList(foodItemNameInput);

            onGoing = askForRepeat();
        } while (onGoing);
    }

    private void tryToDeleteFoodItemFromFoodItemList(String foodItemNameInput) {
        if (foodItemList.containInFoodItemList(foodItemNameInput)) {
            for (FoodItem foodItem : foodItemList.getFoodItemList()) {
                if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
                    foodItemList.deleteFoodItem(foodItem);
                    break;
                }
            }
            System.out.println("you have deleted: " + foodItemNameInput + " in your FoodItem List");
        } else {
            System.out.println("Can't find " + foodItemNameInput + " in your FoodItem List");
        }
    }

    private void doDeleteShoppingItem() {
        boolean onGoing;
        String foodItemNameInput;
        do {
            foodItemNameInput = "";
            System.out.println("\nPlease enter FoodItem's Name you want to delete in your ShoppingItem List");
            while (foodItemNameInput.length() < 1) {
                foodItemNameInput = input.nextLine();
            }
            if (foodItemNameInput.length() >= 1) {
                System.out.println("you entered: " + foodItemNameInput);
            }
            tryToDeleteFoodItemFromShoppingItemList(foodItemNameInput);
            onGoing = askForRepeat();
        } while (onGoing);
    }

    private void tryToDeleteFoodItemFromShoppingItemList(String foodItemNameInput) {
        if (shoppingItemList.containInShoppingItemList(foodItemNameInput)) {
            for (FoodItem foodItem : shoppingItemList.getShoppingItemList()) {
                if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
                    shoppingItemList.deleteFoodItem(foodItem);
                    break;
                }
            }
            System.out.println("you have deleted: " + foodItemNameInput + " in your ShoppingItem List");
        } else {
            System.out.println("Can't find " + foodItemNameInput + " in your ShoppingItem List");
        }

    }

    private void doMarkFoodItem() {
        boolean notEnd = true;
        while (notEnd) {
            System.out.println(
                    "\nPlease enter FoodItem's Name you want to mark as Used or OutOfStock in your FoodItem List");
            String foodItemNameInput = input.nextLine();

            while (foodItemNameInput.length() < 1) {
                foodItemNameInput = input.nextLine();
                if (foodItemNameInput.length() >= 1) {
                    System.out.println("you entered: " + foodItemNameInput);
                }
            }

            if (foodItemList.containInFoodItemList(foodItemNameInput)) {
                askForChangingStatus(foodItemNameInput);

            } else {
                System.out.println("Can't find " + foodItemNameInput + " in FoodItem list");
            }
            notEnd = askForRepeat();
        }
    }

    private void askForChangingStatus(String foodItemNameInput) {
        System.out.println("\nPlease enter one of the Status: type used or outofstock");
        String choiceInput;
        do {
            choiceInput = input.nextLine();
            if (choiceInput.length() >= 1) {
                if (choiceInput.equals("used") || choiceInput.equals("outofstock")) {
                    System.out.println("you entered: " + choiceInput);
                } else {
                    System.out.println("\ninvalid input, please try again!");
                }
            } else {
                System.out.println("\nempty input, please try again!");
            }
        } while (choiceInput.length() < 1 || (!choiceInput.equals("used") && !choiceInput.equals("outofstock")));

        changeFoodItemStatus(foodItemNameInput, choiceInput);

        if (choiceInput.equals("used") || choiceInput.equals("outofstock")) {

            System.out.println(
                    "you have marked: " + foodItemNameInput + " as " + choiceInput + " in your FoodItem List");
        } else {
            System.out.println("invalid input");
        }
    }

    private void changeFoodItemStatus(String foodItemNameInput, String choiceInput) {
        for (FoodItem foodItem : foodItemList.getFoodItemList()) {
            if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
                if (choiceInput.equals("used")) {
                    foodItem.markFoodItemStatus(FoodItem.Status.Used);
                } else if (choiceInput.equals("outofstock")) {
                    foodItem.markFoodItemStatus(FoodItem.Status.OutOfStock);
                }
            }
        }

    }


    private void doAddShoppingItem() {
        boolean notEnd = true;
        while (notEnd) {
            System.out.println("\nPlease enter FoodItem's Name");
            String foodItemNameInput = input.nextLine();

            while (foodItemNameInput.length() < 1) {
                foodItemNameInput = input.nextLine();
                if (foodItemNameInput.length() >= 1) {
                    System.out.println("you have added: " + foodItemNameInput + " in your ShoppingItem List");
                }
            }
            FoodItem foodItem2 = new FoodItem(foodItemNameInput, 0, 0);
            shoppingItemList.addFoodItem(foodItem2);
            notEnd = askForRepeat();
        }
    }

    private void doViewFoodItemList() {
        if (foodItemList.getFoodItemList().isEmpty()) {
            System.out.println("FoodItem list is empty");
        } else {
            for (FoodItem foodItem : foodItemList.getFoodItemList()) {
                System.out.println(foodItem.getFoodItemName());
                System.out.println("Purchased date: " + foodItem.milliSecondToDate(foodItem.getPurchasedDateInMilli()));
                System.out.println("Expiry date: " + foodItem.milliSecondToDate(foodItem.getExpiryDateInMilli()));
                System.out.println("Food Status: " + foodItem.getStatus());
            }
        }


    }

    private void doViewExpiryItem() {
        expiryFoodItemList = foodItemList.returnExpiryFoodItem();

        if (expiryFoodItemList.getFoodItemList().isEmpty()) {
            System.out.println("Expiry FoodItem list is empty");
        } else {
            for (FoodItem foodItem : expiryFoodItemList.getFoodItemList()) {
                System.out.println(foodItem.getFoodItemName());
                System.out.println("Purchased date: " + foodItem.getPurchasedDateInMilli());
                System.out.println("Expiry date: " + foodItem.getExpiryDateInMilli());
                System.out.println("Food Status: " + foodItem.getStatus());

            }
        }

    }


    private void doViewShoppingItemList() {
        if (shoppingItemList.getShoppingItemList().isEmpty()) {
            System.out.println("ShoppingItem list is empty");
        } else {
            for (FoodItem foodItem : shoppingItemList.getShoppingItemList()) {
                System.out.println(foodItem.getFoodItemName());
            }
        }

    }

    // EFFECTS: saves the foodItemList to file
    private void saveFoodItemList() {
        try {
            jsonWriterFoodItemList.open();
            jsonWriterFoodItemList.write(foodItemList);
            jsonWriterFoodItemList.close();
            System.out.println("Saved " + foodItemList + " to " + JSON_STORE_FoodItemList);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_FoodItemList);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads foodItemList from file
    private void loadFoodItemList() {
        try {
            foodItemList = jsonReaderFoodItemList.readFoodItemList();
            System.out.println("Loaded " + foodItemList + " from " + JSON_STORE_FoodItemList);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_FoodItemList);
        }
    }

    // EFFECTS: saves the shoppingItemList to file
    private void saveShoppingItemList() {
        try {
            jsonWriterShoppingItemList.open();
            jsonWriterShoppingItemList.write(shoppingItemList);
            jsonWriterShoppingItemList.close();
            System.out.println("Saved " + shoppingItemList + " to " + JSON_STORE_ShoppingItemList);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE_ShoppingItemList);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads shoppingItemList from file
    private void loadShoppingItemList() {
        try {
            shoppingItemList = jsonReaderShoppingItemList.readShoppingItemList();
            System.out.println("Loaded " + shoppingItemList + " from " + JSON_STORE_ShoppingItemList);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE_ShoppingItemList);
        }
    }


}



