package JzOffer;

// over
public class N67_回溯法_机器人的运动范围 {
	
	
	int res = 0;
    public int movingCount(int threshold, int rows, int cols)
    {
    	boolean[][] visited = new boolean[rows][cols];
    	movingCount(threshold, 0, 0, rows, cols, visited);
    	return res;
    }
	private void movingCount(int threshold, int r, int c, int rows, int cols, boolean[][] visited) {
		if(r < 0 || r >= rows || c < 0 || c >= cols || visited[r][c]) return;
		visited[r][c] = true;
		if(sum(r, c) > threshold){
			//visited[r][c] = false;  // 注意
			return;
		}
		res++;
		movingCount(threshold, r+1, c, rows, cols, visited);
		movingCount(threshold, r-1, c, rows, cols, visited);
		movingCount(threshold, r, c+1, rows, cols, visited);
		movingCount(threshold, r, c-1, rows, cols, visited);
		//visited[r][c] = false;  // 注意
		return;
	}
	private int sum(int r, int c) {
		int res = 0;
		while(r != 0){
			res += (r % 10);
			r /= 10;
		}
		while(c != 0){
			res += (c % 10);
			c /= 10;
		}
		return res;
	}
	
	////////////////////////////////////////////
	boolean[][] visited;
	public int movingCount2(int threshold, int rows, int cols)
    {
		visited = new boolean[rows][cols];
		return count(threshold, rows, cols, 0, 0);
    }
	private int count(int threshold, int rows, int cols, int i, int j) {
		if(i < 0 || j < 0 || i >= rows || j >= cols || visited[i][j] == true || (sum(i) + sum(j)) > threshold) return 0;
		visited[i][j] = true;
		int res = 1;
		res += count(threshold, rows, cols, i-1, j) 
				+ count(threshold, rows, cols, i+1, j)
				+ count(threshold, rows, cols, i, j-1)
				+ count(threshold, rows, cols, i, j+1);
		return res;
	}
	private int sum(int i) {
		int res = 0;
		while(i > 0)
		{
			res += i % 10;
			i = i / 10;
		}
		return res;
	}
}
