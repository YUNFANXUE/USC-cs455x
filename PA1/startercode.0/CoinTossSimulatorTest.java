/**CoinTossSimulatorTester.java
 * A program to test your CoinTossSimulator class independently from its use in the CoinSimViewer program.
 * It contains the test situations below.
 *
 * Test the initialization.
 * Test the 1 trial.
 * Test the 10 trials and add to the previous result.
 * Test the 100 trials and add to the previous result.
 * Reset the simulator.
 * Test the 1000 trials.
 *
 * */

public class CoinTossSimulatorTest {
    public static void main(String[] args) {

	//initial the coin toss simulator tester
	CoinTossSimulator simulator = new CoinTossSimulator();
	
	System.out.println("After constructor:");
	System.out.println("Number of trials [exp:0]: " + simulator.getNumTrials());
	System.out.println("Two-head tosses: " + simulator.getTwoHeads());
	System.out.println("Two-tail tosses: " + simulator.getTwoTails());
	System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
	if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
	    System.out.println("true");
	} else {
	    System.out.println("false");
	}
	System.out.println("");

	//test the trial 1
	simulator.run(1);
	System.out.println("Number of trials [exp:1]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
        if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
	System.out.println("");

	//test the trials 10 and add to the previous 
	simulator.run(10);
        System.out.println("Number of trials [exp:11]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
        if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
	System.out.println("");

	//test the trials 100 and add to the previous
	simulator.run(100);
        System.out.println("Number of trials [exp:111]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
        if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
	System.out.println("");

	System.out.println("[ . . . output for tests with different number of trials were here . . .]");
	System.out.println("");

	//reset the simulator
	simulator.reset();
	System.out.println("After reset:");
        System.out.println("Number of trials [exp:0]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
	if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
	System.out.println("");
	
	//test the trials 1000
	simulator.run(1000);
        System.out.println("Number of trials [exp:1000]: " + simulator.getNumTrials());
        System.out.println("Two-head tosses: " + simulator.getTwoHeads());
        System.out.println("Two-tail tosses: " + simulator.getTwoTails());
        System.out.println("One-head one-tail tosses: " + simulator.getHeadTails());
	System.out.print("Tosses add up correctly? ");
        if((simulator.getTwoHeads() + simulator.getTwoTails() + simulator.getHeadTails()) == simulator.getNumTrials()) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
	System.out.println("");

	System.out.println("[ . . . output for tests on more runs were here . . .]");
    }
}
