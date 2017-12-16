package JzOffer;

import java.util.ArrayList;

// over
public class N20_顺时针打印矩阵 {
	public ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(matrix == null) return res;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int start = 0;
		while(rows > 2 * start && cols > 2 * start) // 注意循环结束条件
		{
			printCLW(matrix, rows, cols, start, res);
			start++;
		}
		return res;
    }

	// over
	private void printCLW(int[][] matrix, int rows, int cols, int start, ArrayList<Integer> res) {
		
		//注意 i 的循环条件
		//从左到右打印
		for(int i = start; i <= cols-1-start; i++)
		{
			res.add(matrix[start][i]);
		}
		
		//注意 i 的循环条件
		//从上到下打印
		for(int i = start+1; i <= rows-1-start; i++)
		{
			res.add(matrix[i][cols-1-start]);
		}
		
		//注意 i 的循环条件
		//从右到左打印 小心重复打印 eg:只有一行
		for(int i = cols-2-start; i >= start && rows-1-start != start; i--)
		{
			res.add(matrix[rows-1-start][i]);
		}
		/**
		for(int i = cols-2-start; i >= start; i--)
		{
			res.add(matrix[rows-1-start][i]);
		}
		*/
		
		//注意 i 的循环条件
		//从下到上打印 小心重复打印 eg:只有一列
		for(int i = rows-2-start; i >= start+1 && cols-1-start != start; i--)
		{
			res.add(matrix[i][start]);
		}
		/**
		for(int i = rows-2-start; i >= start+1; i--)
		{
			res.add(matrix[i][start]);
		}
		*/
	}
}
