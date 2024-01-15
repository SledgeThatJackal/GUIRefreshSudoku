package games.practice;

import javax.swing.*;
import java.awt.*;

public class Notes {
    // Swing Components
    private JPanel notesPanel;

    // Fields
    private JTextField[] notes;

    public Notes(Color backgroundColor, Color textColor) {
        notes = new JTextField[9];
        GridLayout currentLayout = new GridLayout(0, 3, 0, 0);
        notesPanel.setLayout(currentLayout);
        notesPanel.setFocusable(true);

        for(int i = 0; i < 9; i++) {
            JTextField noteTextField = new JTextField();

            noteTextField.setEditable(false);
            noteTextField.setFocusable(false);
            noteTextField.setFont(new Font("SansSerif", Font.PLAIN, 20));
            noteTextField.setHorizontalAlignment(JTextField.CENTER);
            noteTextField.setBackground(backgroundColor);
            noteTextField.setForeground(textColor);
            noteTextField.setBorder(null);
            noteTextField.setText(String.valueOf(i + 1));
            noteTextField.setVisible(false);

            notes[i] = noteTextField;
            notesPanel.add(noteTextField);
        }
    }

    public JPanel getNotesPanel() {
        return notesPanel;
    }
}
