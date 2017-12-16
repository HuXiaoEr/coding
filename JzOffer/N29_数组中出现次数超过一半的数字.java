package JzOffer;

/**
 * 特殊情况：
 * （1）数组为空、数组长度为0
 * （2）数组中没有出现次数超过一半的元素  isMoreThanHalf()
 * （3）partition()函数
 * 
 * 基于partition()的方法会修改输入的数组
 */

// over
public class N29_数组中出现次数超过一半的数字 {
	
	//方法二：
	//better
	public int MoreThanHalfNum_Solution2(int [] array) {
		if(array == null || array.length == 0) 
		{
			isNotValidInput = true;
			return 0;
		}
        int res = 0;
        int count = 0;
        for(int i = 0; i < array.length; i++)
        {
        	if(count == 0)
        	{
        		res = array[i];
        		count = 1;
        		continue;
        	}
        	if(array[i] == res) count++;
        	else count--;
        }
        if(isMoreThanHalf(array, res)) return res;
		isNotValidInput = true;
		return 0;
    }
	
	//方法一：
	boolean isNotValidInput; //全局变量标识输出是否有效
	public int MoreThanHalfNum_Solution(int [] array) {
		if(array == null || array.length == 0)
		{
			isNotValidInput = true;
			return 0;
		}
        
		int index = partition(array, 0, array.length-1);
		int mid = array.length >> 1;
		System.out.println(index);
		while(index != mid)
		{
			System.out.println(index);
			if(index < mid) index = partition(array, index+1, array.length-1);
			else index = partition(array, 0, index-1);
		}
		if(isMoreThanHalf(array, array[mid])) return array[mid];
		isNotValidInput = true;
		return 0;
    }
	
	private boolean isMoreThanHalf(int[] array, int num) {
		// TODO Auto-generated method stub
		int count = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(array[i] == num) count++;
		}
		if((count << 1) > array.length) return true;
		return false;
	}

	private int partition(int[] array, int lo, int hi) {
		int small = lo;
		for(int i = lo; i <= hi; i++)
		{
			if(array[i] < array[lo])
			{
				small++;
				if(small != i) exch(array, small, i);
			}
		}
		exch(array, small, lo);
		return small;
	}
	private void exch(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j]= temp;
	}
	
	/**
	 * careful when 数组元素个数小于2（0，1）
	 * isn't right in this problem when array is [1, 2, 2]
	 */
	private int partition2(int[] array, int lo, int hi) {
		int i = lo;
		int j = hi+1;
		while(true)
		{
			while(array[++i] < array[lo])
				if(i == hi) break;
			while(array[--j] > array[lo])
				if(j == lo) break;
			if(i >= j) break;
			exch(array, i, j);
		}
		exch(array, lo, j);
		return j;
	}
	
	public static void main(String[] args) {
		System.out.println(new N29_数组中出现次数超过一半的数字().MoreThanHalfNum_Solution(new int[]{1,2,2}));
	}
}
