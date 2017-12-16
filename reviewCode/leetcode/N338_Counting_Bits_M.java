package reviewCode.leetcode;
/**
Given a non negative integer number num. 
For every numbers i in the range 0 ¡Ü i ¡Ü num calculate the number of 1's in their binary representation and return them as an array.

Example:
For num = 5 you should return [0,1,1,2,1,2].

Follow up:

	It is very easy to come up with a solution with run time O(n*sizeof(integer)). 
But can you do it in linear time O(n) /possibly in a single pass?
	Space complexity should be O(n).
	Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */

// YES
public class N338_Counting_Bits_M {
    public int[] countBits(int num) {
    	int[] res = new int[num+1];
        for(int i = 0; i < 32; i++){
        	for(int j = 0; j <= num; j++){
        		if((j & (1 << i)) != 0) res[j]++;
        	}
        }
    	return res;
    }
}
