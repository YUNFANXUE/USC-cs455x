// Name: Yunfan Xue
// USC NetID: yunfanxu
// CSCI 455 PA5
// Fall 2018

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************


Table::Table() {
   hashSize = HASH_SIZE;
   data = new ListType[hashSize]();
}


Table::Table(unsigned int hSize) {
   hashSize = hSize;
   data = new ListType[hashSize]();
}


int * Table::lookup(const string &key) {
   head = data[hashCode(key)];
   return get(head, key);
}

bool Table::remove(const string &key) {
   bool flag = removeEle(data[hashCode(key)], key);
   return flag;
}

bool Table::insert(const string &key, int value) {
   bool flag = add(data[hashCode(key)], key, value);
   return flag;
}

int Table::numEntries() const {
   int num = 0;
   for (int i = 0; i < hashSize; i++) {
      ListType head = data[i];
      num += size(head);
   }
   return num;    
}


void Table::printAll() const {
   for (int i = 0; i < hashSize; i++) {
      ListType head = data[i];
      print(head);
   }
}

//   number of buckets: 997
//   number of entries: 10
//   number of non-empty buckets: 9
//   longest chain: 2
void Table::hashStats(ostream &out) const {
   int largestNum = 0;
   int numNonEmpty = 0;
   for (int i = 0; i < hashSize; i++) {
      ListType head = data[i];
      if (size(head) > largestNum) { largestNum = size(head); }
      if (head != NULL) { numNonEmpty++; }
   }
   out << "number of buckets: " << hashSize << endl;
   out << "number of entries: " << numEntries() << endl;
   out << "number of non-empty buckets: " << numNonEmpty << endl;
   out << "longest chain: " << largestNum << endl;
}

// add definitions for your private methods here

