package reviewCode.leetcode;
/**
You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't exceed 100. Determine the perimeter of the island.

Example:

[[0,1,0,0],
 [1,1,1,0],
 [0,1,0,0],
 [1,1,0,0]]

Answer: 16
Explanation: The perimeter is the 16 yellow stripes in the image below:

 */

// YES TWO
public class N463_Island_Perimeter_E {
	int res;
	// my code : dfs
	// 137ms -> beats 82.71%
    public int islandPerimeter(int[][] grid) {
    	boolean[][] visited = new boolean[grid.length][grid[0].length];
        for(int i = 0; i < grid.length; i++){
        	for(int j = 0; j < grid[0].length; j++){
        		if(grid[i][j] == 1) {
        			dfs(grid, i, j, visited);
        			break;
        		}
        	}
        }
    	return res;
    }
	private void dfs(int[][] grid, int row, int col, boolean[][] visited) {
		if(row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] != 1){
			res++;
			return;
		}
		if(visited[row][col]) return;
		visited[row][col] = true;
		dfs(grid, row-1, col, visited);
		dfs(grid, row+1, col, visited);
		dfs(grid, row, col-1, visited);
		dfs(grid, row, col+1, visited);
	}
	
	// another idea
	public int islandPerimeter2(int[][] grid) {
        int islands = 0, neighbours = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }
        return islands * 4 - neighbours * 2;
    }
	public int islandPerimeter3(int[][] grid) {
	    int border = 0;
	    for(int i = 0; i < grid.length; i++) {
	        for(int j = 0; j < grid[i].length; j++) {
	            if(grid[i][j] == 1) {
	                if(i == 0 || grid[i-1][j] == 0) border++; // above water
	                if(j == 0 || grid[i][j-1] == 0) border++; // left water
	                if(j+1 == grid[i].length || grid[i][j+1] == 0) border++; // right water
	                if(i+1 == grid.length || grid[i+1][j] == 0) border++; // below water
	            }
	        }
	    }
	    return border;
	}
}
