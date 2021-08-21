package interfaces;

import ships.Ship;

public interface IPort {
	
	void incomingShip(Ship s);
	
	void outgoingShip(Ship s);
}
