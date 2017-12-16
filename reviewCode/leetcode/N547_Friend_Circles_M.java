package reviewCode.leetcode;
/**
There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. 
For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. 
And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. 
If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. 
And you have to output the total number of friend circles among all the students.

Example 1:
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
Note:
N is in range [1,200].
M[i][i] = 1 for all students.
If M[i][j] = 1, then M[j][i] = 1.
 */

// NO TWO
// UF ??
public class N547_Friend_Circles_M {
	
	// Runtime Error Message : Line 53: java.lang.StackOverflowError
    public int findCircleNum(int[][] M) {
    	boolean[][] visited = new boolean[M.length][M[0].length];
    	int res = 0;
        for(int i = 0; i < M.length; i++){
        	for(int j = 0; j < M[0].length; j++){
        		if(M[i][j] == 1 && !visited[i][j]){
        			dfs(M, i, j, visited);
        			res++;
        		}
        	}
        }
    	return res;
    }
	private void dfs(int[][] M, int row, int col, boolean[][] visited) {
		if(row < 0 || row >= M.length || col < 0 || col >= M[0].length || visited[row][col] || M[row][col] == 0) return;
		visited[row][col] = true;
		dfs(M, row-1, col, visited);
		dfs(M, row+1, col, visited);
		dfs(M, row, col-1, visited);
		dfs(M, row, col+1, visited);
	}
	
	public int findCircleNum2(int[][] M) {
        //DFS Solution 
        boolean[] visited = new boolean[M.length];
        int count = 0;
        for(int i = 0; i < M.length; i++) 
            if(!visited[i]) {
                dfs(M, visited, i);
                count++;
            }
        return count;
    }
    private void dfs(int[][] M, boolean[] visited, int i) {
        for(int j = 0; j < M.length; j++) 
            if(M[i][j] == 1 && !visited[j]) {
                visited[j] = true;
                dfs(M, visited, j);
            }
    }
}
