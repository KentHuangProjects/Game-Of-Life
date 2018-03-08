package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * Herbivore. 
 * Displayed as a yellow Cell. 
 * Can move to adjacent empty cell and eat plant, it any.
 * Must eat within 6 turns or dies.
 * Can reproduce.
 * 
 * @author Kent, Huang
 *
 */
public class Herbivore extends Animal implements CarnEdible, OmniEdible {
    
    private static final int moveRange = 1;
    
    private static final int numSameTypeToR = 1;
    
    private static final int numOfEmptyToR = 2;
    
    private static final int numOfFoodCellToR = 2;
    
    private static final int numOfBab = 2;
    
    private static final int HP = 6;


    /**
     * Start with yellow color.
     */
    private static final Color INITCOLOR = new Color(255, 255, 0);


    /**
     * Construct a Herbivore.
     * 
     * @param location
     *            The cell the herbivore claims it lives in.
     */
    public Herbivore(final Cell location) {
        super(location);
    }

    /**
     * Initiate the herbivore and set it to be shown as a yellow cell.
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
        return (inhabitant instanceof HerbEdible); 
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
        return (inhabitant instanceof Herbivore);
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
     *            The Cell ArrayList that has the cells that new Herbivore will
     *            be put on.
     * @return The Inhabitant ArrayList that has all the Herbivore babies.
     */
    public ArrayList<Inhabitant> reproduce(final ArrayList<Cell> toBeBorn) {
        ArrayList<Inhabitant> newOnes = new ArrayList<Inhabitant>();
        
        for (Cell cell : toBeBorn) {
            Inhabitant baby = new Herbivore(cell);
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
