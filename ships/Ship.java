package ships;
import containers.*;
import interfaces.IShip;
import ports.Port;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Ship class that implements the IShip interface
 * @author zbuseaydin
 *
 */
public class Ship implements IShip, Comparable<Ship> {
	/**
	 * Ship ID
	 */
	final public int ID;
	
	/**
	 * Current amount of fuel, initially 0
	 */
	public double fuel = 0;
	
	/**
	 * The port where the ship currently is
	 */
	private Port currentPort;
	
	/**
	 * Maximum weight of all containers in the ship
	 */
	final private int totalWeightCapacity;
	
	/**
	 * Maximum number of all containers in the ship
	 */
	final private int maxNumberOfAllContainers;
	
	/**
	 * Maximum number of heavy containers in the ship
	 */
	final private int maxNumberOfHeavyContainers;
	
	/**
	 * Maximum number of refrigerated containers in the ship
	 */
	final private int maxNumberOfRefrigeratedContainers;
	
	/**
	 * Maximum number of liquid containers in the ship
	 */
	final private int maxNumberOfLiquidContainers;
	
	/**
	 * Fuel consumption per KM of the ship
	 */
	final private double fuelConsumptionPerKM;
	
	/**
	 * ArrayList of containers that are currently in the ship 
	 */
	private ArrayList<Container> currentContainers;
	
	/**
	 * ArrayList of all the ships that are in the simulation
	 */
	public static ArrayList<Ship> allShips = new ArrayList<Ship>();

	
	/**
	 * Constructor of Ship, creates an object of Ship class
	 * Adds the ship to the current port's ArrayList of current ships
	 * Creates the ArrayList of containers that are currently in the ship
	 * @param ID Ship ID
	 * @param p the port in which the ship currently is
	 * @param totalWeightCapacity maximum weight of all containers in the ship
	 * @param maxNumberOfAllContainers maximum number of all containers in the ship
	 * @param maxNumberOfHeavyContainers maximum number of heavy containers in the ship
	 * @param maxNumberOfRefrigeratedContainers maximum number of refrigerated containers in the ship
	 * @param maxNumberOfLiquidContainers maximum number of liquid containers in the ship
	 * @param fuelConsumptionPerKM fuel consumption per KM of the ship
	 */
	public Ship(int ID, Port p, int totalWeightCapacity, int maxNumberOfAllContainers, int maxNumberOfHeavyContainers, int maxNumberOfRefrigeratedContainers, int maxNumberOfLiquidContainers, double fuelConsumptionPerKM) {
		this.ID = ID;
		this.currentPort = p;
		this.totalWeightCapacity = totalWeightCapacity;
		this.maxNumberOfAllContainers = maxNumberOfAllContainers;
		this.maxNumberOfHeavyContainers = maxNumberOfHeavyContainers;
		this.maxNumberOfLiquidContainers = maxNumberOfLiquidContainers;
		this.maxNumberOfRefrigeratedContainers = maxNumberOfRefrigeratedContainers;
		this.fuelConsumptionPerKM = fuelConsumptionPerKM;
		currentContainers = new ArrayList<Container>();
		this.currentPort.current.add(this);
		allShips.add(this);
	}
	
	/**
	 * Getter method of currentContainers ArrayList
	 * @return ArrayList of containers that are currently in the ship
	 */
	public ArrayList<Container> getCurrentContainers(){
		Collections.sort(currentContainers);
		return currentContainers;
	}
	
	/**
	 * Creates an ArrayList of basic containers that are currently in the ship
	 * @return basicContainers ArrayList
	 */
	public ArrayList<Container> basicContainers(){
		ArrayList<Container> basicContainers = new ArrayList<Container>();
		for(Container c: currentContainers) {
			if(c instanceof BasicContainer)
				basicContainers.add(c);
		}
		return basicContainers;
	}
	
	/**
	 * Creates an ArrayList of heavy containers that are currently in the ship
	 * But doesn't include liquid containers or refrigerated containers
	 * @return heavyContainers ArrayList
	 */
	public ArrayList<Container> heavyContainers(){
		ArrayList<Container> heavyContainers = new ArrayList<Container>();
		for(Container c: currentContainers) {
			if((c instanceof HeavyContainer)&&(!(c instanceof LiquidContainer)&&(!(c instanceof RefrigeratedContainer))))
				heavyContainers.add(c);
		}
		return heavyContainers;	
	}
	
	/**
	 * Creates an ArrayList of refrigerated containers that are currently in the ship
	 * @return refrigeratedContainers ArrayList
	 */
	public ArrayList<Container> refrigeratedContainers(){
		ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
		for(Container c: currentContainers) {
			if(c instanceof RefrigeratedContainer) {
				refrigeratedContainers.add(c);
			}
		}
		return refrigeratedContainers;
	}
	
	/**
	 * Creates an ArrayList of liquid containers that are currently in the ship
	 * @return liquidContainers ArrayList
	 */
	public ArrayList<Container> liquidContainers(){
		ArrayList<Container> liquidContainers = new ArrayList<Container>();
		for(Container c: currentContainers) {
			if(c instanceof LiquidContainer)
				liquidContainers.add(c);
		}
		return liquidContainers;
	}
	
	/**
	 * Calculates the total weight of all containers that are currently in the ship
	 * @return total weight of all containers in the ship
	 */
	public int currentWeightOfContainers() {
		int currentWeightOfContainers = 0;
		for(Container c: currentContainers) {
			currentWeightOfContainers += c.weight;
		}
		return currentWeightOfContainers;
	}
	
	/**
	 * Calculates the number of heavy containers that are currently in the ship
	 * @return the number of heavy containers in the ship
	 */
	public int numOfCurrentHeavyContainers() {
		int numOfCurrentHeavyContainers = 0;
		for(Container c: currentContainers) {
			if(c instanceof HeavyContainer)
				numOfCurrentHeavyContainers++;
		}
		return numOfCurrentHeavyContainers;
	}
	
	/**
	 * Calculates the number of refrigerated containers that are currently in the ship
	 * @return the number of refrigerated containers in the ship
	 */
	public int numOfCurrentRefrigeratedContainers() {
		int numOfCurrentRefrigeratedContainers = 0;
		for(Container c: currentContainers) {
			if(c instanceof RefrigeratedContainer)
				numOfCurrentRefrigeratedContainers++;
		}
		return numOfCurrentRefrigeratedContainers;
	}
	
	/**
	 * Calculates the number of liquid containers that are currently in the ship
	 * @return the number of liquid containers in the ship
	 */
	public int numOfCurrentLiquidContainers() {
		int numOfCurrentLiquidContainers = 0;
		for(Container c: currentContainers) {
			if(c instanceof LiquidContainer)
				numOfCurrentLiquidContainers++;
		}
		return numOfCurrentLiquidContainers;
	}
	
	/**
	 * Checks if the ship can sail to Port p
	 * If it can, makes the necessary adjustments
	 */
	@Override
	public boolean sailTo(Port p) {	
		
		double containerFuel = 0;
		
		if(!this.currentContainers.isEmpty()) {
			for(Container c: currentContainers) {
				containerFuel += c.consumption();
			}
		}
		
		double fuelNeeded = currentPort.getDistance(p) * (fuelConsumptionPerKM + containerFuel);
		
		if(fuelNeeded > fuel) {
			return false;
		}
		
		fuel -= fuelNeeded;
		currentPort.outgoingShip(this);
		this.currentPort = p;
		currentPort.incomingShip(this);
		return true;
	}

	/**
	 * Adds fuel
	 */
	@Override
	public void reFuel(double newFuel) {
		fuel += newFuel;
	}

	/**
	 * Checks if Container cont can be loaded to the ship
	 * If it can, adds the container to the currentContainers ArrayList and
	 * removes it from the containers ArrayList of the currentPort
	 */
	@Override
	public boolean load(Container cont) {
		if(!this.currentPort.containers.contains(cont)) {
			return false;
		}else if(this.currentContainers.contains(cont)) {
			return false;
		}else if(this.getCurrentContainers().size() + 1 > maxNumberOfAllContainers) {
			return false;
		}else if(cont.weight + this.currentWeightOfContainers() > totalWeightCapacity) {
			return false;
		}else if(cont instanceof HeavyContainer) {
			if(numOfCurrentHeavyContainers() + 1 > maxNumberOfHeavyContainers) {
				return false;
			}else if((cont instanceof RefrigeratedContainer) && (numOfCurrentRefrigeratedContainers()+1 > maxNumberOfRefrigeratedContainers)) {
				return false;
			}else if((cont instanceof LiquidContainer) && (numOfCurrentLiquidContainers()+1 > maxNumberOfLiquidContainers)) {
				return false;
			}
		}
		this.currentContainers.add(cont);
		this.currentPort.containers.remove(cont);
		return true;
	}

	/**
	 * Checks if Container cont can be unloaded from the ship
	 * If it can, removes the container from the currentContainers ArrayList and
	 * adds it to the containers ArrayList of the currentPort
	 */
	@Override
	public boolean unLoad(Container cont) {
		if(!this.currentContainers.contains(cont)) {
			return false;
		}else if(this.currentPort.containers.contains(cont)) {
			return false;
		}
		this.currentPort.containers.add(cont);
		this.currentContainers.remove(cont);
		return true;
	}

	/**
	 * Getter method of currentPort ArrayList
	 * @return currentPort
	 */
	public Port getCurrentPort() {
		return currentPort;
	}
	/**
	 * Getter method of currentPort ArrayList
	 * @param currentPort ArrayList
	 */
	public void setCurrentPort(Port currentPort) {
		this.currentPort = currentPort;
	}

	/**
	 * Getter method of totalWeightCapacity
	 * @return totalWeightCapacity
	 */
	public int getTotalWeightCapacity() {
		return totalWeightCapacity;
	}
	
	/**
	 * Getter method of maxNumberOfAllContainers
	 * @return maxNumberOfAllContainers
	 */
	public int getMaxNumberOfAllContainers() {
		return maxNumberOfAllContainers;
	}
	
	/**
	 * Getter method of maxNumberOfHeavyContainers
	 * @return maxNumberOfHeavyContainers
	 */
	public int getMaxNumberOfHeavyContainers() {
		return maxNumberOfHeavyContainers;
	}

	/**
	 * Getter method of maxNumberOfRefrigeratedContainers
	 * @return maxNumberOfRefrigeratedContainers
	 */
	public int getMaxNumberOfRefrigeratedContainers() {
		return maxNumberOfRefrigeratedContainers;
	}

	/**
	 * Getter method of maxNumberOfLiquidContainers
	 * @return maxNumberOfLiquidContainers
	 */
	public int getMaxNumberOfLiquidContainers() {
		return maxNumberOfLiquidContainers;
	}

	/**
	 * Getter method of fuelConsumptionPerKM
	 * @return fuelConsumptionPerKM
	 */
	public double getFuelConsumptionPerKM() {
		return fuelConsumptionPerKM;
	}

	/**
	 * Setter method of currentContainers
	 * @param currentContainers ArrayList
	 */
	public void setCurrentContainers(ArrayList<Container> currentContainers) {
		this.currentContainers = currentContainers;
	}
	
	/**
	 * Need this method to sort the ArrayList ships in the increasing order of the IDs
	 */
	@Override
	public int compareTo(Ship o) {
		return this.ID - o.ID;
	}
	
}


