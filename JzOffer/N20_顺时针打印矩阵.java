package JzOffer;

import java.util.ArrayList;

// over
public class N20_˳ʱ���ӡ���� {
	public ArrayList<Integer> printMatrix(int [][] matrix) {
		ArrayList<Integer> res = new ArrayList<Integer>();
		if(matrix == null) return res;
		int rows = matrix.length;
		int cols = matrix[0].length;
		int start = 0;
		while(rows > 2 * start && cols > 2 * start) // ע��ѭ����������
		{
			printCLW(matrix, rows, cols, start, res);
			start++;
		}
		return res;
    }

	// over
	private void printCLW(int[][] matrix, int rows, int cols, int start, ArrayList<Integer> res) {
		
		//ע�� i ��ѭ������
		//�����Ҵ�ӡ
		for(int i = start; i <= cols-1-start; i++)
		{
			res.add(matrix[start][i]);
		}
		
		//ע�� i ��ѭ������
		//���ϵ��´�ӡ
		for(int i = start+1; i <= rows-1-start; i++)
		{
			res.add(matrix[i][cols-1-start]);
		}
		
		//ע�� i ��ѭ������
		//���ҵ����ӡ С���ظ���ӡ eg:ֻ��һ��
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
		
		//ע�� i ��ѭ������
		//���µ��ϴ�ӡ С���ظ���ӡ eg:ֻ��һ��
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
