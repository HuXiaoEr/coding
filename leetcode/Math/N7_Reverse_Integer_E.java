package leetcode.Math;
/**
Reverse digits of an integer.

Example1: x = 123, return 321
Example2: x = -123, return -321

click to show spoilers.

Have you thought about this?
Here are some good questions to ask before coding. Bonus points for you if you have already thought through this!

If the integer's last digit is 0, what should the output be? ie, cases such as 10, 100.

Did you notice that the reversed integer might overflow? 
Assume the input is a 32-bit integer, then the reverse of 1000000003 overflows. How should you handle such cases?

For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.

Note:
The input is assumed to be a 32-bit signed integer. 
Your function should return 0 when the reversed integer overflows.
 */

// NO
// how to judge the overflow
public class N7_Reverse_Integer_E {
	
	// my code 
	// bad
	// bad performance
    public static int reverse12(int x) {
        if(x == -2147483648) return 0;  // attention : -x = x;
        long res = 0;
        boolean neg = false;
        if(x < 0){
        	neg = true;
        	x = -x;
        }
        while(x != 0){
        	int rem = x % 10;
        	res = res * 10 + rem;
        	x /= 10;
        	if((!neg && res > Integer.MAX_VALUE) || (neg && -res < Integer.MIN_VALUE)) return 0;
        }
        if(neg) res = -res;
        return (int)res;
    }

    // not my code
	public int reverse(int x) {
		int result = 0;
		while (x != 0) {
			int tail = x % 10;
			int newResult = result * 10 + tail;
			if ((newResult - tail) / 10 != result)
				return 0;
			result = newResult;
			x = x / 10;
		}
		return result;
	}
	
	public int reverse2(int x) {
        long rev= 0;
        while( x != 0){
            rev= rev*10 + x % 10;
            x= x/10;
            if( rev > Integer.MAX_VALUE || rev < Integer.MIN_VALUE)
                return 0;
        }
        return (int) rev;
    }
}
