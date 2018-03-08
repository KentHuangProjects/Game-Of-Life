package ca.bcit.comp2526.a2b;

import java.awt.GridLayout;
import javax.swing.JFrame;

/**
 * GameFrame.
 * 
 * @author BCIT
 * @version 1.0
 */
@SuppressWarnings("serial")
public class GameFrame extends JFrame {

    private final World world;

    /**
     * Constructs an object of type GameFrame.
     * 
     * @param world
     *            A World
     */
    public GameFrame(final World world) {
        this.world = world;
    }

    /**
     * Initializes this GameFrame.
     */
    public void init() {
        setTitle("Assignment 2a");

        setLayout(new GridLayout(world.getRowCount(), world.getColumnCount()));

        for (int row = 0; row < world.getRowCount(); row++) {
            for (int col = 0; col < world.getColumnCount(); col++) {
                add(world.getCellAt(row, col));
            }
        }
        addMouseListener(new TurnListener(this));
    }

    /**
     * Takes a turn and repaints the world.
     */
    public void takeTurn() {
        world.takeTurn();
        revalidate();
        repaint();
    }
}
