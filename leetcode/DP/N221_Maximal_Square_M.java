package leetcode.DP;

/**
Given a 2D binary matrix filled with 0's and 1's, 
find the largest square containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 4.
 */

//NO
//NO TWO
public class N221_Maximal_Square_M {
	
	//Not Mine DP
	/*
	 * P[i][j] represent the edge length of the largest square ENDING at position (i, j)
	 * 		P[0][j] = matrix[0][j] (topmost row);
	 * 		P[i][0] = matrix[i][0] (leftmost column);
	 * 		For i > 0 and j > 0: if matrix[i][j] = 0, P[i][j] = 0;
	 * 		if matrix[i][j] = 1, P[i][j] = min(P[i - 1][j], P[i][j - 1], P[i - 1][j - 1]) + 1.
	 */		
	public int maximalSquare(char[][] a) {
	    if(a.length == 0) return 0;
	    int m = a.length, n = a[0].length, result = 0;
	    int[][] b = new int[m+1][n+1];
	    for (int i = 1 ; i <= m; i++) {
	        for (int j = 1; j <= n; j++) {
	            if(a[i-1][j-1] == '1') {
	                b[i][j] = Math.min(Math.min(b[i][j-1] , b[i-1][j-1]), b[i-1][j]) + 1;
	                result = Math.max(b[i][j], result); // update result
	            }
	        }
	    }
	    return result*result;
	}
	
	//best DP
	//o(n) space
	// Í³Ò» £¿£¿
	public int maximalSquare2(char[][] matrix) {
        if(matrix.length == 0)
            return 0;
        int[] curr = new int[matrix[0].length + 1];
        int upperLeft = 0; //¡ï
        int res = 0; 
        int temp = 0;
        for(int i = 1 ;i <= matrix.length; i++){
            for(int j = 1; j <= matrix[0].length; j++) {
                temp = curr[j];
                if(matrix[i-1][j-1] == '1') {
                    curr[j] = Math.min(Math.min(curr[j-1],upperLeft), curr[j]) + 1;
                    res = Math.max(res,curr[j]);
                }
                else
                    curr[j] = 0;
                upperLeft = temp;
            }
        }
        return res*res;
    }
	
	
}
