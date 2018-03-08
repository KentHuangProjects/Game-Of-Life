package ca.bcit.comp2526.a2b;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JPanel;

@SuppressWarnings("serial")
/**
 * Inhabitant. Live in a cell.
 * 
 * @author Kent, Huang
 * @version 1.0
 *
 */
public abstract class Inhabitant extends JPanel {

    private static final int FIRSTLAYER = 1;

    protected Cell locateCell; // The Cell it lives in.
    protected int hp; // HP(health) for the Inhabitant.

    /**
     * Construct an Inhabitant.
     * 
     * @param location
     *            Location the Inhabitant is in, as a Cell type.
     */
    public Inhabitant(final Cell location) {
        locateCell = location;
        this.init();
    }

    /**
     * Set the Inhabitant object to the specified Cell.
     * 
     * @param location
     *            The Cell to accept the Inhabitant.
     */
    public void setCell(final Cell location) {
        this.locateCell.removeInhabit();
        locateCell = location;
        location.setInhabit(this);
    }

    /**
     * Decrease the hp and darker the color.
     */
    public void age() {
        hp--;
        darkerColor();
    }

    /**
     * Return an neighbourCell ArrayList within the specified radius.
     * 
     * @param radius
     *            The specified radius as an int.
     * @return The neighbourCell ArrayList.
     */
    public ArrayList<Cell> getNeighbours(int radius) {
        return locateCell.getAdjacentCells(radius);
    }
    

    /**
     * Eat the prey in the specified cell and its HP is back to max.
     * 
     * @param dest
     *            The specified cell.
     * @return The prey as Inhabitant; or null if there is no prey.
     */
    public Inhabitant eat(final Cell dest) {
        Inhabitant prey = dest.getInhabit();
        if (isEdible(prey)) {
            prey.die();
            this.init();
            return prey;
        }

        return null;
    }

    /**
     * Check if the inhabitant can reproduce or not.
     * 
     * @return True if it can reproduce;False if it cannot.
     */
    public boolean canReproduce() {
        ArrayList<Cell> neighbours = getNeighbours(FIRSTLAYER);

        int numOfSameT = 0;
        int numOfEmpty = 0;
        int numOfFoodC = 0;

        for (Cell cell : neighbours) {
            if (isSameType(cell.getInhabit())) {
                numOfSameT++;
            } else if (cell.getInhabit() == null && isTerrainAccessiable(cell)) {
                numOfEmpty++;
            } else if (isEdible(cell.getInhabit())) {
                numOfFoodC++;
            }
        }

        return (numOfSameT >= numOfSameTypeNeighbourToReproduce() 
                && numOfEmpty >= numOfEmptyToReproduce()
                && numOfFoodC >= numOfFoodCellToReproduce());
    }

    /**
     * Darker the Color.
     */
    public void darkerColor() {
        int nextR = (int) (getBackground().getRed() * 0.8);
        int nextG = (int) (getBackground().getGreen() * 0.8);
        int nextB = (int) (getBackground().getBlue() * 0.8);
        setBackground(new Color(nextR, nextG, nextB));
    }

    /**
     * Return the Cell ArrayList that babies will be put on.
     * 
     * @return The Cell ArrayList that babies will be put on
     */
    public ArrayList<Cell> findPlacesToGiveBirth() {

        // Randomly choose the number of babies.
        int numOfBabyToBeBorn = new Random().nextInt(this.numOfBaby()) + 1;

        ArrayList<Cell> newEmpty = this.getNeighbours(1);
        Collections.shuffle(newEmpty);

        ArrayList<Cell> placeToBeBorn = new ArrayList<Cell>();

        int countEmptyCell = 0;

        for (int findEmpt = 0; findEmpt < newEmpty.size()
                && countEmptyCell < numOfBabyToBeBorn; findEmpt++, countEmptyCell++) {
            if (newEmpty.get(findEmpt).getInhabit() == null 
                    && isTerrainAccessiable(newEmpty.get(findEmpt))) {
                placeToBeBorn.add(newEmpty.get(findEmpt));
            }
        }
        return placeToBeBorn;
    }
    
    /**
     * Return the hp value.
     * 
     * @return The hp value.
     */
    public int getHp() {
        return hp;
    }

    /**
     * Kill itself.
     */
    public void die() {
        locateCell.removeInhabit();
    }

    /**
     * Initialize the inhabitant.
     */
    public abstract void init();

    /**
     * Reproduce and put descendants into Cells in the specified Cell ArrayList.
     * 
     * @param toBeBorn
     *            The Cell ArrayList that has the cells that descendants will be
     *            put on.
     * @return the Inhabitant ArrayList that has all the babies.
     */
    public abstract ArrayList<Inhabitant> reproduce(ArrayList<Cell> toBeBorn);

    /**
     * Check the Cell is accessible or not.
     * 
     * @param cell
     *            The cell to be checked.
     * @return True if it is accessible; false if it is not.
     */
    public abstract boolean isTerrainAccessiable(final Cell cell);

    /**
     * Check if the inhabitant is edible or not.
     * 
     * @param inhabitant
     *            Inhabitant to be checked.
     * @return True if it is edible; false if it is not.
     */
    public abstract boolean isEdible(final Inhabitant inhabitant);

    /**
     * Check if the inhabitant is the same type or not.
     * 
     * @param inhabitant
     *            Inhabitant to be checked.
     * @return True if it is the same type; false if it is not.
     */
    public abstract boolean isSameType(final Inhabitant inhabitant);

    /**
     * Return the required number of same type neighbour for reproduction.
     * 
     * @return The required number of same type neighbour for reproduction.
     */
    public abstract int numOfSameTypeNeighbourToReproduce();

    /**
     * Return the required number of empty cell for reproduction.
     * 
     * @return The required number of empty cell for reproduction.
     */
    public abstract int numOfEmptyToReproduce();

    /**
     * Return the required number of food cell for reproduction.
     * 
     * @return The required number of food cell for reproduction.
     */
    public abstract int numOfFoodCellToReproduce();

    /**
     * Return the number of baby that it will give birth to.
     * 
     * @return The number of baby that it will give birth to.
     */
    public abstract int numOfBaby();



}
