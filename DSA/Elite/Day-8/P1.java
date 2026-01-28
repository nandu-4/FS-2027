/*
You are participating in a futuristic space exploration mission where you must 
navigate through a series of planets aligned in a straight line. The planets 
are numbered from 0 to n-1, and you start your journey on planet 0.

You are given a 0-indexed array planets, where each element planets[i] represents 
the maximum number of planets you can move forward from planet i. In simpler terms, 
standing on planet i, you can move to any planet from i+1 to i+planets[i], as 
long as you don't exceed the last planet.

Your goal is to reach the final planet (planet n-1) in the fewest number of moves 
possible.

It is guaranteed that a path to the final planet always exists.

Return the minimum number of moves needed to reach planet n-1.

Sample Input:
-------------
5
2 3 1 0 4

Sample Output:
--------------
2

Explanation:
------------
- Move from planet 0 to planet 1.
- Move from planet 1 to planet 4 (last planet).
*/


import java.util.*;

class Main{
    static int helper(int arr[],int idx,int c){
        if(arr.length-1<=idx){
            return c;
        }
        int min=Integer.MAX_VALUE;
        for(int i=arr[idx];i>0;i--){
            min=Math.min(min,helper(arr,idx+i,c+1));
            if(min!=Integer.MAX_VALUE)return min;
        }
        return min;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        int min=helper(arr,0,0);
        System.out.println((min==Integer.MAX_VALUE)?-1:min);
    }
}

