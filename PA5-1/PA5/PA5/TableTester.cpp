#include <iostream>
#include <string>

using namespace std;

#include "Table.h"

int main() {
    
    Table * grades = new Table();
    grades->insert("a", 100);
    grades->insert("b", 90);
    grades->insert("c", 80);
    grades->insert("d", 70);
    grades->remove("a");
    grades->printAll();
    grades->hashStats(cout);
    delete grades;
    grades = NULL;
    

    /*
    Table concord;
    concord.insert("a", 100);
    concord.insert("b", 90);
    concord.insert("c", 80);
    concord.insert("a", 70);
    concord.printAll();
    int * val = concord.lookup("a");
    cout << val << endl;
    cout << *val << endl;
    (*val) += 1;
    concord.printAll();
    */
    return 0;
}