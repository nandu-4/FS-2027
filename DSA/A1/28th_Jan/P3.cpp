/*
You are given an integer n.
Return all numbers in the range 1 to n whose binary representation contains 
only set bits (1s).

Example:
Input = 7
Output = 1 3 7
*/

#include <iostream>
using namespace std;

bool allSetBits(int num) {
    return (num & (num + 1)) == 0;
}

int main() {
    int n;
    cin >> n;

    for (int i = 1; i <= n; i++) {
        if (allSetBits(i)) {
            cout << i << " ";
        }
    }
    return 0;
}
