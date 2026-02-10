/*

Balbir Singh is working with Binary Trees.
The elements of the tree is given in the level order format.
Balbir has a task to split the tree into two parts by removing only one edge
in the tree, such that the product of sums of both the splitted-trees should be maximum.

You will be given the root of the binary tree.
Your task is to help the Balbir Singh to split the binary tree as specified.
print the product value, as the product may be large, print the (product % 1000000007)
	
NOTE: 
Please do consider the node with data as '-1' as null in the given trees.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
Print an integer value.


Sample Input-1:
---------------
1 2 4 3 5 6
Sample Output-1:
----------------
110

Explanation:
------------
if you split the tree by removing edge between 1 and 4, 
then the sums of two trees are 11 and 10. So, the max product is 110.


Sample Input-2:
---------------
3 2 4 3 2 -1 6
  3           3
 2 4        5   7       20, 10 
3 2 -1 6  8 7     13
Sample Output-2:
----------------
100
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
    int findsum(Node root){
        if(root==null)return 0;
        return root.data+findsum(root.left)+findsum(root.right);
    }
    void maxsum(Node root,int totalsum,int max[]){
        if(root==null)return;
        int lsum=findsum(root.left);
        int rsum=findsum(root.right);
        int lmul=lsum*(totalsum-lsum);
        int rmul=rsum*(totalsum-rsum);
        max[0]=Math.max(max[0],Math.max(lmul,rmul)%1000000007);
        maxsum(root.left,totalsum,max);
        maxsum(root.right,totalsum,max);
    }
    int maxmul(Node root){
        int max[]=new int[]{0};
        maxsum(root,findsum(root),max);
        return max[0];
    }

}

class Main{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int arr[]=Arrays.stream(sc.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        Tree t=new Tree();
        Node root=t.buildtree(arr);
        System.out.println(t.maxmul(root));

    }
}