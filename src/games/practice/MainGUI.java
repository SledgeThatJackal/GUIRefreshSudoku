package games.practice;

import javax.swing.*;

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

        sudokuPanel = new SudokuGUI(0).getSudokuPanel();

        EasyButton.addActionListener(e -> {
            setContentPane(sudokuPanel);
            setVisible(true);
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}