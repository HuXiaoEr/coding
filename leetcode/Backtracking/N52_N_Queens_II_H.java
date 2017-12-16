package leetcode.Backtracking;
/**
Follow up for N-Queens problem.

Now, instead outputting board configurations, return the total number of distinct solutions.

 */

//YES
public class N52_N_Queens_II_H {
	
	//my code
    public int totalNQueens(int n) {
        return dfs(0, n, new boolean[n], new boolean[2*n], new boolean[2*n]);
    }
	private int dfs(int depth, int n, boolean[] cols, boolean[] degree45, boolean[] degree135) {
		if(depth == n) return 1;  //not return 0
		int res = 0;
		for(int i = 0; i < n; i++){
			if(!cols[i] && !degree45[depth+i] && !degree135[n-1+i-depth]){
				cols[i] = degree45[depth+i] = degree135[n-1+i-depth] = true;
				res += dfs(depth+1, n, cols, degree45, degree135);
				cols[i] = degree45[depth+i] = degree135[n-1+i-depth] = false;
			}
		}
		return res;
	}
	
	//same idea 
	//bitmap
	int count = 0;
	public int totalNQueens2(int n) {
		dfs(0, n, 0, 0, 0);
		return count;
	}
	private void dfs(int row, int n, int column, int diag, int antiDiag) {
		if (row == n) {
			++count;
			return;
		}
		for (int i = 0; i < n; ++i) {
			boolean isColSafe = ((1 << i) & column) == 0;
			boolean isDiagSafe = ((1 << (n - 1 + row - i)) & diag) == 0;
			boolean isAntiDiagSafe = ((1 << (row + i)) & antiDiag) == 0;
			if (isColSafe && isDiagSafe && isAntiDiagSafe) {
				dfs(row + 1, n, (1 << i) | column, (1 << (n - 1 + row - i)) | diag, (1 << (row + i)) | antiDiag);
			}
		}
	}
}
