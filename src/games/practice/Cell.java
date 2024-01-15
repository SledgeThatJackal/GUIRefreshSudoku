package games.practice;

import javax.swing.*;
import java.awt.*;

public class Cell{

    // Swing
    private JPanel cell;

    // Game Fields
    private int playerValue;
    private final int actualValue;
    private boolean isCorrect;
    private boolean[] writtenNotes = new boolean[9];
    private final boolean isLocked;
    private Number number;
    private Notes notes;
    private final String numberCard = "number";
    private final String notesCard = "notes";


    public Cell(int actualValue, boolean isLocked, Color backgroundColor, Color textColor) {
        this.actualValue = actualValue;
        this.isLocked = isLocked;
        this.playerValue = isLocked ? actualValue : 0;

        number = isLocked ? new Number(actualValue) : new Number();
        notes = new Notes(backgroundColor, textColor);

        cell.add(number.getNumberPanel(), numberCard);
        cell.add(notes.getNotesPanel(), notesCard);
    }

    public int getPlayerValue() {
        return playerValue;
    }

    public void setPlayerValue(int playerValue) {
        this.playerValue = playerValue;
    }

    public int getActualValue() {
        return actualValue;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean[] getWrittenNotes() {
        return writtenNotes;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public JPanel getCell() {
        return cell;
    }

    public void inputMove(int move){
        playerValue = move;
        number.setValue(move);

        // Check if the numbers are equal and pass the T/F value into the Number Object to select a color to change to
        number.setTextColorBasedOnValue(move == actualValue);
    }

    public void changeBackground(Color color){
        number.getUserInput().setBackground(color);
    }

    public JTextField getNumberTextField(){
        return number.getUserInput();
    }
}
