package leetcode.DP;

/**
A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

The robot can only move either down or right at any point in time. 
The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

How many possible unique paths are there?

Note: m and n will be at most 100.
 */


public class N62_Unique_Paths_M {
	
	//Mine Backtracking
	//Bad
	//Time Limit Exceeded
	boolean[][] visited;
	public int uniquePaths(int m, int n) {
		visited = new boolean[m][n];
		return uniquePaths(m, n, 0, 0);
    }
	private int uniquePaths(int row, int col, int r, int c) {
		if(r < 0 || c < 0 || r >= row || c >= col || visited[r][c]) return 0;
		if(r == row-1 && c == col-1) return 1;
		visited[r][c] = true;
		int res = 0;
		res += uniquePaths(row, col, r+1, c) + uniquePaths(row, col, r, c+1);
		visited[r][c] = false;
		return res;
	}
	
	//Mine DP
	//o(n*m) space
	public int uniquePaths2(int m, int n) {
		int[][] res = new int[m+1][n+1];
		res[1][1] = 1;
		//×óÓÒ  -- ÉÏÏÂ
		for(int i = 1; i <= m; i++){
			for(int j = 1; j <= n; j++){
                if(i == 1 && j == 1) continue;
				res[i][j] = res[i-1][j] + res[i][j-1];
			}
		}
		return res[m][n];
    }
	//Mine DP
	public int uniquePaths6(int m, int n) {
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
	
	//better DP space optimize
	//o(m) space
	public static int uniquePaths5(int m, int n) {
	    int[] dp = new int[m];
	    for(int j = 0; j < n; j++)
	    {
	    	dp[0] = 1; //¡ï¡ï  initialization dp[0][j-1] = 1
	        for(int i = 1; i < m; i++)
	            dp[i] = dp[i-1] + dp[i]; //calculate dp[i][j]
	    }
	    return dp[m-1];
	}
	public static void main(String[] args) {
		System.out.println(uniquePaths5(2, 2));
	}
	//using formula
	//best
	int uniquePaths3(int m, int n) {
        int N = n + m - 2;// how much steps we need to do
        int k = m - 1; // number of steps that need to go down
        double res = 1;
        // here we calculate the total possible path number 
        // Combination(N, k) = N! / (k!(N - k)!)
        // reduce the numerator and denominator and get
        // C = ( (N - k + 1) * (N - k + 2) * ... * N ) / k!
        for (int i = 1; i <= k; i++)
            res = res * (N - k + i) / i;
        return (int)res;  // ?? (int)Math.round(res);
    }
}
