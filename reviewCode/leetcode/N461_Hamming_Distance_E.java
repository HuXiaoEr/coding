package reviewCode.leetcode;
/**
The Hamming distance between two integers is the number of positions at which the corresponding bits are different.

Given two integers x and y, calculate the Hamming distance.

Note:
0 ¡Ü x, y < 231.

Example:

Input: x = 1, y = 4

Output: 2

Explanation:
1   (0 0 0 1)
4   (0 1 0 0)
       ?   ?

The above arrows point to positions where the corresponding bits are different.
 */


public class N461_Hamming_Distance_E {
    public int hammingDistance(int x, int y) {
        int res = 0;
        for(int i = 0; i < 32; i++){
        	int xtem = (1 << i) & x;
        	int ytem = (1 << i) & y;
        	if(xtem != ytem) res++;
        }
    	return res;
    }
}
