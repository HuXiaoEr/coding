package leetcode.DP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
Given a positive integer n, 
find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.

For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
given n = 13, return 2 because 13 = 4 + 9.
 */

//YES
public class N_279_Perfect_Squares_M {

	//Mine DP
	//Bad bad performance
	public int numSquares(int n) {
        int[] dp = new int[n+1];
        for(int i = 1; i <= n; i++){
        	int min = 0;
        	if(square(i)) min = 1;
        	else
        		for(int j = i-1; j > 0; j--){
        			min = min == 0 ? dp[j]+dp[i-j] : Math.min(min, dp[j]+dp[i-j]);
        		}
        	dp[i] = min;
        }
		return dp[n];
    }
	private boolean square(int num) {
		for(int i = 1; i <= num; i++){
			if(i*i > num) return false;
			else if(i*i == num) return true;
		}
		return false;
	}
	
	//below is not mine
	
	//better DP
	public int numSquares2(int n) {
		int[] dp = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		for(int i = 1; i <= n; ++i) 
			for(int j = 1; j*j <= i; j++)
				dp[i] = Math.min(dp[i], dp[i - j*j] + 1);		
		return dp[n];
	}
	//static DP
	static List<Integer> result = new ArrayList<>();
    public int numSquares4(int n) {
        if (result.size() == 0) {
            result.add(0);
        }
        while (result.size() <= n) {
            int m = result.size();
            int tempMin = Integer.MAX_VALUE;
            for (int j = 1; j * j <= m; j++) {
                tempMin = Math.min(tempMin, result.get(m - j * j) + 1);
            }
            result.add(tempMin);
        }
        return result.get(n);
    }

	//BFS
    public int numSquares5(int n) {
        Queue<Integer> q = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        q.offer(0);
        visited.add(0);
        int depth = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            depth++;
            while(size-- > 0) {
                int u = q.poll();
                for(int i = 1; i*i <= n; i++) {
                    int v = u+i*i;
                    if(v == n) {
                        return depth;
                    }
                    if(v > n) {
                        break;
                    }
                    if(!visited.contains(v)) {
                        q.offer(v);
                        visited.add(v);
                    }
                }
            }
        }
        return depth;
    }
	
	//Mathematical
	//https://discuss.leetcode.com/topic/23846/4ms-c-code-solve-it-mathematically
	//https://discuss.leetcode.com/topic/24014/o-sqrt-n-2-applying-fermat-s-theorm-with-brahmagupta-fibonacci-identity
	
}
