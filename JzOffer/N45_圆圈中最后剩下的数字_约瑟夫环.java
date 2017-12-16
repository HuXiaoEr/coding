package JzOffer;

import java.util.ArrayList;

public class N45_圆圈中最后剩下的数字_约瑟夫环 {

	// best
    public int LastRemaining_Solution(int n, int m) {
		if(m <= 0 || n <= 0) return -1;
		int res = 0;
		for(int i = 2; i <= n; i++){
			res = (res+m) % i;
		}
		return res;
	}
    
    /*
	 * 这道题我用数组来模拟环，思路还是比较简单，但是各种下标要理清 more clean than mine
	 */
	public static int findLastNumber2(int n, int m) {
		if (n < 1 || m < 1)
			return -1;
		int[] array = new int[n];
		int i = -1, step = 0, count = n;
		while (count > 0) { // 跳出循环时将最后一个元素也设置为了-1 important       
			i++; // 指向上一个被删除对象的下一个元素。            
			if (i >= n)
				i = 0; // 模拟环。            
			if (array[i] == -1)
				continue; // 跳过被删除的对象。            
			step++; // 记录已走过的。            
			if (step == m) { // 找到待删除的对象。
				array[i] = -1;
				step = 0;
				count--;
			}
		}
		return i; // 返回跳出循环时的i,即最后一个被设置为-1的元素   
	}
    
	public int LastRemaining_Solution3(int n, int m) {
		// donnot forget
		if (n < 1 || m < 1)
			return -1;
		int[] data = new int[n];
		for (int i = 0; i < n; i++) {
			data[i] = i;
		}
		int i = 0;
		int count = 0;
		int round = 0;
		while (round < n - 1) {
			if (data[i] == -1) {
				i = (i + 1) % n;
			} else {
				if (count == m - 1) {
					data[i] = -1;
					i = (i + 1) % n;
					count = 0;
					round++;
				} else {
					count++;
					i = (i + 1) % n;
				}

			}
		}
		for (int j = 0; j < n; j++) {
			// System.out.print(data[j] + " ");
			if (data[j] != -1)
				return data[j];
		}
		return -1;
	}

	public static void main(String[] args) {
		// System.out.println(new N_45().LastRemaining_Solution(5, 3));
		System.out.println((4) % (-7));
		System.out.println(1 + 2 % 3);
	}

	
}
