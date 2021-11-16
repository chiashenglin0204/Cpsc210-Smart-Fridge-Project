package ui;

import model.DateFoodItem;
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
import java.util.List;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;

import static com.sun.tools.internal.ws.wsdl.parser.Util.fail;


public class SmartFridgeApp extends JFrame {
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
    private DateFoodItem emptyDateFoodItem = new DateFoodItem();

    public static final int frameWidth = 800;
    public static final int frameHeight = 800;
    public static final Color backgroundColor = Color.white;
    public static final int buttonWidth = 135;
    public static final int buttonHeight = 30;
    public static final int labelWidth = frameWidth;
    public static final int labelHeight = 30;
    public static final int textFieldWidth = 200;
    public static final int textFieldHeight = 30;


    private JButton createButton(String displayText, int x, int y) {
        JButton button = new JButton(displayText);
        button.setVisible(true);
        button.setBounds(x, y, buttonWidth, buttonHeight);
        button.setBackground(backgroundColor);
        button.setOpaque(true);
        return button;
    }

    private JTextField createTextField(int x, int y) {
        JTextField textField = new JTextField();
        textField.setBounds(x, y, textFieldWidth, textFieldHeight);
        textField.setOpaque(true);
        return textField;
    }

    private JLabel createLabel(String displayText, int x, int y) {
        JLabel label = new JLabel(displayText);
        label.setBounds(x, y, labelWidth, labelHeight);
        return label;
    }


    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setSize(frameWidth, frameHeight);
        panel.setBackground(backgroundColor);
        return panel;
    }

    private JFrame createFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);
        return frame;
    }

    public SmartFridgeApp() throws FileNotFoundException, ParseException {
        jsonWriterFoodItemList = new JsonWriterFoodItemList(JSON_STORE_FoodItemList);
        jsonWriterShoppingItemList = new JsonWriterShoppingItemList(JSON_STORE_ShoppingItemList);
        jsonReaderShoppingItemList = new JsonReaderShoppingItemList(JSON_STORE_ShoppingItemList);
        jsonReaderFoodItemList = new JsonReaderFoodItemList(JSON_STORE_FoodItemList);


        runSmartFridge();
    }


    private void runSmartFridge() throws ParseException, FileNotFoundException {
        //boolean keepGoing = true;
        //String command = null;
        init();

        displayMenu1();
//            command = input.next();
//            command = command.toLowerCase();

//            if (command.equals("q")) {
//                keepgoing = false;
//            } else {
//                processCommand(command);
//            }


    }

//    // MODIFIES: this
//    // EFFECTS: processes user command
//    private void processCommand(String command) throws ParseException {
//        if (command.equals("1")) {
//            doAddFoodItem();
//        } else if (command.equals("2")) {
//            doAddShoppingItem();
//        } else if (command.equals("3")) {
//            doDeleteFoodItem();
//        } else if (command.equals("4")) {
//            doDeleteShoppingItem();
//        } else if (command.equals("5")) {
//            doViewFoodItemList();
//        } else if (command.equals("6")) {
//            doViewShoppingItemList();
//        } else if (command.equals("7")) {
//            doViewExpiryItem();
//        } else if (command.equals("8")) {
//            doMarkFoodItem();
//        } else if (command.equals("s")) {
//            save();
//        } else if (command.equals("l")) {
//            load();
//        } else {
//            System.out.println("Selection not valid...");
//        }
//    }

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

    private void displayMenu1() {
        JFrame frame = createFrame("Smart Fridge");
        JPanel panel = createPanel();
        frame.add(panel, BorderLayout.CENTER);

        JButton button = addFoodItemToFoodItemListButton();
        JButton button1 = viewFoodItemList();
        JButton button2 = addFoodItemToShoppingItemListButton();
        JButton button3 = viewShoppingItemList();
        JButton button4 = viewExpiryItem();
        JButton button5 = saveButton();
        JButton button6 = loadButton();
        JButton button7 = exitButton(frame);


        panel.add(button);
        panel.add(button1);
        panel.add(button2);
        panel.add(button3);
        panel.add(button4);
        panel.add(button5);
        panel.add(button6);
        panel.add(button7);


        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(frameWidth, frameHeight);


    }

    private JButton loadButton() {
        JButton button = createButton("load", 50, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                load();
            }
        });
        return button;
    }


    private JButton saveButton() {
        JButton button = createButton("save", 50, 50);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
        return button;
    }

    private JButton viewExpiryItem() {
        JButton button = createButton("view your expiry Item", 50, 50);
        doViewExpiryItem1(button);

        return button;
    }

    private void doViewExpiryItem1(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame subFrame = createFrame("view your expiry item");
                JPanel subPanel = createPanel();
                subPanel.setBackground(new Color(0, 128, 128));
                List<FoodItem> foodItems = foodItemList.returnExpiryFoodItem().getFoodItemList();
                String[][] data = new String[100][3];
                int index = 0;
                setColumn(data, index, "Name", "Purchase Date", "Expiry Date");

                for (FoodItem foodItem : foodItems) {
                    index += 1;
                    setColumn(data, index, foodItem.getFoodItemName(),
                            foodItem.getPurchasedDate().getDateInString(), foodItem.getExpiryDate().getDateInString());
                }
                String[] column = {"Name", "Purchased date", "Expiry date"};
                JTable jt = new JTable(data, column);
                jt.setBounds(30, 40, 200, 300);

//                JScrollPane sp = new JScrollPane(jt);
//                subPanel.add(sp);
                createAndAddScrollPane(subPanel, jt);
                addExitButtonToSubPanel(subFrame, subPanel);

                addPanelToFrameAndVisible(subFrame, subPanel);
            }
        });
    }

    private void addExitButtonToSubPanel(JFrame subFrame, JPanel subPanel) {
        JButton exitButton = exitButtonForView(subFrame);
        subPanel.add(exitButton);
    }

    private void addPanelToFrameAndVisible(JFrame subFrame, JPanel subPanel) {
        subFrame.add(subPanel);
        subPanel.setVisible(true);
        subFrame.setVisible(true);
    }

    private void setColumn(String[][] data, int index, String name, String s, String s2) {
        data[index][0] = name;
        data[index][1] = s;
        data[index][2] = s2;
    }

    private JButton viewShoppingItemList() {
        JButton button = createButton("view your shoppingItem List", 50, 50);
        doViewShoppingItemList1(button);

        return button;
    }

    private void doViewShoppingItemList1(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame subFrame = createFrame("view your shoppingItemList");
                JPanel subPanel = createPanel();
                subPanel.setBackground(new Color(0, 128, 128));
                List<FoodItem> foodItems = shoppingItemList.getShoppingItemList();
                String[][] data = new String[100][1];
                int index = 0;
                data[index][0] = "Name";

                for (FoodItem foodItem : foodItems) {
                    index += 1;
                    data[index][0] = foodItem.getFoodItemName();

                }
                String[] column = {"Name"};
                JTable jt = new JTable(data, column);
                jt.setBounds(30, 40, 200, 300);

                createAndAddScrollPane(subPanel, jt);
                addExitButtonToSubPanel(subFrame, subPanel);

                addPanelToFrameAndVisible(subFrame, subPanel);

            }
        });
    }

    private void createAndAddScrollPane(JPanel subPanel, JTable jt) {
        JScrollPane sp = new JScrollPane(jt);
        subPanel.add(sp);
    }

    private JButton addFoodItemToShoppingItemListButton() {
        JButton button = createButton("add FoodItem in the ShoppingItem list", 50, 130);
        doAddShoppingItem1(button);

        return button;
    }

    private void doAddShoppingItem1(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create new Frame for adding foodItem to shoppingItemList with label and textField and
                // continue button
                JFrame subFrame = createFrame("add FoodItem to your ShoppingItemList");
                JPanel subPanel = createPanel();
                subPanel.setBackground(new Color(0, 128, 128));

                JLabel label = createLabel(
                        "please enter foodItem purchase name",
                        frameWidth / 2, frameHeight / 4 + 100 - 50);
                JTextField textField = createTextField(frameWidth / 2, frameHeight / 4 + 100);


                JButton exitButton = exitButtonForAddShoppingItemList(subFrame, textField);

                subPanel.add(label, BorderLayout.CENTER);

                subPanel.add(textField);

                subPanel.add(exitButton);

                subPanel.setLayout(null);

                addPanelToFrameAndVisible(subFrame, subPanel);


            }


        });
    }

    private JButton exitButtonForAddShoppingItemList(JFrame subFrame, JTextField textField) {
        JButton button = createButton("Complete!", frameWidth / 2, frameHeight / 4 - 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodItemName = textField.getText();

                FoodItem foodItem1 = new FoodItem(foodItemName, emptyDateFoodItem, emptyDateFoodItem);
                shoppingItemList.addFoodItem(foodItem1);
                subFrame.setVisible(false);

            }
        });
        return button;
    }

    private JButton exitButtonForView(JFrame subFrame) {
        JButton button = createButton("Complete!", frameWidth / 2, frameHeight / 4 - 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                subFrame.setVisible(false);

            }
        });
        return button;
    }

    private JButton viewFoodItemList() {
        JButton button = createButton("view your foodItem List", 50, 50);
        doViewFoodItemList1(button);

        return button;
    }

    private JButton addFoodItemToFoodItemListButton() {
        JButton button = createButton("add FoodItem in the FoodItem list", 50, 90);
        doAddFoodItem1(button);

        return button;

    }

    private void doViewFoodItemList1(JButton button) {
        button.addActionListener(e -> {
            JFrame subFrame = createFrame("view your foodItemList");
            JPanel subPanel = createPanel();
            subPanel.setBackground(new Color(0, 128, 128));
            List<FoodItem> foodItems = foodItemList.getFoodItemList();
            String[][] data = new String[100][3];
            int index = 0;
            setColumn(data, index, "Name", "Purchase Date", "Expiry Date");

            for (FoodItem foodItem : foodItems) {
                index += 1;
                setColumn(data, index, foodItem.getFoodItemName(),
                        foodItem.getPurchasedDate().getDateInString(), foodItem.getExpiryDate().getDateInString());
            }
            String[] column = {"Name", "Purchased date", "Expiry date"};
            JTable jt = new JTable(data, column);
            jt.setBounds(30, 40, 200, 300);

            JScrollPane sp = new JScrollPane(jt);
            addExitButtonToSubPanel(subFrame, subPanel);
            subPanel.add(sp);
            addPanelToFrameAndVisible(subFrame, subPanel);

        });
    }


    private void doAddFoodItem1(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // create new Frame for adding foodItem to foodItemList with label and textField and
                // continue button
                JFrame subFrame = createFrame("add FoodItem to your FoodItemList");
                JPanel subPanel = createPanel();
                subPanel.setBackground(new Color(0, 128, 128));

                JLabel label = getjLabel("please enter foodItem purchase name", 100);
                JTextField textField = createTextField(frameWidth / 2, frameHeight / 4 + 100);

                JLabel label1 = getjLabel("please enter foodItem purchase date", 200);
                JTextField textField1 = createTextField(frameWidth / 2, frameHeight / 4 + 200);

                JLabel label2 = getjLabel("please enter foodItem expiry date", 300);
                JTextField textField2 = createTextField(frameWidth / 2, frameHeight / 4 + 300);

                JButton exit = exitButtonForAddFoodItemList(subFrame, textField, textField1, textField2);

                addJComponentToPanel(subPanel, label, textField, label1, textField1, label2, textField2, exit);

                subPanel.setLayout(null);
                subFrame.add(subPanel);
                subPanel.setVisible(true);
                subFrame.setVisible(true);


            }


        });
    }

    private void addJComponentToPanel(
            JPanel subPanel, JLabel label, JTextField textField, JLabel label1, JTextField textField1,
            JLabel label2, JTextField textField2, JButton exit) {
        subPanel.add(label, BorderLayout.CENTER);
        subPanel.add(label1, BorderLayout.CENTER);
        subPanel.add(label2, BorderLayout.CENTER);
        subPanel.add(textField);
        subPanel.add(textField1);
        subPanel.add(textField2);
        subPanel.add(exit);
    }

    private JLabel getjLabel(String s, int i) {
        return createLabel(
                s,
                frameWidth / 2, frameHeight / 4 + i - 50);
    }

    private JButton exitButtonForAddFoodItemList(
            JFrame subFrame, JTextField textField, JTextField textField1, JTextField textField2) {
        JButton button = createButton("Complete!", frameWidth / 2, frameHeight / 4 - 100);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String foodItemName = textField.getText();
                    DateFoodItem purchaseDate = new DateFoodItem().dateStringToMilli(textField1.getText());
                    DateFoodItem expiryDate = new DateFoodItem().dateStringToMilli(textField2.getText());

                    FoodItem foodItem1 = new FoodItem(foodItemName, purchaseDate, expiryDate);
                    foodItemList.addFoodItem(foodItem1);
                    subFrame.setVisible(false);
                } catch (ParseException exception) {
                    fail("invalid date input");
                }

            }
        });
        return button;
    }

    private JButton exitButton(JFrame frame) {
        JButton button = createButton("click to exit", 800, 800);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });
        return button;

    }

//    private void displayMenu() {
//        System.out.println("\nSelect from:");
//        System.out.println("\t1 -> Add a food Item in your FoodItem List");
//        System.out.println("\t2 -> Add a food item in your ShoppingItem List");
//        System.out.println("\t3 -> Delete a food item in your FoodItem List");
//        System.out.println("\t4 -> Delete a food item in your ShoppingItem List");
//        System.out.println("\t5 -> view your FoodItem List");
//        System.out.println("\t6 -> view your ShoppingItem List");
//        System.out.println("\t7 -> view your Expiry FoodItems");
//        System.out.println("\t8 -> Mark your FoodItem in FoodItem List");
//        System.out.println("\ts -> Save your file");
//        System.out.println("\tl -> Load your file");
//        System.out.println("\tq -> quit");
//    }

//    private void doAddFoodItem() throws ParseException {
//        boolean notEnd = true;
//        while (notEnd) {
//            System.out.println("\nPlease enter FoodItem's Name");
//            String foodItemName = input.nextLine();
//
//            while (foodItemName.length() < 1) {
//                foodItemName = input.nextLine();
//                if (foodItemName.length() >= 1) {
//                    System.out.println("you entered: " + foodItemName);
//                }
//            }
//
//            FoodItem foodItem1 = new FoodItem(foodItemName, askForPurchasedDate(), askForExpiryDate());
//            foodItemList.addFoodItem(foodItem1);
//            System.out.println(foodItemList.getFoodItemList().get(0).getFoodItemName());
//            System.out.println(
//                    "Purchased date: " + foodItemList.getFoodItemList().get(0).getPurchasedDate().getDateInString()
//            );
//            System.out.println(
//                    "Expiry date: "
//                            + foodItemList.getFoodItemList().get(0).getPurchasedDate().getDateInString());
//            notEnd = askForRepeat();
//        }
//
//    }

//    private boolean askForRepeat() {
//        while (true) {
//            System.out.println("Want to keep doing?, type y for yes, n for no");
//            String choiceInputTF = input.nextLine();
//            if (choiceInputTF.length() >= 1) {
//                System.out.println("you entered:" + choiceInputTF);
//            }
//
//            if (choiceInputTF.equals("y")) {
//                return true;
//            } else if (choiceInputTF.equals("n")) {
//                return false;
//            } else {
//                System.out.println("invalid input, please type again");
//            }
//        }
//    }

//    private DateFoodItem askForExpiryDate() throws ParseException {
//        System.out.println("\nPlease enter FoodItem's Expiry Date in yyyy/mm/dd");
//        String expiryDate;
//        // wait for the users to enter something
//
//        expiryDate = input.nextLine();
//        if (expiryDate.length() >= 1) {
//            System.out.println("you entered:" + expiryDate);
//        }
//
//        DateFoodItem date = new DateFoodItem();
//        return date.dateStringToMilli(expiryDate);
//    }

//    private DateFoodItem askForPurchasedDate() throws ParseException {
//        System.out.println("\nPlease enter FoodItem's Purchased Date in yyyy/mm/dd");
//        String purchasedDate = input.nextLine();
//
//        if (purchasedDate.length() >= 1) {
//            System.out.println("you entered: " + purchasedDate);
//        }
//
//        DateFoodItem date = new DateFoodItem();
//        return date.dateStringToMilli(purchasedDate);
//    }

//    private void doDeleteFoodItem() {
//        boolean onGoing;
//        String foodItemNameInput;
//        do {
//            foodItemNameInput = "";
//            System.out.println("\nPlease enter FoodItem's Name you want to delete in your FoodItem List");
//            while (foodItemNameInput.length() < 1) {
//                foodItemNameInput = input.nextLine();
//            }
//            if (foodItemNameInput.length() >= 1) {
//                System.out.println("you entered: " + foodItemNameInput);
//            }
//            tryToDeleteFoodItemFromFoodItemList(foodItemNameInput);
//
//            onGoing = askForRepeat();
//        } while (onGoing);
//    }

//    private void tryToDeleteFoodItemFromFoodItemList(String foodItemNameInput) {
//        if (foodItemList.containInFoodItemList(foodItemNameInput)) {
//            for (FoodItem foodItem : foodItemList.getFoodItemList()) {
//                if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
//                    foodItemList.deleteFoodItem(foodItem);
//                    break;
//                }
//            }
//            System.out.println("you have deleted: " + foodItemNameInput + " in your FoodItem List");
//        } else {
//            System.out.println("Can't find " + foodItemNameInput + " in your FoodItem List");
//        }
//    }

//    private void doDeleteShoppingItem() {
//        boolean onGoing;
//        String foodItemNameInput;
//        do {
//            foodItemNameInput = "";
//            System.out.println("\nPlease enter FoodItem's Name you want to delete in your ShoppingItem List");
//            while (foodItemNameInput.length() < 1) {
//                foodItemNameInput = input.nextLine();
//            }
//            if (foodItemNameInput.length() >= 1) {
//                System.out.println("you entered: " + foodItemNameInput);
//            }
//            tryToDeleteFoodItemFromShoppingItemList(foodItemNameInput);
//            onGoing = askForRepeat();
//        } while (onGoing);
//    }

//    private void tryToDeleteFoodItemFromShoppingItemList(String foodItemNameInput) {
//        if (shoppingItemList.containInShoppingItemList(foodItemNameInput)) {
//            for (FoodItem foodItem : shoppingItemList.getShoppingItemList()) {
//                if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
//                    shoppingItemList.deleteFoodItem(foodItem);
//                    break;
//                }
//            }
//            System.out.println("you have deleted: " + foodItemNameInput + " in your ShoppingItem List");
//        } else {
//            System.out.println("Can't find " + foodItemNameInput + " in your ShoppingItem List");
//        }
//
//    }

//    private void doMarkFoodItem() {
//        boolean notEnd = true;
//        while (notEnd) {
//            System.out.println(
//                    "\nPlease enter FoodItem's Name you want to mark as Used or OutOfStock in your FoodItem List");
//            String foodItemNameInput = input.nextLine();
//
//            while (foodItemNameInput.length() < 1) {
//                foodItemNameInput = input.nextLine();
//                if (foodItemNameInput.length() >= 1) {
//                    System.out.println("you entered: " + foodItemNameInput);
//                }
//            }
//
//            if (foodItemList.containInFoodItemList(foodItemNameInput)) {
//                askForChangingStatus(foodItemNameInput);
//
//            } else {
//                System.out.println("Can't find " + foodItemNameInput + " in FoodItem list");
//            }
//            notEnd = askForRepeat();
//        }
//    }

//    private void askForChangingStatus(String foodItemNameInput) {
//        System.out.println("\nPlease enter one of the Status: type used or outofstock");
//        String choiceInput;
//        do {
//            choiceInput = input.nextLine();
//            if (choiceInput.length() >= 1) {
//                if (choiceInput.equals("used") || choiceInput.equals("outofstock")) {
//                    System.out.println("you entered: " + choiceInput);
//                } else {
//                    System.out.println("\ninvalid input, please try again!");
//                }
//            } else {
//                System.out.println("\nempty input, please try again!");
//            }
//        } while (choiceInput.length() < 1 || (!choiceInput.equals("used") && !choiceInput.equals("outofstock")));
//
//        changeFoodItemStatus(foodItemNameInput, choiceInput);
//
//        if (choiceInput.equals("used") || choiceInput.equals("outofstock")) {
//
//            System.out.println(
//                    "you have marked: " + foodItemNameInput + " as " + choiceInput + " in your FoodItem List");
//        } else {
//            System.out.println("invalid input");
//        }
//    }

//    private void changeFoodItemStatus(String foodItemNameInput, String choiceInput) {
//        for (FoodItem foodItem : foodItemList.getFoodItemList()) {
//            if (foodItem.getFoodItemName().equals(foodItemNameInput)) {
//                if (choiceInput.equals("used")) {
//                    foodItem.markFoodItemStatus(FoodItem.Status.Used);
//                } else if (choiceInput.equals("outofstock")) {
//                    foodItem.markFoodItemStatus(FoodItem.Status.OutOfStock);
//                }
//            }
//        }
//
//    }


//    private void doAddShoppingItem() {
//        boolean notEnd = true;
//        while (notEnd) {
//            System.out.println("\nPlease enter FoodItem's Name");
//            String foodItemNameInput = input.nextLine();
//
//            while (foodItemNameInput.length() < 1) {
//                foodItemNameInput = input.nextLine();
//                if (foodItemNameInput.length() >= 1) {
//                    System.out.println("you have added: " + foodItemNameInput + " in your ShoppingItem List");
//                }
//            }
//            FoodItem foodItem2 = new FoodItem(foodItemNameInput, new DateFoodItem(), new DateFoodItem());
//            shoppingItemList.addFoodItem(foodItem2);
//            notEnd = askForRepeat();
//        }
//    }

//    private void doViewFoodItemList() {
//        if (foodItemList.getFoodItemList().isEmpty()) {
//            System.out.println("FoodItem list is empty");
//        } else {
//            for (FoodItem foodItem : foodItemList.getFoodItemList()) {
//                System.out.println(foodItem.getFoodItemName());
//                System.out.println("Purchased date: " + foodItem.getPurchasedDate().getDateInString());
//                System.out.println("Expiry date: " + foodItem.getExpiryDate().getDateInString());
//                System.out.println("Food Status: " + foodItem.getStatus());
//            }
//        }
//
//
//    }

//    private void doViewExpiryItem() {
//        expiryFoodItemList = foodItemList.returnExpiryFoodItem();
//
//        if (expiryFoodItemList.getFoodItemList().isEmpty()) {
//            System.out.println("Expiry FoodItem list is empty");
//        } else {
//            for (FoodItem foodItem : expiryFoodItemList.getFoodItemList()) {
//                System.out.println(foodItem.getFoodItemName());
//                System.out.println("Purchased date: " + foodItem.getPurchasedDate().getDateInString());
//                System.out.println("Expiry date: " + foodItem.getExpiryDate().getDateInString());
//                System.out.println("Food Status: " + foodItem.getStatus());
//            }
//        }
//
//    }


//    private void doViewShoppingItemList() {
//        if (shoppingItemList.getShoppingItemList().isEmpty()) {
//            System.out.println("ShoppingItem list is empty");
//        } else {
//            for (FoodItem foodItem : shoppingItemList.getShoppingItemList()) {
//                System.out.println(foodItem.getFoodItemName());
//            }
//        }
//
//    }

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



