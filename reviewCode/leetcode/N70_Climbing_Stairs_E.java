package reviewCode.leetcode;

public class N70_Climbing_Stairs_E {
	public int climbStairs(int n) {
		if(n < 1) return 0;
		if(n == 1) return 1;
		if(n == 2) return 2;
		int res = 0;
		int n1 = 1, n2 = 2;
		for(int i = 3; i <= n; i++){
			res = n1 + n2;
			n1 = n2; 
			n2 = res;
		}
		return res;
	}
}
