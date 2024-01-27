package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

/**
 * Creates the board and runs general game logic
 */
public class GameGUI{
    private JPanel gamePanel;
    private JPanel tilePanel;
    private JPanel helpPanel;
    private JButton notesButton;
    private final Cell[][] cells = new Cell[9][9];
    private final GameInfo info;
    private Cell focus;

    private int x;
    private int y;
    private boolean creatingNotes = false;

    /**
     * Stops the mouse event from running unless the panel is ACTUALLY displayed
     */
    public static boolean isDisplayed = false;

    /**
     * Sets up focus events, key binds, and holds all 81 cells on the game board
     */
    public GameGUI(){
        info = new GameInfo();
        notesButton.setBackground(Resources.NOTE_BUTTON_OFF);
        notesButton.setFocusPainted(false);

        // An action that sets the number a tile displays based on the key pressed
        Action numAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(focus.isLocked()){
                    return;
                }

                int key = Integer.parseInt(e.getActionCommand());

                clearHighlighting();

                if (creatingNotes) {
                    if(focus.getPlayerValue() > 0){
                        highlightCells(focus.getPlayerValue());
                        return;
                    }

                    if(checkNote(key)){
                        highlightCells(10);
                        return;
                    }

                    focus.setWrittenNote(key);
                } else {
                    focus.inputMove(key);
                }

                highlightCells(key);

                focus.changeCard();
            }
        };

        // An action for removing a player provided value
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

        // This condition was chosen, so the panels with focus contained in the tilePanel would trigger them
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
        iM.put(KeyStroke.getKeyStroke("BACK_SPACE"), Resources.BACKSPACE);
        aM.put(Resources.BACKSPACE, deleteAction);

        iM.put(KeyStroke.getKeyStroke("DELETE"), Resources.DELETE);
        aM.put(Resources.DELETE, deleteAction);

        // A generic FocusManager for detecting when a focus change occurs
        KeyboardFocusManager focusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener("focusOwner", event -> {
            if(event.getNewValue() instanceof JPanel){
                // Removing all the highlighting on the board from a previous focus owner
                clearHighlighting();

                // Update Object with Focus
                for(int j = 0; j < 9; j++){
                    for(int k = 0; k < 9; k++) {

                        // Grabbing the correct cell based on the panel with focus
                        if(cells[j][k].getCell().equals(event.getNewValue())){
                            focus = cells[j][k];
                            x = j;
                            y = k;

                            j = k = 10;
                        }
                    }
                }

                // Highlighting all cells related to the cell with focus,
                // if a player removes their value it will highlight everything except for a related number
                highlightCells(focus.getPlayerValue() != 0 ? focus.getPlayerValue() : 10);
            }
        });

        notesButton.addActionListener(e -> {
            creatingNotes = !creatingNotes;
            notesButton.setBackground(creatingNotes ? Resources.NOTE_BUTTON_ON : Resources.NOTE_BUTTON_OFF);
        });
    }

    /**
     * Highlights every cell that is related to the current cell with focus
     * @param num The number a user placed on a cell
     */
    public void highlightCells(int num){
        if(num == 0){
            return;
        }

        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                Cell currentCell = cells[j][k];

                // Row, Column, Block, Number
                if(x == j || y == k || ((x / 3) == (j / 3) && (y / 3)  == (k / 3)) || currentCell.getPlayerValue() == num){
                    currentCell.changeBackground(Resources.REALTED_COLOR);
                } else {
                    if(num < 10){
                        currentCell.toggleNoteHighlight(num - 1, Resources.REALTED_COLOR); // Decide between Related and Resources.NOTE_HIGHLIGHT_COLOR
                    }
                }
            }
        }

        focus.changeBackground(Resources.FOCUSED_COLOR);
    }

    /**
     * Removes the highlighting for every cell on the board
     */
    public void clearHighlighting(){
        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                Cell currentCell = cells[j][k];

                currentCell.changeBackground(Resources.SUDOKU_BACKGROUND_COLOR);

                for(int i = 0; i < 9; i++){
                    currentCell.toggleNoteHighlight(i, Resources.SUDOKU_BACKGROUND_COLOR);
                }
            }
        }
    }

    public JPanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Creates all 81 cells of the game board with a provided difficulty level
     * @param difficulty The difficulty the game created is
     */
    public void createCells(int difficulty){
        info.createBoard(difficulty);

        // Tile Creation
        GridLayout currentLayout = new GridLayout(0, 9, 0, 0);
        tilePanel.setLayout(currentLayout);
        for(int j = 0; j < 9; j++){
            for(int k = 0; k < 9; k++) {
                Cell currentCell = new Cell(info.getGeneratedGame()[j][k], info.getGame()[j][k] != 0);
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

                // Creates the board look by checking where a cell is located on the game board
                currentCell.getCell().setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Resources.GRID_LINE_COLOR));

                tilePanel.add(currentCell.getCell());
            }
        }
    }

    /**
     * Determines if the MouseEvent was a left click and then determines
     * if it was over a panel on the game board and then toggles its focus
     * @param mE Mouse Event for any Mouse event on the entire application
     */
    public void pressMouse(MouseEvent mE){
        if(mE.getModifiersEx() == InputEvent.BUTTON1_DOWN_MASK){
            PointerInfo pointerInfo = MouseInfo.getPointerInfo();
            Point pointer = pointerInfo.getLocation();

            int xCord = 0;
            int yCord = 0;
            if(isDisplayed) {
                // Determines what panel was clicked on based on the cursors position in relation to the tilePanel
                Point panelPointer = tilePanel.getLocationOnScreen();
                xCord = pointer.x - panelPointer.x;
                yCord = pointer.y - panelPointer.y;
            }

            Component c = tilePanel.getComponentAt(xCord, yCord);
            if(c != null){
                // Making the panel request focus, so the (focus) property change listener will be called
                c.requestFocusInWindow();
            }
        }
    }

    private boolean checkNote(int number){
        for(int j = 0; j < 9; j++) {
            for (int k = 0; k < 9; k++) {
                if(x == j || y == k || ((x / 3) == (j / 3) && (y / 3)  == (k / 3))){
                    if(cells[j][k].getPlayerValue() == number){
                        return true;
                    }
                }
            }
        }

        return false;
    }
}