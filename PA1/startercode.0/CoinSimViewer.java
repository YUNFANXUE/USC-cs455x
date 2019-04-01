/**CoinSimViewer.java
 * Contains the main method.
 * Prompts for the number of trials, and creates the JFrame containing the CoinSimComponent.
 * Display the three bar.
 * Besides CoinSimComponent, this class does not depend on any of the other classes mentioned here (e.g., if one of those other classes changed, CoinSimViewer would not have to change.)
 * The later section on communicating information between objects will be useful when developing this and the next class listed.
 * */

import javax.swing.JFrame;
import java.util.Scanner;

public class CoinSimViewer {
    public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.print("Enter number of trials: ");
	int numTrials = in.nextInt();

	//set flag to see if we finish one simulation.
	boolean unfinish = true;

	while(unfinish) {
	    if(numTrials <= 0) {
		System.out.println("ERROR: Number entered must be greater than 0.");
		System.out.print("Enter number of trials: ");
		numTrials = in.nextInt();
	    } else {
		CoinSimComponent component = new CoinSimComponent(numTrials);
		JFrame frame = new JFrame();

		frame.setSize(800, 500);
		frame.setTitle("CoinSim");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(component);

		frame.setVisible(true);

		unfinish = false;
	    }   
	}
    }
}
