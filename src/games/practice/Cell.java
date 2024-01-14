package games.practice;

import javax.swing.*;

public class Cell {

    // Swing
    private JTextField note1;
    private JPanel cell;
    private JPanel note;
    private JTextField note4;
    private JTextField note7;
    private JTextField note2;
    private JTextField note3;
    private JTextField note5;
    private JTextField note6;
    private JTextField note8;
    private JTextField note9;
    private JTabbedPane tabbedPane1;
    private JPanel playerInput;
    private JTextField userInput;

    // Game Fields
    private int playerValue;
    private int actualValue;
    private boolean isCorrect;
    private boolean[] notes;
    private boolean isLocked;

    public Cell(int actualValue, boolean isLocked) {
        this.actualValue = actualValue;
        this.isLocked = isLocked;
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public void setPlayerValue(int playerValue) {
        this.playerValue = playerValue;
    }

    public JTextField getUserInput() {
        return note1;
    }

    public int getActualValue() {
        return actualValue;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean[] getNotes() {
        return notes;
    }

    public boolean isLocked() {
        return isLocked;
    }
}
