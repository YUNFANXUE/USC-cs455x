// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA4
// Fall 2018

import java.util.Map;
import java.util.TreeMap;

public class Multiset {

    private String str;
    private int[] freq;
    private Map<Character, Integer> letterMap;

    /**
        Initialization of this Multiset object. The Multiset class store the letter information of one particular word.
        The letter which occurs in the this word will be in ascending order while stored in a String.
        Each letter's frequency is soter in the array which has exactly have the same size with String.
        For example, for word "abbcdcd", the string we get will be "abcd", the array we get will be {1,2,2,2}
        I achieve the sorting step by storeing them into a tree map.
        If the input string is empty, then we get a empty string and a empty array.
        @param s input word
    */
    public Multiset(String s) {
        str = "";

        letterMap = new TreeMap<Character, Integer>();
        if (s.length() < 1) {
            str = "";
            freq = new int[]{};
        }

        for (int i = 0; i < s.length(); i++) {
            if (letterMap.containsKey(s.charAt(i))) {
                letterMap.put(s.charAt(i), letterMap.get(s.charAt(i)) + 1);
            } else {
                letterMap.put(s.charAt(i), 1);
            }
        }

        freq = new int[letterMap.size()];
        int cur = 0;
        for (Map.Entry<Character, Integer> entry : letterMap.entrySet()) {
            str = str + entry.getKey();
            freq[cur] = entry.getValue();
            cur ++;
        }
    }

    /**
        Return the String of this word.
        @return str
    */
    public String getString() {
        return str;
    }

    /**
        Return the frequency of each letter which stored in the int[] array.
        @return freq
    */
    public int[] getFreq() {
        return freq;
    }

    /**
        Judge if these two object is equal.
        We need to compare if the String is equal and if the array is totally equal.
        Return true iff all the elements are equal.
        @param a the other object to compare to
        @return 
    */
    public boolean equals(Object a) {
        Multiset set = (Multiset)a;
        int[] arrayCompare = set.getFreq();
        if (freq.length != arrayCompare.length || !str.equals(set.getString())) { return false; }
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] != arrayCompare[i]) { return false; }
        }
        return true;
    }

    /**
        generate the hashCode of this object. I use the hasCode() of String and add to the total value of frequency.
        @return hash code.
    */
    public int hashCode() {
        int cnt = 0;
        for (int i = 0; i < freq.length; i++) {
            cnt += freq[i];
        }
        return str.hashCode() + cnt;
    }


}