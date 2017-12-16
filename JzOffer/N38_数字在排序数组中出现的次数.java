package JzOffer;

// over
public class N38_数字在排序数组中出现的次数 {
	
	//法三：best  binary search
		public int GetNumberOfK(int [] array , int k) {
			if(array == null) return -1;
			int start = getFirst(array, k);
			int end = getEnd(array, k);
			return end - start;
	    }
		private int getFirst(int[] array, int val) {
			int lo = 0;
			int hi = array.length-1;
			int mid = 0;
			while(lo <= hi){
				mid = lo + ((hi-lo) >> 1);
				if(val <= array[mid]) hi = mid - 1;
				else lo = mid + 1;
			}
			return lo;
		}
		private int getEnd(int[] array, int val) {
			int lo = 0;
			int hi = array.length-1;
			int mid = 0;
			while(lo <= hi){
				mid = lo + ((hi-lo) >> 1);
				if(val < array[mid]) hi = mid - 1;
				else lo = mid + 1;
			}
			return lo;
		}

	
	//法二：Not best
	public int GetNumberOfK2(int [] array , int k) {
		if(array == null) return -1;
		boolean flag = false;
		int res = 0;
		for(int i = 0; i < array.length; i++)
		{
			if(flag)
				if(array[i] == k) res++;
				else break;
			else 
			{
				if(array[i] == k)
				{
					res++;
					flag = true;
				}
			}
		}
		return res;
    }
	public static void main(String[] args) {
		//System.out.println(new N_38().GetNumberOfK3(new int[]{3,3,3,3,4,5}, 3));
	}
}
