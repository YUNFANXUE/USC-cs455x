// Name: Yunfan Xue
// USC NetID: yunfanxu
// CS 455 PA5
// Fall 2018

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"


int main() {
   ListType h1 = NULL;
   cout << "Size of list: " <<size(h1) << endl;
   cout << "Test add" << endl;
   cout <<"Add node (a,3):" << add(h1, "a", 3) << endl;
   cout <<"Add node (a,2):" << add(h1, "a", 2) << endl;
   cout <<"Add node (b,2):" << add(h1, "b", 2) << endl;
   cout <<"Add node (b,2):" << add(h1, "b", 3) << endl;
   cout <<"Add node (c,2):" << add(h1, "c", 5) << endl;
   cout <<"Add node (a,1):" << add(h1, "a", 1) << endl;
   print(h1);
   cout << "Size of list: " << size(h1) << endl;
   cout << endl;
   
   cout << "Test get and change" << endl;
   
   cout <<"Get a: " << get(h1, "a") << endl;
   int * p = get(h1, "a");
   *p = 10;
   //cout <<"Get a: " << get(h1, "a") << endl;
   print(h1);

   cout << endl;
   
   cout << "Test remove" << endl;
   cout <<"Remove a:" << removeEle(h1, "a") << endl;
   cout <<"Add node (e,1):" << add(h1, "e", 1) << endl;
   cout <<"Remove e:" << removeEle(h1, "e") << endl;
   cout <<"Remove k:" << removeEle(h1, "k") << endl;
   cout <<"Remove c:" << removeEle(h1, "c") << endl;
   print(h1);
   cout << "Size of list: " <<size(h1) << endl;

   return 0;
}
