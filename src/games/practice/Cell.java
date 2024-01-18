package games.practice;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Set;

public class Cell{

    // Swing
    private JPanel cell;

    // Property Names
    public static final String VISIBLE_NOTE = "visibleNote";

    // Game Fields
    private int playerValue;
    private final int actualValue;
    private boolean isCorrect;
    private boolean[] writtenNotes = new boolean[9];
    private final boolean isLocked;
    private final Number number;
    private final Notes notes;
    private final String numberCard = "number";
    private final String notesCard = "notes";
    private final PropertyChangeSupport PCS = new PropertyChangeSupport(this);


    public Cell(int actualValue, boolean isLocked, Color backgroundColor, Color textColor, Font font) {
        this.actualValue = actualValue;
        this.isLocked = isLocked;
        this.playerValue = isLocked ? actualValue : 0;
        this.isCorrect = isLocked;

        cell.setFocusable(true);

        number = isLocked ? new Number(actualValue, backgroundColor, textColor, font) : new Number(backgroundColor, textColor, font);
        notes = new Notes(backgroundColor, textColor, font, this);

        cell.add(number.getNumberPanel(), numberCard);
        cell.add(notes.getNotesPanel(), notesCard);
    }

    public void changeCard(){
        CardLayout cl = (CardLayout) cell.getLayout();

        cl.show(cell, playerValue == 0 ? notesCard : numberCard);
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
        isCorrect = move == actualValue;
        number.setTextColorBasedOnValue(isCorrect);
    }

    public void changeBackground(Color color){
        number.getNumberPanel().setBackground(color);

        for(JLabel currentLabel: notes.getNotes()){
            currentLabel.setBackground(color);
        }

        notes.changeBackgroundColor(color);
    }

    public JLabel getNumberTextField(){
        return number.getUserInput();
    }

    public Set<JLabel> getNoteLabels(){
        return notes.getNotes();
    }

    public boolean[] getWrittenNotes() {
        return writtenNotes;
    }

    public void setWrittenNotes(boolean[] writtenNotes) {
        boolean[] oldNotes = this.writtenNotes;
        this.writtenNotes = writtenNotes;

        PCS.firePropertyChange(VISIBLE_NOTE, oldNotes, writtenNotes);
    }

    public boolean getWrittenNote(int i) {
        return writtenNotes[i];
    }

    public void setWrittenNote(int i) {
        int index = i - 1;

        boolean oldNote = writtenNotes[index];
        writtenNotes[index] = !writtenNotes[index];

        PCS.fireIndexedPropertyChange(VISIBLE_NOTE, index, oldNote, writtenNotes[index]);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        PCS.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        PCS.removePropertyChangeListener(listener);
    }
}
