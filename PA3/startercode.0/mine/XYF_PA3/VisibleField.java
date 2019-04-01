// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA3
// Fall 2018


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   /**
      Representation invariant:
      visibleFieldArray size = the mineFieldArray size in input MineField object
      Elements in visibleFieldArray should be integer with the boundary of [-3, 11]
      numMineOnVFA <= mineField.numMines()
      numVFARows >= 1
      numVFACols >= 1

   */
   private int[][] visibleFieldArray; // arrays that store all the square status
   private int numMineOnVFA;          // number of mines to guess
   private int numVFARows;            // number of rows of visibleFieldArray
   private int numVFACols;            // number of cols of visibleFieldArray
   private boolean gameOver;          // is game over
   private MineField mineFieldVisible;  

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
      mineFieldVisible = mineField;
      numVFARows = mineField.numRows();
      numVFACols = mineField.numCols();
      visibleFieldArray = new int[mineField.numRows()][mineField.numCols()];
      numMineOnVFA = mineField.numMines(); 
      resetGameDisplay();
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying MineField. 
   */     
   public void resetGameDisplay() {
      for (int i = 0; i < numVFARows; i++) {
         for (int j = 0; j < numVFACols; j++) {
            visibleFieldArray[i][j] = COVERED;
         }
      }
      gameOver = false;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      return mineFieldVisible;       // DUMMY CODE so skeleton compiles
   }
   
   
   /**
      get the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
      return visibleFieldArray[row][col];       // DUMMY CODE so skeleton compiles
   }

   
   /**
      Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
      int numGuess = 0;
      for (int i = 0; i < numVFARows; i++) {
         for (int j = 0; j < numVFACols; j++) {
            if (visibleFieldArray[i][j] == MINE_GUESS) {
               numGuess++;
            }
         }
      }
      return numMineOnVFA - numGuess;
   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   public void cycleGuess(int row, int col) {
      if (visibleFieldArray[row][col] == COVERED) {
         visibleFieldArray[row][col] = MINE_GUESS;
      } else if (visibleFieldArray[row][col] == MINE_GUESS) {
         visibleFieldArray[row][col] = QUESTION;
      } else {
         visibleFieldArray[row][col] = COVERED;
      }
      
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
      int numMineAround = mineFieldVisible.numAdjacentMines(row, col);

      // if the uncover square is a mine, exploded and game over. 
      if(mineFieldVisible.hasMine(row, col)) {
         visibleFieldArray[row][col] = EXPLODED_MINE;
         gameOver = true;
         
         // After game over, we need to set all the wrong guess to INCORRECT_GUESS
         // set all other hidden mine to MINE
         for (int i = 0; i < numVFARows; i++) {
        	   for (int j = 0; j < numVFACols; j++) {
        		   if(visibleFieldArray[i][j] == MINE_GUESS && !mineFieldVisible.hasMine(i, j)) {
               visibleFieldArray[i][j] = INCORRECT_GUESS;
        	      }
               if ((visibleFieldArray[i][j] == COVERED || visibleFieldArray[i][j] == QUESTION) 
        				&& mineFieldVisible.hasMine(i, j)) {
                  visibleFieldArray[i][j] = MINE;
               }
            }
         }
         return false;
         
      } else {  //if it is not a mine
         //if there is no mine around, we need to do exploration
         if (numMineAround == 0) {     
            exploration(row, col);
         } else {
            visibleFieldArray[row][col] = numMineAround;
         }
         return true;
      }
   }
 
   
   /**
      Returns whether the game is over.
      I use boolean gameOver on popurse. Because once we open a mine, we no longer need to check the rest square and return directly.
      It will save some execution time.
      If we have not explode a mine, then we neet to check every non-mine square is uncovered. 
      If yes, then game is over. We won. If no, game continue.
      @return whether game over
    */
   public boolean isGameOver() {
      if (gameOver) {
         return gameOver;
      } else {
         if (isAllNotMineUncover()) {
            gameOver = true;
         }
         return gameOver;
      }
   }
 
   
   /**
      Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
      if (visibleFieldArray[row][col] >= 0) {
         return true;
      } else {
         return false;
      }
   }
   
   
   // <put private methods here>

   /**
      Do Flood Fill Algorithm recursively. 
      Since all the mine must be surrounded by numbers and all the blank (which is 0 mines around) must be surrounded by numbers,
      the only thing we need to do is explore the current locatios eight surround location recursively.
      
      When we reach the boundry of array or the uncovered number or the MINE_GUESS, then we stop recursion and return.
      When we reach a covered number but has non-zero number of mines around it, we uncover it and stop recursion and return.
      Else, which is the situation that we open a square with number 0, we keep exploring.

      @param row of the square
      @param col of the square
      PRE: getMineField().inRange(row, col)
   */
   private void exploration(int row, int col) {
      //if we are out of bound
      if(row >= numVFARows || col >= numVFACols || row < 0 || col < 0 ) {
         return;
      } 
      //if it is already detected, or has mark, we return
      if (visibleFieldArray[row][col] < COVERED || visibleFieldArray[row][col] >= 0) {
         return;
      } 
      //if it has non-zero mines around, that means we reach the detection edges. 
      if (mineFieldVisible.numAdjacentMines(row, col) > 0) {
         visibleFieldArray[row][col] = mineFieldVisible.numAdjacentMines(row, col);
         return;
      } 
      
      visibleFieldArray[row][col] = mineFieldVisible.numAdjacentMines(row, col);
      //explore the eight surrounding location.
      exploration(row - 1, col);
      exploration(row + 1, col);
      exploration(row, col + 1);
      exploration(row, col - 1);
      exploration(row - 1, col - 1);
      exploration(row + 1, col - 1);
      exploration(row - 1 , col + 1);
      exploration(row + 1, col + 1);
      
   }

   /**
      Check whether all the non-mine square is uncovered.
      Return true if all the non-mine square is uncovered, else return false.
      @return 
   */
   private boolean isAllNotMineUncover() {
      for(int i = 0; i < numVFARows; i++) {
         for(int j = 0; j < numVFACols; j++) {
            if (!mineFieldVisible.hasMine(i, j) && visibleFieldArray[i][j] <= COVERED) {
            	return false;
            }
         }
      }
      return true;
   }
}
