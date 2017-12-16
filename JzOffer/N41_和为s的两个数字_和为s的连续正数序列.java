package JzOffer;

import java.util.ArrayList;

public class N41_和为s的两个数字_和为s的连续正数序列 {
	
	// over
	//和为s的连续正数序列
	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(sum < 3) return res;
		int small = 1;
		int big = 2;
		int curSum = small + big;
		while(small < ((sum+1) >> 1))
		{
			if(curSum < sum) 
			{
				big++;
				curSum += big;
			}
			else if(curSum == sum)
			{
				ArrayList<Integer> data = new ArrayList<Integer>();
				for(int i = small; i <= big; i++)
				{
					data.add(i);
				}
				res.add(data);
				curSum -= small; // 别忘了
				small++;  // 别忘了
			}
			else
			{
				curSum -= small;
				small++;
			}
		}
		return res;
    }
	
	// over
	//鲁棒性
	//和为s的两个数字
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(array.length < 3) return res;
        int bigIndex = array.length-1;
        int smallIndex = 0;
        int curSum = 0;
        //int multi = array[bigIndex] * array[bigIndex];
        while(smallIndex < bigIndex)
        {
        	//System.out.println(small + " " + big);
        	//System.out.println(curSum + "***");
        	curSum = array[smallIndex] + array[bigIndex];
        	if(curSum < sum){
                smallIndex++;
                
        	}
        	else if(curSum == sum)
        	{
        		//unnecessary
        		/*if(array[smallIndex] * array[bigIndex] < multi)
        		{
        			res.add(array[smallIndex]);
        			res.add(array[bigIndex]);
        			multi = array[smallIndex] * array[bigIndex];
        		}
        		smallIndex++;
    			bigIndex--;*/
        		/**
        		 * 
					找到的第一组（相差最大的）就是乘积最小的。
					可以这样证明：考虑x+y=C（C是常数），x*y的大小。
					不妨设y>=x，y-x=d>=0，
					即y=x+d, 2x+d=C, x=(C-d)/2, x*y=x(x+d)=(C-d)(C+d)/4=(C^2-d^2)/4，
					也就是x*y是一个关于变量d的二次函数，对称轴是y轴，开口向下。d是>=0的，d越大, x*y也就越小。
        		 */
        		res.add(array[smallIndex]);
    			res.add(array[bigIndex]);
    			break;
        	}
        	else
        	{
        		bigIndex--;
        	}
        }
        return res;
    }
	
	public static void main(String[] args) {
		System.out.println(new N41_和为s的两个数字_和为s的连续正数序列().FindNumbersWithSum(new int[]{1,2,4,7,11,15}, 15));
	}
}
