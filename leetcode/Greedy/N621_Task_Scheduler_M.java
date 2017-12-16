package leetcode.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
Given a char array representing tasks CPU need to do. It contains capital letters A to Z where different letters represent different tasks.
Tasks could be done without original order. Each task could be done in one interval. For each interval, CPU could finish one task or just be idle.

However, there is a non-negative cooling interval n that means between two same tasks, 
there must be at least n intervals that CPU are doing different tasks or just be idle.

You need to return the least number of intervals the CPU will take to finish all the given tasks.

Example 1:
Input: tasks = ['A','A','A','B','B','B'], n = 2
Output: 8
Explanation: A -> B -> idle -> A -> B -> idle -> A -> B.
Note:
The number of tasks is in the range [1, 10000].
The integer n is in the range [0, 100].
 */

//NO TWO
public class N621_Task_Scheduler_M {
	
	//best
	public int leastInterval(char[] tasks, int n) {
        int[] c = new int[26];
        for(char t : tasks){
            c[t - 'A']++;
        }
        Arrays.sort(c);
        int i = 25;
        while(i >= 0 && c[i] == c[25]) i--;
        return Math.max(tasks.length, (c[25] - 1) * (n + 1) + 25 - i); //бя
    }
	
	/*
	 * Similar problem Rearrange string K distance apart
	 * 	 -> We need to arrange the characters in string such that each same character is K distance apart.
	 * 
	 * Idea is to add them to a priority Q and sort based on the highest frequency.
	 * And pick the task in each round of 'n' with highest frequency. 
	 * As you pick the task, decrease the frequency, and put them back after the round.
	 */
    public int leastInterval2(char[] tasks, int n) {
        Map<Character, Integer> counts = new HashMap<Character, Integer>();
        for (char t : tasks) {
            counts.put(t, counts.getOrDefault(t, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        pq.addAll(counts.values());
        int alltime = 0;
        int cycle = n + 1;
        while (!pq.isEmpty()) {
            int worktime = 0;
            List<Integer> tmp = new ArrayList<Integer>();
            for (int i = 0; i < cycle; i++) {
                if (!pq.isEmpty()) {
                    tmp.add(pq.poll());
                    worktime++;
                }
            }
            for (int cnt : tmp) {
                if (--cnt > 0) {
                    pq.offer(cnt);
                }
            }
            alltime += !pq.isEmpty() ? cycle : worktime;
        }
        return alltime;
    }
    
    public static void main(String[] args) {
		//leastInterval("AAAABBBEEFFGG".toCharArray(), 3);
	}
}
