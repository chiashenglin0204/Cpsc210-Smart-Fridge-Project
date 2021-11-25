package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;



public class Main {
    public static void main(String[] args) {
        try {
            new SmartFridgeApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        } catch (ParseException e) {
            System.out.println("Did not expect ParseException");
        } catch (IOException e) {
            System.out.println("Unable to run application: IOException");

        }

    }
}
