/*
You are given two matrices A and B.
Your task is to compute the matrix multiplication result C = A × B.

The implementation must strictly follow this rule:
    - Each thread must be responsible for computing exactly one cell 
      of the result matrix.

This means that:
    One thread computes one value C[i][j]
    No thread may compute more than one cll
    No cell may be computed by more than one thread

🧠 Matrix Multiplication Definition
-----------------------------------
Matrix A is of size R × K
Matrix B is of size K × C
Then the result matrix C is of size R × C, where:
    C[i][j] = A[i][0]×B[0][j] + A[i][1]×B[1][j] + ... + A[i][K−1]×B[K−1][j]

Multithreading Requirement
--------------------------
    - The total number of threads created must be equal to the number of cells in 
    the result matrix
    - Threads must run concurrently
    - Each thread must write its computed value directly to the shared result matrix
    - The final output must be correct and deterministic

Input Format
------------
R K
Matrix A (R rows and K columns)
K C
Matrix B (K rows and C columns)

Output Format
-------------
Result matrix C (R rows and C columns)
Each row printed on a new line with space-separated integers


Sample Input
------------
2 3
1 2 3
4 5 6
3 2
7 8
9 10
11 12

Sample Output
-------------
58 64
139 154
*/

import java.util.*;
import java.util.concurrent.*;

class matrixcal implements Callable<Integer>{
    int row[];
    int col[][];
    int ci;
    matrixcal(int row[],int col[][],int ci){
        this.row=row;
        this.col=col;
        this.ci=ci;
    }
    public Integer call() throws Exception{
        int sum=0;
        for(int i=0;i<col.length;i++){
            sum+=row[i]*col[i][ci];
        }
        return sum;
    }
}
public class Main{
    public static int[][] matrixmultiply(int row[][],int col[][],int r1,int c1,int r2,int c2){
        ExecutorService exe=Executors.newFixedThreadPool(
            Math.min(r1*c2,Runtime.getRuntime().availableProcessors())
        );
        List<matrixcal> tasks=new ArrayList<>();
        List<Future<Integer>> futures=new ArrayList<>();
        for(int i=0;i<r1;i++){
            for(int j=0;j<c2;j++){
                tasks.add(new matrixcal(row[i],col,j));
            }
        }
        for(matrixcal i:tasks){
            futures.add(exe.submit(i));
        }
        int res[][]=new int[r1][c2],idx=0;
        for(int i=0;i<r1;i++){
            for(int j=0;j<c2;j++){
                try{
                    res[i][j]=futures.get(idx++).get();
                }catch(Exception e){
                    throw new RuntimeException(e);
                }
            }
        }
        exe.shutdown();
        return res;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int r1=sc.nextInt();
        int c1=sc.nextInt();
        int row[][]=new int[r1][c1]; 
        for(int i=0;i<r1;i++){
            for(int j=0;j<c1;j++){
                row[i][j]=sc.nextInt();
            }
        }
        int r2=sc.nextInt();
        int c2=sc.nextInt();
        int col[][]=new int[r2][c2]; 
        for(int i=0;i<r2;i++){
            for(int j=0;j<c2;j++){
                col[i][j]=sc.nextInt();
            }
        }
        int ans[][]=matrixmultiply(row,col,r1,c1,r2,c2);
        for(int r[]:ans){
            for(int val:r){
                System.out.print(val+" ");
            }
            System.out.println();
        }
        sc.close();
    }
}