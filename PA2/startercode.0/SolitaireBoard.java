// Name: Yunfan Xue
// USC NetID: yunfanxue
// CSCI455 PA2
// Fall 2018

import java.util.Random;
import java.util.Scanner;
import java.util.ArrayList;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/

public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   /**
      Representation invariant:

      <put rep. invar. comment here>
      pileArray - size = CARD_TOTAL, numbers in array add up should = CARD_TOTAL, 
                         all elements should be positive integer and smaller than CARD_TOTAL
      randomNum - random generator
   */
   // <add instance variables here>
   private int[] pileArray = new int[CARD_TOTAL];
   Random randomNum = new Random();
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.

      @param piles contains a sequence of positive integer that sum = SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
      for (int i = 0; i < piles.size(); i++) {
         pileArray[i] = piles.get(i);
      }

      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
      First pick a random number in 0 ~ 44, assume is 30,
      then it represent we get 31, so we have 44 - 30 rest.
      
      restNum - boundary of rest random number to pick
   */
   public SolitaireBoard() {

      int restNum = CARD_TOTAL;
      for (int i = 0; i < pileArray.length; i++) {
         if (restNum > 0) {
            pileArray[i] = randomNum.nextInt(restNum) + 1;
            restNum = restNum - pileArray[i];
         } else {
            break;
         }
      }
      assert isValidSolitaireBoard();
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
      
      method variables:
      zeroCount - initial to 0, count the total zero number before the current element.
      totalNums - count the total effective number in the array.

      Method intro:
      My method is operated in O(n).
      In the for loop, we let the current number - 1. 
      If the there is one zero, that means we have to move the afterward number foreward once.
      we count the total zero bofore which the current number. The number of zero equals the step that the current number have to move forward.
      
      For example. Assume we have 25 1 1 3 1 5 7 2
      When i = 0, after minus, we get 24 > 0. There is no zero afont of it.
      When i = 1, after minus, we get 0. We count zero 1.
      When i = 2, we get 0, we count zero 2.
      When i = 3, we get 2. Because there are two zero in front of it, so we let the array[3 - 2] = array[3]. Move finished.
      ...
      When i = 5, we get 4. Because there are three zeros in front of 4, so we let the array[5 - 3] = array[5]. Move finished.

      I also get the total number of the array. After operate every array elements, we let the end number = total number.

      Then I let the fix the rest "-1" number to 0.

      Total time complexity is O(n).
   */
   public void playRound() {
      int zeroCount = 0;
      int totalNums = 0;
      for (int i = 0; i < pileArray.length; i++) {
         pileArray[i] -= 1;
         if (pileArray[i] == 0) {
            zeroCount ++;
            totalNums++;
         } else if (pileArray[i] > 0) {
            pileArray[i - zeroCount] = pileArray[i];
            totalNums++;
         } else {
            pileArray[i] = 0;
         }
      }
      pileArray[totalNums - zeroCount] = totalNums;

      //set the all the number behind to zero.
      for (int i = totalNums - zeroCount + 1; i < pileArray.length; i++) {
         pileArray[i] = 0;
      }
      
      assert isValidSolitaireBoard();
      
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.

      method variables:
      compareArray - store what pileArray should be.
      countCorrect - count correct number

      @return isCorrect return true if reaches the end.
                        return false if not.
   */
   public boolean isDone() {
      boolean isCorrect = true;
      int[] compareArray = new int[NUM_FINAL_PILES];
      int countCorrect = 0;

      for (int i = 0; i < NUM_FINAL_PILES; i++) {
         if ((pileArray[i] - 1) < NUM_FINAL_PILES && (pileArray[i] - 1) >= 0) {
            compareArray[pileArray[i] - 1] = 1;
         }
      }

      for (int i = 0; i < NUM_FINAL_PILES; i++) {
         countCorrect += compareArray[i];
      }

      if (countCorrect == NUM_FINAL_PILES) {
         isCorrect = true;
      } else {
         isCorrect = false;
      }

      assert isValidSolitaireBoard();
      return isCorrect;  
      
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
      @return outString Unless is reach the end of array or it reach 0, continue push the array element + " " into it.
                        When reach the end or reach "0", then just push in the last non-zero element.
   */
   public String configString() {
      String outString = "";
      for (int i = 0; i < pileArray.length - 1; i++) {
         if (pileArray[i + 1] == 0 && pileArray[i] != 0) {
            outString = outString + pileArray[i];
         } else if (pileArray[i] != 0 && pileArray[i + 1] != 0){
            outString = outString + pileArray[i] + " ";
         } else {
            outString = outString;
         }
      }
      if (pileArray[pileArray.length - 1] != 0) {
         outString = outString + pileArray[pileArray.length - 1];
      }

      assert isValidSolitaireBoard();
      return outString;
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)
   */
   private boolean isValidSolitaireBoard() {
      int totalCards = 0;
      for (int i = 0; i < pileArray.length; i++) {
         totalCards += pileArray[i];
         if (pileArray[i] > CARD_TOTAL || pileArray[i] < 0) {
            return false;
         }
      }
      if (pileArray.length == CARD_TOTAL
         && totalCards == CARD_TOTAL) {
      return true;
      } else {
      return false;
      }
   }
   
}
