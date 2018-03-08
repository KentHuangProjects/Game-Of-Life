cd into src folder:
compile:javac ca/bcit/comp2526/a2b/*.java
run: java ca.bcit.comp2526.a2b.Main


Class hierarchy:             Inhabitant
			     /       \
			    /         \
			Plant       Animal
                                       |
				Herbivore, Carnivore and Omnivore


For Inhabitant class:

Non-abstract methods:

public Inhabitant(final Cell location);
public void setCell(final Cell location);
public void age();
public Inhabitant eat(final Cell dest);
public ArrayList<Cell> getNeighbours(int radius);
public boolean canReproduce();
public void darkerColor();
public ArrayList<Cell> findPlacesToGiveBirth();
public int getHp();
public void die();

Abstract methods:

public abstract void init();
public abstract ArrayList<Inhabitant> reproduce(ArrayList<Cell> toBeBorn);
public abstract boolean isTerrainAccessiable(final Cell cell);
public abstract boolean isEdible(final Inhabitant inhabitant);
public abstract boolean isSameType(final Inhabitant inhabitant);
public abstract int numOfSameTypeNeighbourToReproduce();
public abstract int numOfEmptyToReproduce();
public abstract int numOfFoodCellToReproduce();
public abstract int numOfBaby();


For Animal class:

Non-abstract methods:

public Animal(final Cell location);
public Cell choosePosition();public void move(final Cell dest);
public void move(final Cell dest);

abstract method:
public abstract int getMovementRange();


Non-abstract methods will call the abstract methods.
abstract methods will be implemented by the sub-classes.

In the taketurn() of the world class, I use a shallow copy of the AllInhabitants ArrayList to loop through all the Inhabitants.
Inside the while loop, when one inhabitant finishes its turn, I remove that Inhabitant from the AllInhabitants ArrayList.
So when the a shallow copy of the AllInhabitants ArrayList is empty, one turn of the world has been conducted.
