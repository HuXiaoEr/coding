package JzOffer;

public class N9_斐波那契数列_青蛙跳台阶_变态跳台阶 {
	
	// over
	public int Fibonacci(int n) {
		if(n < 2) return n;
		int pre = 0, in = 1, res = 0;
		for(int i = 2; i <= n; i++)
		{
			res = pre + in;
			pre = in;
			in = res;
		}
		return res;
    }
	
	// 青蛙跳台阶
	// 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloor(int n) {
		if(n <= 2) return n;
    	int pre = 1;
    	int now = 2;
    	int temp = 0;
    	for(int i = 3; i <= n; i++){
    		temp = now;
    		now += pre;
    		pre = temp;
    	}
    	return now;
    }
	
    // 变态跳台阶
    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        //return (int)Math.pow(2, target-1);  // 注意强转
        return 1 << --target;
    }
    
	/*
	 * 青蛙可以跳1级台阶，也可以跳2级...也可以跳n级，则青蛙跳上一个n级台阶共有多少种跳法？
	 * f(n) = 2 ^ (n-1)
	 * 
	 * 分析：
	 * f(n-1) = f(0) + f(1)+f(2)+f(3) + ... + f((n-1)-1) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2)
	 * f(n) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1) = f(n-1) + f(n-1)
	 * 可以得出：
	 * f(n) = 2*f(n-1)
	 * 
	 *  -> f(n) = 2 ^ (n-1)
	 */
}
