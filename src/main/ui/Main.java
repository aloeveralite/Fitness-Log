package ui;

import java.io.FileNotFoundException;

// main class runs gym log application
public class Main {
    public static void main(String[] args) {
        try {
            new GymLogApp();
        } catch (FileNotFoundException e) {
            System.out.println("Application is unable to be run: file not found");
        }
    }
}


// Aid from: AccountNotRobust
// Class: Main
// Aid from: JsonSerializationDemo
// Class: Main