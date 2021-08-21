package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

import java.util.Scanner;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;

import containers.*;
import ports.Port;
import ships.Ship;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * The runnable class of the simulation
 * @author zbuseaydin
 *
 */
public class Main {
	
	/**
	 * Sorting the ArrayList of containers (by their IDs) and printing them on out one by one
	 * @param lst an ArrayList of containers
	 * @param out using to write in output
	 */
	public static void printIDs(ArrayList<Container> lst, PrintStream out) {
		Collections.sort(lst);
		for(Container c: lst)
			out.print(" " + c.getID());
		out.println();
	}
	
	/**
	 * Reads from the input file and writes in the output file
	 * @param args input and output files
	 * @throws FileNotFoundException if a file can not be accessed
	 */
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(new File(args[0]));
		PrintStream out = new PrintStream(new File(args[1]));
		
		int N = in.nextInt();
		
		int shipID = 0;
		int portID = 0;
		int contID = 0;
		
		for(int i=0; i<N; i++) {
			int j = in.nextInt();
			if(j==1) {
				
				String containerLine = in.nextLine();
				ArrayList<String> containerList = new ArrayList<String>(Arrays.asList(containerLine.split(" ")));
				int conPortID = Integer.parseInt((containerList).get(1));
				int weight = Integer.parseInt((containerList).get(2));
				Container c;
				
				if(containerList.contains("R"))
					c = new RefrigeratedContainer(contID, weight);
				else if(containerList.contains("L"))
					c = new LiquidContainer(contID, weight);
				else if(weight<=3000)
					c = new BasicContainer(contID, weight);
				else
					c = new HeavyContainer(contID, weight);	
				Port.allPorts.get(conPortID).containers.add(c);
				contID++;

			}else if(j==2) {

				int initialPortID = in.nextInt();
				int maxWeight = in.nextInt();
				int maxNumOfContainers = in.nextInt();
				int maxNumOfHeavyContainers = in.nextInt();
				int maxNumOfRefrigeratedContainers = in.nextInt();
				int maxNumOfLiquidContainers = in.nextInt();
				double fuelPerKM = in.nextDouble();
				
				Ship s = new Ship(shipID, Port.allPorts.get(initialPortID), maxWeight, maxNumOfContainers, maxNumOfHeavyContainers, maxNumOfRefrigeratedContainers, maxNumOfLiquidContainers, fuelPerKM);
				shipID++;
				
			}else if(j==3) {

				double X = in.nextDouble();
				double Y = in.nextDouble();
				
				Port p = new Port(portID, X, Y);
				portID++;
				
			}else if(j==4) {
				
				int currentShipID = in.nextInt();
				int conID = in.nextInt();
				Ship.allShips.get(currentShipID).load(Container.allContainers.get(conID));
				
			}else if(j==5) {
				
				int currentShipID = in.nextInt();
				int conID = in.nextInt();

				Ship.allShips.get(currentShipID).unLoad(Container.allContainers.get(conID));
				
			}else if(j==6) {
				
				int currentShipID = in.nextInt();
				int desPortID = in.nextInt();
				
				Ship.allShips.get(currentShipID).sailTo(Port.allPorts.get(desPortID));
				
			}else if(j==7) {
				
				int currentShipID = in.nextInt();
				double amountOfFuel = in.nextDouble();
				
				Ship.allShips.get(currentShipID).reFuel(amountOfFuel);
			}
		}
		
		for(Port p: Port.allPorts) {
			out.printf("Port %d: (%.2f"+ ", %.2f)\n", p.getID(), p.X, p.Y);
			
			if(p.basicContainers().size()!=0) {
				out.print("  BasicContainer:");
				printIDs(p.basicContainers(), out);
			}
			
			if(p.heavyContainers().size()!=0) {
				out.print("  HeavyContainer:");
				printIDs(p.heavyContainers(), out);
			}
			
			if(p.refrigeratedContainers().size()!=0) {
				out.print("  RefrigeratedContainer:");
				printIDs(p.refrigeratedContainers(), out);
			}
			
			if(p.liquidContainers().size()!=0) {
				out.print("  LiquidContainer:");
				printIDs(p.liquidContainers(), out);
			}
			
			if(p.current.size()!=0) {
				Collections.sort(p.current);
				for(Ship s: p.current) {
					out.printf("  Ship " + s.ID + ": %.2f\n", s.fuel);
					
					if(s.basicContainers().size()!=0) {
						out.print("    BasicContainer:");
						printIDs(s.basicContainers(), out);
					}
					
					if(s.heavyContainers().size()!=0) {
						out.print("    HeavyContainer:");
						printIDs(s.heavyContainers(), out);
					}
					
					if(s.refrigeratedContainers().size()!=0) {
						out.print("    RefrigeratedContainer:");
						printIDs(s.refrigeratedContainers(), out);
					}
					
					if(s.liquidContainers().size()!=0) {
						out.print("    LiquidContainer:");
						printIDs(s.liquidContainers(), out);
					}
				}
			}	
		}
		in.close();
		out.close();
	}
}

