/*
You are participating in a game.
You are given an integer number n, which represents n treasure chests arranged 
in a line.
The treasure chests are shown using the binary representation of the number:
1 → a treasure chest with gold
0 → an empty treasure chest
Your task is to find the longest distance between any two adjacent treasure 
chests that contain gold.

If there are no two treasure chests with gold, return 0 as the answer

Input Format:
--------------
A single integer n

Output Format:
---------------
A single integer
The longest distance between two adjacent treasure chests with gold

Example:
----------------------
If n = 22:
Binary representation of 22 is: 10110

Here:
The first two 1s are with a distance of 2
The Second two 1s are with a distance of 1
Highest Distance between them = 2
Hence 
Output is 2.
*/