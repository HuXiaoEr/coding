package JzOffer;

import java.util.Arrays;

public class N44_扑克牌的顺子 {

	public static void main(String[] args) {
		//System.out.println(isContinuous(new int[]{0,1,2,1,3}));
	}

		//数字可能重复
		/**
		 * 鲁棒性：
		 * 1.判断数组长度是否为5
		 * 2.判断数组元素是否在[0-13]之间  two
		 * 
		 * 使用额外空间hash表判断是否重复
		 * 
		 * max 记录 最大值 
		 * min 记录  最小值 
		 * min ,max 都不记0 
		 * 满足条件 
		 *   1 max - min<5         
		 * 	 2 除0外没有重复的数字(牌)   
		 * 	 3 数组长度为5
		 */
		public boolean isContinuous2(int [] numbers) 
		{ 
			int[] d = new int[14];
			d[0] = -5;
			int len = numbers.length;
			int max = -1;
			int min = 14;
			for(int i =0;i<len;i++)
			{
				d[numbers[i]]++;
				if(numbers[i] == 0) continue;
				if(d[numbers[i]]>1) return false;
				if(numbers[i] >max) max = numbers[i];
				if(numbers[i] <min) min = numbers[i];
			}
			if(max -min<5) return true;
			return false; 
		}
		
		// my code
	    public boolean isContinuous(int [] numbers) {
	        if(numbers == null || numbers.length == 0) return false;
	    	int zero = 0;
	    	Arrays.sort(numbers);
	    	int internal = 0;
	        if(numbers[0] == 0) zero++;
	    	for(int i = 1; i < numbers.length; i++){
	    		if(numbers[i] == 0) {
	    			zero++;
	    			continue;
	    		}
	    		if(numbers[i] == numbers[i-1] && numbers[i] != 0) return false;
	    		if(numbers[i-1] != 0) internal += numbers[i]-numbers[i-1]-1;
	    	}
	    	return zero >= internal;
	    }
		
		
		/**
		 * bitmap sorting 用bit做标记,用二进制位来判断是否有数字重复
		 * 
		 * 必须满足两个条件
		 * 	 1. 除0外没有重复的数
		 * 	 2. max - min < 5
		 */
		public boolean isContinuous3(int [] numbers) 
		{
			if(numbers.length != 5) return false;
			int min = 14;
			int max = -1;
			int flag = 0;
			for(int i = 0; i < numbers.length; i++) 
			{
				int number = numbers[i];
				if(number < 0 || number > 13) return false;
				if(number == 0) continue;
				
				//bitmap sorting 用bit做标记,用二进制位来判断是否有数字重复
				if(((flag >> number) & 1) == 1) return false;
				flag |= (1 << number);
				
				if(number > max) max = number;
				if(number < min) min = number;
				if(max - min >= 5) return false;
			}
			return true;
		}


}
