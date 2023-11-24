package ui.tabs;

import model.GymExercise;
import model.GymLog;
import ui.GymLogGUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents an abstract class tab that provides the GUI functions to its subclasses
public abstract class Tab extends JPanel {
    private GymLogGUI gymLogController;

    // EFFECTS: constructs a tab with the GUI controller
    public Tab(GymLogGUI gymLogController) {
        this.gymLogController = gymLogController;
    }

    public GymLogGUI getGymLogController() {
        return gymLogController;
    }
}

// Aid from: LongFormProblemStarters
// Package: SmartHome
// Class: Tab
