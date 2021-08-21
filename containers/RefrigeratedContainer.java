package containers;

/**
 * Child Class of HeavyContainer
 * @author zeynp
 *
 */
public class RefrigeratedContainer extends HeavyContainer {
	
	/**
	 * Constructor of RefrigeratedContainer, creates an object of class RefrigeratedContainer
	 * @param ID of Container
	 * @param weight of the container
	 */
	public RefrigeratedContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelConsumption = 5.00;
	}

}

