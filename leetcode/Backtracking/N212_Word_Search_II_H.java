package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
The same letter cell may not be used more than once in a word.

For example,
Given words = ["oath","pea","eat","rain"] and board =

[
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
Return ["eat","oath"].
Note:
You may assume that all inputs are consist of lowercase letters a-z.

click to show hint :

You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?

If the current candidate does not exist in all words' prefix, you could stop backtracking immediately. 
What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not? How about a Trie? 
If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree) first.
 */

//NO
public class N212_Word_Search_II_H {

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//mine code -> TLE
	public List<String> findWords(char[][] board, String[] words) {
		Set<String> res = new HashSet<>();   //in case of words has duplicated string
		for(int i = 0; i < words.length; i++){
			if(exist(board, words[i])) res.add(words[i]);
		}
		return new ArrayList<String>(res);
    }
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
		visited[i][j] = false;
		return false;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	//Not Mine
	// Step 1: build up the Trie structure
	// Step 2: search for words starting from each cell on the board and record the results
	public List<String> findWords2(char[][] board, String[] words) {
	    List<String> res = new ArrayList<>();
	    TrieNode root = buildTrie(words);
	    for (int i = 0; i < board.length; i++) {
	        for (int j = 0; j < board[0].length; j++) {
	            dfs (board, i, j, root, res);
	        }
	    }
	    return res;
	}
	public void dfs(char[][] board, int i, int j, TrieNode p, List<String> res) {
	    char c = board[i][j];
	    if (c == '#' || p.next[c - 'a'] == null) return;
	    p = p.next[c - 'a'];
	    if (p.word != null) {   // found one  ★
	        res.add(p.word); 
	        p.word = null;     // de-duplicate  ★
	    }
	    board[i][j] = '#';
	    if (i > 0) dfs(board, i - 1, j ,p, res); 
	    if (j > 0) dfs(board, i, j - 1, p, res);
	    if (i < board.length - 1) dfs(board, i + 1, j, p, res); 
	    if (j < board[0].length - 1) dfs(board, i, j + 1, p, res); 
	    board[i][j] = c;
	}
	public TrieNode buildTrie(String[] words) {
	    TrieNode root = new TrieNode();
	    for (String w : words) {
	        TrieNode p = root;
	        for (char c : w.toCharArray()) {  // w.toCharArray() ★
	            int i = c - 'a';
	            if (p.next[i] == null) p.next[i] = new TrieNode();
	            p = p.next[i];
	       }
	       p.word = w;
	    }
	    return root;
	}
	class TrieNode {
	    TrieNode[] next = new TrieNode[26];
	    String word;
	}
}
