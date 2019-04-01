// Name: Yunfan Xue
// USC NetID: yunfanxu
// CSCI 455 PA5
// Fall 2018


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in header files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H
  

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

//add elements into the list.
bool add(ListType& head, const std::string key , int value);

//
int * get(ListType& , const std::string);

//delete the elements into the list
bool removeEle(ListType&, const std::string);

//print all the element of this list.
void print(ListType);

//return the size of the list
int size(ListType);

// keep the following line at the end of the file
#endif
