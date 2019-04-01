#include <iostream>
#include <string>

using namespace std;

#include "Table.h"

int main() {
    Table concord;
    cout << "stop" << endl;
    cout << concord.insert("a", 1) << endl;
    cout << "stop" << endl;
    concord.printAll();
    cout << "stop at printAll" << endl;

    int * val = concord.lookup("a");
    cout << val << endl;
    cout << *val << endl;
    (*val) += 1;
    concord.printAll();
}