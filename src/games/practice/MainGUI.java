package games.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI extends JFrame {
    private JLabel levelInfo;
    private JPanel DifficultySelectorPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JButton ExpertButton;

    private JPanel sudokuPanel;


    public MainGUI(){
        setContentPane(DifficultySelectorPanel);
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(750, 750);
        setLocationRelativeTo(null);
        setVisible(true);

        sudokuPanel = new SudokuGUI().getSudokuPanel();

        EasyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(sudokuPanel);
                setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}