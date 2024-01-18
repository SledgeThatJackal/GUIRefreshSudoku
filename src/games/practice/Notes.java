package games.practice;

import javax.swing.*;
import java.awt.*;
import java.beans.IndexedPropertyChangeEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Set;

public class Notes implements PropertyChangeListener {
    // Swing Components
    private JPanel notesPanel;

    // Fields
    private final ArrayList<JTextField> notes;
    private final Color noteTextColor = new Color(91, 91, 91, 0);
    private final Color aNoteTextColor = new Color(91, 91, 91);

    public Notes(Color backgroundColor, Color textColor, Font font, Cell cell) {
        notesPanel.setBackground(backgroundColor);

        notes = new ArrayList<>();
        GridLayout currentLayout = new GridLayout(3, 3, 0, 0);
        notesPanel.setLayout(currentLayout);

        for(int i = 0; i < 9; i++) {
            JTextField textField = new JTextField();

            textField.setEditable(false);
            textField.setFont(font);
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setBackground(backgroundColor);
            textField.setForeground(noteTextColor);
            textField.setBorder(null);
            textField.setText(String.valueOf(i + 1));
            textField.setFocusable(false);

            notes.add(textField);
            notesPanel.add(textField);
        }

        cell.addPropertyChangeListener(this);
    }

    public JPanel getNotesPanel() {
        return notesPanel;
    }

    public Set<JTextField> getNotes() {
        return Set.copyOf(notes);
    }


    public void changeBackgroundColor(Color color){
        notesPanel.setBackground(color);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(Cell.VISIBLE_NOTE.equals(evt.getPropertyName())){
            notes.get(((IndexedPropertyChangeEvent) evt).getIndex()).setForeground((Boolean) evt.getNewValue() ? aNoteTextColor : noteTextColor);
        }
    }
}
