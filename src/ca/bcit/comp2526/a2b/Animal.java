package ca.bcit.comp2526.a2b;

import java.util.ArrayList;
import java.util.Random;

/**
 * Animal class. Inhabitant that can move.
 * 
 * @author Kent, Huang
 *
 */
@SuppressWarnings("serial")
public abstract class Animal extends Inhabitant {

    private static final int THEFIRSTLAYER = 1;

    /**
     * Construct an Animal object with the specified Cell.
     * 
     * @param location
     *            The Cell object that holds the new Animal.
     */
    public Animal(final Cell location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    /**
     * choose the position to move to.
     * 
     * @return The cell object as the destination.
     */
    public Cell choosePosition() {
        ArrayList<Cell> neighbours = null;
        ArrayList<Cell> allPossibleCells = new ArrayList<Cell>();
        ArrayList<Cell> allCellsWithEdible = new ArrayList<Cell>();

        // Find position from the inside layer to the outside layer.
        // getMovementRange() returns the animal object's movementRange per
        // turn.
        for (int i = THEFIRSTLAYER; i <= getMovementRange(); i++) {

            allPossibleCells.clear();
            allCellsWithEdible.clear();

            neighbours = getNeighbours(i);

            // Edible ArrayList
            for (int x = 0; x < neighbours.size(); x++) {

                // If the cell is :
                // 1.accessible;
                // 2.empty or has prey;
                // Add the cell to AllPossibleCells arraylist.
                if (isTerrainAccessiable(neighbours.get(x))
                        && (neighbours.get(x).getInhabit() == null 
                        || isEdible(neighbours.get(x).getInhabit()))) {
                    allPossibleCells.add(neighbours.get(x));

                    // If the cell has prey, add the cell to AllCellsWithEdible
                    // arraylist.
                    if (isEdible(neighbours.get(x).getInhabit())) {
                        allCellsWithEdible.add(neighbours.get(x));
                    }
                }

            }

            // If find a prey, choose this position right away.
            if (!allCellsWithEdible.isEmpty()) {
                return allCellsWithEdible.get(new Random().nextInt(allCellsWithEdible.size()));
            }
        }

        // If no prey found, but has empty cells, return one random empty cell.
        if (!allPossibleCells.isEmpty()) {
            return allPossibleCells.get(new Random().nextInt(allPossibleCells.size()));
        }

        // No empty cell, return current cell(cannot move).
        return locateCell;

    }

    /**
     * Move to the specified cell after choosing the position.
     * 
     * @param dest
     *            The destination cell that the animal will move to.
     */
    public void move(final Cell dest) {

        // If it is water, set HP back to maximum.
        if (dest instanceof Water) {
            this.init();
        }

        // Move the animal.
        setCell(dest);

    }

    /**
     * Return the movement Range per turn(eg. 1 or 2).
     * 
     * @return The movement Range as an int.
     */
    public abstract int getMovementRange();

}
