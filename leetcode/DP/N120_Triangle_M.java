package leetcode.DP;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
Given a triangle, find the minimum path sum from top to bottom. 
Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

Note:
Bonus point if you are able to do this using only O(n) extra space, 
where n is the total number of rows in the triangle.
 */

//YES
public class N120_Triangle_M {
	
	//Mine DP
	//2D DP o(n*n)space
	public int minimumTotal(List<List<Integer>> triangle) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		for(int i = 0; i < triangle.size(); i++){
			List<Integer> listtemp = new ArrayList<>();
			for(int j = 0; j < triangle.get(i).size(); j++){
				int temp = 0;
				if(i == 0){
					temp = triangle.get(i).get(j);
				}
				else{
				    temp = triangle.get(i).get(j);
					if(j > 0 && j < res.get(i-1).size()) temp += Math.min(res.get(i-1).get(j-1), res.get(i-1).get(j));
					else if(j > 0) temp += res.get(i-1).get(j-1);
					else temp += res.get(i-1).get(j);
				}
				listtemp.add(temp);
			}
			res.add(listtemp);
		}
		int min = res.get(res.size()-1).get(0);
		for(int i = 0; i < res.get(res.size()-1).size(); i++)
		{
			min = Math.min(min, res.get(res.size()-1).get(i));
		}
		return min;
    }
	
	//Mine 1D DP o(n)space
	public int minimumTotal2(List<List<Integer>> triangle) {
		int[] res = new int[triangle.size()];
		
		for(int i = 0; i < triangle.size(); i++){
			for(int j = triangle.get(i).size()-1; j >= 0; j--){
				int temp = triangle.get(i).get(j);
				if(j > 0 && j < triangle.get(i).size()-1) res[j] = temp + Math.min(res[j], res[j-1]);
				else if(j == 0) res[j] = temp + res[j];
				else res[j] = temp + res[j-1];
			}
		}
		int min = res[0];
		for(int i = 1; i < res.length; i++){
			min = Math.min(min, res[i]);
		}
		return min;
    }
	
	//best  o(n)space
	//bottom to up
		//minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];
		//For the kth level:
		//minpath[i] = min( minpath[i], minpath[i+1]) + triangle[k][i]; 
	//cleaner
	public int minimumTotal3(List<List<Integer>> triangle) {
	    int[] A = new int[triangle.size()+1];
	    for(int i=triangle.size()-1;i>=0;i--){
	        for(int j=0;j<triangle.get(i).size();j++){
	            A[j] = Math.min(A[j],A[j+1])+triangle.get(i).get(j);
	        }
	    }
	    return A[0];
	}
	
	//o(1)space
	public int minimumTotal5(List<List<Integer>> triangle) {
        for(int i = triangle.size() - 2; i >= 0; i--)
            for(int j = 0; j <= i; j++)
                triangle.get(i).set(j, triangle.get(i).get(j) + Math.min(triangle.get(i + 1).get(j), triangle.get(i + 1).get(j + 1)));
        return triangle.get(0).get(0);
    }
	
	//BFS
	public int minimumTotal4(List<List<Integer>> triangle) {
	    Deque<Integer> queue = new LinkedList<Integer>();
	    int count=triangle.size();
	    queue.add(triangle.get(0).get(0));
	    for (int i=1;i<count;i++){
	        List<Integer> list= triangle.get(i);
	        for (int j=0;j<=i;j++){
	        	int min=0;
	            if (j==0)
	            	 min=list.get(0)+queue.peekFirst();               	
	            else if (j==i)
	            	 min =list.get(j)+queue.pollFirst();              	
	            else
	            	min = Math.min(queue.pollFirst(),queue.peekFirst())+list.get(j);              	               
	            queue.addLast(min);               
	        }
	    }
	    int result=Integer.MAX_VALUE;
	    for (int i=0;i<count;i++)
	    	result=Math.min(result, queue.pollFirst());
	    return result;
	}
	
	
	public static void main(String[] args) {
		List<Integer> list1 = new ArrayList();
		List<Integer> list2 = new ArrayList();
		list1.add(1);
		list2.add(2);
		list2.add(3);
		List<List<Integer>> tr = new ArrayList<>();
		tr.add(list1);
		tr.add(list2);
		System.out.println(new N120_Triangle_M().minimumTotal(tr));
	}
}
