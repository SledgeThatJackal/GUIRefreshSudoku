package games.practice;

import java.awt.*;

/**
 * Contains all the static variables for the entire package
 */
public class Resources {

    /**
     * The color for a focused tile
     */
    public static final Color FOCUSED_COLOR = new Color(145, 144, 144);

    /**
     * The color for any tile related to the focus tile
     */
    public static final Color REALTED_COLOR = new Color(180, 180, 180);

    /**
     * The generic background color for the Sudoku Board
     */
    public static final Color SUDOKU_BACKGROUND_COLOR = new Color(103, 140, 152);

    /**
     * The color a number will use when it is in the correct place
     */
    public static final Color CORRECT_TEXT_COLOR = new Color(39, 86, 2);

    /**
     * The color a number will use when it is in the incorrect place
     */
    public static final Color INCORRECT_TEXT_COLOR = new Color(121, 0, 0);

    /**
     * The color all notes will use
     */
    public static final Color NOTE_TEXT_COLOR = new Color(91, 91, 91);

    /**
     * The default font for all text in the application
     */
    public static final Font FONT = new Font("SansSerif", Font.BOLD, 20);


    /**
     * The Property Name for the visibility of note labels
     */
    public static final String VISIBLE_NOTE = "visibleNote";

    /**
     * The constraint for the number panel
     */
    public static final String NUMBER_CARD = "number";

    /**
     * The constraint for the notes panel
     */
    public static final String NOTE_CARD = "notes";


    /**
     * The ActionMapValue for the backspace key
     */
    public static final String BACKSPACE = "backspace";

    /**
     * The ActionMapValue for the delete key
     */
    public static final String DELETE = "delete";
}
