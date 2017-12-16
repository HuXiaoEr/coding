package leetcode.binarysearch;
/**
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

Integers in each row are sorted in ascending from left to right.
Integers in each column are sorted in ascending from top to bottom.
For example,

Consider the following matrix:

[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
Given target = 5, return true.

Given target = 20, return false.
 */

// NO TWO
public class N240_Search_a_2D_Matrix_II_M {
	
	//TLE ->  126 / 129 test cases passed
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false; // бя
        int rows = matrix.length-1;
        int cols = matrix[0].length-1;
    	return searchMatrix(matrix, rows, cols, target);
    }
	private boolean searchMatrix(int[][] matrix, int rows, int cols, int target) {
        if(rows < 0 || cols < 0) return false;
		else if(matrix[rows][cols] == target) return true;
		else if(matrix[rows][cols] < target) return false;		
		return searchMatrix(matrix, rows-1, cols, target) || searchMatrix(matrix, rows, cols-1, target);
	}
	
	//Not Mine
	// rule out one row or one column each time
	// O(m+n)
	public boolean searchMatrix2(int[][] matrix, int target) {
        if(matrix == null || matrix.length < 1 || matrix[0].length <1) {
            return false;
        }
        int col = matrix[0].length-1;
        int row = 0;
        while(col >= 0 && row <= matrix.length-1) {
            if(target == matrix[row][col]) {
                return true;
            } else if(target < matrix[row][col]) {
                col--;
            } else if(target > matrix[row][col]) {
                row++;
            }
        }
        return false;
    }
	
	//divide and conquer
	/**
	   First, we divide the matrix into four quarters as shown below:

  		zone 1      zone 2
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
		-----------------------
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
	 	*  *  *  * | *  *  *  *
  		zone 3      zone 4
		We then compare the element in the center of the matrix with the target. There are three possibilities:

		center < target. In this case, we discard zone 1 because all elements in zone 1 are less than target.

		center > target. In this case, we discard zone 4.

		center == target. return true.

		For time complexity, if the matrix is a square matrix of size nxn, then for the worst case,

		T(nxn) = 3T(n/2 x n/2)
		which makes

		T(nxn) = O(n^log3)
	 */
	public boolean searchMatrix3(int[][] matrix, int target) {
	    if(matrix == null || matrix.length == 0 || matrix[0].length == 0) return false;
	    if(matrix.length == 1 && matrix[0].length == 1) return matrix[0][0] == target;
	    return helper(matrix, target, 0, matrix.length-1, 0, matrix[0].length-1);
	}
	public boolean helper(int[][] matrix, int target, int rS, int rE, int cS, int cE) {
	    if(rS < 0 || rE >= matrix.length || cS < 0 || cE >= matrix[0].length || rS > rE || cS > cE) return false;
	    
	    int rM = rS + (rE-rS)/2, cM = cS + (cE-cS)/2;
	    
	    if(matrix[rM][cM] == target) return true;
	    else if(matrix[rM][cM] > target) {
	        return helper(matrix, target, rS, rM-1, cS, cM-1) || helper(matrix, target, rS, rM-1, cM, cE) || helper(matrix, target, rM, rE, cS, cM-1);
	    } else { // matrix[rM][cM] < target
	        return helper(matrix, target, rM+1, rE, cM+1, cE) || helper(matrix, target, rM+1, rE, cS, cM) || helper(matrix, target, rS, rM, cM+1, cE);
	    }
	}
}
