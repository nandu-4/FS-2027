/*
Given the root of a binary tree, return its maximum depth.

A binary tree's maximum depth is the number of nodes along the 
longest path from the root node down to the farthest leaf node.

Input Format:
--------------
Line-1: An integer n, the number of nodes (tokens) in the tree.
Line-2: n space-separated tokens representing the tree in level-order traversal 
        (use "null" for missing children).

Output Format:
---------------
Line-1: A single integer representing the maximum depth of the binary tree.


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
3

Sample Input-2:
---------------
1
1

Sample Output-2:
----------------
1
*/