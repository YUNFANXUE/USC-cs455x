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

#include <sstream>
using namespace std;

/**
   Print the help information of the legal command of this program.
 */
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

/**
   process the command. There are 9 cases of command:
      [insert name score]:     Insert this name and score in the grade table."
      [change name newscore]:  Change the score for name."
      [lookup name]:           Lookup the name."
      [remove name]:           Remove this student."
      [print]:                 Prints out all names and scores in the table."
      [size]:                  Prints out the number of entries in the table."
      [stats]:                 Prints out statistics about the hash table at this point."
      [help]:                  Prints out a brief command summary."
      [quit]:                  Exits the program." << endl;
   @param grades Table that store the data of name and score
   @param command command of operation
   @param name student name
   @param score student score
   @return true if we continue input the command, return false if we type command "quit".
 */
bool processCommand(Table * & grades, const string &command, const string &name, string &score) {
   if (command == "quit") { return false;
   } else if (command == "insert") {
      
      if (!grades->insert(name, stoi(score))) { cout << "Already have that student." << endl; }
      
   } else if (command == "change") {
      
      int *sco = grades->lookup(name);
      if (sco == NULL) { cout << "Can't find such student!" << endl; }
      else { (*sco) = stoi(score); }
      
   } else if (command == "lookup") {
      
      int *sco = grades->lookup(name);
      if (sco == NULL) { cout << "Can't find such student!" << endl; }
      else {cout << "Score: " << *sco << endl;}
      
   } else if (command == "remove") {
      
      if (!grades->remove(name)) { cout << "Can't find such student!" << endl; }
      
   } else if (command == "print") {
      
      grades->printAll();
      
   } else if (command == "size") {
      
      cout << "Size: " << grades->numEntries() << endl;
      
   } else if (command == "stats") {
      
      grades->hashStats(cout);
      
   } else if (command == "help") {
      
      printHelp();
      
   } else { cout << "ERROR: invalid command" << endl; }
   
   return true;
}


int main(int argc, char * argv[]) {

   // gets the hash table size from the command line
   int hashSize = Table::HASH_SIZE;
   Table * grades;  // Table is dynamically allocated below, so we can call
   // different constructors depending on input from the user.

   if (argc > 1) {
      hashSize = atoi(argv[1]);  // atoi converts c-string to int
      if (hashSize < 1) {
         cout << "Command line argument (hashSize) must be a positive number" << endl;
         return 1;
      }
      grades = new Table(hashSize);
   } else {   // no command line args given -- use default table size
      grades = new Table();
   }
   grades->hashStats(cout);

   // add more code here
   // Reminder: use -> when calling Table methods, since grades is type Table*
   // process the input command line and seperate each word
   while (true) {
      cout << "cmd> ";
      string command;
      string name;
      string score;
      string cmd;
      getline(cin, cmd);
      istringstream istr(cmd);
      istr >> command;
      istr >> name;
      istr >> score;
      if(!processCommand(grades, command, name, score)) { break; }
   }
   delete grades;
   grades = NULL;
   return 0;
}
