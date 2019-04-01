// CoinTossSimulator.java
// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA1
// Fall 2018

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */

import java.util.Random;


public class CoinTossSimulator {

   private int numTrials;
   private int numTwoHeads;
   private int numHeadTails;
   private int numTwoTails;

   Random randomNum = new Random();
   //int coinFace = randomNum.nextInt(4);

   /**
      Creates a coin toss simulator with no trials done yet.
      And initialize all the fields.
   */
   public CoinTossSimulator() {
      numTrials = 0;
      numTwoHeads = 0;
      numHeadTails = 0;
      numTwoTails = 0;
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
      this.numTrials += numTrials;
      if(numTrials >=  1) {
	 int coinFace = randomNum.nextInt(4);
	 for(int trialsCounts = 0; trialsCounts < numTrials; trialsCounts++) {
	    if(coinFace == 0) {    //the propability of two heads is 25%
		numTwoHeads++;
	    } else if(coinFace == 1) {  //the propability of two tails is 25%
		numTwoTails++;
	    } else {                   //the propability of one head and one tails is 50%
		numHeadTails++;  
	    }
	    coinFace = randomNum.nextInt(4);
	 }
      } else {
         System.out.println("Please enter the numTrials no smaller than 1!");
      }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return numTrials;
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return numTwoHeads;
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return numTwoTails;
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return numHeadTails;
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
      numTrials = 0;
      numTwoHeads = 0;
      numHeadTails = 0;
      numTwoTails = 0;
   }

}
