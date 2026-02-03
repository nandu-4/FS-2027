/*
Given 3 positives numbers a, b and c. Return the minimum flips required 
in some bits of a and b to make ( a OR b == c )
Flip operation consists of change any single bit 1 to 0 or change the bit 
0 to 1 in their binary representation.

Example 1:
--------------------------------
Input: a = 2, b = 6, c = 5
Output: 3
Explanation: After flips a = 1 , b = 4 , c = 5 such that (a OR b == c)

Sample Input 1:
--------------------
2 6 5

Sample Output 1:
---------------------
3

Constraint:
------------------
1 <= a,b,c <= 10^9

*/