package leetcode.Backtracking;
/**
Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, 
where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

For example,
Given board =

[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
word = "ABCCED", -> returns true,
word = "SEE", -> returns true,
word = "ABCB", -> returns false.
 */

//NO
public class N79_Word_Search_M {

	/*
	 * I think the time complexity is (mn*4^k) where k is the length of the string; mn for for loop and for the dfs method its 4^k. 
	 * Since the dfs method goes only as deep as the word length we have T(k)=4(T(k-1))=4*4T(k-2)=....=.. which will be 4^k.
	 */
    public boolean exist(char[][] board, String word) {
    	int rows = board.length;
    	int cols = board[0].length; //aaaa
    	boolean[][] visited = new boolean[rows][cols];
    	for(int i = 0; i < rows; i++){
    		for(int j = 0; j < cols; j++){
    			if(board[i][j] == word.charAt(0)){
    				if(exist(board, word, i, j, 0, rows, cols, visited)) return true;
    			}
    		}
    	}
    	return false;
    }
	private boolean exist(char[][] board, String word, int i, int j, int idx, int rows, int cols, boolean[][] visited) {
		if (idx >= word.length())
			return true;   //attention : 在第一行
		if (i < 0 || i >= rows || j < 0 || j >= cols || visited[i][j] || board[i][j] != word.charAt(idx))
			return false;

		//board[y][x] ^= 256;  to save the space of boolean
		visited[i][j] = true;
		if (exist(board, word, i - 1, j, idx + 1, rows, cols, visited)
				|| exist(board, word, i + 1, j, idx + 1, rows, cols, visited)
				|| exist(board, word, i, j - 1, idx + 1, rows, cols, visited)
				|| exist(board, word, i, j + 1, idx + 1, rows, cols, visited))
			return true;
		//board[y][x] ^= 256;  to save the space of boolean
		visited[i][j] = false;  //别忘记这行
		return false;
	}
}
