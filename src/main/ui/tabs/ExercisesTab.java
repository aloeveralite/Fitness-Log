package ui.tabs;

import model.GymExercise;
import ui.GymLogGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a tab containing exercises on the JFrame window that extends Tab
public class ExercisesTab extends Tab {
    private JButton showExercises;
    private JButton selectExercise;
    private JLabel selectedExercise;
    private JScrollPane exercisePane;
    private DefaultListModel<String> listModel;
    private JList<String> gymExercises;

    // EFFECTS: constructs an exercise tab that sets up a grid layout of the panel and instantiates lists
    public ExercisesTab(GymLogGUI gymLogController) {
        super(gymLogController);
        listModel = new DefaultListModel<>();
        gymExercises = new JList<>(listModel);
        setLayout(new GridLayout(3, 1));
        exercisesRepository();
    }

    // EFFECTS: creates buttons, a label, and a scroll pane and adds it to the JPanel
    private void exercisesRepository() {
        showExercises = new JButton("All exercises");
        selectExercise = new JButton("Select an exercise");
        selectedExercise = new JLabel("");
        exercisePane = new JScrollPane(gymExercises);

        viewALlExercises();
        selectExercise();

        JPanel panel = new JPanel();
        panel.add(exercisePane);
        panel.add(showExercises);
        panel.add(selectExercise);
        panel.add(selectedExercise);

        this.add(panel);
    }

    // MODIFIES: this
    // EFFECTS:  adds and displays all exercises added using the GUI with action listener
    private void viewALlExercises() {
        showExercises.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();

                if (buttonPressed.equals("All exercises")) {
                    if (!(getGymLogController().getGymLog().getGymExercises().isEmpty())) {
                        for (GymExercise gymExercise : getGymLogController().getGymLog().getGymExercises()) {
                            listModel.addElement(gymExercise.getName());
                        }
                    }
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: prompts a dialogue to user through GUI to select an exercise from all exercises added in the scroll
    //          pane
    private void selectExercise() {
        selectExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String select = JOptionPane.showInputDialog("Select an exercise by index: (from 0)");
                int selectInt = Integer.parseInt(select);
                if (selectInt < getGymLogController().getGymLog().totalGymExercisesRecorded()) {
                    String exerciseName = getGymLogController().getGymLog().viewGymExercise(selectInt);
                    selectedExercise.setText(exerciseName);
                }
            }
        });
    }
}

// Aid from: LongFormProblemStarters
// Package: SmartHome
// Class: ReportTab