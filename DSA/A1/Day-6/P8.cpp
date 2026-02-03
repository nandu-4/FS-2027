/*
Given two integers start and goal, determine the minimum number of bit flips 
required to convert start into goal.
A bit flip means changing a binary bit:
from 0 to 1 or
from 1 to 0.

Input Format:
------------
Input consists of two integers:
start goal

Output Format:
--------------
Print a single integer — the minimum number of bit flips required.

Explanation:
-------------
If we XOR the numbers:
start ^ goal

The result will have 1 in positions where bits differ.
Counting these 1's gives the minimum flips.

Example:
---------
Input:
------
10 7

Processing:
10  -> 1010 (binary)
 7  -> 0111 (binary)
XOR -> 1101 (3 bits differ)

Output:
-------
3
*/