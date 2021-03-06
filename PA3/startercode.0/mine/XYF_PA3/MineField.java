// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA3
// Fall 2018

import java.util.Random;


/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
public class MineField {
   
   // <put instance variables here>
   /**
      Representation invariant:
      0 <= numMinesInArray <= 1/3 * total number of field location.
      mineFieldArrayRows >= 1
      mineFieldArrayColums >= 1
      mineFieldArray should be the size with row = mineFieldArrayRows, colum = mineFieldArrayColums
   */
   private int numMinesInArray;        // number of mines in array
   private int mineFieldArrayRows;     // array rows
   private int mineFieldArrayColums;   // array cols
   private boolean mineFieldArray[][]; // array that store the location of mine
   
   
   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {
      numMinesInArray = 0;
      mineFieldArrayRows = mineData.length;
      mineFieldArrayColums = mineData[0].length;
      mineFieldArray = new boolean[mineFieldArrayRows][mineFieldArrayColums];

      for (int i = 0; i < mineFieldArrayRows; i++) {
         for (int j = 0; j < mineFieldArrayColums; j++) {
            mineFieldArray[i][j] = mineData[i][j];
            if (mineData[i][j] == true) {
               numMinesInArray++;
            }
         }
      }
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      mineFieldArrayRows = numCols;
      mineFieldArrayColums = numRows;
      mineFieldArray = new boolean[numRows][numCols];
      numMinesInArray = numMines;

   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
      mineGenerator();
      while (mineFieldArray[row][col] == true) {
         mineGenerator();
      }
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
      for (int i = 0; i < mineFieldArrayRows; i++) {
         for (int j = 0; j < mineFieldArrayColums; j++) {
            mineFieldArray[i][j] = false;
         }
      }
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
      int countMines = 0;
      //check whether the point is at the edge
      for (int i = Math.max(0, row - 1); i <= Math.min(row + 1, mineFieldArrayRows - 1); i++) {
         for (int j = Math.max(0, col - 1); j <= Math.min(col + 1, mineFieldArrayColums - 1); j++) {
            if (mineFieldArray[i][j] == true) {
               countMines++;
            }
         }
      }
      if (mineFieldArray[row][col] == true) {
         countMines -= 1;
      }
      return countMines;  
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
      if (row >=  0 && row < mineFieldArrayRows
           && col >= 0 && col < mineFieldArrayColums){
         return true;
      } else {
         return false;
      } 
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
      return mineFieldArrayRows;      
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */    
   public int numCols() {
      return mineFieldArrayColums;      
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
      return mineFieldArray[row][col];
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
      @return
    */
   public int numMines() {
      return numMinesInArray;     
   }

   
   // <put private methods here>
   /**
      Generate mine randomly.
      Before every generation, reset the array. 
      Strech the 2D array into a 1D array and each of its index map to the location of 2D array.
      Generate random number from the index. If the new index has already had a mine, then generate a new one.
      Do generation until generate numMinesInArray number of mines.
   */
   private void mineGenerator() {
      resetEmpty();
      int numGenerate = 0;
      int randomBound = mineFieldArrayRows * mineFieldArrayColums - 1;
      Random randomGenerator = new Random();
      while (numGenerate < numMinesInArray) {
         int index = randomGenerator.nextInt(randomBound + 1);
         if(mineFieldArray[index / mineFieldArrayColums][index % mineFieldArrayColums] != true) {
            mineFieldArray[index / mineFieldArrayColums][index % mineFieldArrayColums] = true;
            numGenerate++;
         }
      }
   }
         
}

