package leetcode.DP;

/**
Given a m x n grid filled with non-negative numbers, 
find a path from top left to bottom right which minimizes the sum of all numbers along its path.

Note: You can only move either down or right at any point in time.
 */

//YES
public class N64_Minimum_Path_Sum_M {
	
	//Mine DP
	public int minPathSum(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        for(int i = 0; i < rows; i++){
        	for(int j = 0; j < cols; j++){
        		if(i > 0 && j > 0){
        			if(grid[i-1][j] > grid[i][j-1]) grid[i][j] += grid[i][j-1];
        			else grid[i][j] += grid[i-1][j];
        		}
        		else if(i > 0) grid[i][j] += grid[i-1][j];
        		else if(j > 0) grid[i][j] += grid[i][j-1];
        	}
        }
		return grid[rows-1][cols-1];
    }
}
