package containers;

/**
 * Child Class of Container
 * @author zbuseaydin
 *
 */
public class HeavyContainer extends Container {
	
	/**
	 * Constructor of BasicContainer, creates an object of class BasicContainer
	 * @param ID of Container
	 * @param weight of the container
	 */
	public HeavyContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelConsumption =  3.00;
	}
	
}

