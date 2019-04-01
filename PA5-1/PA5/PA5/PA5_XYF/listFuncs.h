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

/**
   Add elements into the list.
   @param head the list to add.
   @param key the key to add.
   @param value the value of the key.
   @return Return false if the key has already in the list. Return true if add key successfully.
 */
bool add(ListType & head, const std::string key , int value);

/**
 Get the pointer of one key's value.
 @param head the list to add.
 @param key the key to add.
 @return the pointer which points to the value of particular key. If the key not exists, then return NULL.
 */
int * get(ListType head, const std::string key);

/**
 Remove one key and its value in the list.
 @param head the list to add.
 @param key the key to add.
 @return true if remove the key sucessfully, return false if the key does not exists.
 */
bool removeEle(ListType & head, const std::string key);

/**
   Print all the element of the list.
   @param head the list to add.
 */
void print(ListType head);

/**
 Get the number of entries in the list.
 @param head the list to add.
 @return value of entries
 */
int size(ListType head);

// keep the following line at the end of the file
#endif
