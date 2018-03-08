package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Cell. Can hold a Plant or an Herbivore or nothing. Represented as a square in
 * the world.
 * 
 * @author Kent,Huang
 * 
 */
public class Cell extends JPanel {

    private static final int CELLHOLDINGROW = 1;
    private static final int CELLHOLDINGCOLUMN = 1;

    private static final int CURX = 0;
    private static final int CURY = 0;

    private World theWorld; // The world this cell exists in
    private Point location; // Cell's location in the world

    /**
     * Construct a cell. Set one-row-and-one-column grid layout to hold one
     * inhabitant, set the world it belongs to, and set and the location in the
     * world.
     * 
     * @param world
     *            World it belongs to.
     * @param row
     *            Row position in the world, as an int.
     * @param column
     *            Column position in the world, as an int.
     */
    public Cell(final World world, int row, int column) {
        this.setLayout(new GridLayout(CELLHOLDINGROW, CELLHOLDINGCOLUMN));
        theWorld = world;
        location = new Point(row, column);
    }

    /**
     * Initiate the Cell: set the black boarders.
     */
    public void init() {
        // set black border.
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

    }

    /**
     * Returns the location of the Cell on the World.
     * 
     * @return The location of the Cell on the World, as a Point type.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Return the adjacent Cells within the specified radius.
     * 
     * @param radius
     *            The radius for searching adjacent Cells.
     * 
     * @return The adjacent Cells ArrayList.
     */
    public ArrayList<Cell> getAdjacentCells(int radius) {

        ArrayList<Cell> adjacentCells = new ArrayList<Cell>();

        // Loop through the adjacent positions from the left-top to the
        // right-bottom with the given radius.
        for (int i = 1; i <= radius; i++) {
            for (int row = (-1) * radius; row <= radius; row++) {
                for (int col = (-1) * radius; col <= radius; col++) {

                    try {

                        // Excluding the current cell itself(the center
                        // position)
                        if (row != CURX || col != CURY) {
                            adjacentCells.add(theWorld.getCellAt(this.location.x + row, 
                                                                    this.location.y + col));
                        }
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        // No cell object in the position, so do not increment
                        // count
                    }
                }

            }
        }

        return adjacentCells;
    }

    /**
     * Return the inhabitant in this cell, as an Inhabitant type.
     * 
     * @return The inhabitant in this cell, as an Inhabitant type.
     */
    public Inhabitant getInhabit() {

        Component[] all = this.getComponents(); // array contains all components
        for (int i = 0; i < all.length; i++) {
            if (all[i] instanceof Inhabitant) { // if it is Inhabitant type, returns it.
                return (Inhabitant) all[i];
            }
        }

        return null;
    }

    /**
     * Add inhabitant to the cell.
     * 
     * @param inhabit
     *            Inhabitant to be set, as an Inhabitant type.
     */
    public void setInhabit(final Inhabitant inhabit) {
        if (this.getInhabit() != null) {
            removeInhabit();
        }
        add(inhabit);

    }

    /**
     * Remove the current Inhabitant out of the cell.
     */
    public void removeInhabit() {
        if (this.getInhabit() != null) {
            remove(this.getInhabit());
        }
    }

}
