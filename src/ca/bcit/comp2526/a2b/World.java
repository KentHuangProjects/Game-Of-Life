package ca.bcit.comp2526.a2b;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * World. It holds Cells.
 * 
 * @author Kent, Huang
 * @version 1.0
 *
 */
public class World {

    /**
     * The percentage of creating water terrain.
     */
    private static double WATERCHANCE = 0.03;

    /**
     * The percentage of creating Herbivore(0 to 0.25).
     */
    private static final double HERBIVORECHANCE = 0.25;

    /**
     * The percentage of creating Plant(0.25 to 0.55).
     */
    private static final double PLANTBOUND = 0.55;

    /**
     * The percentage of creating Carnivore(0.55 to 0.65).
     */
    private static final double CARNIVOREBOUND = 0.65;

    /**
     * The percentage of creating Herbivore(0.65 to 0.75).
     */
    private static final double OMNIVOREBOUND = 0.75;
    
    private static final int GETFIRSTONE = 0;

    private static Random ramGenerator = new Random();

    private Cell[][] cells; // All the Cells.
    private int row; // Number of row the world has.
    private int column; // Number of column the world has.

    private ArrayList<Inhabitant> allInhabitants = new ArrayList<Inhabitant>();

    /**
     * Construct a World object.
     * 
     * @param rows
     *            Number of row the world has.
     * @param columns
     *            Number of row the world has.
     */
    public World(int rows, int columns) {
        row = rows;
        column = columns;
        cells = new Cell[rows][columns];

    }

    /**
     * Initiate the world and create the cells it has.
     */
    public void init() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {

                double createCellpercent = ramGenerator.nextFloat();
                
                //Add cells and waterCells to the world.
                if (createCellpercent <= WATERCHANCE) {
                    cells[i][j] = new Water(this, i, j);
                    cells[i][j].init();
                } else {
                    cells[i][j] = new Cell(this, i, j);
                    cells[i][j].init();
                }
            }
        }
        
        // Add Inhabitants to the world.
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                // generate random number(0.0 to 1.0)
                double createPercentage = ramGenerator.nextFloat();
                // generate herbivore or plant if possible.
                if (createPercentage <= HERBIVORECHANCE) {
                    getCellAt(i, j).setInhabit(new Herbivore(getCellAt(i, j)));
                } else if (createPercentage <= PLANTBOUND) {
                    getCellAt(i, j).setInhabit(new Plant(getCellAt(i, j)));
                } else if (createPercentage <= CARNIVOREBOUND) {
                    getCellAt(i, j).setInhabit(new Carnivore(getCellAt(i, j)));
                } else if (createPercentage <= OMNIVOREBOUND) {
                    getCellAt(i, j).setInhabit(new Omnivore(getCellAt(i, j)));
                }

                //Add all Inhabitants to the arrayList for moving every turn.
                if (getCellAt(i, j).getInhabit() != null) {
                    allInhabitants.add(getCellAt(i, j).getInhabit());
                }
            }
        }
    }

    /**
     * Retrieve the requested Cell from the specified location in the World.
     * 
     * @param row
     *            The specified row location.
     * @param column
     *            The specified column location.
     * @return The Cell object in the specified location.
     */
    public Cell getCellAt(int row, int column) {
        return cells[row][column];
    }

    /**
     * Conduct a turn.
     */
    public void takeTurn() {

        // For storing new born ones.
        ArrayList<Inhabitant> newOnes = new ArrayList<Inhabitant>();

        // For removing inhabitants from the all-Inhabitants arraylist when each turn is over.
        ArrayList<Inhabitant> toRemoveOnesAtEnd = new ArrayList<Inhabitant>();

        // shallow copy for looping.
        @SuppressWarnings("unchecked")
        ArrayList<Inhabitant> allInhabitantForLooping =  
            (ArrayList<Inhabitant>) allInhabitants.clone();
        Collections.shuffle(allInhabitantForLooping);

        // If one object is updated, it will be removed.
        // When it is empty, all the inhabitants are moved in one turn.
        while (!allInhabitantForLooping.isEmpty()) {
            Inhabitant one = allInhabitantForLooping.get(GETFIRSTONE);
            
            one.age();

            //Only Animal(Herbivore, Carnivore and Omnivore) can move.
            if (one instanceof Animal) {
                
                Animal oneAnimal = (Animal) one;
                //Choose position.
                Cell dest = oneAnimal.choosePosition();
                
                //Eat prey in the destination, if there is one.
                Inhabitant prey = oneAnimal.eat(dest);

                if (prey != null) {

                    // Store preys to remove from ArrayList<Inhabitant>
                    // allInhabitants at the end.
                    toRemoveOnesAtEnd.add(prey);

                    // Remove from Forlooping ArrayList.
                    allInhabitantForLooping.remove(prey);
                }

                oneAnimal.move(dest);
            }
            
            //Check if it satisfies the condition to reproduce.
            if (one.canReproduce()) {
                //Reproduce and add the new baby to the newOnes arraylist.
                newOnes.addAll(one.reproduce(one.findPlacesToGiveBirth()));
            }

            if (one.getHp() == 0) {
                one.die();
                toRemoveOnesAtEnd.add(one);
            }

            //Once one inhabitant finishes it move, remove it from the ForLooping arrayList.
            allInhabitantForLooping.remove(one);

        }
        
        //Update the allInhabitants arraylist after one turn.
        allInhabitants.removeAll(toRemoveOnesAtEnd);
        allInhabitants.addAll(newOnes);

    }

    /**
     * Return the number of row the world has.
     * 
     * @return The number of row the world has, as an int.
     */
    public int getRowCount() {
        return row;
    }

    /**
     * Return the number of column the world has.
     * 
     * @return The number of column the world has, as an int.
     */
    public int getColumnCount() {
        return column;
    }
}
