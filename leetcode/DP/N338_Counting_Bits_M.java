package leetcode.DP;
/**
Given a non negative integer number num. 
For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
Space complexity should be O(n).
Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */

// YES TWO
//i & 1 is o(1)
public class N338_Counting_Bits_M {
	
	// my code 
	// bit manipulation
	public int[] countBits5(int num) {
    	int[] res = new int[num+1];
        for(int i = 0; i < 32; i++){
        	for(int j = 0; j <= num; j++){
        		if((j & (1 << i)) != 0) res[j]++;
        	}
        }
    	return res;
    }
	
	/*
	 * Take number X for example, 10011001.
	 * Divide it in 2 parts:
	 * <1>the last digit ( 1 or 0, which is " i&1 ", equivalent to " i%2 " )
	 * <2>the other digits ( the number of 1, which is " f[i >> 1] ", equivalent to " f[i/2] " )
	 */
	public int[] countBits(int num) {
	    int[] f = new int[num + 1];
	    for (int i=1; i<=num; i++) f[i] = f[i >> 1] + (i & 1);
	    return f;
	}
	
	/*
	 * i&(i-1) drops the lowest set bit.
	 * For example: i = 14, its binary representation is 1110, so i-1 is 1101, i&(i-1) = 1100, 
	 * the number of "1" in its binary representation decrease one, so ret[i] = ret[i&(i-1)] + 1. 
	 */
	public int[] countBits2(int num) {
	    int[] f = new int[num + 1];
	    for (int i=1; i<=num; i++) f[i] = f[i&(i-1)] + 1;
	    return f;
	}
	
	/*
	 * 减去最高位的1
	 * dp[index] = dp[index - offset] + 1;
	 * https://discuss.leetcode.com/topic/40195/how-we-handle-this-question-on-interview-thinking-process-dp-solution/2
	 */
	public int[] countBits3(int num) {
        int[] answer = new int[num+1];
        int offset = 1;
        for(int i = 1; i < answer.length; i++){
            if(offset * 2 == i) offset *= 2;
            answer[i] = 1 + answer[i - offset];
        }
        return answer;
    }

}
