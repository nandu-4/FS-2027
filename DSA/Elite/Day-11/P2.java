/*
Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Refernce Node:
--------------
class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}


Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 5 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 2 4 3 2

Sample Output-2:
----------------
[3, 4, 2]

*/

import java.util.*;

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}

class BT{
    TreeNode root;
    BT(){
        root=null;
    }
    List<Integer> buildtree(int arr[]){
        root=new TreeNode(arr[0]);
        Queue<TreeNode> q=new LinkedList<>();
        List<Integer> l=new ArrayList<>();
        q.offer(root);
        int i=1;
        while(!q.isEmpty() && i<arr.length){
            int size=q.size();
            for(int k=0;k<size;k++){
                TreeNode curr=q.poll();
                if(i<arr.length && arr[i]!=-1){
                    curr.left=new TreeNode(arr[i]);
                    q.offer(curr.left);
                }
                i++;
                if(i<arr.length && arr[i]!=-1){
                    curr.right=new TreeNode(arr[i]);
                    q.offer(curr.right);
                }
                i++;
                if(k==size-1)l.add(curr.val);
            }
        }
        while(q.size()>1){q.poll();}
        l.add(q.poll().val);
        return l;
    }
    // List<Integer> levelorder(){
    //     Queue<TreeNode> q=new LinkedList<>();
    //     List<Integer> l=new ArrayList<>();
    //     q.offer(root);
    //     while(!q.isEmpty()){
    //         int size=q.size();
    //         for(int i=0;i<size;i++){
    //             TreeNode curr=q.poll();
    //             if(curr.left!=null)q.offer(curr.left);
    //             if(curr.right!=null)q.offer(curr.right);
    //             if(i==size-1){
    //                 l.add(curr.val);
    //             }
    //         }
    //     }
    //     return l;
    // }
}

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String str[]=sc.nextLine().split(" ");
        int arr[]=new int[str.length];
        for(int i=0;i<str.length;i++){
            arr[i]=Integer.parseInt(str[i]);
        }
        BT t=new BT();
        System.out.println(t.buildtree(arr));
    }
}