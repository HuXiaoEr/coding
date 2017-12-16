package JzOffer;

// over
public class N34_丑数 {
	
    //best
    public int GetUglyNumber_Solution2(int index)
    {
    	if(index <= 0) return 0;
    	int[] uglyData = new int[index];
    	uglyData[0] = 1;
    	int index2 = 0;
    	int index3 = 0;
    	int index5 = 0;
    	for(int i = 1; i < index; i++)
    	{
    		int temp = min(2*uglyData[index2], 3*uglyData[index3], 5*uglyData[index5]);
    		if(temp == 2*uglyData[index2]) index2++;//三条if防止值是一样的，不要改成else的
    		if(temp == 3*uglyData[index3]) index3++;//三条if防止值是一样的，不要改成else的
    		if(temp == 5*uglyData[index5]) index5++;//三条if防止值是一样的，不要改成else的
    		uglyData[i] = temp;
    	}
    	return uglyData[index-1];
    }
	private int min(int i, int j, int k) {
		int min = i < j ? i : j;
		return min < k ? min : k;
	}

	
	//mine not best
    public int GetUglyNumber_Solution(int index) {
    	if(index <= 0) return 0;
    	int[] uglyData = new int[index];
    	uglyData[0] = 1;
    	int[] num = new int[3];
    	num[0] = 2;
    	num[1] = 3;
    	num[2] = 5;
    	for(int i = 1; i < uglyData.length; i++)
    	{
    		int minMax = 2*uglyData[i-1];
    		for(int j = 0; j < 3; j++)
    		{
    			for(int k = 0; k < i; k++)
    			{
    				int temp = num[j]*uglyData[k];
    				if(temp > uglyData[i-1] && temp < minMax) minMax = temp;
    			}
    		}
    		uglyData[i] = minMax;
    	}
        return uglyData[index-1];
    }
    public static void main(String[] args)
    {
    	System.out.println(new N34_丑数().GetUglyNumber_Solution(10));
    }
    
}
