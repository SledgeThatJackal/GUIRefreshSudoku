package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class SudokuGUI {
    private JPanel SudokuPanel;
    private JTextField focus;
    private List<List<JTextField>> textFields = new ArrayList<>();

    public SudokuGUI(){
        // General Formatting
        Font defaultFont = new Font("SansSerif", Font.PLAIN, 20);
        SudokuPanel.setBackground(Color.BLACK);

        // Tile Creation
        GridLayout currentLayout = new GridLayout(0, 9, 3, 3);
        SudokuPanel.setLayout(currentLayout);
        for(int j = 0; j < 9; j++){
            for(int k = 0; k < 9; k++) {
                JTextField textField = new JTextField();
                textField.setEditable(false);
                textField.setFont(defaultFont);
                textField.setHorizontalAlignment(JTextField.CENTER);
                textField.setBackground(Color.darkGray);
                textField.setForeground(Color.LIGHT_GRAY);
                textField.setBorder(null);

                SudokuPanel.add(textField);
                textFields.get(j).add(textField);
            }
        }

        // Input Values
        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent event){
                String propertyName = event.getPropertyName();
                if(("focusOwner".equals(propertyName)) && (event.getNewValue() instanceof JTextField)){
                    // Once the focus changes, change the background color back on the field that previously had focus
                    if(focus != null){
                        focus.setBackground(Color.darkGray);
                    }

                    // Update Object with Focus
                    focus = (JTextField) event.getNewValue();
                    focus.setBackground(Color.gray);

                    // Determine if a key was press, if it was a number, and if it was display it.
                    focus.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            super.keyPressed(e);
                            char key = e.getKeyChar();

                            // Only changing the TextField if the character pressed is 1-9
                            if(Character.isDigit(key)){
                                if(Character.getNumericValue(key) != 0){
                                    focus.setText(String.valueOf(key));
                                    for(int j = 0; j < 9; j++){
                                        for(int k = 0; k < 9; k++){
                                            JTextField currJTextField = textFields.get(j).get(k);
                                            if(currJTextField == focus){
                                                // Call Game Info Method
                                            }   // Add configuration for 0 case
                                        }
                                    }
                                }
                            }

                            // Checking if user hits backspace
                            if(e.getKeyCode() == 8){
                                focus.setText("");
                            }
                        }
                    });
                }
            }
        });
    }

    public JPanel getSudokuPanel() {
        return SudokuPanel;
    }

}
