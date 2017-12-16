package leetcode.Greedy;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

/**
There are a number of spherical balloons spread in two-dimensional space. For each balloon, provided input is the start and end coordinates of the horizontal diameter. 
Since it's horizontal, y-coordinates don't matter and hence the x-coordinates of start and end of the diameter suffice. 
Start is always smaller than end. There will be at most 104 balloons.

An arrow can be shot up exactly vertically from different points along the x-axis. A balloon with xstart and xend bursts by an arrow shot at x if xstart ≤ x ≤ xend. 
There is no limit to the number of arrows that can be shot. An arrow once shot keeps travelling up infinitely. 
The problem is to find the minimum number of arrows that must be shot to burst all balloons.

Example:

Input:
[[10,16], [2,8], [1,6], [7,12]]

Output:
2

Explanation:
One way is to shoot one arrow for example at x = 6 (bursting the balloons [2,8] and [1,6]) and another arrow at x = 11 (bursting the other two balloons).
 */

//NO
public class N452_Minimum_Number_of_Arrows_to_Burst_Balloons_M {
	
	//not pass all -> wrong answer
	//reason : sorting by the start is not right 
	//   当[0]相等时，未按[1]排序
    public int findMinArrowShots(int[][] points) {
    	int[] pointStart = new int[points.length];
    	HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < points.length; i++){
        	pointStart[i] = points[i][0];
        	map.put(points[i][0], i);
        }
    	Arrays.sort(pointStart);
    	int start = Integer.MIN_VALUE;
    	int end = Integer.MAX_VALUE;
    	int shots = 1;
    	System.out.println(map);
    	for(int i = 0; i < pointStart.length; i++){
    		int index = map.get(pointStart[i]);
    		//System.out.println(index + " " + points[index][0]);
    		int nextStart = points[index][0];
    		int nextEnd = points[index][1];
    		if(nextStart <= end){
    			start = Math.max(start, nextStart);
    			end = Math.min(end, nextEnd);
    		}
    		else{
    			shots++;
    			nextStart = start;
    			nextEnd = end;
    		}
    		System.out.println(start + " " + points[index][0]);
    	}
    	return shots;
    }
    public static void main(String[] args) {
		int res = new N452_Minimum_Number_of_Arrows_to_Burst_Balloons_M().findMinArrowShots(new int[][]{{10,16},{2,8},{1,6},{7,12}});
		System.out.println(res);
	}
    
    //Not mine code
    /*
     * sort the array of balloons by their ending position
     * 如果[1]相等，不关心start的排序
     * shoot as right as possible
     */
    public int findMinArrowShots2(int[][] points) {
        if (points.length == 0) {
            return 0;
        }
        Arrays.sort(points, (a, b) -> a[1] - b[1]);
        int arrowPos = points[0][1];
        int arrowCnt = 1;
        for (int i = 1; i < points.length; i++) {
            if (arrowPos >= points[i][0]) {
                continue;
            }
            arrowCnt++;
            arrowPos = points[i][1];
        }
        return arrowCnt;
    }
    
    //same idea to mine
	public int findMinArrowShots3(int[][] points) {
		if (points == null || points.length == 0 || points[0].length == 0)
			return 0;
		
		Arrays.sort(points, new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				if (a[0] == b[0])
					return a[1] - b[1];  //★
				else
					return a[0] - b[0];
			}
		});    //★
		
		int minArrows = 1;
		int arrowLimit = points[0][1];
		for (int i = 1; i < points.length; i++) {
			int[] baloon = points[i];
			if (baloon[0] <= arrowLimit) {
				arrowLimit = Math.min(arrowLimit, baloon[1]);
			} else {
				minArrows++;
				arrowLimit = baloon[1];
			}
		}
		return minArrows;
	}
}
