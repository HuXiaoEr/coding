package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
The n-queens puzzle is the problem of placing n queens on an nб┴n chessboard such that no two queens attack each other.



Given an integer n, return all distinct solutions to the n-queens puzzle.

Each solution contains a distinct board configuration of the n-queens' placement, 
where 'Q' and '.' both indicate a queen and an empty space respectively.

For example,
There exist two distinct solutions to the 4-queens puzzle:

[
 [".Q..",  // Solution 1
  "...Q",
  "Q...",
  "..Q."],

 ["..Q.",  // Solution 2
  "Q...",
  "...Q",
  ".Q.."]
]

 */

//NO
public class N51_N_Queens_H {
	
	
	private void helper(int r, boolean[] cols, boolean[] d1, boolean[] d2, String[] board, List<String[]> res) {
		if (r == board.length)
			res.add(board.clone());
		else {
			for (int c = 0; c < board.length; c++) {
				int id1 = r - c + board.length, id2 = 2 * board.length - r - c - 1;
				if (!cols[c] && !d1[id1] && !d2[id2]) {
					char[] row = new char[board.length];
					Arrays.fill(row, '.');
					row[c] = 'Q';
					board[r] = new String(row);
					cols[c] = true;
					d1[id1] = true;
					d2[id2] = true;
					helper(r + 1, cols, d1, d2, board, res);
					cols[c] = false;
					d1[id1] = false;
					d2[id2] = false;
				}
			}
		}
	}
	public List<String[]> solveNQueens3(int n) {
		List<String[]> res = new ArrayList<>();
		helper(0, new boolean[n], new boolean[2 * n], new boolean[2 * n], new String[n], res);
		return res;
	}
	
	
	public List<List<String>> solveNQueens(int n) {
        boolean[] 
            //ocp0 = new boolean[n], //whether there's a queen ocupying nth row, I don't need it
            ocp90 = new boolean[n], //whether there's a queen ocupying nth column
            ocp45 = new boolean[2 * n - 1], // mark 45 degree occupation
            ocp135 = new boolean[2 * n - 1]; // mark 135 degree occupation
        List<List<String>> ans = new ArrayList<List<String>>();
        char[][] map = new char[n][n];
        for (char[] tmp : map) Arrays.fill(tmp, '.'); //init
        
        solve(0, n, map, ans, ocp45, ocp90, ocp135);
        return ans;
    }
    private void solve(int depth, int n, char[][] map, List<List<String>> ans, 
    boolean[] ocp45, boolean[] ocp90, boolean[] ocp135) {
        if (depth == n) {
            addSolution(ans, map);
            return;
        }
 //When reach [row, col], the column No. is col, the 45бу diagonal No. is row + col and the 135бу diagonal No. is n - 1 + col - row
        for (int j = 0; j < n; j++)
            if (!ocp90[j] && !ocp45[depth + j] && !ocp135[j - depth + n - 1]) {
                ocp90[j] = true;
                ocp45[depth + j] = true;
                ocp135[j - depth + n - 1] = true;
                map[depth][j] = 'Q';
                solve(depth + 1, n, map, ans, ocp45, ocp90, ocp135);
                ocp90[j] = false;
                ocp45[depth + j] = false;
                ocp135[j - depth + n - 1] = false;
                map[depth][j] = '.';
            }
    }
    private void addSolution(List<List<String>> ans, char[][] map) {
        List<String> cur = new ArrayList<String>();
        for (char[] i : map) cur.add(String.valueOf(i));
        ans.add(cur);
    }
    
    //space optimization
    /*
     * flag[0] to flag[n - 1] to indicate if the column had a queen before.
     * flag[n] to flag[3 * n - 2] to indicate if the 45бу diagonal had a queen before.
     * flag[3 * n - 1] to flag[5 * n - 3] to indicate if the 135бу diagonal had a queen before.
     */
    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> res=new ArrayList<List<String>>();
        String[] queens=new String[n];
        char[] initial=new char[n];
        Arrays.fill(initial,'.');
        Arrays.fill(queens,String.valueOf(Arrays.copyOf(initial,n)));
        int[] flag=new int[5*n-2];
        Arrays.fill(flag,1);
        slove(res,queens,flag,0,n);
        return res;
    }
    private void slove(List<List<String>> res,String[] queens,int[] flag,int row,int n){
        if(row==n){
            res.add(new ArrayList<String>(Arrays.asList(queens)));
            return;
        }
        for(int col=0;col!=n;col++){
            if(flag[col]==1 && flag[n+col+row]==1 && flag[4*n-2+col-row]==1){
                flag[col]=0;
                flag[n+col+row]=0;
                flag[4*n-2+col-row]=0;
                char[] chars=queens[row].toCharArray();
                
                chars[col]='Q';
                queens[row]=String.valueOf(chars);
                
                slove(res,queens,flag,row+1,n);
                
                chars=queens[row].toCharArray();
                chars[col]='.';
                queens[row]=String.valueOf(chars);
                
                flag[col]=1;
                flag[n+col+row]=1;
                flag[4*n-2+col-row]=1;
            }
        }
    }
}
