package sort;

public class Sort {
	
	//ѡ������
	public static void selectSort(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			int min = i;
			for(int j = i+1; j < a.length; j++)
			{
				if(a[j] < a[min]) min = j;
			}
			exch(a, i, min);
		}
	}
	//ѡ�������Ż�
	public static void selectSortOpt(int[] a)
	{
		int lo = 0;
		int hi = a.length - 1;
		int max = lo;
		int min = lo;
		while(lo <= hi){
			max = lo;
			min = lo;
			for(int i = lo; i <= hi; i++){
				if(a[i] < a[min]) min = i;
				if(a[i] > a[max]) max = i;
			}
			exch(a, lo, min);
			if(lo == max) max = min;
			exch(a, hi, max);
			lo++;
			hi--;
		}
	}
	//��������1
	public static void insertSort(int[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			for(int j = i; j >= 1 && a[j] < a[j-1]; j--)  // forѭ��������  j >= 1
				exch(a, j, j-1);
		}
	}
	//��������2
	//��ѭ���н��ϴ�Ԫ�������ƶ������ǽ�����Ԫ�أ���������Ԫ�ش�������
	public static void insertSort2(int[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			int cur = a[i];
			int j = i-1;
			for(j = i-1; j >= 0 && a[j] > cur; j--) // j >= 0  ���� j >= 1
				a[j+1] = a[j];
			a[j+1] = cur;
		}
	}
	//���������Ż�1
	//���ֲ��Ҳ�������
	//http://www.cnblogs.com/heyuquan/p/insert-sort.html
	public static void insertSortOpt(int[] a)
	{
		for(int i = 1; i < a.length; i++)
		{
			int idx = binarySearch(a, i-1, a[i]);
			if(i != idx){
				int cur = a[i];
				int j = i-1;
				for(j = i-1; j >= idx; j--)
					a[j+1] = a[j];
				a[j+1] = cur;  // ����ֱ�� a[idx] = a[i]����Ϊa[i]����ֵ����
			}
		}
	}
	private static int binarySearch(int[] a, int idx, int val) {
		int lo = 0;
		int hi = idx;
		while(lo <= hi){
			int mid = lo + ((hi - lo) >> 1);
			if(val < a[mid]) hi = mid-1;
			else lo = mid+1;
		}
		return lo;
	}
	//���������Ż�2
	//ϣ������
	
	//ϣ������1
	public static void shellSort(int[] a)
	{
		int h = 1;
		while(h < a.length / 3) h = 3 * h + 1;
		while(h >= 1)
		{
			for(int i = h; i < a.length; i++)
			{
				for(int j = i; j >= h && a[j] < a[j-h]; j -= h)
					exch(a, j, j-h);
			}
			h /= 3;
		}
	}
	//ϣ������2
	public static void shellSort2(int[] a) {
		int h = 1;
		while (h < a.length / 3)
			h = 3 * h + 1;
		while (h >= 1) {
			for (int i = h; i < a.length; i++) {
				int currentNumber = a[i];
				int j = i - h;
				for(j = i-h; j >= 0 && a[j] > currentNumber; j -= h)
					a[j+h] = a[j];
				a[j+h] = currentNumber;
			}
			h /= 3;
		}
	}
	
	//ð������
	public static void bubbleSort(int[] a)
	{
		for(int i = 0; i < a.length-1; i++)
		{
			for(int j = 0; j < a.length-1-i; j++)
			{
				if(a[j] > a[j+1]) exch(a, j, j+1);
			}
			print(a);
		}
	}
	public static void bubbleSortOpt(int[] a){
		boolean flag = false;
		for(int i = 0; i < a.length-1; i++)
		{
			flag = true;
			for(int j = 0; j < a.length-1-i; j++)
			{
				if(a[j] > a[j+1]){
					exch(a, j, j+1);
					flag = false;
				}
			}
			if(flag) break;
			print(a);
		}
	}
	
	//�鲢����
	/*
	 * �Ż���
	 * 1. ����С���飬ʹ�ò�������
	 * 2. ��merge()����֮ǰ�������ж�һ��a[mid]�Ƿ�С�ڵ���a[mid+1]������ǵĻ���ô�Ͳ��ù鲢��
	 * 3. ��ʡ�����鸴�Ƶ����������ʱ�䣬
	 * 4. ȥ����߼��Ĵ��룬��ʹ�����ȶ�
	 *  	void merge(int[] a, int lo, int mid, int hi, int[] aux) {
			for (int k = lo; k <= mid; k++) {
    			aux[k] = a[k];
			}
			for (int k = mid + 1;k <= hi; k++) {
    			aux[k] = a[hi - k + mid + 1];
			}
			int i = lo, j = hi;      //���������м�
			for (int k = lo; k <= hi; k++)
    			if (aux[i] <= aux[j]) a[k] = aux[i++];
    			else a[k] = aux[j--];
			}
	 * 
	 */
	public static void mergeSort(int[] a)
	{
		int[] aux = new int[a.length];
		mergeSort(a, 0, a.length-1, aux);
	}
	
	private static void mergeSort(int[] a, int lo, int hi, int[] aux) {
		if(lo >= hi) return;
		int mid = lo + ((hi - lo) >> 1);
		mergeSort(a, lo, mid, aux);
		mergeSort(a, mid+1, hi, aux);
		merge(a, lo, mid, hi, aux);
	}
	private static void merge(int[] a, int lo, int mid, int hi, int[] aux) {
		for(int i = lo; i <= hi; i++) aux[i] = a[i];
		int left = lo;
		int right = mid+1;
		for(int i = lo; i <= hi; i++)
		{
			if(left > mid) a[i] = aux[right++];
			else if(right > hi) a[i] = aux[left++];
			else if(aux[left] < aux[right]) a[i] = aux[left++]; // ע���ǱȽ�aux��Ԫ�صĴ�С ��
			else a[i] = aux[right++];
		}
	}
	
	//�鲢�����Ż�
	// https://algs4.cs.princeton.edu/22mergesort/MergeX.java.html
	public static void mergeSortOpt(int[] a){
		int[] aux = new int[a.length];
		mergeSortOpt(aux, a, 0, a.length-1);
	}
	private static void mergeSortOpt(int[] src, int[] dst, int lo, int hi) {
		if(hi <= lo + 7) {
			//insertSort(dst, lo, hi);
			return;
		}
		int mid = lo + ((hi - lo) >> 1);
		mergeSortOpt(dst, src, lo, mid);
		mergeSortOpt(dst, src, mid+1, hi);
		if(src[mid] <= src[mid+1]){
			System.arraycopy(src, lo, dst, lo, hi-lo+1);
			return;
		}
		mergeOpt(src, dst, lo, mid, hi);
	}
	private static void mergeOpt(int[] src, int[] dst, int lo, int mid, int hi) {
		int left = lo;
		int right = mid+1;
		for(int i = lo; i <= hi; i++)
		{
			if(left > mid) dst[i] = src[right++];
			else if(right > hi) dst[i] = src[left++];
			else if(src[left] < src[right]) dst[i] = src[left++]; // ע���ǱȽ�aux��Ԫ�صĴ�С ��
			else dst[i] = src[right++];
		}
	}
	
	//��������  Arrays
	/*
	 * �Ż���
	 * 1. ȡ�з�Ԫ��ʱ�������medium-of-three  medium-of-nine
	 */			/**
				�������СС��7�������ʹ�ÿ�������
        		if (r - p + 1 < 7) {
            		for (int i = p; i <= r; i++) {
                		for (int j = i; j > p && a[j - 1] > a[j]; j--) {
                    		swap(a, j, j - 1);
                		}
            		}
            		return;
        		}
        		// �������鳤��
        		int len = r - p + 1;
        		// ����е㣬��С����7������ֱ��ѡ������
        		int m = p + (len >> 1);
        		// ��С����7
        		if (len > 7) {
            		int l = p;
            		int n = p + len - 1;
            		if (len > 40) { // �����飬����median-of-nineѡ��
                		int s = len / 8;
                		l = med3(a, l, l + s, l + 2 * s); // ȡ����˵�3�������ó�����
                		m = med3(a, m - s, m, m + s); // ȡ���е�3�������ó�����
                		n = med3(a, n - 2 * s, n - s, n); // ȡ���Ҷ˵�3�������ó�����
            		}
            		m = med3(a, l, m, n); // ȡ�����е�����,median-of-three
        		}
        		// ����pivot����˵㣬����Ĳ�����qsort4��ͬ
        		swap(a, p, m);
        		*/
	 /* 
	  * 2. �����Сʱ���л�����������
	  *  3. �����ظ�Ԫ�ؽ϶�ʱ�����������зֿ�������
	  */
	/**
	 * ���С��a[lo]ʱͣ��  -> a[lo..small-1] < a[small] <= a[small+1..hi] ���
	 * ���С�ڵ���a[lo]ʱͣ��  -> a[lo..small-1] <= a[small] < a[small+1..hi] ���
	 * return small
	 */
	public static void quickSort(int[] a)
	{
		quickSort(a, 0, a.length-1);
	}
	private static void quickSort(int[] a, int lo, int hi) {
		if(lo >= hi) return;
		int index = partition(a, lo, hi);
		quickSort(a, lo, index-1);
		quickSort(a, index+1, hi);
	}
	private static int partition(int[] a, int lo, int hi) {
		int small = lo;
		for(int i = lo+1; i <= hi; i++)
		{
			if(a[i] < a[lo])
			/** or if(a[i] <= a[lo]) */
			/** a[lo..small-1] <= a[small] < a[small+1..hi] ��� */
			{
				small++;
				if(small != i) exch(a, small, i);
			}
		}
		exch(a, small, lo);
		return small;   /** a[lo..small-1] < a[small] <= a[small+1..hi] ��� */
	}
	//��������2
	public static void quickSort2(int[] a)
	{
		quickSort2(a, 0, a.length-1);
	}
	private static void quickSort2(int[] a, int lo, int hi) {
		if(lo >= hi) return;
		int index = partition2(a, lo, hi);
		quickSort(a, lo, index-1);
		quickSort(a, index+1, hi);
	}
	/**
	 * �����ڵ���a[lo]ʱͣ��  and �Ҳ�С�ڵ���a[lo]ʱͣ��  -> a[lo..j-1] <= a[j] <= a[j+1..hi] ���
	 * �����ڵ���a[lo]ʱͣ��  and �Ҳ�С��a[lo]ʱͣ��  -> a[lo..j-1] < a[j] <= a[j+1..hi] ���
	 * ������a[lo]ʱͣ��  and �Ҳ�С�ڵ���a[lo]ʱͣ��  -> a[lo..j-1] <= a[j] < a[j+1..hi] ���
	 * ������a[lo]ʱͣ��  and �Ҳ�С��a[lo]ʱͣ��  -> a[lo..j-1] <= a[j] <= a[j+1..hi] ���
	 * return j
	 */
	private static int partition2(int[] a, int lo, int hi) {
		int i = lo;
		int j = hi + 1;  //��
		while(true){
			while(a[++i] < a[lo]) if(i == hi) break;  /** �����ڵ���a[lo]ʱͣ��  */
			while(a[--j] > a[lo]) if(j == lo) break;  /** �Ҳ�С�ڵ���a[lo]ʱͣ��  */
			if(i >= j) break;
			exch(a, i, j);
		}
		exch(a, lo, j);
		return j;    /** a[lo..j-1] <= a[j] <= a[j+1..hi] ��� */
	}
	
	//������
	/**
	 * �洢Ԫ�ش�����0��ʼ��algs4�洢Ԫ�ش�����1��ʼ
	 * ����
	 * ����� ��С����
	 * ����Ϊi�Ľڵ�
	 * parent(i):floor((i-1)/2)
	 * left(i):2*i+1
	 * right(i):2*i+2
	 */
	public static void maxHeapSort(int[] a)
	{
		int n = a.length-1;
		for(int i = (n - 1) / 2; i >= 0; i--)
		{
			sinkMaxHeap(a, i, n);
		}
		print(a);
		while(n >= 0)
		{
			exch(a, 0, n--);
			sinkMaxHeap(a, 0, n);
		}
	}
	private static void sinkMaxHeap(int[] a, int i, int n) {
		while(2 * i + 1 <= n)
		{
			int j = 2 * i + 1;
			if(j < n && a[j] < a[j+1]) j++;
			if(a[i] >= a[j]) break;
			exch(a, i, j);
			i = j;
		}
	}
	
	private static void swimMaxHeap(int[] a, int i) {
		while(i > 0 && a[i] > a[(i-1)/2])
		{
			exch(a, i, (i-1)/2);
			i = (i-1) / 2;
		}
	}
	//������
	/**
	 * ��С��
	 * ����� �ɴ�С
	 * ����Ϊi�Ľڵ�
	 * parent(i):floor((i-1)/2)
	 * left(i):2*i+1
	 * right(i):2*i+2
	 */
	public static void minHeapSort(int[] a)
	{
		int n = a.length-1;
		for(int i = (n - 1) / 2; i >= 0; i--)
		{
			sinkMinHeap(a, i, n);
		}
		print(a);
		while(n >= 0)
		{
			exch(a, 0, n--);
			sinkMinHeap(a, 0, n);
		}
	}
	private static void sinkMinHeap(int[] a, int i, int n) {
		while(2 * i + 1 <= n)
		{
			int j = 2 * i + 1;
			if(j < n && a[j+1] < a[j]) j++;
			if(a[i] <= a[j]) break;
			exch(a, i, j);
			i = j;
		}
	}
	private static void swimMinHeap(int[] a, int i) {
		while(i > 0 && a[i] < a[(i-1)/2])
		{
			exch(a, i, (i-1)/2);
			i = (i-1) / 2;
		}
	}
	
	public static void main(String[] args) {
		int[] a = new int[]{3,4,2,7,1,6,5};
		int[] a1 = new int[]{3,3,3,3,4,4,2,2,1,4,6};
		print(a1);
//		selectSort(a);
//		selectSortOpt(a1);
		
//		insertSort2(a1);
//		insertSort2(a);
//		insertSortOpt(a1);
		
//		bubbleSort(a);
		
//		shellSort2(a1);
		
		mergeSort(a1);
		
//		quickSort(a);
		
//		minHeapSort(a);
		
		print(a1);
		//Integer aa = null;
		//System.out.println(aa == 1);
		
	}
	public static void exch(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	public static void print(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			System.out.print(a[i] + " ");
		}
		System.out.println();
	}
}
