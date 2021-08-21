package containers;

/**
 * Child Class of Container
 * @author zbuseaydin
 *
 */
public class BasicContainer extends Container {

	/**
	 * Constructor of BasicContainer, creates an object of class BasicContainer
	 * @param ID of Container
	 * @param weight of the container
	 */
	public BasicContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelConsumption = 2.50;
	}
	
}
