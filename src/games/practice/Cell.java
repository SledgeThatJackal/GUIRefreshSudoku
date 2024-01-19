package games.practice;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * Controls an individual cell on the game board
 */
public class Cell{
    private JPanel cell;

    private int playerValue;
    private final int actualValue;
    private boolean isCorrect;
    private boolean[] writtenNotes = new boolean[9];
    private final boolean isLocked;
    private final Number number;
    private final Notes notes;
    private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);

    /**
     * Setups the cell panel and sets up the number and note fields
     * @param actualValue The value a player should determine
     * @param isLocked A toggle for determining whether a tile has a player or computer generated tile
     */
    public Cell(int actualValue, boolean isLocked) {
        this.actualValue = actualValue;
        this.isLocked = isLocked;
        this.playerValue = isLocked ? actualValue : 0;
        this.isCorrect = isLocked;

        cell.setFocusable(true);

        number = isLocked ? new Number(actualValue) : new Number();
        notes = new Notes(this);

        cell.add(number.getNumberPanel(), Resources.NUMBER_CARD);
        cell.add(notes.getNotesPanel(), Resources.NOTE_CARD);
    }

    /**
     * Changes the card based on the value within a tile
     */
    public void changeCard(){
        CardLayout cl = (CardLayout) cell.getLayout();

        cl.show(cell, playerValue == 0 ? Resources.NOTE_CARD : Resources.NUMBER_CARD);
    }

    public int getPlayerValue() {
        return playerValue;
    }

    /**
     * (WIP) A getter method for checking the game state
     * @return If the tile contains the correct value
     */
    public boolean isCorrect() {
        return isCorrect;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public JPanel getCell() {
        return cell;
    }

    /**
     * Highlights a note on a cell
     * @param index Which note is getting highlighted
     * @param highlight enable/disable highlighting
     */
    public void highlightNote(int index, boolean highlight){
        if(writtenNotes[index]){
            notes.highlightNote(index, highlight);
        }
    }

    /**
     * Sets the cell's value based on the user input and changes colors accordingly
     * @param move The number a user inputs onto a cell
     */
    public void inputMove(int move){
        playerValue = move;
        number.setValue(move);

        // Check if the numbers are equal and pass the T/F value into the Number Object to select a color to change to
        isCorrect = move == actualValue;
        number.setTextColorBasedOnValue(isCorrect);
    }

    /**
     * Changes the color for both the note and number tile
     * @param color The new color for the background
     */
    public void changeBackground(Color color){
        number.changeBackgroundColor(color);
        notes.changeBackgroundColor(color);
    }

    public boolean[] getWrittenNotes() {
        return writtenNotes;
    }

    /**
     * Updates whether a note is displayed and fires a property change
     * @param i Index where the note is located
     */
    public void setWrittenNote(int i) {
        int index = i - 1;

        boolean oldNote = writtenNotes[index];
        writtenNotes[index] = !writtenNotes[index];

        PCS.fireIndexedPropertyChange(Resources.VISIBLE_NOTE, index, oldNote, writtenNotes[index]);
    }

    /**
     * Adds a listener from this class that's used for note visibility
     * @param listener Class listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        PCS.addPropertyChangeListener(listener);
    }

//    public void removePropertyChangeListener(PropertyChangeListener listener) {
//        PCS.removePropertyChangeListener(listener);
//    }
}
