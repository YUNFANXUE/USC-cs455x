// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA4
// Fall 2018

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.File;
import java.util.Scanner;


/**
   A dictionary of all anagram sets. 
   Note: the processing is case-sensitive; so if the dictionary has all lower
   case words, you will likely want any string you test to have all lower case
   letters too, and likewise if the dictionary words are all upper case.
 */

public class AnagramDictionary {

   private Map<Multiset, ArrayList<String>> dicMap;

   /**
      Create an anagram dictionary from the list of words given in the file
      indicated by fileName.  
      PRE: The strings in the file are unique.
      @param fileName  the name of the file to read from
      @throws FileNotFoundException  if the file is not found
    */
   public AnagramDictionary(String fileName) throws FileNotFoundException {
      
      dicMap = new HashMap<Multiset, ArrayList<String>>();

      File inFile = new File(fileName);
      Scanner fileScanner = new Scanner(inFile);

      while (fileScanner.hasNext()) {
         String curWord = fileScanner.next();
         Multiset multi = new Multiset(curWord);
         if(dicMap.containsKey(multi)) {
            ArrayList<String> temp = dicMap.get(multi);
            temp.add(curWord);
            dicMap.put(multi, temp);
         } else {
            ArrayList<String> start = new ArrayList<String>();
            start.add(curWord);
            dicMap.put(multi, start);
         }
      }
   }   

   /**
      Get all anagrams of the given string. This method is case-sensitive.
      E.g. "CARE" and "race" would not be recognized as anagrams.
      @param s string to process
      @return a list of the anagrams of s
    */
   public ArrayList<String> getAnagramsOf(String s) {
      Multiset target = new Multiset(s);
      if (dicMap.containsKey(target)) {
         return dicMap.get(target);
      }
      return new ArrayList<String>();
   }
   
}
