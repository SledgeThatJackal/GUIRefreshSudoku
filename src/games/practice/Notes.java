package games.practice;

import javax.swing.*;
import java.awt.*;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

/**
 * Contains/controls the note GUI
 */
public class Notes implements PropertyChangeListener {
    private JPanel notesPanel;

    private final ArrayList<JLabel> notes;

    /**
     * Creates all the labels that will display the notes
     * @param cell The parent object that holds this class for determining when a note visibility changes
     */
    public Notes(Cell cell) {
        notes = new ArrayList<>();
        GridLayout currentLayout = new GridLayout(3, 3, 0, 0);
        notesPanel.setLayout(currentLayout);

        for(int i = 0; i < 9; i++) {
            JLabel label = new JLabel();

            label.setFont(Resources.FONT);
            label.setHorizontalAlignment(JTextField.CENTER);
            label.setBackground(Resources.SUDOKU_BACKGROUND_COLOR);
            label.setForeground(Resources.NOTE_TEXT_COLOR);
            label.setBorder(null);
            label.setText(String.valueOf(i + 1));
            label.setVisible(false);

            notes.add(label);
            notesPanel.add(label);
        }

        cell.addPropertyChangeListener(this);
    }

    public JPanel getNotesPanel() {
        return notesPanel;
    }

    /**
     * Changes the background color of the notes panel
     * @param color The new color to be displayed
     */
    public void changeBackgroundColor(Color color){
        notesPanel.setBackground(color);
    }

    /**
     * Changes the note visibility, if the property it's linked to changes
     * @param evt A PropertyChangeEvent object describing the event source
     *          and the property that has changed.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(Resources.VISIBLE_NOTE.equals(evt.getPropertyName())){
            notes.get(((IndexedPropertyChangeEvent) evt).getIndex()).setVisible((Boolean) evt.getNewValue());
        }
    }
}
