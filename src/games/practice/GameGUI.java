package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class GameGUI{
    private JPanel gamePanel;
    private JPanel tilePanel;
    private JPanel helpPanel;
    private JButton notesButton;
    private JTextField textField1;
    private Cell[][] cells = new Cell[9][9];
    private GameInfo info;
    private Cell focus;

    // Fields
    private Color focusedColor = new Color(145, 144, 144);
    private Color relatedColor = new Color(180, 180, 180);
    private Color backgroundColor = new Color(103, 140, 152);
    private Color textColor = new Color(39, 86, 2);
    private Font font = new Font("SansSerif", Font.BOLD, 20);
    private int x;
    private int y;
    private boolean creatingNotes = false;
    private boolean isDisplayed = false;

    public GameGUI(){
        // Game Setup
        info = new GameInfo();
//        tilePanel.addMouseListener(this);

//        int condition = JComponent.WHEN_FOCUSED;
//        InputMap iM = tilePanel.getInputMap(condition);
//        ActionMap aM = tilePanel.getActionMap();


        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener("focusOwner", new PropertyChangeListener(){
            public void propertyChange(PropertyChangeEvent event){
                if(event.getNewValue() instanceof JPanel){
                    System.out.println(event.getNewValue());
                    if(focus != null){
                        clearHighlighting();
                    }

                    // Update Object with Focus
                    for(int j = 0; j < 9; j++){
                        for(int k = 0; k < 9; k++) {
                            if(cells[j][k].getCell().equals((JPanel) event.getNewValue())){
                                focus = cells[j][k];
                                x = j;
                                y = k;

                                j = k = 10;
                            }
                        }
                    }

                    focusNumericValue(focus.getPlayerValue() > 0 ? focus.getPlayerValue() : 10);
                }
            }
        });

        notesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNotes = !creatingNotes;
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
                    currentCell.changeBackground(relatedColor);
                }
            }
        }

        focus.changeBackground(focusedColor);
    }

    public void clearHighlighting(){
        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                cells[j][k].changeBackground(backgroundColor);
            }
        }
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    public JPanel getTilePanel(){
        return tilePanel;
    }

    public void createCells(int difficulty){
        info.createBoard(difficulty);

        // Tile Creation
        GridLayout currentLayout = new GridLayout(0, 9, 0, 0);
        tilePanel.setLayout(currentLayout);
        for(int j = 0; j < 9; j++){
            for(int k = 0; k < 9; k++) {
                Cell currentCell = new Cell(info.getGeneratedGame()[j][k], info.getGame()[j][k] != 0, backgroundColor, textColor, font);
                cells[j][k] = currentCell;

                int top = 2;
                int left = 2;
                int bottom = 2;
                int right = 2;

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

                tilePanel.add(currentCell.getCell());
            }
        }
    }

    public void keyEventMethod(KeyEvent e){
        char key = e.getKeyChar();

        if(key != '0'){
            clearHighlighting();
        }

        // Only changing the TextField if the character pressed is 1-9
        if(Character.isDigit(key)){
            if(Character.getNumericValue(key) != 0){
                int input = Character.getNumericValue(key);

                if (creatingNotes) {
                    focus.setWrittenNote(input);
                } else {
                    focus.inputMove(input);
                }

                focusNumericValue(input);
            }
        }

        // Checking if user hits backspace
        if(e.getKeyCode() == 8){
            focus.inputMove(0);

            focusNumericValue(10);
        }

        focus.changeCard();
    }

    public void setIsDisplayed(boolean isDisplayed){
        this.isDisplayed = isDisplayed;
    }

    public void pressMouse(MouseEvent mE){
        if(mE.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point pointer = pointerInfo.getLocation();

            int xCord = 0;
            int yCord = 0;
            if(isDisplayed) {
                Point panelPointer = tilePanel.getLocationOnScreen();
                xCord = pointer.x - panelPointer.x;
                yCord = pointer.y - panelPointer.y;
            }

            Component c = tilePanel.getComponentAt(xCord, yCord);
            if(c != null){
                c.requestFocusInWindow();
            }
        }
    }
}