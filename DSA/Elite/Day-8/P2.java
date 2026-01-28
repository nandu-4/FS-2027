/*
There are N computers in a network, all the computers are connected as tree 
structure. And one new connection is added in the Network. The computers in 
the network are identified with their IDs, the IDs are numbered between 1 to N.

The connections in the network is given as coonection[i] = [comp-A, comp-B], 
there is a connection between comp-A and comp-B.

Your task is to remove a connection in the network and print it, so that 
all the computers are connected as tree structure. If there are multiple 
options to remove, remove the connection that occurs last in the input.


Input Format:
-------------
Line-1: Two space separated integers N, number of computers.
Next N lines: Two space separated integers, comp-A & comp-B.

Output Format:
--------------
Print the connection which is removed.


Sample Input-1:
---------------
6
1 2
3 4
3 6
4 5
5 6
2 3

Sample Output-1:
---------------
5 6


Sample Input-2:
---------------
4
1 2
2 3
3 4
2 4

Sample Output-2:
---------------
2 4

*/

import java.util.*;

class DSU{
    int parent[];
    int ans[];
    DSU(int n){
        parent=new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }
    int find(int n){
        if(parent[n]!=n)
        parent[n]=find(parent[n]);
        return parent[n];
    }
    void union(int a,int b){
        int pa=find(a);
        int pb=find(b);
        if(pa==pb){
            ans=new int[]{a,b};
            return;
        }if(pa>pb){
            parent[pb]=pa;
        }else{
            parent[pa]=pb;
        }
    }
    void getans(){
        System.out.println((ans[0]+1)+" "+(ans[1]+1));
    }
}
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        DSU set=new DSU(n);
        for(int i=0;i<n;i++){
            set.union(sc.nextInt()-1,sc.nextInt()-1);
        }
        set.getans();
    }
} 