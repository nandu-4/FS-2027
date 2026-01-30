/*

Given the in-order and post-order traversals of a binary tree, construct 
the original binary tree. 

The output should list the nodes in the order they appear in pre-order.

Input Format:
-------------
An integer N representing the number nodes.
A space-separated list of N integers, the nodes in in-order traversal.
A space-separated list of N integers, the nodes in post-order traversal.

Output Format:
--------------
Print the list of the nodes in pre-order.


Sample Input:
-------------
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1

Sample Output:
--------------
[1, 2, 4, 5, 3, 6, 7]

Explanation:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
    
*/


import java.util.*;

class Main{
    static void buildpreorder(List<Integer> in,List<Integer> post,int instart,int inend,int poststart,int postend,List<Integer> pre){
        if(instart>inend || poststart>postend)return;
        int root=post.get(postend);
        pre.add(root);
        int rootidx=in.indexOf(root);
        int leftsize=rootidx-instart;
        buildpreorder(in,post,instart,rootidx-1,poststart,poststart+leftsize-1,pre);
        buildpreorder(in,post,rootidx+1,inend,poststart+leftsize,postend-1,pre);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer> inorder=new ArrayList<>(n);
        List<Integer> postorder=new ArrayList<>(n);
        List<Integer> preorder=new ArrayList<>(n);
        for(int i=0;i<n;i++){
            inorder.add(sc.nextInt());
        }
        for(int i=0;i<n;i++){
            postorder.add(sc.nextInt());
        }
        buildpreorder(inorder,postorder,0,n-1,0,n-1,preorder);
        System.out.println(preorder);
    }    
}