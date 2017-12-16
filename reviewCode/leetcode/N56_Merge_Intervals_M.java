package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

 */

//YES
 class Interval {
     int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
 }
public class N56_Merge_Intervals_M {
	
	//根据start排序
	//然后分三种情况讨论 ： 1.next.start > p1  2.next.end < p1  3.else
	//注意hashmap的问题
	//Collections.sort(...) ★
	//my code 麻烦！！！  beats 30%
	public List<Interval> merge2(List<Interval> intervals) {
        List<Interval> res = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) return res;
    	int[] st = new int[intervals.size()];
    	//hashmap 键不允许重复  当intervals.get(i).start容易出错
        HashMap<Integer, List<Interval>> hash = new HashMap<>();
        for(int i = 0; i < intervals.size(); i++){
        	if(!hash.containsKey(intervals.get(i).start))
        		hash.put(intervals.get(i).start, new ArrayList<Interval>());
        	hash.get(intervals.get(i).start).add(intervals.get(i));
        	st[i] = intervals.get(i).start;
        }
        Arrays.sort(st);
        int p0 = hash.get(st[0]).get(0).start;
        int p1 = hash.get(st[0]).get(0).end;
		for (int i = 0; i < st.length; i++) {
			for (Interval temp : hash.get(st[i])) {
				if (temp.start > p1) {
					res.add(new Interval(p0, p1));
					p0 = temp.start;
					p1 = temp.end;
				} else if (temp.end < p1) {
					continue;
				} else {
					p1 = temp.end;
				}
			}
		}
		res.add(new Interval(p0, p1));
		return res;
    }
	
	//best
	//beats 80%
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
        if(intervals == null || intervals.size() == 0) return res;
        Collections.sort(intervals, new Comparator<Interval>(){
        	public int compare(Interval i1, Interval i2){
        		return i1.start - i2.start;
        	}
        });
        int start = intervals.get(0).start;
        int end = intervals.get(0).end;
        
        for (Interval interval : intervals) {
            if (interval.start <= end) // Overlapping intervals, move the end if needed
                end = Math.max(end, interval.end);
            else {                     // Disjoint intervals, add the previous one and reset bounds
                res.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        
        // Add the last interval
        res.add(new Interval(start, end));
        return res;
	}
}
