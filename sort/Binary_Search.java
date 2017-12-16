package sort;

public class Binary_Search {
	
	/** 1.1 查找某个数的下标（任意一个） */
	/*
	 * 首先要把握下面几个要点：
	 * hi = n-1 => while(lo <= hi) => hi = middle-1;  // [hi]有可能等于[mid]
	 * hi = n   => while(lo <  hi) => hi = middle;    // [hi]不可能等于[mid]
	 * middle的计算不能写在while循环外，否则无法得到更新。
	 */
	public int binary_search(int array[], int value) {
		int lo = 0;
		int hi = array.length - 1;
		// 如果这里是int hi = n 的话，那么下面有两处地方需要修改，以保证一一对应：
		// 1、下面循环的条件则是while(lo < hi)
		// 2、循环内当array[middle]>value 的时候，hi = mid
		while (lo <= hi) // 循环条件，适时而变
		{
			int middle = lo + ((hi - lo) >> 1); // 防止溢出，移位也更高效。同时，每次循环都需要更新。
			if (value < array[middle]) {
				hi = middle - 1; // hi赋值，适时而变
			} else if (value > array[middle]) {
				lo = middle + 1;
			} else
				return middle;
			// 可能会有读者认为刚开始时就要判断相等，但毕竟数组中不相等的情况更多
			// 如果每次循环都判断一下是否相等，将耗费时间
		}
		return -1;
	}
	
	/** 1.2 第一个大于等于某个数的下标 */
	public int firstGreatOrEqual(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value <= array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return lo < array.length ? lo : -1;  
	}
	
	/** 1.3 第一个大于某个数的下标 */
	public int firstGreat(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value < array[mid]) hi = mid - 1;  
            else lo = mid + 1;
        }  
        return lo < array.length ? lo : -1;  
	}
	
	/** 1.4 小于某个数的最大下标 （参考1.2）*/
	public int bigestLess(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value <= array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return (lo - 1 >= 0) ? lo - 1 : -1;  
	}
	
	/** 1.5 某个数位置的最小下标 （参考1.2）*/
	public int firstIndex(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value <= array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return (lo < array.length) && (array[lo] == value) ? lo : -1;  
	}
	
	/** 1.6 某个数位置的最大下标 （参考1.3）*/
	public int lastIndex(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value < array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return (lo - 1 >= 0 && (array[lo - 1] == value))? lo - 1: -1;  
	}
	
	/** 1.7 数组中某个数的出现次数 （参考1.2 1.3）*/
	// 健壮性判断：数字在数组中没出现 ?? 
	public int getCount(int array[], int value) {
	    int first = firstGreatOrEqual2(array, value);  
	    int last = firstGreat2(array, value);  
	    return last - first;
	}
	/** 参考 1.2 第一个大于等于某个数的下标 */
	public int firstGreatOrEqual2(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value <= array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return lo;  
	}
	/** 参考 1.3 第一个大于某个数的下标 */
	public int firstGreat2(int array[], int value) {
        int lo = 0;  
        int hi = array.length-1;  
        int mid = 0;  
        while(lo <= hi) {  
            mid = lo + ((hi-lo) >> 1);  
            if(value < array[mid]) {  
                hi = mid - 1;  
            } else {  
                lo = mid + 1;  
            }  
        }  
        return lo;  
	}
	
}
