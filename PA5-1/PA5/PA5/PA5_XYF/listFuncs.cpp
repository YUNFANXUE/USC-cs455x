// Name: Yunfan Xue
// USC NetID: yunfanxu
// CSCI 455 PA5
// Fall 2018


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

//add elements into the list.

bool add(ListType & head,const string key, int value) {
   ListType p = head;
   if (p == NULL) {
      head = new Node(key, value);
      return true;
   }
   
   while (p->next != NULL) {
      if (p->key == key) { return false; }
      p = p->next;
   }
   if (p->key == key) { return false; }
   else { p->next = new Node(key, value); }
   return true;
}

//delete the elements into the list. If the given key does not exist in the list, return false.
bool removeEle(ListType & head, const string key) {
   ListType p = head;
   
   if (p == NULL) { return false; }
   
   // if the target is the first elements of the list
   if (p->key == key) {
      head = head->next;
      delete p;            //release the uneeded memory
      p = NULL;
      return true;
   }
   while (p->next->next != NULL && p->next->key != key) {
      p = p->next;
   }
   if (p->next->key == key) {
      ListType temp = p->next;
      p->next = p->next->next;
      delete temp;            //release the uneeded memory
      temp = NULL;
      return true;
   }
   return false;
}

//get the value of key. If the key does not exist in the list, return null.
int * get(ListType  head, const string key) {
   ListType p = head;
   while (p != NULL) {
      if (p->key == key) { return &(p->value); }
      p = p->next;
   }
   return NULL;
}

//print all the element of this list.
void print(ListType head) {
   ListType p = head;
   while (p != NULL) {
      cout << p->key << " " << p->value << endl;
      p = p->next;
   }
}

//get the size of the list.
int size(ListType head) {
   ListType p = head;
   int num = 0;
   while (p != NULL) {
      p = p->next;
      ++num;
   }
   return num;
}
