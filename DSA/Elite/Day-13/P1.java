/*
Imagine you're managing a busy warehouse where every product is delivered in pairs to ensure proper stocking. However, due to a mix-up at the shipping dock, two unique product IDs ended up without their matching pair, while all other products arrived as complete pairs. Your task is to identify these two solitary product IDs.

You're given list of product IDs. In this list, every product ID appears exactly twice except for two IDs that appear only once. Return these two unique product IDs in any order.

You must design an algorithm that runs in linear time and uses only constant extra space.


Example 1:
----------
Input: 
101 102 101 103 102 105  
Output: 
[103, 105] 
 
Explanation: The IDs 103 and 105 appear only once, while all other IDs appear twice. [105, 103] is also an acceptable answer.

Example 2:
-----------
Input: 121 136
Output: [121, 136] 
*/

import java.util.*;

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int str[]=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int xor=0;
        for(int i:str){
            xor^=i;
        }
        int setbit=xor & -xor;
        int a=0,b=0;
        for(int i:str){
            if((i & setbit)!=0)a^=i;
            else b^=i;
        }
        System.out.println("["+a+", "+b+"]");
        
    }
}

