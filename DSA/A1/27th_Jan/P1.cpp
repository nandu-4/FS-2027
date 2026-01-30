/*
You are given a sequence of integers. Using bitwise operations only, perform the following computations:
Compute the bitwise OR of two given integers.
Determine whether a given integer is even or odd using a bitwise operation.

Count the total number of set bits (1s) in the binary representation of an integer.

Find the position of the first set bit (1-based indexing from the least significant bit).

If the number is 0, output 0.

Perform a left shift operation by one position on an integer.

Perform a right shift operation by one position on an integer.

All operations must be implemented using bitwise operators without using built-in library functions for binary manipulation.

Line 1: Two integers a and b
Line 2: An integer n (for even/odd check)
Line 3: An integer n (for counting set bits)
Line 4: An integer n (for first set bit position)
Line 5: An integer n (for left shift)
Line 6: An integer n (for right shift)

*/

#include <iostream>
using namespace std;

// Count number of set bits
int countSetBits(int n) {
    int count = 0;
    while (n > 0) {
        n = n & (n - 1);
        count++;
    }
    return count;
}

// Find position of first set bit (1-based from right)
int firstSetBitPosition(int n) {
    if (n == 0) return 0;
    int pos = 1;
    while (n > 0) {
        if (n & 1) return pos;
        n >>= 1;
        pos++;
    }
    return -1;
}

int main() {
    int a, b, n;
    
    // Bitwise OR
    cin >> a >> b;
    cout << (a | b) << endl;

    // Even or Odd
    cin >> n;
    cout << ((n & 1) == 0) << endl;  // if 0==0 true even

    // Count set bits
    cin >> n;
    cout << countSetBits(n) << endl;

    // First set bit position
    cin >> n;
    cout << firstSetBitPosition(n) << endl;

    // Left shift by 1
    cin >> n;
    cout << (n << 1) << endl;

    // Right shift by 1
    cin >> n;
    cout << (n >> 1) << endl;

    return 0;
}
