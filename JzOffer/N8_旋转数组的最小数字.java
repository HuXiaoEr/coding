package JzOffer;

/*
 * 原始数组为递增排序
 * 数组元素可以重复
 */
public class N8_旋转数组的最小数字 {
	
	// best
	/*
采用二分法解答这个问题，
mid = low + (high - low)/2
需要考虑三种情况：
(1)array[mid] > array[high]:
出现这种情况的array类似[3,4,5,6,0,1,2]，此时最小数字一定在mid的右边。
low = mid + 1
(2)array[mid] == array[high]:
出现这种情况的array类似 [1,0,1,1,1] 或者[1,1,1,0,1]，此时最小数字不好判断在mid左边
还是右边,这时只好一个一个试 ，
high = high - 1
(3)array[mid] < array[high]:
出现这种情况的array类似[2,2,3,4,5,6,6],此时最小数字一定就是array[mid]或者在mid的左
边。因为右边必然都是递增的。
high = mid
注意这里有个坑：如果待查询的范围最后只剩两个数，那么mid 一定会指向下标靠前的数字
比如 array = [4,6]
array[low] = 4 ;array[mid] = 4 ; array[high] = 6 ;
如果high = mid - 1，就会产生错误， 因此high = mid
但情形(1)中low = mid + 1就不会错误
	 */
	
	public int minNumberInRotateArray(int[] array) {
		int low = 0;
		int high = array.length - 1;
		// or while(low <= high)
		while (low < high) {
			int mid = low + (high - low) / 2;
			if (array[mid] > array[high]) {
				low = mid + 1;
			} else if (array[mid] == array[high]) {
				high = high - 1;
			} else {
				high = mid;
			}
		}
		return array[low];
	}

	// mine
    public static int minNumberInRotateArray2(int [] array) {
        if(array == null || array.length == 0) return 0;
        int lo = 0;
        int hi = array.length-1;
        while(lo < hi){
        	if(hi-lo == 1) return array[hi];  // ★
            int mid = lo + (hi-lo)/2;
            if(array[mid] == array[lo] && array[mid] == array[hi]){
                int min = array[lo];
                for(int i = lo; i <= hi; i++){
                    if(array[i] < min) min = array[i];
                }
                return min;
            }
            if(array[mid] >= array[lo]) lo = mid; //
            else if(array[mid] <= array[hi]) hi = mid; //
        }
        return array[lo];
    }
	
	
	boolean isValid;
	public int minNumberInRotateArray3(int [] array) {
		if(array == null)
		{
			isValid = false;
			return -1;
		}
		
	    int lo = 0, hi = array.length-1;
	    int mid = 0;
	    int min = 0;  // min初始化为0，当将前0个元素旋转时，为原数组本身，此时while条件不成立，直接返回array[min]
	    while(array[lo] >= array[hi])
	    {
	    	if(hi - lo == 1)
	    	{
	    		min = hi;
	    		break;
	    	}
	    	mid = lo + ((hi - lo) >> 1); // careful 运算符优先级  加括号
	    	
	    	/*
	    	 * 两个指针指向的数字以及他们的中间数字，三者相等
	    	 * 无法判断中间数字位于前面的子数组还是后面的子数组、
	    	 * 只能顺序查找
	    	 */
	    	if(array[lo] == array[mid] && array[mid] == array[hi])
	    	{
	    		for(int i = lo; i <= hi; i++)
	    			if(array[i] < min) min = i;
	    		break;
	    	}
	    	if(array[mid] >= array[lo]) lo = mid; // 没有减一
	    	else if(array[mid] <= array[hi]) hi = mid; // 没有减一
	    }
	    isValid = true;
		return array[min];
    }
	public static void main(String[] args) {
		System.out.println(new N8_旋转数组的最小数字().minNumberInRotateArray(new int[]{3, 4, 5, 1, 2}));
	}
}
