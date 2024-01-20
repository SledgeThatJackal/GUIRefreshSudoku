package games.practice;

import javax.swing.*;
import java.awt.*;

/**
 * Contains/controls the number GUI
 */
public class Number {
    private JPanel numberPanel;
    private JLabel userInput;

    /**
     * Setups up the panel and label that displays the player inputted value
     */
    public Number() {
        numberPanel.setBackground(Resources.SUDOKU_BACKGROUND_COLOR);

        userInput.setFont(Resources.DEFAULT_FONT);
        userInput.setHorizontalAlignment(JTextField.CENTER);
        userInput.setForeground(Resources.CORRECT_TEXT_COLOR);
        userInput.setBorder(null);
        userInput.setFocusable(false);
    }

    /**
     * Overloaded constructor for creating a number panel for provided tiles
     * @param value The value that will be displayed at the start the game
     */
    public Number(int value) {
        this();
        userInput.setText(String.valueOf(value));
        userInput.setForeground(Color.BLACK);
    }

    public void setValue(int value) {
        userInput.setText(value == 0 ? "" : String.valueOf(value));
    }

    public JPanel getNumberPanel() {
        return numberPanel;
    }

    /**
     * Changes the text color between green and red based on the value provided
     * @param isCorrect T/F based on the number the user inputted onto a cell
     */
    public void setTextColorBasedOnValue(boolean isCorrect){
        userInput.setForeground(isCorrect ? Resources.CORRECT_TEXT_COLOR : Resources.INCORRECT_TEXT_COLOR);
    }

    /**
     * A method for changing the color on the number panel
     * @param color The new color the background will be
     */
    public void changeBackgroundColor(Color color){
        numberPanel.setBackground(color);
    }
}
