package leetcode.DP;


/**
Follow up for "Unique Paths":

Now consider if some obstacles are added to the grids. How many unique paths would there be?

An obstacle and empty space is marked as 1 and 0 respectively in the grid.

For example,
There is one obstacle in the middle of a 3x3 grid as illustrated below.

[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
The total number of unique paths is 2.

Note: m and n will be at most 100.
 */

//YES
public class N63_Unique_Paths_II_M {
	
	//Mine DP
	//o(m*n) space
	public int uniquePathsWithObstacles(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
        int[][] res = new int[m][n];
        res[0][0] = 1;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				if(obstacleGrid[i][j] == 1) res[i][j] = 0;
				else
				{
					if(i-1 >= 0) res[i][j] += res[i-1][j];
					if(j-1 >= 0) res[i][j] += res[i][j-1];
				}
			}
		}
		return res[m-1][n-1];
    }
	
	//Mine DP
	//o(m) space
	public int uniquePathsWithObstacles2(int[][] obstacleGrid) {
		int m = obstacleGrid.length;
		int n = obstacleGrid[0].length;
        int[] res = new int[m];
        res[0] = 1;
        for(int i = 0; i < n; i++)
        {
        	for(int j = 0; j < m; j++)
        	{
        		if(obstacleGrid[j][i] == 1) res[j] = 0;
        		else if(j >= 1) res[j] += res[j-1];
        	}
        }
		return res[m-1];
    }
	
	//better
	//o(1) space
	public int uniquePathsWithObstacles3(int[][] obstacleGrid) {
        //Empty case
        if(obstacleGrid.length == 0) return 0;
        int rows = obstacleGrid.length;
        int cols = obstacleGrid[0].length;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = 0;
                else if(i == 0 && j == 0)
                    obstacleGrid[i][j] = 1;
                else if(i == 0)
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] * 1;// For row 0, if there are no paths to left cell, then its 0,else 1
                else if(j == 0)
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] * 1;// For col 0, if there are no paths to upper cell, then its 0,else 1
                else
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }
        return obstacleGrid[rows - 1][cols - 1];
    }
}
