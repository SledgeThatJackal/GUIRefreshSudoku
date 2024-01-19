package games.practice;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Handles the display, click event, and the MainMenu logic
 */
public class MainGUI extends JFrame implements ActionListener {
    private JPanel MainMenuPanel;
    private JButton EasyButton;
    private JButton MediumButton;
    private JButton HardButton;
    private JButton ExpertButton;
    private JButton QuitButton;
    private JButton SettingsButton;
    private JPanel MenuPanel;
    private JLabel levelInfo;
    private final GameGUI gameGUI;
    private final CardLayout cardLayout;

    /**
     * Setups the frame and the Main Menu
     */
    public MainGUI(){
        setContentPane(MainMenuPanel);
        setTitle("Sudoku");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1400, 1200);
        setLocationRelativeTo(null);
        setVisible(true);

        gameGUI = new GameGUI();
        Settings settings = new Settings();
        setJMenuBar(settings.getMenuBar());

        cardLayout = (CardLayout) MainMenuPanel.getLayout();
        MainMenuPanel.add(gameGUI.getGamePanel(), "game");

        EasyButton.addActionListener(this);
        MediumButton.addActionListener(this);
        HardButton.addActionListener(this);
        ExpertButton.addActionListener(this);

        SettingsButton.addActionListener(e -> {

        });

        QuitButton.addActionListener(e -> dispatchEvent(new WindowEvent(getFrames()[0], WindowEvent.WINDOW_CLOSING)));

        final AWTEventListener listener = event -> {
            MouseEvent mE = (MouseEvent) event;
            gameGUI.pressMouse(mE);
        };

        Toolkit.getDefaultToolkit().addAWTEventListener(listener, AWTEvent.MOUSE_EVENT_MASK);
    }

    /**
     * Creates the game cells and changes the display to the game screen
     * @param e the event to be processed
     */
    public void actionPerformed(ActionEvent e){
        gameGUI.createCells(Integer.parseInt(e.getActionCommand()));
        cardLayout.show(MainMenuPanel, "game");
        GameGUI.isDisplayed = true;
    }

    public static void main(String[] args) {
        new MainGUI();
    }
}