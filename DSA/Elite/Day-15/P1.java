/*
VishnuVardan is working with Decision Trees for AI-based predictions.
To analyze alternative outcomes, Kishore has planned to flip the decision 
tree horizontally to simulate a reverse processing approach.

Rules for Flipping the Decision Tree:
	- The original root node becomes the new rightmost node.
	- The original left child becomes the new root node.
	- The original right child becomes the new left child.
This transformation is applied level by level recursively.

Note:
------
- Each node in the given tree has either 0 or 2 children.
- Every right node in the tree has a left sibling sharing the same parent.
- Every right node has no further children (i.e., they are leaf nodes).

Your task is to help VishnuVardan flip the Decision Tree while following 
the given transformation rules.

Input Format:
-------------
Space separated integers, nodes of the tree.

Output Format:
--------------
Print the list of nodes of flipped tree as described below.


Sample Input-1:
---------------
4 2 3 5 1

Sample Output-1:
----------------
5 1 2 3 4


Sample Input-2:
---------------
4 2 5

Sample Output-2:
----------------
2 5 4
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
class Tree{
    Node buildtree(int arr[]){
        if(arr.length==0)return null;
        Node root=new Node(arr[0]);
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        int idx=1;
        while(idx<arr.length && !q.isEmpty()){
            Node cur=q.poll();
            if(idx<arr.length && arr[idx]!=-1){
                cur.left=new Node(arr[idx]);
                q.offer(cur.left);
            }idx++;
            if(idx<arr.length && arr[idx]!=-1){
                cur.right=new Node(arr[idx]);
                q.offer(cur.right);
            }idx++;
        }return root;
    }
    Node buildDT(Node root){
        if(root==null || (root.right==null && root.left==null))return root;
        Node cur=buildDT(root.left);
        if(root.left!=null){
            root.left.left=root.right;
            root.left.right=root;
        }
        root.left=null;
        root.right=null;
        return cur;
    }
    void levelorder(Node root){
        if(root==null)return;
        Queue<Node> q=new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int size=q.size();
            for(int i=0;i<size;i++){
                Node cur=q.poll();
                System.out.print(cur.data+" ");
                if(cur.left!=null)q.offer(cur.left);
                if(cur.right!=null)q.offer(cur.right);
            }
        }
    }
}

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int arr[]=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Tree t=new Tree();
        Node root=t.buildtree(arr);
        Node cur=t.buildDT(root);
        t.levelorder(cur);
    }
}