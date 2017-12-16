package leetcode.DP;

/**
You are climbing a stair case. It takes n steps to reach to the top.

Each time you can either climb 1 or 2 steps. 
In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer. 
 */

//Yes
public class N_70_Climbing_Stairs_Easy_DP {
	
	//Mine DP
	public int climbStairs(int n) {
        if(n <= 0) return 0;
        if(n <= 2) return n;
        int n1 = 1;
        int n2 = 2;
        int res = 0;
        for(int i = 3; i <= n; i++)
        {
        	res = n1 + n2;
        	n1 = n2;
        	n2 = res;
        }
		return res;
    }
}
