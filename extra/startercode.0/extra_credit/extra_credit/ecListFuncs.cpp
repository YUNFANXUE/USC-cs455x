/*  Name: Yunfan Xue
 *  USC NetID: yunfanxu
 *  CS 455 Fall 2018
 *
 *  See ecListFuncs.h for specification of each function.
 *
 *  NOTE: remove the print statements below as you implement each function
 *  or you will receive no credit for that function.
 *
 */

#include <iostream>
#include <vector>

#include "ecListFuncs.h"

using namespace std;


ListType vectorToList(const vector<int> & nums) {
   if (nums.size() == 0) return NULL;
   
   ListType head = new Node(nums[0]);
   ListType p = head;
   for (int i  = 1; i < nums.size(); i++) {
      p -> next = new Node(nums[i]);
      p = p->next;
   }
   return head;
}



int countRuns(ListType list) {
   if (list == NULL) {return 0;}
   
   int number = 0;
   bool second = false;
   ListType p = list;
   int target = p->data;
   p = p->next;
   while (p != NULL) {
      if(p->data == target) {
         if(second == false) { number ++; }
         second = true;
      } else {
         target = p->data;
         second = false;
      }
      p = p->next;
   }
   return number;
}



ListType reverse(ListType list) {
   if (list == NULL || list->next == NULL) {
      return list;
   }
   ListType prev = NULL;
   ListType cur = list;
   ListType p = list;
   while (p != NULL) {
      cur = new Node(p->data, prev);
      p = p->next;
      prev = cur;
   }
   return prev;  

}



void removeMiddle(ListType &list) {
   if (list == NULL || list->next == NULL) { //if there is no element or only one element
      list = NULL;
      return;
   }
   if (list->next->next == NULL) {   // if there are two elements, delete the first one
      list = list->next;
      return;
   }
   
   int size = 0;
   ListType p = list;
   while (p != NULL) {
      size++;
      p = p->next;
   }
   p = list;
   for (int i = 1; i < (size+1) /2 - 1 ; i++) {
      p = p->next;
   }
   p->next = p->next->next;
}



void split(ListType &list, int splitVal, ListType &a, ListType &b) {
   if (list == NULL) {
      a = NULL;
      b = NULL;
      return;
   }
   
   // determine if the splitVal is in the list. If not in the list, then let a be the list, b be NULL.
   bool findVal = false;
   ListType cur = list;
   while (cur != NULL) {
      if (cur->data == splitVal) {
         findVal = true;
      }
      cur = cur->next;
   }
   if (!findVal) {
      a = list;
      b = NULL;
      list = NULL;
      return;
   }
   
   // splitVal is in the list, move to the location of that splitVal in the list
   ListType prev = NULL;
   cur = list;
   while (cur->data != splitVal) {
      prev = cur;
      cur = cur->next;
   }
   
   if(prev != NULL) {        //splitVal is not the first element
      prev->next = NULL;
      a = list;
   } else {                  // splitVal is the first element
      a = NULL;
   }
   list = NULL;
   b = cur->next;
}
