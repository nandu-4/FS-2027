// You are given an integer n.
// Your task is to print all numbers from 0 to n (inclusive) whose binary 
// representation is a palindrome.

// Example:
// n=7
// Output is 0 1 3 5 7



#include <iostream>
using namespace std;

bool isBinaryPalindrome(int num) {
    int original = num;
    int reversed = 0;

    while (num > 0) {
        reversed = (reversed << 1) | (num & 1);
        num >>= 1;
    }
    return original == reversed;
}

int main() {
    int n;
    cin >> n;

    for (int i = 0; i <= n; i++) {
        if (isBinaryPalindrome(i)) {
            cout << i << " ";
        }
    }
    return 0;
}
