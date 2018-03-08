package ca.bcit.comp2526.a2b;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.JFrame;

/**
 * Main.
 * 
 * @author Kent, Huang
 * @version 2.0
 */
public final class Main {
    
    private static final int WORLDROW = 25;
    
    private static final int WORLDCOL = 25;
     

    private static final Toolkit TOOLKIT;

    /**
     * This is a static initialization block. What is the purpose of a static
     * initialization block?
     */
    static {
        TOOLKIT = Toolkit.getDefaultToolkit();
    }

    /**
     * Private constructor prevents instantiation of Main.
     */
    private Main() {
    }

    /**
     * Drives the program.
     * 
     * @param argv
     *            command line arguments
     */
    public static void main(final String[] argv) {
        final World world; // You need a World class
        final GameFrame frame;

        world = new World(WORLDROW, WORLDCOL);
        world.init();

        frame = new GameFrame(world);
        position(frame);
        frame.init();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    /**
     * Sets the position of the specified GameFrame on the screen.
     * 
     * @param frame
     *            the GameFrame
     */
    private static void position(final GameFrame frame) {
        final Dimension size;
        size = calculateScreenArea(0.80f, 0.80f);
        frame.setSize(size);
        frame.setLocation(centreOnScreen(size));
    }

    /**
     * Returns the centre point of the screen.
     * 
     * @param size
     *            a Dimension
     * @return a Point that refers to the centre point of the screen.
     */
    public static Point centreOnScreen(final Dimension size) {
        final Dimension screenSize;
        if (size == null) {
            throw new IllegalArgumentException("Size cannot be null");
        }
        screenSize = TOOLKIT.getScreenSize();
        return (new Point((screenSize.width - size.width) / 2, 
                            (screenSize.height - size.height) / 2));
    }

    /**
     * Returns correct size of the game given the specified width and height
     * percentages.
     * 
     * @param widthPercent
     *            a float
     * @param heightPercent
     *            a float
     * @return size as a Dimension
     */
    public static Dimension calculateScreenArea(final float widthPercent, 
                                                final float heightPercent) {
        final Dimension screenSize;
        final Dimension area;
        final int width;
        final int height;
        final int size;

        if ((widthPercent <= 0.0f) || (widthPercent > 100.0f)) {
            throw new IllegalArgumentException("widthPercent cannot be " + "<= 0 " 
                                                  + "or > 100 - got: " + widthPercent);
        }

        if ((heightPercent <= 0.0f) || (heightPercent > 100.0f)) {
            throw new IllegalArgumentException(
                    "heightPercent cannot be " + "<= 0 " + "or > 100 - got: " + heightPercent);
        }

        screenSize = TOOLKIT.getScreenSize();
        width = (int) (screenSize.width * widthPercent);
        height = (int) (screenSize.height * heightPercent);
        size = Math.min(width, height);
        area = new Dimension(size, size);

        return (area);
    }
}
