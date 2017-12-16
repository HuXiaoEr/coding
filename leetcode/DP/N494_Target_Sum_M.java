package leetcode.DP;

/**
You are given a list of non-negative integers, a1, a2, ..., an, and a target, S. 
Now you have 2 symbols + and -. For each integer, 
you should choose one from + and - as its new symbol.

Find out how many ways to assign symbols to make sum of integers equal to target S.

Example 1:
Input: nums is [1, 1, 1, 1, 1], S is 3. 
Output: 5
Explanation: 

-1+1+1+1+1 = 3
+1-1+1+1+1 = 3
+1+1-1+1+1 = 3
+1+1+1-1+1 = 3
+1+1+1+1-1 = 3

There are 5 ways to assign symbols to make the sum of nums be target 3.
Note:
The length of the given array is positive and will not exceed 20.
The sum of elements in the given array will not exceed 1000.
Your output answer is guaranteed to be fitted in a 32-bit integer.
 */

//DFS YES
//DP No
//DP NO TWO
//DFS NO TWO
//N416_Partition_Equal_Subset_Sum_M
public class N494_Target_Sum_M {
	
	//Mine DFS
	//Bad
	public int findTargetSumWays(int[] nums, int S) {
        if(nums == null || nums.length == 0) return 0;
		return findTargetSumWays(nums, S, 0, 0);
    }
	private int findTargetSumWays(int[] nums, int s, int length, int sum) {
		if(length >= nums.length) 
		{
			if(sum == s) return 1;
			return 0;
		}
		int res = 0;
		res += findTargetSumWays(nums, s, length+1, sum - nums[length]);
		res += findTargetSumWays(nums, s, length+1, sum + nums[length]);
		//System.out.println(length + " " + sumL + " " + sumR + " " + res );
		return res;
	}
	/*
	 * Not Right
	private int findTargetSumWays(int[] nums, int s, int length, int sum) {
		if(length >= nums.length) return 0;
		int res = 0;
		int sumL = sum - nums[length];
		int sumR = sum + nums[length];
		if(sumL == s) res++;  //Not Right
		if(sumR == s) res++;  //Not Right
		res += findTargetSumWays(nums, s, length+1, sumL);
		res += findTargetSumWays(nums, s, length+1, sumR);
		System.out.println(length + " " + sumL + " " + sumR + " " + res );
		return res;
	}
	*/
	public static void main(String[] args) {
		System.out.println(new N494_Target_Sum_M().findTargetSumWays(new int[]{1,1,1,1,1,}, 3));
	}


	
/*
this is a classic knapsack problem
in knapsack, we decide whether we choose this element or not
in this question, we decide whether we add this element or minus it

So start with a two dimensional array dp[i][j] which means the number of ways for first i-th element to reach a sum j

we can easily observe that dp[i][j] = dp[i-1][j+nums[i]] + dp[i-1][j-nums[i]],

further observation is that each row is only effected by the last row, we can reduce a two dimensional array to two single arrays
dp[i] means the number of ways to reach a sum i

Another part which is quite confusing is return value, here we return dp[sum+S], why is that?
because dp's range starts from -sum-->0-->sum
so we need to add sum first, then the total starts from 0, then we add S

Actually most of Sum problems can be treated as knapsack problem, hope it helps
*/
	
	//better
	//knapsack problem
	//2D DP knapsack 
	/*
	The following is a 2D DP knapsack (with array index translation: from [-sum... sum] to [0, 2 * sum] ) solution. 
	It can be converted to 1D DP by rolling arrays or two single arrays easily. 
	Hope it can help understand above 1D DP solution.
	*/
	public int findTargetSumWays2(int[] nums, int S) {
        if (nums == null) { return 0; }
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (S < -sum || S > sum) { return 0;}
        int n = nums.length;
        int[][] f = new int[n + 1][2 * sum + 1];
        f[0][0 + sum] = 1;//It seems like make no sense, but it is the base value;
        for (int i = 1; i < n + 1; i++) {
            //Option 1: easy to understand
            for (int j = 0; j < 2 * sum + 1; j++) {
                //f[i][j] = f[i - 1][j - nums[i - 1]] + f[i - 1][j + nums[i - 1]]; 
                if (j - nums[i - 1] >= 0) {
                    f[i][j] += f[i - 1][j - nums[i - 1]];
                }
                if (j + nums[i - 1] <= 2 * sum) {
                    f[i][j] += f[i - 1][j + nums[i - 1]];
                }
            }
            
            /*//Option 2: efficient but we should think in a reverse way
            for (int j = nums[i - 1]; j < 2 * sum + 1 - nums[i - 1]; j++) {
            //for (int j = 0; j < 2 * sum + 1; j++) {//It also works
                /*if (f[i - 1][j] > 0) {
                    //the trick is we should do calculation in a reverse way: 
                    //add f[i - 1][j] to f[i - 1][j +/- nums[i - 1]] only when f[i - 1][j] != 0
                    f[i][j - nums[i - 1]] += f[i - 1][j];
                    f[i][j + nums[i - 1]] += f[i - 1][j];
                }*/
        }
        return f[n][sum + S];
	}
	
	
	//better
	//knapsack problem
	//1D DP knapsack 
	public int findTargetSumWays3(int[] nums, int s) {
        int sum = 0; 
        for(int i: nums) sum+=i;
        if(s>sum || s<-sum) return 0;
        int[] dp = new int[2*sum+1];
        dp[0+sum] = 1;
        for(int i = 0; i<nums.length; i++){
            int[] next = new int[2*sum+1];
            for(int k = 0; k<2*sum+1; k++){
            	
                if(dp[k]!=0){//
                    next[k + nums[i]] += dp[k];
                    next[k - nums[i]] += dp[k];
                }
                //or
//              if(k+nums[i] < 2*sum+1) next[k] += dp[k+nums[i]];
//              if(k-nums[i] >= 0) next[k] += dp[k-nums[i]];
            }
            dp = next;
        }
        return dp[sum+s];
    }
	
	//DP
	//subset sum problem
	//N416_Partition_Equal_Subset_Sum_M
/*
The recursive solution is very slow, because its runtime is exponential

The original problem statement is equivalent to:
Find a subset of nums that need to be positive, and the rest of them negative, such that the sum is equal to target

Let P be the positive subset and N be the negative subset
For example:
Given nums = [1, 2, 3, 4, 5] and target = 3 then one possible solution is +1-2+3-4+5 = 3
Here positive subset is P = [1, 3, 5] and negative subset is N = [2, 4]

Then let's see how this can be converted to a subset sum problem:

                  sum(P) - sum(N) = target
	sum(P) + sum(N) + sum(P) - sum(N) = target + sum(P) + sum(N)
                       2 * sum(P) = target + sum(nums)
So the original problem has been converted to a subset sum problem as follows:
Find a subset P of nums such that sum(P) = (target + sum(nums)) / 2

Note that the above formula has proved that target + sum(nums) must be even
We can use that fact to quickly identify inputs that do not have a solution (Thanks to @BrunoDeNadaiSarnaglia for the suggestion)
For detailed explanation on how to solve subset sum problem, you may refer to Partition Equal Subset Sum

Here is Java solution (15 ms)
 */
	public int findTargetSumWays4(int[] nums, int s) {
        int sum = 0;
        for (int n : nums)
            sum += n;
        return sum < s || (s + sum) % 2 > 0 ? 0 : subsetSum(nums, (s + sum) >>> 1); 
    }   

    public int subsetSum(int[] nums, int s) {
        int[] dp = new int[s + 1]; 
        dp[0] = 1;
        for (int n : nums)
            for (int i = s; i >= n; i--)
                dp[i] += dp[i - n]; 
        return dp[s];
    }
}
