package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class GameGUI{
    private JPanel gamePanel;
    private JPanel tilePanel;
    private JPanel helpPanel;
    private JButton notesButton;
    private final Cell[][] cells = new Cell[9][9];
    private final GameInfo info;
    private Cell focus;

    // Fields
    private final Color focusedColor = new Color(145, 144, 144);
    private final Color relatedColor = new Color(180, 180, 180);
    private final Color backgroundColor = new Color(103, 140, 152);
    private final Color textColor = new Color(39, 86, 2);
    private final Font font = new Font("SansSerif", Font.BOLD, 20);
    private int x;
    private int y;
    private boolean creatingNotes = false;
    private boolean isDisplayed = false;

    public GameGUI(){
        // Game Setup
        info = new GameInfo();

        Action numAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(focus.isLocked()){
                    return;
                }

                int key = Integer.parseInt(e.getActionCommand());

                clearHighlighting();

                if (creatingNotes) {
                    focus.setWrittenNote(key);
                } else {
                    focus.inputMove(key);
                }

                highlightCells(key);

                focus.changeCard();
            }
        };

        Action deleteAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(focus.isLocked()){
                    return;
                }

                focus.inputMove(0);
                highlightCells(10);

                focus.changeCard();
            }
        };

        int condition = JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT;
        InputMap iM = tilePanel.getInputMap(condition);
        ActionMap aM = tilePanel.getActionMap();

        // Assigning every number a key bind (1-9 and NumPad 1-9)
        for(int i = 1; i <= 9; i++){
            String value = String.valueOf(i);
            iM.put(KeyStroke.getKeyStroke(value), value);
            iM.put(KeyStroke.getKeyStroke("NUMPAD" + value), value);
            aM.put(value, numAction);
        }

        // Assigning Backspace and Delete a key bind
        iM.put(KeyStroke.getKeyStroke("BACK_SPACE"), "backSpace");
        aM.put("backSpace", deleteAction);

        iM.put(KeyStroke.getKeyStroke("DELETE"), "delete");
        aM.put("delete", deleteAction);


        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener("focusOwner", event -> {
            if(event.getNewValue() instanceof JPanel){
                if(focus != null){
                    clearHighlighting();
                }

                // Update Object with Focus
                for(int j = 0; j < 9; j++){
                    for(int k = 0; k < 9; k++) {
                        if(cells[j][k].getCell().equals(event.getNewValue())){
                            focus = cells[j][k];
                            x = j;
                            y = k;

                            j = k = 10;
                        }
                    }
                }

                highlightCells(focus.getPlayerValue() > 0 ? focus.getPlayerValue() : 10);
            }
        });

        notesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                creatingNotes = !creatingNotes;
            }
        });
    }

    public void highlightCells(int num){
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

    public void toggleIsDisplayed(){
        this.isDisplayed = true;
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