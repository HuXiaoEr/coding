package reviewCode.leetcode;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

// NO TWO
public class N494_Target_Sum_M {
    public int findTargetSumWays(int[] nums, int s) {
    	if(s < 0) s = -s;
    	int[][] dp = new int[nums.length+1][1001];
    	dp[0][0] = 1;
    	for(int i = 1; i <= nums.length; i++){
    		for(int j = 0; j <= s; j++){
    			dp[i][j] += dp[i-1][j+nums[i-1]];
    			dp[i][j] += dp[i-1][Math.abs(j-nums[i-1])];
    		}
    	}
    	
    	return dp[nums.length][s];
    }
    public static void main(String[] args) {
    	LinkedList<String> a = new LinkedList<>();
    	a.add(null);
    	Hashtable<String, String> ha = new Hashtable<>();
    	CopyOnWriteArrayList<String> c = new CopyOnWriteArrayList<>();
    	//ha.put("s", null);
    	System.out.println("****");
    	//ReentrantReadWriteLock
    	//PriorityBlockingQueue
    	// TreeMap
    	// FutureTask
    	// AbstractQueuedSynchronizer
    	// LockSupport
	}
}
