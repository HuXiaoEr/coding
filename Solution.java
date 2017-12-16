import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import JzOffer.N23_从上到下打印二叉树.TreeNode;

public class Solution {
	public static void printPosibility(int n){
		int[][] data = new int[6*n+1][2];
		int idx = 0;
		for(int i = 1; i < 6; i++){
			data[i][idx] = 1;
		}
		for(int i = 2; i <= n; i++){
			for(int j = 0; j < i; j++) data[j][1-idx] = 0;
			for(int j = i; j <= 6*i; j++){
				for(int k = 1; k <= 6 && j - k >= 0; k++){
					data[j][1-idx] += data[j-k][idx];
				}
			}
		}
	}
}