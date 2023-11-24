package ui;

import model.GymLog;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.ExercisesTab;
import ui.tabs.MainTab;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the Graphical User Interface for the Gym Log and sets up the JFrame window, the panels for each
// tab, and a tabbed pane containing access to all tabs
public class GymLogGUI extends JFrame {
    private static final String JSON_STORAGE = "./data/gymlog.json";
    private GymLog gymLog;
    private JTabbedPane tabbedPane;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;

    // EFFECTS: constructs a gym log graphical user interface that sets how to close the window, the size, visibility,
    //          instantiates a gym log, and tabbed panes
    public GymLogGUI() {
        super("Your Gym Log");
        setSize(800, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        gymLog = new GymLog("Your Gym Log");
        jsonReader = new JsonReader(JSON_STORAGE);
        jsonWriter = new JsonWriter(JSON_STORAGE);

        formatTabbedPane();

        add(tabbedPane);

        setVisible(true);
    }

    // EFFECTS: creates a tabbed pane on the JFrame on the right side of the window with two tab options of HomeTab
    //          and ExercisesTab
    public void formatTabbedPane() {
        tabbedPane = new JTabbedPane();
        tabbedPane.setTabPlacement(JTabbedPane.RIGHT);

        JPanel homeTab = new MainTab(this);
        JPanel exercisesTab = new ExercisesTab(this);

        tabbedPane.add(homeTab, 0);
        tabbedPane.setTitleAt(0, "Main");
        tabbedPane.add(exercisesTab, 1);
        tabbedPane.setTitleAt(1, "Exercises");
    }

    // MODIFIES: this
    // EFFECTS: saves the gym log to file
    public void save() {
        try {
            jsonWriter.openWriter();
            jsonWriter.writeGymLog(gymLog);
            jsonWriter.closeWriter();
            System.out.println("Saving " + gymLog.getName() + " to " + JSON_STORAGE);
        } catch (FileNotFoundException e) {
            System.out.println("Not able to write to file: " + JSON_STORAGE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads the gym log from file
    public void load() {
        try {
            gymLog = jsonReader.readGymLog();
            System.out.println("Loading " + gymLog.getName() + " from " + JSON_STORAGE);
        } catch (IOException e) {
            System.out.println("Not able to read from file: " + JSON_STORAGE);
        }
    }

    public GymLog getGymLog() {
        return gymLog;
    }

    public static void main(String[] args) {
        new GymLogGUI();
    }
}

// Aid from: LongFormProblemStarters
// Package: SmartHome
// Class: SmartHomeUI