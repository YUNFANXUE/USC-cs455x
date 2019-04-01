// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA4
// Fall 2018

/**
   This class has information about Scrabble scores for scrabble letters and words. 
   It also store a particular word and its score.
   In scrabble not every letter has the same value. 
   Letters that occur more often in the English language are worth less (e.g., 'e' and 's' are each worth 1 point), 
   and letters that occur less often are worth more (e.g., 'q' and 'z' are worth 10 points each). 
*/

public class ScoreTable implements Comparable<ScoreTable> {

   private final int[] SCOREARRAY;  // array that store letter score
   private int score;
   String word;

   public ScoreTable(String word) {
      /**
         (1 point)-A, E, I, O, U, L, N, S, T, R 
         (2 points)-D, G 
         (3 points)-B, C, M, P 
         (4 points)-F, H, V, W, Y 
         (5 points)-K 
         (8 points)- J, X 
         (10 points)-Q, Z
      */
      SCOREARRAY = new int[]{1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10, 1, 1, 1, 1, 4, 4, 8, 4, 10};
      this.word = word;
      score = getScore(word);
   }

   /**
      @return score of this ScoreTable
   */
   public int returnScore() {
      return score;
   }

   /**
      @return this word
   */
   public String getWord() {
      return word;
   }

   /**
      construct the compareTo() method. 
      Since we want to sort the object with decending order by its score value. 
      If cur score is larger than the object a.score, we need to return a negative value.
      If cur score is smaller than the object a.score we need to return a positive calue.
      If the score is equal, then we need to compare the String in alphabatical order. We can just use the compareTo() of String.
      @param a another ScoreTable to compare.
      @return 
   */
   @Override
   public int compareTo(ScoreTable a) {
      int scoreDiff = a.score - score;
      if (scoreDiff != 0) {
         return scoreDiff;
      } else {
         return word.compareTo(a.word);
      }
   }

   /**
      calculate the score of particular word.
      @param str particular String
      @return totalScore score of that word.
   */
   private int getScore(String str) {
      int totalScore = 0;
      for (char letter : str.toCharArray()) {
         totalScore += SCOREARRAY[Character.toLowerCase(letter) - 'a'];
      }
      return totalScore;
   }

}