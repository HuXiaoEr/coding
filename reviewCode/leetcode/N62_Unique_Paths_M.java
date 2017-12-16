package reviewCode.leetcode;

//YES
public class N62_Unique_Paths_M {
    public int uniquePaths(int m, int n) {
    	int[][] dp = new int[m][n];
    	return path(0, 0, m, n, dp);
    }
	private int path(int i, int j, int m, int n, int[][]dp) {
		if(i >= m || j >= n) return 0;
		if(i == m-1 && j == n-1) return 1;
		if(dp[i][j] != 0) return dp[i][j];
		dp[i][j] = path(i+1, j, m, n, dp) + path(i, j+1, m, n, dp);
		return dp[i][j];
	}

}
