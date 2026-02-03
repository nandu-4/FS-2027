/*
Given an array of integers nums, find the length of the longest Nice Subarray.
A Nice Subarray is defined as a subarray where no two elements share a common 
set bit in their binary representation.

Formally, for any i ≠ j inside the subarray:
nums[i] & nums[j] = 0

Input Format:
--------------
First line contains integer N — size of the array
Second line contains N space-separated integers

Output Format
--------------
Print a single integer — the maximum length of a nice subarray.

Example
--------
Input:
5
1 3 8 48 10

Output:
3

Explanation
--------------
Longest Nice Subarray is [1, 3, 8] of length 3 because:
1 & 3 = 0
1 & 8 = 0
3 & 8 = 0

*/