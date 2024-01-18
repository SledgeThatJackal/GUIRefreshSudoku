package games.practice;

import javax.swing.*;
import java.awt.*;

public class Number {
    // Swing Components
    private JPanel numberPanel;
    private JLabel userInput;
    private Color textColor;

    public Number(Color backgroundColor, Color textColor, Font font) {
        this.textColor = textColor;

        numberPanel.setBackground(backgroundColor);

        userInput.setFont(font);
        userInput.setHorizontalAlignment(JTextField.CENTER);
        userInput.setForeground(textColor);
        userInput.setBorder(null);
        userInput.setFocusable(false);
    }

    public Number(int value, Color backgroundColor, Color textColor, Font font) {
        this(backgroundColor, textColor, font);
        userInput.setText(String.valueOf(value));
        userInput.setForeground(new Color(0, 0, 0));
    }

    public void setValue(int value) {
        userInput.setText(value == 0 ? "" : String.valueOf(value));
    }

    public JPanel getNumberPanel() {
        return numberPanel;
    }

    public void setTextColorBasedOnValue(boolean isCorrect){
        userInput.setForeground(isCorrect ? textColor : new Color(121, 0, 0));
    }

    public JLabel getUserInput(){
        return userInput;
    }
}
