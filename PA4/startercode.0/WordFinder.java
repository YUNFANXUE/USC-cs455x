// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collections;

public class WordFinder {
   /**
      The main method will catch arguments when excuting the java file. It will read source file of dictionary from the arguments.
      If no arguments input, then it will use defult file sowpod.txt which is at the same directory with this java file.
      It will read word from the system input. We will only read the first word. If we meet a non letter character, we remove it.
      For example: input: "abc%def" we only get "abcdef"
   */
   public static void main(String args[]) {
      String filename;
      if (args.length != 0) { 
         filename = args[0];
      } else { 
         filename = "sowpods.txt";
      }

      try {
         // initial dictionary
         AnagramDictionary dic = new AnagramDictionary(filename);

         System.out.println("Type . to quit.");
         Scanner scan = new Scanner(System.in);
         while (true) {
            System.out.print("Rack? ");
            String line = scan.nextLine();
            Scanner word = new Scanner(line);
            String inputWord = word.next();
            if (inputWord.equals(".")) { break; }
            String newInput = inputWord.replaceAll("[^(A-Za-z)]", "");
            Rack rc = new Rack(newInput);
            printResult(getAllResult(rc.getSubset(), dic), inputWord);
         }
      }
      catch (FileNotFoundException e) {
         System.out.println("ERROR!!! The file \"" + filename + "\" can not be found!");
      }
   } 

   /**
      Find all the valid word in dictionary of all the subset.
      @param array input of all the subset of word
      @param dictionary input of dictionary
      @return combination of all the valid word stored in ArrayList.
   */
   private static ArrayList<String> getAllResult(ArrayList<String> array, AnagramDictionary dictionary) {
      ArrayList<String> validWord = new ArrayList<String>();
      for (int i = 0; i < array.size(); i++) {
         validWord.addAll(dictionary.getAnagramsOf(array.get(i)));
      }
      return validWord;
   }

   /**
      Store each valid word into ScoreTable class. 
      Calculate each one's score and sorted all the word by their score value.
      If they have the same value, then sorted them by alphebatical order.
      Then print all valid word and their score out.
      @param array input of all valid word we get from getAnagramsOf()
      @param str input word
   */
   private static void printResult(ArrayList<String> array, String str) {
      ArrayList<ScoreTable> scoreList = new ArrayList<ScoreTable>();
      System.out.println("We can make " + array.size() + " words from \"" + str + "\"");
      if (array.size() != 0) {
         System.out.println("All of the words with their scores (sorted by score):");
         for(String word : array) {
            scoreList.add(new ScoreTable(word));
         }
         Collections.sort(scoreList);
         for(ScoreTable s : scoreList) {
            System.out.println(s.returnScore() + ": " + s.getWord());
         }
      }
      
   }
}