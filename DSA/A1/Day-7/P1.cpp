/*
You are given an integer array pref of size n.
The array pref is created from another array arr using this rule:
pref[i] = arr[0] ^ arr[1] ^ ... ^ arr[i]   (for i = 0 to n-1)

Here, ^ means bitwise XOR.

Your task is to find and return the original array arr.

Constraints:
------------------
1 <= pref.length <= 105
0 <= pref[i] <= 106

Input Format:
---------------------
First line → integer n → the size of pref
Second line → n space-separated integers → elements of pref

Output Format:
-----------------------
Print the original array arr in array format (with brackets and commas)

Sample Input:
---------------
5
5 2 0 3 1

Sample Output:
--------------
[5, 7, 2, 3, 2]

Explanation:
-----------------
arr[0] = pref[0] = 5
arr[1] = pref[0] ^ pref[1] = 5 ^ 2 = 7
arr[2] = pref[1] ^ pref[2] = 2 ^ 0 = 2
arr[3] = pref[2] ^ pref[3] = 0 ^ 3 = 3
arr[4] = pref[3] ^ pref[4] = 3 ^ 1 = 2

*/