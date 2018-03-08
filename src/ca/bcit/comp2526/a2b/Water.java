package ca.bcit.comp2526.a2b;

import java.awt.Color;

import javax.swing.BorderFactory;

@SuppressWarnings("serial")
/**
 * Water Class.
 * Is a cell.
 * @author Kent
 *
 */
public class Water extends Cell {

    /**
     * Construct a water cell. Set the world it belongs to, and set and the location in the
     * world.
     * 
     * @param world
     *            World it belongs to.
     * @param row
     *            Row position in the world, as an int.
     * @param column
     *            Column position in the world, as an int.
     */
    public Water(final World world, int row, int column) {
        super(world, row, column);
        // TODO Auto-generated constructor stub
    }
    
    /**
     * Initiate the Water: set the black boarders and set background color to blue.
     */
    public void init() {
        setBackground(Color.blue);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

}
