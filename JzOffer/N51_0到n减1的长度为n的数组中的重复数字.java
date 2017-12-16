package JzOffer;

/**
 * 
 * @author 胡小二
 *
 *在一个长度为n的数组里的所有数字都在0到n-1的范围内。 
 *数组中某些数字是重复的，但不知道有几个数字是重复的。也不知道每个数字重复几次。
 *请找出数组中任意一个重复的数字。 
 *例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是重复的数字2或者3。
 */
// over
public class N51_0到n减1的长度为n的数组中的重复数字 {
	
	/*
	 * o(n)time o(1)space
	 * 每个数字最多只要交换两次就可以放到正确位置
	 */
	public boolean duplicate1(int numbers[],int length,int [] duplication) {
	    if(numbers == null || numbers.length != length || numbers.length < 2) return false;
		int i = 0;
	    while(i < length)
		{
			if(numbers[i] == i) i++;
			else if(numbers[numbers[i]] == numbers[i])
			{
				duplication[0] = numbers[i];
				return true;
			}
			else
			{
				int index2 = numbers[i];
				int temp = numbers[i];
				numbers[i] = numbers[index2];
				numbers[index2] = temp;
			}
		}
		return false;
    }
	
	
	
	public boolean duplicate2(int numbers[],int length,int [] duplication) {
	    if(numbers == null || numbers.length != length || numbers.length < 2) return false;
		int[] data = new int[length];
		for(int i = 0; i < length; i++)
		{
			data[numbers[i]]++;
			if(data[numbers[i]] > 1)
			{
				duplication[0] = numbers[i];
				return true;
			}
		}
		return false;
    }
	
	public static void main(String[] args) {
		N51_0到n减1的长度为n的数组中的重复数字 n51 = new N51_0到n减1的长度为n的数组中的重复数字();
		int[] p = new int[1];
		n51.duplicate2(new int[]{1,2,3,1,4}, 5, p);
		System.out.println(p[0]);
	}
}
