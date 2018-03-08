package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * Plant. Displayed as a green Cell Cannot move. Wilts away and dies after 10
 * turns. Can reproduce.
 * 
 * @author Kent, Huang
 * 
 *
 */
public class Plant extends Inhabitant implements HerbEdible, OmniEdible {

    private static final int numSameTypeToR = 3;

    private static final int numOfEmptyToR = 2;

    private static final int numOfFoodCellToR = 0;

    private static final int numOfBab = 2;
    /**
     * Start with green color.
     */
    private static final Color INITCOLOR = new Color(0, 255, 0);

    /**
     * Construct a Plant Object.
     * 
     * @param location
     *            Location the Plant object is in, as a Cell type.
     */
    public Plant(final Cell location) {
        super(location);

    }

    /**
     * Initiate the Plant object. Set its color to be green.
     */
    public void init() {
        this.setBackground(INITCOLOR);
        hp = 10;
    }

    /**
     * Set the Plant object to the specified Cell.
     * 
     * @param location
     *            The Cell to accept the Plant.
     */
    public void setCell(final Cell location) {
        super.setCell(location);

    }

    @Override
    /**
     * Check if the inhabitant is edible or not.
     * 
     * @param inhabitant
     *            Inhabitant to be checked.
     * @return True if it is edible; false if it is not.
     */
    public boolean isEdible(final Inhabitant inhabitant) {
        return false;
    }

    @Override
    /**
     * Check if the inhabitant is the same type or not.
     * 
     * @param inhabitant
     *            Inhabitant to be checked.
     * @return True if it is the same type; false if it is not.
     */
    public boolean isSameType(final Inhabitant inhabitant) {
        return (inhabitant instanceof Plant);
    }

    @Override
    /**
     * Return the required number of same type neighbour for reproduction.
     * 
     * @return The required number of same type neighbour for reproduction.
     */
    public int numOfSameTypeNeighbourToReproduce() {
        return numSameTypeToR;
    }

    @Override
    /**
     * Return the required number of empty cell for reproduction.
     * 
     * @return The required number of empty cell for reproduction.
     */
    public int numOfEmptyToReproduce() {
        return numOfEmptyToR;
    }

    @Override
    /**
     * Return the required number of food cell for reproduction.
     * 
     * @return The required number of food cell for reproduction.
     */
    public int numOfFoodCellToReproduce() {
        return numOfFoodCellToR;
    }

    @Override
    /**
     * Return the number of baby that it will give birth to.
     * 
     * @return The number of baby that it will give birth to.
     */
    public int numOfBaby() {
        return numOfBab;
    }

    /**
     * Reproduce and put descendants into Cells in the specified Cell ArrayList.
     * 
     * @param toBeBorn
     *            The Cell ArrayList that has the cells that new Plant will
     *            be put on.
     * @return The Inhabitant ArrayList that has all the Plant babies.
     */
    public ArrayList<Inhabitant> reproduce(final ArrayList<Cell> toBeBorn) {

        ArrayList<Inhabitant> newOnes = new ArrayList<Inhabitant>();

        for (Cell cell : toBeBorn) {
            Inhabitant baby = new Plant(cell);
            cell.setInhabit(baby);
            newOnes.add(baby);
        }

        return newOnes;
    }

    @Override
    /**
     * Check the Cell is accessible or not.
     * 
     * @param cell
     *            The cell to be checked.
     * @return True if it is accessible; false if it is not.
     */
    public boolean isTerrainAccessiable(final Cell cellToBeChecked) {
        //For reproduction.
        return !(cellToBeChecked instanceof Water);
    }

}
