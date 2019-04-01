// Name: Yunfan Xue
// USC NetID: yunfanxu
// CSCI455 PA2
// Fall 2018

import java.util.ArrayList;
import java.util.Scanner;

/**
   <add main program comment here>
   Initialized all the variables that we need to use.
   When -u is true, let user input number of piles as they want. Chenck if they are legal piles number. 
   If yes, continue the process. If no, print error message and let user input new piles number.
   When -u is false, randomly create number of piles and continue the process.

   When -s is true, processing number of piles until the end.
   When -s is false, processing step by step.   

   main method variables:
   singleStep  - if use single step, true
   userConfig  - if use user config, true
   round  - store the current round
   inputArray  - store the input initial piles.
   in  - scanner that scan the system input from user.
   inputLine  - read whole line of input piles number.
   boardSimulator  - generate random number of piles.
*/
public class BulgarianSolitaireSimulator {
   public static void main (String[] args) {
     
      boolean singleStep = false;
      boolean userConfig = false;
      int round = 1;

      ArrayList<Integer> inputArray = new ArrayList<Integer>();
      Scanner in = new Scanner(System.in);
      SolitaireBoard boardSimulator;

      for (int i = 0; i < args.length; i++) {
         if (args[i].equals("-u")) {
            userConfig = true;
         }
         else if (args[i].equals("-s")) {
            singleStep = true;
         }
      }

      // <add code here>
      if (userConfig) {

         System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL);
         System.out.println("You will be entering the initial configuration of the cards (i.e., how many in each pile).");
         System.out.println("Please enter a space-separated list of positive integers followed by newline:");

         String inputLine = readNextLine(in);

         while (!isInputLegal(inputLine)) {
            System.out.println("ERROR: Each pile must have at least one card and the total number of cards must be 45");
            System.out.println("Please enter a space-separated list of positive integers followed by newline:");
            inputLine = readNextLine(in);
         }

         boardSimulator = new SolitaireBoard(readAllInt(inputLine));       
      } else {
         boardSimulator = new SolitaireBoard();
      }
      System.out.println("Initial configuration: " + boardSimulator.configString());
            
      if (singleStep) {                 //use user input and process step by step
         processByStep(boardSimulator, in, round);
      } else {                         //use user input and process until the end
         processToEnd(boardSimulator, round);
      }

   }

   
   // <add private static methods here>

   /**
      read the next line from System.in
      PRE: there have to be a next line
   */
   private static String readNextLine(Scanner in) {
      if (in.hasNextLine()) {
      	String newLine = in.nextLine();
      	return newLine;
      } else {
         return "No other Line!";
      }
   }

   /**
      Judge if the input is legal.
      Create a new scanner to scan every elements. First we see if there contain a non-integer elements. If yes, then input illegal.
      Second we see if input number are non-positive. If yes, then unput illegal.
      Third we see if the total input piles number equals to the total card number. If not euqal, then input illegal.

      method variables:
      legal  - store the legal status of input
      addTotal  - store the total sum up of input. Should = CARD_TOTAL
      lineScanner  - scanner that scan all the element in inputLine.
      compareArray  - store every int number that we scanned from inputLine. 
   */
   private static boolean isInputLegal(String inputLine) {
      boolean legal = true;
      int addTotal = 0;
      Scanner lineScanner =  new Scanner(inputLine);
      ArrayList<Integer> compareArray = new ArrayList<Integer>();

      // see if it contain a non-integer elements
      while (lineScanner.hasNext()) {
         if (!lineScanner.hasNextInt()) {
            legal = false;
            break;
         }
         compareArray.add(lineScanner.nextInt());
      }
      // see if input number all positive integer
      for (int i = 0; i < compareArray.size(); i++) {
         if (compareArray.get(i) <= 0) {
            legal = false;
         }
         addTotal += compareArray.get(i);
      }

      //see if input number add up equals total card number
      if (addTotal != SolitaireBoard.CARD_TOTAL) {
         legal = false;
      }

      return legal;
   }

   /**
      Read all int number from the line that readNextLine get.
      Construct a new scanner to scan the string and get the int number and store them into a ArrayList.
      Return the ArrayList.
      @param inputLine isInputLegal = true. which the number of inputLine add up equal to CARD_TOTAL

      method variable
      readNextLineIntScanner  - scanner of input string
      inputArray  - store integer of input string      
   */
   private static ArrayList<Integer> readAllInt(String inputLine) {

      assert isInputLegal(inputLine);

      Scanner readNextIntScanner = new Scanner(inputLine);
      ArrayList<Integer> inputArray = new ArrayList<Integer>();

         while (readNextIntScanner.hasNextInt()) {
               inputArray.add(readNextIntScanner.nextInt());
         }
      return inputArray;
   }


   /** 
      process until the simulator has finished(boardSimulator.isDone() == true)
      print "Done!" at the end.
      @param round  store round number
   */
   private static void processToEnd(SolitaireBoard boardSimulator, int round) {

      while (!boardSimulator.isDone()) {
         boardSimulator.playRound();
            System.out.println("[" + round + "]" + " Current configuration: " + boardSimulator.configString());
            round++;
      }
      System.out.println("Done!");
   }

   /**
      process only step by step. user need to type "return" to do the next step.
      print "Done!" at the end.
      @param in  system.in scanner
      @param round store round number

      method variable:
      inputLine  - read and store the next line.
   */
   private static void processByStep(SolitaireBoard boardSimulator, Scanner in, int round) {
      String inputLine;
      while (!boardSimulator.isDone()) {    
         boardSimulator.playRound();
         System.out.println("[" + round + "]" + "Current configuration: " + boardSimulator.configString());
         round++;
         System.out.print("<Type return to continue>");
         inputLine = readNextLine(in);
      }
      System.out.println("Done!");
   }
  
}