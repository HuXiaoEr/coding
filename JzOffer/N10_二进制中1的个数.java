package JzOffer;

// over
public class N10_二进制中1的个数 {
	
	// 负数右移一位之后，最高位补0
	//mine, not best
	public int NumberOf1(int n) {
		int res = 0;
		while(n != 0)  //not n > 0;	 careful n < 0  ★
		{
			if((n & 1) == 1) res++;
			n = n >>> 1;
		}
		return res;
    }
	
	// 注意与第一个方法的不同
	// 负数右移一位之后，最高位补0
	public int NumberOf1_1(int n) {
		int res = 0;
		int flag = 1;
		// or
		// while(flag != 0)
		while(flag > 0)  // 注意 while条件
		{
			if((n & flag) != 0) res++;
			flag = flag << 1;
		}
		return res;
    }
	
	//best
	public int NumberOf1_2(int n) {
		int res = 0;
		while(n != 0)
		{
			res++;
			n = (n-1) & n; // important
		}
		return res;
    }
}
