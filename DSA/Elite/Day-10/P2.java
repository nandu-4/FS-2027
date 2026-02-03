/*
Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7
4 5 2 6 7 3 1

Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3

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
    int pre_idx;
    BT(){
        root=null;
        pre_idx=0;
    }
    Node Build(int pre[],int post[],int l,int r){
        if(pre_idx>=pre.length ||l>r)return null;
        Node node=new Node(pre[pre_idx++]);
        if(l==r)return node;
        int i;
        for(i=l;i<=r;i++){
            if(post[i]==pre[pre_idx])break;
        }
        node.left=Build(pre,post,l,i);
        node.right=Build(pre,post,i+1,r-1);
        return node;
    }
    void Buildtree(int pre[],int post[]){
        root=Build(pre,post,0,post.length-1);
    } 
    void levelorder(){
        Queue<Node> q=new LinkedList<>();
        List<List<Integer>> l=new ArrayList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            List<Integer> temp=new ArrayList<>();
            for(int i=0;i<size;i++){
                Node cur=q.poll();
                temp.add(cur.data);
                if(cur.left!=null)q.offer(cur.left);
                if(cur.right!=null)q.offer(cur.right);
            }
            l.add(temp);
        }
        System.out.println(l);
    }
}
class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String pre[]=sc.nextLine().split(" ");
        String post[]=sc.nextLine().split(" ");
        int preorder[]=new int[pre.length];
        int postorder[]=new int[post.length];
        for(int i=0;i<pre.length;i++){
            preorder[i]=Integer.parseInt(pre[i]);
        }
        for(int i=0;i<post.length;i++){
            postorder[i]=Integer.parseInt(post[i]);
        }
        BT t=new BT();
        t.Buildtree(preorder,postorder);
        t.levelorder();
    }
}