package bytedance;

public class Longest_Increasing_Path_in_a_Matrix {
	// TLE -> 134 / 137 test cases passed
	int res;
	public int longestIncreasingPath0(int[][] arr) {
        if(arr == null || arr.length == 0 || arr[0].length == 0) return 0;
        boolean[][] visi = new boolean[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
        	for(int j = 0; j < arr[0].length; j++){
        		res = Math.max(res, dfs(i, j, arr, visi));
        	}
        }
		return res;
    }
	private int dfs(int r, int c, int[][] arr, boolean[][] visi) {
		if(r < 0 || r > arr.length || c < 0 || c > arr[0].length || visi[r][c]) return 0;
		visi[r][c] = true;
		int res = 1;
		int max = 0;
		if(r-1 >= 0 && arr[r][c] > arr[r-1][c]) max = Math.max(max, dfs(r-1, c, arr, visi));
		if(r+1 < arr.length && arr[r][c] > arr[r+1][c]) max = Math.max(max, dfs(r+1, c, arr, visi)); 
		if(c-1 >= 0 && arr[r][c] > arr[r][c-1]) max = Math.max(max, dfs(r, c-1, arr, visi));
		if(c+1 < arr[0].length && arr[r][c] > arr[r][c+1]) max = Math.max(max, dfs(r, c+1, arr, visi));
		visi[r][c] = false;
		return res + max;
	}
	
	// 40 ms
	// time o(mn) Every element in the matrix can be only computed once cuz we use the cache.
	// space o(mn)
	// cache
	public int longestIncreasingPath(int[][] arr) {
        if(arr == null || arr.length == 0 || arr[0].length == 0) return 0;
        boolean[][] visi = new boolean[arr.length][arr[0].length];
        int[][] cach = new int[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++){
        	for(int j = 0; j < arr[0].length; j++){
        		res = Math.max(res, dfs(i, j, arr, visi, cach));
        	}
        }
		return res;
    }
	private int dfs(int r, int c, int[][] arr, boolean[][] visi, int[][] cach) {
		if(r < 0 || r > arr.length || c < 0 || c > arr[0].length || visi[r][c]) return 0;
		if(cach[r][c] != 0) return cach[r][c];
		visi[r][c] = true;
		int res = 1;
		int max = 0;
		if(r-1 >= 0 && arr[r][c] > arr[r-1][c]) max = Math.max(max, dfs(r-1, c, arr, visi, cach));
		if(r+1 < arr.length && arr[r][c] > arr[r+1][c]) max = Math.max(max, dfs(r+1, c, arr, visi, cach)); 
		if(c-1 >= 0 && arr[r][c] > arr[r][c-1]) max = Math.max(max, dfs(r, c-1, arr, visi, cach));
		if(c+1 < arr[0].length && arr[r][c] > arr[r][c+1]) max = Math.max(max, dfs(r, c+1, arr, visi, cach));
		visi[r][c] = false;
		cach[r][c] = res + max;
		return res + max;
	}
}
