package JzOffer;

public class N66_回溯法_矩阵中的路径 {
	// over
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
    	boolean[] visited = new boolean[matrix.length];
    	boolean res = false;
    	for(int i = 0; i < rows; i++){
    		for(int j = 0; j < cols; j++){
    			int idx = i * cols + j;  // 乘以 cols
    			if(matrix[idx] == str[0]) res = hasPath(matrix, rows, cols, i, j, 0, str, visited);
    			if(res) return res;
    		}
    	}
    	return res;
    }
	private boolean hasPath(char[] matrix, int rows, int cols, int r, int c, int s, char[] str, boolean[] visited) {
        if(s == str.length) return true;
		int idx = r * cols + c;  // 乘以 cols
		if(r < 0 || r >= rows || c < 0 || c >= cols || visited[idx]) return false;
		visited[idx] = true;
		if(matrix[idx] != str[s]){
			visited[idx] = false;  // 别忘了
			return false;
		}
		boolean res = hasPath(matrix, rows, cols, r-1, c, s+1, str, visited)
				|| hasPath(matrix, rows, cols, r+1, c, s+1, str, visited)
				|| hasPath(matrix, rows, cols, r, c-1, s+1, str, visited)
				|| hasPath(matrix, rows, cols, r, c+1, s+1, str, visited);
		visited[idx] = false;
		return res;
	}
	
	public static void main(String[] args) {
		new N66_回溯法_矩阵中的路径().hasPath(new char[]{'A','B','C','E','S','F','C','S','A','D','E','E'}, 3, 4, new char[]{'A','B','C','C','E','D'});
	}
}
