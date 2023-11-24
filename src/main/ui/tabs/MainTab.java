package ui.tabs;

import model.GymExercise;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.GymLogGUI;

import javax.swing.*;
import java.awt.*;

// Represents the main tab allowing users through GUI to add exercises
public class MainTab extends Tab {
    private String name;
    private String target;
    private int weight;
    private int sets;
    private int repetitions;
    private JLabel status;
    private JTextField nameField;
    private JTextField targetField;
    private JTextField weightField;
    private JTextField setsField;
    private JTextField repetitionsField;
    private GymExercise gymExercise;

    // EFFECTS: constructs a home tab that sets up a grid layout of the panel and all the buttons
    public MainTab(GymLogGUI gymLogController) {
        super(gymLogController);
        setLayout(new GridLayout(3, 1));
        allButtons();
        name = "";
        target = "";
        weight = 0;
        sets = 0;
        repetitions = 0;
    }

    // EFFECTS: creates all the buttons for adding a new exercise, saving and loading state, and a label to keep track
    //          of the status of the buttons
    private void allButtons() {
        JButton addNewExercise = new JButton("Add new exercise");
        JButton save = new JButton("Save current exercise(s)");
        JButton load = new JButton("Load previous exercise(s)");
        status = new JLabel("Status of exercise");

        addExerciseName();
        addExerciseTarget();
        addExerciseWeight();
        addExerciseSets();
        addExerciseRepetitions();

        JPanel buttonRow = new JPanel();
        buttonRow.setLayout(new FlowLayout());
        buttonRow.add(status);
        buttonRow.add(addNewExercise);
        buttonRow.add(save);
        buttonRow.add(load);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        addNewExercise.addActionListener(e -> addExercise());
        save.addActionListener(e -> saveState());
        load.addActionListener(e -> loadState());

        this.add(buttonRow);
    }

    // EFFECTS: creates a label, a text field for users to enter name of the exercise, and button to set
    //          information with action listener and is added to a panel that is added to main panel
    private void addExerciseName() {
        JLabel nameText = new JLabel("Name of the exercise:");
        nameField = new JTextField(10);
        JButton nameButton = new JButton("Enter");

        nameButton.addActionListener(e -> {
            status.setText(nameField.getText());
            name = nameField.getText();
        });

        JPanel exerciseName = new JPanel();

        exerciseName.add(nameText);
        exerciseName.add(nameField);
        exerciseName.add(nameButton);

        this.add(exerciseName);
    }

    // EFFECTS: creates a label, a text field for users to enter target of the exercise, and button to set
    //          information with action listener and is added to a panel that is added to main panel
    private void addExerciseTarget() {
        JLabel targetText = new JLabel("Target of the exercise:");
        targetField = new JTextField(10);
        JButton targetButton = new JButton("Enter");

        targetButton.addActionListener(e -> {
            status.setText(targetField.getText());
            target = targetField.getText();
        });

        JPanel exerciseTarget = new JPanel();

        exerciseTarget.add(targetText);
        exerciseTarget.add(targetField);
        exerciseTarget.add(targetButton);
        this.add(exerciseTarget);
    }

    // EFFECTS: creates a label, a text field for users to enter weight of the exercise, and button to set
    //          information with action listener and is added to a panel that is added to main panel
    private void addExerciseWeight() {
        JLabel weightText = new JLabel("Weight:");
        weightField = new JTextField(10);
        JButton weightButton = new JButton("Enter");

        weightButton.addActionListener(e -> {
            status.setText(weightField.getText());
            String stringText = weightField.getText();
            weight = Integer.parseInt(stringText);
        });

        JPanel exerciseTarget = new JPanel();

        exerciseTarget.add(weightText);
        exerciseTarget.add(weightField);
        exerciseTarget.add(weightButton);

        this.add(exerciseTarget);
    }

    // EFFECTS: creates a label, a text field for users to enter sets of the exercise, and button to set
    //          information with action listener and is added to a panel that is added to main panel
    private void addExerciseSets() {
        JLabel setsText = new JLabel("Sets:");
        setsField = new JTextField(10);
        JButton setsButton = new JButton("Enter");

        setsButton.addActionListener(e -> {
            status.setText(setsField.getText());
            String stringText = setsField.getText();
            sets = Integer.parseInt(stringText);
        });

        setsText.setText("Sets:");

        JPanel exerciseSets = new JPanel();

        exerciseSets.add(setsText);
        exerciseSets.add(setsField);
        exerciseSets.add(setsButton);

        this.add(exerciseSets);
    }

    // EFFECTS: creates a label, a text field for users to enter repetitions of the exercise, and button to set
    //          information with action listener and is added to a panel that is added to main panel
    private void addExerciseRepetitions() {
        JLabel repetitionsText = new JLabel("Repetitions:");
        repetitionsField = new JTextField(10);
        JButton repetitionsButton = new JButton("Enter");

        repetitionsButton.addActionListener(e -> {
            status.setText(repetitionsField.getText());
            String stringText = repetitionsField.getText();
            repetitions = Integer.parseInt(stringText);
        });

        JPanel exerciseRepetitions = new JPanel();

        exerciseRepetitions.add(repetitionsText);
        exerciseRepetitions.add(repetitionsField);
        exerciseRepetitions.add(repetitionsButton);

        this.add(exerciseRepetitions);
    }

    // EFFECTS: using the values of name, target, weight, sets, and repetition acquired through the GUI, creates
    //          a gym exercise object and records it to the gym log
    //          exercise must have a non-empty value for its name and target, else nothing will be recorded
    private void addExercise() {
        nameField.setText("");
        targetField.setText("");
        weightField.setText("");
        setsField.setText("");
        repetitionsField.setText("");

        if ((!(name.equals(""))) && (!(target.equals("")))) {
            status.setText("Exercise successfully added");
            successImagePopUp();
            gymExercise = new GymExercise(name, target, (weight > 0));
            gymExercise.addWeight(weight);
            gymExercise.setSets(sets);
            gymExercise.setRepetitions(repetitions);
            getGymLogController().getGymLog().recordExercise(gymExercise);
        }

        name = "";
        target = "";
        weight = 0;
        sets = 0;
        repetitions = 0;
    }

    // EFFECTS: uses a dialogue to create a pop-up window of a checkmark icon
    private void successImagePopUp() {
        ImageIcon icon = new ImageIcon("data/icons/checkmark.png");

        JLabel label = new JLabel(icon);

        JDialog dialog = new JDialog(this.getGymLogController(), "Success", true);
        dialog.setLayout(new BorderLayout());
        dialog.add(new JScrollPane(label));
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    // EFFECTS: saves current state of what exercises were recorded and prompts a success icon
    private void saveState() {
        getGymLogController().save();
        successImagePopUp();
    }

    // EFFECTS: loads previously saved state of what exercises were recorded and prompts a success icon
    private void loadState() {
        getGymLogController().load();
        successImagePopUp();
    }
}

// Aid from: LongFormProblemStarters
// Package: SmartHome
// Class: HomeTab