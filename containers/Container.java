package containers;

import java.util.ArrayList;

/**
 * Abstract Class Container
 * @author zbuseaydin
 *
 */
public abstract class Container implements Comparable<Container>{
	
	/**
	 * ID of the Container
	 */
	private int ID;
	
	/**
	 * Weight of the container
	 */
	final public int weight;
	
	/**
	 * Fuel consumption per weight of the container
	 */
	protected double fuelConsumption;
	
	/**
	 * ArrayList of all the containers that are in the simulation
	 */
	public static ArrayList<Container> allContainers = new ArrayList<Container>();
	
	/**
	 * Constructor of the Container class, creates an object of class Container
	 * @param ID of Container
	 * @param weight of the container
	 */
	public Container(int ID, int weight) {
		this.ID = ID;
		this.weight = weight;
		allContainers.add(this);
	}
	
	/**
	 * Calculates the fuel consumption per KM required by the container
	 * @return the fuel consumption
	 */
	final public double consumption() {
		double consumption = fuelConsumption * (double)weight;
		return consumption;
	}
	
	/**
	 * Checks if the type, ID and the weight of the containers are the same
	 * @param other container
	 * @return true if they are same, false if not
	 */
	public boolean equals(Container other) {
		if(other.ID!=this.ID)
			return false;
		if(other.weight!=this.weight)
			return false;
		if (other instanceof BasicContainer) {
			if (this instanceof BasicContainer)
				return true;
			else
				return false;
		}
		else if (other instanceof HeavyContainer) {
			if (other instanceof LiquidContainer) {
				if(this instanceof LiquidContainer)
					return true;
				else
					return false;
			}
			else if (other instanceof RefrigeratedContainer) {
				if(this instanceof RefrigeratedContainer)
					return true;
				else
					return false;
			}
			else {
				if(this instanceof LiquidContainer || this instanceof RefrigeratedContainer || this instanceof BasicContainer)
					return false;
				else
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Getter method of Container ID
	 * @return ID of the Container
	 */
	public int getID() {
		return ID;
	}
	/**
	 * Setter method of Container ID
	 * @param ID of the Container
	 */
	public void setID(int ID) {
		this.ID = ID;
	}
	
	/**
	 * Need this method to sort the ArrayList containers in the increasing order of the IDs
	 */
	@Override
	public int compareTo(Container c) {
		return this.ID - c.ID;
	}

}


