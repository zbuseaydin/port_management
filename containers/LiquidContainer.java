package containers;

/**
 * Child Class of HeavyContainer
 * @author zeynp
 *
 */
public class LiquidContainer extends HeavyContainer {

	/**
	 * Constructor of LiquidContainer, creates an object of class LiquidContainer
	 * @param ID of Container
	 * @param weight of the container
	 */
	public LiquidContainer(int ID, int weight) {
		super(ID, weight);
		this.fuelConsumption = 4.00;
	}

}


