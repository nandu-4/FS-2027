/*
Imagine you’re managing a busy cafe where every order is logged. You have a 
record—a list of dish names ordered throughout the day—and you want to determine 
which dishes are the most popular. Given an list of strings representing the dish 
names and an integer P, your task is to return the P most frequently ordered dishes.

The results must be sorted by the number of orders, from the most frequent to 
the least. If two dishes have been ordered the same number of times, they 
should be listed in alphabetical order.

Input Format:
-------------
Line-1: comma separated line of words, list of words.
Line-2: An integer P, number of words to display. 

Output Format:
--------------
Print P number of most common used words.

Example 1:
----------
Input=
coffee,sandwich,coffee,tea,sandwich,muffin
2
Output=
[coffee, sandwich]

Explanation: "coffee" and "sandwich" are the two most frequently ordered items. 
Although both appear frequently, "coffee" is placed before "sandwich" because 
it comes earlier alphabetically.

Example 2:
----------
Input=
bagel,muffin,scone,bagel,bagel,scone,scone,muffin,muffin
3
Output=
[bagel, muffin, scone] 

Explanation: "bagel", "muffin", and "scone" are the three most popular dishes 
with order frequencies of 3, 3, and 2 respectively. Since "bagel" and "muffin" 
have the same frequency, they are ordered alphabetically.

Constraints:

- 1 ≤ orders.length ≤ 500  
- 1 ≤ orders[i].length ≤ 10  
- Each orders[i] consists of lowercase English letters.  
- P is in the range [1, The number of unique dish names in orders].
*/

import java.util.*;

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String str[]=sc.nextLine().split(",");
        HashMap<String,Integer> m=new HashMap<>();
        for(int i=0;i<str.length;i++){
            m.put(str[i],m.getOrDefault(str[i],0)+1);
        }
        int t=sc.nextInt();
        ArrayList<String> l=new ArrayList<>();
        ArrayList<String> nl=new ArrayList<>();
        for(Map.Entry<String,Integer> e:m.entrySet()){
            l.add(e.getKey()+"-"+e.getValue());
        }
        l.sort((a,b)->{
            int f1=Integer.parseInt(a.split("-")[1]);
            int f2=Integer.parseInt(b.split("-")[1]);
            if(f1==f2)return a.split("-")[0].compareTo(b.split("-")[0]);
            return f2-f1;
        });
        for(int i=0;i<t && i<l.size();i++){
            nl.add(l.get(i).split("-")[0]);
        }
        System.out.println(nl);
    }
}