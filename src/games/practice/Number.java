package games.practice;

import javax.swing.*;
import java.awt.*;

public class Number {
    // Swing Components
    private JPanel numberPanel;
    private JTextField userInput;

    public Number() {
        userInput.setEditable(false);
        userInput.setFont(new Font("SansSerif", Font.PLAIN, 20));
        userInput.setHorizontalAlignment(JTextField.CENTER);
        userInput.setBackground(Color.darkGray);
        userInput.setForeground(Color.LIGHT_GRAY);
        userInput.setBorder(null);
    }

    public Number(int value) {
        this();
        userInput.setText(String.valueOf(value));
        userInput.setFocusable(false);
        userInput.setForeground(new Color(0, 0 , 128)); // Navy
    }

    public void setValue(int value) {
        userInput.setText(value == 0 ? "" : String.valueOf(value));
    }

    public JPanel getNumberPanel() {
        return numberPanel;
    }

    public void setTextColorBasedOnValue(boolean isCorrect){
        userInput.setForeground(isCorrect ? Color.LIGHT_GRAY : Color.RED);
    }

    public JTextField getUserInput(){
        return userInput;
    }
}
