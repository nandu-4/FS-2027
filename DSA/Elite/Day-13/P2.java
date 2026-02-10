/*
Bubloo is working with computer networks, where servers are connected 
in a hierarchical structure, represented as a Binary Tree. Each server (node) 
is uniquely identified by an integer value.

Bubloo has been assigned an important task: find the shortest communication 
path (in terms of network hops) between two specific servers in the network.

Network Structure:
------------------
The network of servers follows a binary tree topology.
Each server (node) has a unique identifier (integer).
If a server does not exist at a certain position, it is represented as '-1' (NULL).

Given the root of the network tree, and two specific server IDs (E1 & E2), 
determine the minimum number of network hops (edges) required to 
communicate between these two servers.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
4 8
Sample Output-1:
----------------
4

Explanation:
------------
The edegs between 4 and 8 are: [4,1], [1,2], [2,3], [3,8]


Sample Input-2:
---------------
1 2 4 3 5 6 7 8 9 10 11 12
6 6

Sample Output-2:
----------------
0

Explanation:
------------
No edegs between 6 and 6.

*/

import java.util.*;

class Node{
    int data;
    Node left,right;
    Node(int val){
        data=val;
        left=right=null;
    }
}

class BT{
    Node root;
    BT(){
        root=null;
    }
    void Buildtree(int arr[]){
        if(arr[0]==-1)return;
        root=new Node(arr[0]);
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        int idx=1;
        while(!q.isEmpty() && idx<arr.length){
            Node cur=q.poll();
            if(idx<arr.length && arr[idx]!=-1){
                cur.left=new Node(arr[idx]);
                q.add(cur.left);
            }idx++;
            if(idx<arr.length && arr[idx]!=-1){
                cur.right=new Node(arr[idx]);
                q.add(cur.right);
            }idx++;
        }
    }
    Node lca(Node root,int a,int b){
        if(root==null)return null;
        if(root.data==a || root.data==b)return root;
        Node left=lca(root.left,a,b);
        Node right=lca(root.right,a,b);
        if(left!=null && right!=null)return root;
        return (left!=null)?left:right;
    }
    int find(int n,Node root){
        int t[]=new int[]{-1};
        findrec(n,root,0,t);
        return t[0];
    }
    void findrec(int n,Node root,int c,int t[]){
        if(root.data==n){
            t[0]=c;return;
        };
        if(root.left!=null) findrec(n,root.left,c+1,t);
        if(root.right!=null) findrec(n,root.right,c+1,t);
    }
    int hops(int a,int b){
        if(a==b)return 0;
        int ahop=find(a,root);
        int bhop=find(b,root);
        if(ahop==-1 || bhop==-1)return -1;
        Node l=lca(root,a,b);
        int dlca=find(l.data,root);
        return ahop+bhop -2*dlca;
    }
}
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int arr[]=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int a=sc.nextInt();
        int b=sc.nextInt();
        BT t=new BT();
        t.Buildtree(arr);
        System.out.println(t.hops(a,b));
    }
}