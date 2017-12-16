package reviewCode.leetcode;

import java.util.Stack;

/**
Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

For example, given the following matrix:

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0
Return 6.
 */

//NO
public class N85_Maximal_Rectangle_H {

	//DP
	//height counts the number of successive '1's above (plus the current one). 
	//The value of left & right means the boundaries of the rectangle which contains the current point with a height of value height.
	//https://discuss.leetcode.com/topic/6650/share-my-dp-solution
	
	
	//based on Largest Rectangle in Histogram
	//https://discuss.leetcode.com/topic/21772/my-java-solution-based-on-maximum-rectangle-in-histogram-with-explanation
	public int maximalRectangle2(char[][] matrix) {
        if (matrix==null||matrix.length==0||matrix[0].length==0) return 0;
        int cLen = matrix[0].length;    // column length
        int rLen = matrix.length;       // row length
        // height array 
        int[] h = new int[cLen+1];
        h[cLen]=0;
        int max = 0;
        for (int row=0;row<rLen;row++) {
            Stack<Integer> s = new Stack<Integer>();
            for (int i=0;i<cLen+1;i++) {
                if (i<cLen)
                    if(matrix[row][i]=='1')
                        h[i]+=1;
                    else h[i]=0;
                
                if (s.isEmpty()||h[s.peek()]<=h[i])
                    s.push(i);
                else {
                    while(!s.isEmpty()&&h[i]<h[s.peek()]){
                        int top = s.pop();
                        int area = h[top]*(s.isEmpty()?i:(i-s.peek()-1));
                        if (area>max)
                            max = area;
                    }
                    s.push(i);
                }
            }
        }
        return max;
    }
}
