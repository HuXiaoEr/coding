package reviewCode.leetcode;

public class N79_Word_Search_M {
	public boolean exist(char[][] board, String word) {
		if(board == null || word == null || board.length == 0 || board[0].length == 0 || word.length()== 0) return false;
		int rows = board.length;
		int cols = board[0].length;
		boolean res = false;
		boolean[][] flag = new boolean[rows][cols];
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < cols; j++){
				if(board[i][j] == word.charAt(0)) res |= backtrack(i, j, rows, cols, 0, board, word, flag);
				if(res) return true;
			}
		}
		return res;
	}
	private boolean backtrack(int r, int c, int rows, int cols, int s, char[][] board, String word, boolean[][] flag) {
		if(s == word.length()) return true;
		if(r >= rows || r < 0 || c >= cols || c < 0 || flag[r][c] || board[r][c] != word.charAt(s)) return false;
		boolean res = false;
		flag[r][c] = true;
		res = backtrack(r-1, c, rows, cols, s+1, board, word, flag)
					|| backtrack(r+1, c, rows, cols, s+1, board, word, flag)
					|| backtrack(r, c-1, rows, cols, s+1, board, word, flag)
					|| backtrack(r, c+1, rows, cols, s+1, board, word, flag);
        flag[r][c] = false;
		return res;
	}
}
