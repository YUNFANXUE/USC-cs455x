// Name: Yunfan Xue
// USC NetID: yunfanxu
// CSCI 455 PA5
// Fall 2018

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 * 
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

// cstdlib needed for call to atoi
#include <cstdlib>

#include <vector>
#include <sstream>
using namespace std;

vector<string> split(string cmd) {
   istringstream str(cmd);
   string word;
   vector<string> words;
   while (str >> word) {
      words.push_back(word);
   }
   return words;
}

void printHelp() {
   cout << "[insert name score]: Insert this name and score in the grade table." << endl;
   cout << "[change name newscore]: Change the score for name." << endl;
   cout << "[lookup name]: Lookup the name." << endl;
   cout << "[remove name]: Remove this student." << endl;
   cout << "[print]: Prints out all names and scores in the table." << endl;
   cout << "[size]: Prints out the number of entries in the table." << endl;
   cout << "[stats]: Prints out statistics about the hash table at this point." << endl;
   cout << "[help]: Prints out a brief command summary." << endl;
   cout << "[quit]: Exits the program." << endl;
}

int main(int argc, char * argv[]) {

   // gets the hash table size from the command line

   int hashSize = Table::HASH_SIZE;
   const int NAME = 1;
   const int SCORE = 2;

   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int

      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" 
              << endl;
         return 1;
      }

      grades = new Table(hashSize);

   }
   else {   // no command line args given -- use default table size
      grades = new Table();
   }

   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   string cmd;
   while(true) {
      cout << "cmd> ";
      getline(cin, cmd);
      vector<string> words = split(cmd);
      if (words[0] == "quit") {
         break;
      } else if (words[0] == "insert") {
         string name = words[NAME];
         //cout << name << endl;  
         int score = stoi(words[SCORE]);
         //cout << score << endl;
         bool success = grades->insert(name, score);
         //cout << success << endl;
         if (!success) { cout << "Already have that student." << endl; }
         
      } else if (words[0] == "change") {
         string name = words[NAME];
         int score = stoi(words[SCORE]);
         int *sco = grades->lookup(name);
         if (sco == NULL) { cout << "Can't find such student!" << endl; }
         else { (*sco) = score;}
         
      } else if (words[0] == "lookup") {
         string name = words[NAME];
         int *sco = grades->lookup(name);
         if (sco == NULL) { cout << "Can't find such student!" << endl; }
         else {cout << "Score: " << *sco << endl;}
         
      } else if (words[0] == "remove") {
         string name = words[NAME];
         bool success = grades->remove(name);
         if (!success) { cout << "Can't find such student!" << endl; }
         
      } else if (words[0] == "print") {
         grades->printAll();
      } else if (words[0] == "size") {
         grades->numEntries();
      } else if (words[0] == "stats") {
         grades->hashStats(cout);
      } else if (words[0] == "help") {
         printHelp();
      } else {
         cout << "ERROR: invalid command" << endl;
      }
      words.clear();
   }
   delete grades;
   grades = NULL;
   return 0;
}
