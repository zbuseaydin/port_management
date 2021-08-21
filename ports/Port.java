package ports;
import java.util.ArrayList;

import containers.*;
import interfaces.IPort;
import ships.Ship;

/**
 * Port class that implements the IPort interface
 * @author zbuseaydin
 *
 */
public class Port implements IPort {
	
	/**
	 * ID of the Port
	 */
	final private int ID;
	
	/**
	 * x component of the location of port
	 */
	final public double X;
	
	/**
	 * y component of the location of port
	 */
	final public double Y;
	
	/**
	 * ArrayList of containers that are currently in the port
	 */
	public ArrayList<Container> containers;
	
	/**
	 * ArrayList of ships that came to the port and left
	 */
	private ArrayList<Ship> history;
	
	/**
	 * ArrayList of ships that are currently in the port
	 */
	public ArrayList<Ship> current;
	
	/**
	 * ArrayList of all the ports that are in the simulation
	 */
	public static ArrayList<Port> allPorts = new ArrayList<Port>();
	
	/**
	 * Constructor of Port, creates an object of Port class
	 * @param ID Port ID
	 * @param X x component of the location of the port
	 * @param Y y component of the location of the port
	 */
	public Port(int ID, double X, double Y) {
		this.ID = ID;
		this.X = X;
		this.Y = Y;
		this.containers = new ArrayList<Container>();
		this.history = new ArrayList<Ship>();
		this.current = new ArrayList<Ship>();
		allPorts.add(this);
	}
	
	/**
	 * Calculates the distance between two ports
	 * @param other Port other
	 * @return the distance
	 */
	public double getDistance(Port other) {
		double distance = Math.sqrt((other.X - this.X)*(other.X - this.X) + (other.Y - this.Y)*(other.Y - this.Y));
		return distance;
	}

	/**
	 * Ship s is coming to the port
	 * Adds s to the port's current ArrayList
	 */
	@Override
	public void incomingShip(Ship s) {
		if(!current.contains(s))
			current.add(s);
	}

	/**
	 * Ship s is leaving the port
	 * Adds s to the port's history ArrayList
	 * Removes s from the port's current ArrayList
	 */
	@Override
	public void outgoingShip(Ship s) {
		current.remove(s);
		if(!history.contains(s))
			history.add(s);
	}
	
	/**
	 * Creates an ArrayList of basic containers that are currently in the port
	 * @return basicContainers ArrayList
	 */
	public ArrayList<Container> basicContainers(){
		ArrayList<Container> basicContainers = new ArrayList<Container>();
		for(Container c: containers) {
			if(c instanceof BasicContainer)
				basicContainers.add(c);
		}
		return basicContainers;
	}
	
	/**
	 * Creates an ArrayList of heavy containers that are currently in the port
	 * But doesn't include liquid containers or refrigerated containers
	 * @return heavyContainers ArrayList
	 */
	public ArrayList<Container> heavyContainers(){
		ArrayList<Container> heavyContainers = new ArrayList<Container>();
		for(Container c: containers) {
			if((c instanceof HeavyContainer)&&(!(c instanceof LiquidContainer)&&(!(c instanceof RefrigeratedContainer))))
				heavyContainers.add(c);
		}
		return heavyContainers;	
	}
	
	/**
	 * Creates an ArrayList of refrigerated containers that are currently in the port
	 * @return refrigeratedContainers ArrayList
	 */
	public ArrayList<Container> refrigeratedContainers(){
		ArrayList<Container> refrigeratedContainers = new ArrayList<Container>();
		for(Container c: containers) {
			if(c instanceof RefrigeratedContainer) {
				refrigeratedContainers.add(c);
			}
		}
		return refrigeratedContainers;
	}
	
	/**
	 * Creates an ArrayList of liquid containers that are currently in the port
	 * @return liquidContainers ArrayList
	 */
	public ArrayList<Container> liquidContainers(){
		ArrayList<Container> liquidContainers = new ArrayList<Container>();
		for(Container c: containers) {
			if(c instanceof LiquidContainer)
				liquidContainers.add(c);
		}
		return liquidContainers;
	}
	
	/**
	 * Getter method of history ArrayList
	 * @return history ArrayList
	 */
	public ArrayList<Ship> getHistory() {
		return history;
	}

	/**
	 * Setter method of history ArrayList
	 * @param history ArrayList of ships
	 */
	public void setHistory(ArrayList<Ship> history) {
		this.history = history;
	}

	/**
	 * Getter method of the Port ID
	 * @return Port ID
	 */
	public int getID() {
		return ID;
	}
	
}


