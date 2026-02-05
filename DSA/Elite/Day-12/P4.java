/*
You are given a 2-dimensional integer matrix representing a feature map obtained 
from a convolutional layer of a neural network. Your task is to compute the Max 
Pooling – Forward Pass on this matrix using Java multithreading.

Max Pooling reduces the spatial dimensions of the input matrix by sliding a fixed-size
window over it and selecting the maximum value from each window.

The computation must be performed in parallel, where multiple threads cooperate to 
compute different parts of the output matrix.

Pooling Rules
-------------
	Pooling window size is K × K
	Stride is S
	No padding is applied
	Windows move from left to right and top to bottom

The output matrix dimensions are:
	Output Rows  = (H − K) / S + 1
	Output Cols  = (W − K) / S + 1
where H × W is the size of the input matrix.

Multithreading Requirement
--------------------------
	The forward pooling computation must be parallelized
	Each thread should compute one or more output rows
	The final output must be deterministic and ordered

Input Format
------------
H W
H lines each containing W space-separated integers
K
S

Input Description
-----------------
H	  Number of rows in the input matrix
W	  Number of columns in the input matrix
K	  Pooling window size
S	  Stride

Output Format
-------------
Output matrix with each row printed on a new line
Each row contains space-separated integers

Sample Input
------------
4 4
1 3 2 4
5 6 1 2
0 2 4 1
3 1 0 2
2
2

Sample Output
-------------
6 4
3 4

Explanation 
--------------
A 2 × 2 window slides over the matrix with stride 2
From each window, the maximum value is selected
These maximum values form the output matrix

Constraints
--------------
1 ≤ H, W ≤ 1000
1 ≤ K ≤ min(H, W)
1 ≤ S ≤ K
Input values are valid integers
(H − K) and (W − K) are divisible by S

*/

import java.util.concurrent.*;
import java.util.*;

class maxval implements Callable<Integer>{
    int x,y;
    int arr[][];
    int k;
    public maxval(int i,int j,int k,int arr[][]){
        this.x=i;this.y=j;this.k=k;this.arr=arr;
    }
    @Override
    public Integer call() throws Exception{
        int max=0;
        for(int i=x;i<k+x;i++){
            for(int j=y;j<k+y;j++){
                max=Math.max(max,arr[i][j]);
            }
        }
        return max;
    }
}
public class MaxPoolingForwardDynamicMT {

    /* ==================================
       Implement Your Code using Callable
       ================================== */
    public static int[][] maxPoolForward(int arr[][],int k,int s){
        int r=arr.length;
        int c=arr[0].length;
        int or=(r-k)/s+1;
        int oc=(c-k)/s+1;
        ExecutorService exe=Executors.newFixedThreadPool(
          Math.min(oc*or,Runtime.getRuntime().availableProcessors())  
        );
        List<maxval> tasks=new ArrayList<>();
        List<Future<Integer>> futures=new ArrayList<>();
        for(int i=0;i+k<=r;i+=s){
            for(int j=0;j+k<=c;j+=s){
                tasks.add(new maxval(i,j,k,arr));
            }
        }
        for(maxval i:tasks){
            futures.add(exe.submit(i));
        }
        int res[][]=new int[or][oc],idx=0;
        for(int i=0;i<or;i++){
            for(int j=0;j<oc;j++){
                try{
                    res[i][j]=futures.get(idx++).get();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        exe.shutdown();
        return res;
    };
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Read matrix dimensions
        int H = sc.nextInt();
        int W = sc.nextInt();

        int[][] input = new int[H][W];

        // Read matrix
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                input[i][j] = sc.nextInt();
            }
        }

        // Read pooling parameters
        int poolSize = sc.nextInt();
        int stride = sc.nextInt();

        int[][] output =
                maxPoolForward(input, poolSize, stride);

        // Print output
        for (int[] row : output) {
            for (int val : row) {
                System.out.print(val + " ");
            }
            System.out.println();
        }

        sc.close();
    }
}