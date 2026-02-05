/*
A software development company is designing a smart home automation 
system that uses sensor networks to monitor and control different devices 
in a house. The sensors are organized in a hierarchical structure, where each 
sensor node has a unique ID and can have up to two child nodes (left and right).

The company wants to analyze the left-most sensors in the system to determine
which ones are critical for detecting environmental changes. The hierarchy of 
the sensors is provided as a level-order input, where missing sensors are 
represented as -1.

Your task is to build the sensor network as a binary tree and then determine 
the left-most sensor IDs at each level.

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
A list of integers representing the left-most sensor IDs at each level


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 2, 4]



Sample Input-2:
---------------
3 2 4 1 5

Sample Output-2:
----------------
[3, 2, 1]


*/

import java.util.*;

class TreeNode 
{
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
                if(k==0)l.add(curr.val);
            }
        }
        l.add(q.poll().val);
        return l;
    }

}

class Main
{
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