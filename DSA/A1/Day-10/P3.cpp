/*
You are given the root of a binary tree.
Return the zigzag level order traversal of its nodes' values.
(That is, from left to right, then right to left for the next level, 
and alternate between.)

Input Format:
--------------
Line-1: An integer n, number of nodes in the tree.
Line-2: n space-separated integers representing the tree in level-order format 
        (use "null" for empty nodes).


Output Format:
---------------
Line-1: Print a list of lists, each representing a level in zigzag order.

Constraints:
------------
*The number of nodes in the tree is in the range [0, 2000].
*-100 ≤ Node.val ≤ 100.

Sample Input-1:
---------------
7
3 9 20 null null 15 7

Sample Output-1:
-----------------
[[3], [20, 9], [15, 7]]

Sample Input-2:
---------------
1
1


Sample Output-2:
----------------
[[1]]

*/