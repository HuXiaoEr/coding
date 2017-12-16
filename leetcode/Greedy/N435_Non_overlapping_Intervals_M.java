package leetcode.Greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
Given a collection of intervals, find the minimum number of intervals you need to remove to make the rest of the intervals non-overlapping.

Note:
You may assume the interval's end point is always bigger than its start point.
Intervals like [1,2] and [2,3] have borders "touching" but they don't overlap each other.
Example 1:
Input: [ [1,2], [2,3], [3,4], [1,3] ]

Output: 1

Explanation: [1,3] can be removed and the rest of intervals are non-overlapping.
Example 2:
Input: [ [1,2], [1,2], [1,2] ]

Output: 2

Explanation: You need to remove two [1,2] to make the rest of intervals non-overlapping.
Example 3:
Input: [ [1,2], [2,3] ]

Output: 0

Explanation: You don't need to remove any of the intervals since they're already non-overlapping.
 */

//NO
public class N435_Non_overlapping_Intervals_M {
	  public class Interval {
		      int start;
		      int end;
		      Interval() { start = 0; end = 0; }
		      Interval(int s, int e) { start = s; end = e; }
		  }
	
	/*
	 * same as "Given a collection of intervals, find the maximum number of intervals that are non-overlapping." 
	 * (the classic Greedy problem: Interval Scheduling).
	 */
	public int eraseOverlapIntervals(Interval[] intervals) {
        if (intervals.length == 0)  return 0;

        Arrays.sort(intervals, new myComparator());
        int end = intervals[0].end;
        int count = 1;        

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i].start >= end) {
                end = intervals[i].end;
                count++;
            }
        }
        return intervals.length - count;
    }
    class myComparator implements Comparator<Interval> {
        public int compare(Interval a, Interval b) {
            return a.end - b.end;
        }
    }
    
    //same algs  -> sort by the start
    public int eraseOverlapIntervals2(Interval[] intervals) {
    	if(intervals==null || intervals.length==0) return 0;
    	Arrays.sort(intervals, new Comparator<Interval>() {
    		public int compare(Interval i1, Interval i2) {
    			return i1.start-i2.start;
    		}
    	});
    	int count=1;
    	int lastEnd = intervals[0].end;
    	for(int i=1;i<intervals.length;i++) {
    		Interval currentInterval = intervals[i];
    		if(currentInterval.start>=lastEnd) {
    			count++;
    			lastEnd=currentInterval.end;
    		} else {
    			lastEnd=Math.min(currentInterval.end,lastEnd);
    		}
    	}
    	return intervals.length-count;
    }
}
