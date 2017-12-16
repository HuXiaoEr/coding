package JzOffer;

// 注意res溢出 其它over
public class N36_数组中的逆序对 {
	
	
	/**
	 * 法二：
	 * （1）better
	 * （2）归并排序
	 */
	public int InversePairs(int [] array) {
		if(array == null) return -1;
		long res = 0;
		int[] aux = new int[array.length];
        res = mergeSort(array, 0, array.length-1, aux);
        System.out.println("**8");
        print(array);
		return (int)(res%1000000007);
    }	
	private long mergeSort(int[] array, int lo, int hi, int[] aux) {
		print(array);
		if(lo >= hi) return 0;  //array.length == 0
		int mid = lo + ((hi - lo) >> 1);
		long left = mergeSort(array, lo, mid, aux);
		long right = mergeSort(array, mid+1, hi, aux);
		long merge = merge(array, lo, mid, hi, aux);
		return left+right+merge;
	}
	private long merge(int[] array, int lo, int mid, int hi, int[] aux) {
		long res = 0l;
		int left = mid;
		int right = hi;
		for(int i = lo; i <= hi; i++) aux[i] = array[i];
		for(int i = hi; i >= lo; i--){
			if(left < lo){
				array[i] = aux[right--];
			}
			else if(right <= mid){
				array[i] = aux[left--];
				//res += hi - mid;  // 注意
			}
			//else if(array[left] > array[right]){ 
			// 注意，归并排序，比较的是aux数组的大小，细心
			else if(aux[left] > aux[right]){
				res += right - mid;
				array[i] = aux[left--];
			}
			else{
				array[i] = aux[right--];
			}
		}
		return res;
	}
		
	public static void print(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		System.out.println(new N36_数组中的逆序对().InversePairs(new int[]{364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575}));
		//System.out.println(new N36_数组中的逆序对().InversePairs2(new int[]{4, 2, 1, 3}));
	}
	
}
