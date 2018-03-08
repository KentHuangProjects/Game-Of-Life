package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * Omnivore Class. Shades of purple. Eats Herbivores and Carnivores and Plants.
 * Must eat within 4 moves or dies. Can reproduce.
 * 
 * @author Kent, Huang
 *
 */
public class Omnivore extends Animal implements CarnEdible {

    private static final int moveRange = 1;

    private static final int numSameTypeToR = 1;

    private static final int numOfEmptyToR = 3;

    private static final int numOfFoodCellToR = 3;

    private static final int numOfBab = 1;

    private static final int HP = 4;

    /**
     * Start with purple color.
     */
    private static final Color INITCOLOR = new Color(128, 0, 128);

    /**
     * Construct a Omnivore object.
     * 
     * @param location
     *            Location the Omnivore is in, as a Cell type.
     */
    public Omnivore(final Cell location) {
        super(location);
        // TODO Auto-generated constructor stub
    }

    /**
     * Initiate the Omnivore and set it to be shown as a yellow cell.
     */
    public void init() {
        this.setBackground(INITCOLOR);
        hp = HP;
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
        return (inhabitant instanceof OmniEdible);
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
        return (inhabitant instanceof Omnivore);
    }

    @Override
    /**
     * Return the movement Range per turn.
     * 
     * @return The movement Range as an int.
     */
    public int getMovementRange() {
        return moveRange;
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
     *            The Cell ArrayList that has the cells that new Omnivore will
     *            be put on.
     * @return The Inhabitant ArrayList that has all the Omnivore babies.
     */
    public ArrayList<Inhabitant> reproduce(final ArrayList<Cell> toBeBorn) {
        ArrayList<Inhabitant> newOnes = new ArrayList<Inhabitant>();

        for (Cell cell : toBeBorn) {
            Inhabitant baby = new Omnivore(cell);
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
        return true;
    }

}
