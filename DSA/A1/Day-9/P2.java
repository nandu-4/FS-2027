/*
In a game, a player starts at level n and wants to reach level 1.
The game engine applies these rules:

If the current level is even, the level is reduced to half.
If the current level is odd, the player may move one level up or down.

The goal is to reach level 1 in the minimum number of moves.


Example 1:
--------------
Input: n = 8
Output: 3
Explanation: 8 -> 4 -> 2 -> 1

Example 2:
--------------
Input: n = 7
Output: 4
Explanation: 7 -> 8 -> 4 -> 2 -> 1
or 7 -> 6 -> 3 -> 2 -> 1

Constraints:
---------------
1 <= n <= 231 - 1
*/