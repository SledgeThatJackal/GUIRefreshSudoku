package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameGUI {
    private JPanel gamePanel;
    private JTextField textField1;
    private Cell[][] cells = new Cell[9][9];
    private GameInfo info;
    private Cell focus;

    // Fields
    private Color focusedColor = Color.GRAY;
    private Color relatedColor = Color.LIGHT_GRAY;
    private Color backgroundColor = Color.darkGray;
    private int x;
    private int y;

    public GameGUI(){
        // Game Setup
        info = new GameInfo();

        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener(new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent event){
                String propertyName = event.getPropertyName();
                if(("focusOwner".equals(propertyName)) && (event.getNewValue() instanceof JTextField)){
                    // Once the focus changes, change the background color back on the field that previously had focus
                    if(focus != null){
                        clearFocus();
                    }

                    // Update Object with Focus
                    for(int j = 0; j < 9; j++){
                        for(int k = 0; k < 9; k++) {
                            if(cells[j][k].getNumberTextField().equals((JTextField) event.getNewValue())){
                                focus = cells[j][k];
                                x = j;
                                y = k;

                                break;
                            }
                        }
                    }

                    focusNumericValue(10);

                    // Determine if a key was press, if it was a number, and if it was display it.
                    focus.getNumberTextField().addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            super.keyPressed(e);

                            char key = e.getKeyChar();

                            if(key != '0'){
                                clearFocus();
                            }

                            // Only changing the TextField if the character pressed is 1-9
                            if(Character.isDigit(key)){
                                if(Character.getNumericValue(key) != 0){
                                    focus.inputMove(Character.getNumericValue(key));
                                    focusNumericValue(Character.getNumericValue(key));
                                }
                            }

                            // Checking if user hits backspace
                            if(e.getKeyCode() == 8){
                                focus.inputMove(0);

                                focusNumericValue(10);
                            }
                        }
                    });
                }
            }
        });
    }

    public void focusNumericValue(int num){
        if(num == 0){
            return;
        }

        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                Cell currentCell = cells[j][k];

                // Row, Column, Block, Number
                if(x == j || y == k || ((x / 3) == (j / 3) && (y / 3)  == (k / 3)) || currentCell.getPlayerValue() == num){
                    cells[j][k].changeBackground(relatedColor);
                }
            }
        }

        focus.changeBackground(focusedColor);
    }

    public void clearFocus(){
        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                cells[j][k].changeBackground(backgroundColor);
            }
        }
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public void createCells(int difficulty){
        info.createBoard(difficulty);

        // Tile Creation
        GridLayout currentLayout = new GridLayout(0, 9, 0, 0);
        gamePanel.setLayout(currentLayout);
        for(int j = 0; j < 9; j++){
            for(int k = 0; k < 9; k++) {
                Cell currentCell = new Cell(info.getGeneratedGame()[j][k], info.getGame()[j][k] != 0, backgroundColor, relatedColor);
                cells[j][k] = currentCell;

                int top = 1;
                int left = 1;
                int bottom = 1;
                int right = 1;

                // Row
                switch(j){
                    case 2, 5 -> bottom = 5;
                    case 3, 6 -> top = 5;
                }

                // Column
                switch(k){
                    case 2, 5 -> right = 5;
                    case 3, 6 -> left = 5;
                }

                currentCell.getCell().setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.black));

                gamePanel.add(currentCell.getCell());
            }
        }
    }
}
