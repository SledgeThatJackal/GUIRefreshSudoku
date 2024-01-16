package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class MainGUI extends JFrame {
    private JPanel MainMenuPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JButton ExpertButton;
    private JButton QuitButton;
    private JButton SettingsButton;
    private JPanel MenuPanel;
    private JLabel levelInfo;
    private GameGUI gameGUI;
    private Settings settings;
    private CardLayout cardLayout;


    public MainGUI(){
        setContentPane(MainMenuPanel);
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        gameGUI = new GameGUI();
        settings = new Settings();
        setJMenuBar(settings.getMenuBar());

        cardLayout = (CardLayout) MainMenuPanel.getLayout();
        MainMenuPanel.add(gameGUI.getGamePanel(), "game");

        EasyButton.addActionListener(e -> {
            gameGUI.createCells(0);
            cardLayout.show(MainMenuPanel, "game");
        });

        MediumButton.addActionListener(e -> {
            gameGUI.createCells(1);
            cardLayout.show(MainMenuPanel, "game");
        });

        HardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.createCells(2);
                cardLayout.show(MainMenuPanel, "game");
            }
        });

        ExpertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameGUI.createCells(3);
                cardLayout.show(MainMenuPanel, "game");
            }
        });

        SettingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        QuitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING));
            }
        });
    }

    public static void main(String[] args) {
        new MainGUI();
    }

    private void setupGame(){

    }
}