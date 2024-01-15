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
    private JPanel cellPanel;
    private JPanel gamePanel;


    public MainGUI(){
        setContentPane(DifficultySelectorPanel);
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        //sudokuPanel = new SudokuGUI(0).getSudokuPanel();
        gamePanel = new GameGUI(0).getGamePanel();

        EasyButton.addActionListener(e -> {
            setContentPane(gamePanel);
            setVisible(true);
        });

        MediumButton.addActionListener(e -> {

        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}