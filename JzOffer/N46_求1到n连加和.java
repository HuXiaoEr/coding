package JzOffer;

/**
 * 
 * @author 胡小二
 * 
 *求1+2+3+...+n，
 *要求不能使用乘除法、for、while、if、else、switch、case等关键字
 *及条件判断语句（A?B:C）。
 */

// 均over
public class N46_求1到n连加和 {
	
	/*
	 * 1.需利用逻辑与的短路特性实现递归终止。
	 * 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
	 * 3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
	 * 
	 * 这题剑指offer上说有用构造函数来解的，但是在java中：
	 * temp[] test = new temp[n]
	 * 这样的初始化，并不会调用构造函数
	 */
	public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }
	
	/*
	 * Math的幂函数
	 */
	public int Sum_Solution2(int n) {
		n = (int) (Math.pow(n, 2)+n)>>1;
		return n;
    }
}
