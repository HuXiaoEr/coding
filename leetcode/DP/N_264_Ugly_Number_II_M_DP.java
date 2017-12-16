package leetcode.DP;

import java.util.TreeSet;

/**
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of 
the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, 
and n does not exceed 1690.

 */

//Yes
public class N_264_Ugly_Number_II_M_DP {
	
	//Mine 
	//best
	public int nthUglyNumber(int n) {
		if(n <= 0) return 0;
        int index2 = 0;
        int index3 = 0;
        int index5 = 0;
        int[] uglyNum = new int[n];
        uglyNum[0] = 1;
        for(int i = 1; i < n; i++)
        {
        	uglyNum[i] = min(2*uglyNum[index2], 3*uglyNum[index3], 5*uglyNum[index5]);
        	if(uglyNum[i] == 2*uglyNum[index2]) index2++;
        	if(uglyNum[i] == 3*uglyNum[index3]) index3++;
        	if(uglyNum[i] == 5*uglyNum[index5]) index5++;
        }
		return uglyNum[n-1];
    }
	private int min(int i, int j, int k) {
		int min = i < j ? i : j;
		return min < k ? min : k;
	}
	
	//Not Mine
	public int nthUglyNumber2(int n) {
		TreeSet<Long> ans = new TreeSet<>();
		ans.add(1L);
		for (int i = 0; i < n - 1; ++i) {
			long first = ans.pollFirst();
			ans.add(first * 2);
			ans.add(first * 3);
			ans.add(first * 5);
		}
		return ans.first().intValue();
	}

}
