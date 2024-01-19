package games.practice;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * (WIP) Might one day control the settings menu/options
 */
public class Settings{
    private JPanel SettingsPanel;

    JMenuBar menuBar;
    JMenu menu, subMenu;
    JMenuItem menuItem;
    JMenuItem menuItem2;

    public Settings(){
        menuBar = new JMenuBar();

        menu = new JMenu("A menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Random info");
        menuBar.add(menu);

        menuItem = new JMenuItem("random menu item", KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.getAccessibleContext().setAccessibleDescription("This doesn't do anything");
        menu.add(menuItem);

        subMenu = new JMenu("Submenu");
        subMenu.setMnemonic(KeyEvent.VK_S);

        menuItem2 = new JMenuItem("Random item on the submenu");
        menuItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        subMenu.add(menuItem2);
        menu.add(subMenu);

        menu = new JMenu("Another menu");
        menu.setMnemonic(KeyEvent.VK_N);
        menu.getAccessibleContext().setAccessibleDescription("This menu does nothing");
        menuBar.add(menu);
    }

    public JMenuBar getMenuBar() {
        return menuBar;
    }
}
