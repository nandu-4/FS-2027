/*

There are N cities, and M routes[], each route is a path between two cities.
routes[i] = [city1, city2], there is a travel route between city1 and city2.
Each city is numbered from 0 to N-1.
 
There are one or more Regions formed among N cities. 
A Region is formed in such way that you can travel between any two cities 
in the region that are connected directly and indirectly.
 
Your task is to findout the number of regions formed between N cities. 
 
Input Format:
-------------
Line-1: Two space separated integers N and M, number of cities and routes
Next M lines: Two space separated integers city1, city2.
 
Output Format:
--------------
Print an integer, number of regions formed.
 
 
Sample Input-1:
---------------
5 4
0 1
0 2
1 2
3 4
 
Sample Output-1:
----------------
2
 
 
Sample Input-2:
---------------
5 6
0 1
0 2
2 3
1 2
1 4
2 4
 
Sample Output-2:
----------------
1
 
*/

import java.util.*;

class DSU{
    int parent[];
    int rank[];
    DSU(int n){
        parent=new int[n];
        rank=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }
    int find(int n){
        if(parent[n]!=n){
            parent[n]=find(parent[n]) ;
        }
        return parent[n];
    }
    void union(int a,int b){
        int pa=find(a);
        int pb=find(b);
        if(pa==pb)return;
        if(rank[pa]>rank[pb]){
            parent[pb]=pa;
        }else if(rank[pa]<rank[pb]){
            parent[pa]=pb;
        }else{
            parent[pa]=pb;
            rank[pa]++;
        }
    }
    int getregions(){
        HashSet<Integer> s=new HashSet<>();
        for(int i:parent){
            s.add(find(i));
        }
        return s.size();
    }
}
class Main{
    
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int w=sc.nextInt();
        DSU set=new DSU(n);
        for(int i=0;i<w;i++){
            set.union(sc.nextInt(),sc.nextInt());
        }
        System.out.println(set.getregions());
    }
}