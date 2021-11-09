package ui;

import java.io.FileNotFoundException;
import java.text.ParseException;
import javax.swing.*;


public class Main {
    public static void main(String[] args) {
        try {
            new SmartFridgeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (ParseException e) {
            System.out.println("Did not expect ParseException");
        }


    }
}
