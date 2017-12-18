package sort;

public class Binary_Search {
	
	/** 1.1 ����ĳ�������±꣨����һ���� */
	/*
	 * ����Ҫ�������漸��Ҫ�㣺
	 * hi = n-1 => while(lo <= hi) => hi = middle-1;  // [hi]�п��ܵ���[mid]
	 * hi = n   => while(lo <  hi) => hi = middle;    // [hi]�����ܵ���[mid]
	 * middle�ļ��㲻��д��whileѭ���⣬�����޷��õ����¡�
	 */
	public int binary_search(int array[], int value) {
		int lo = 0;
		int hi = array.length - 1;
		// ���������int hi = n �Ļ�����ô�����������ط���Ҫ�޸ģ��Ա�֤һһ��Ӧ��
		// 1������ѭ������������while(lo < hi)
		// 2��ѭ���ڵ�array[middle]>value ��ʱ��hi = mid
		while (lo <= hi) // ѭ����������ʱ����
		{
			int middle = lo + ((hi - lo) >> 1); // ��ֹ�������λҲ����Ч��ͬʱ��ÿ��ѭ������Ҫ���¡�
			if (value < array[middle]) {
				hi = middle - 1; // hi��ֵ����ʱ����
			} else if (value > array[middle]) {
				lo = middle + 1;
			} else
				return middle;
			// ���ܻ��ж�����Ϊ�տ�ʼʱ��Ҫ�ж���ȣ����Ͼ������в���ȵ��������
			// ���ÿ��ѭ�����ж�һ���Ƿ���ȣ����ķ�ʱ��
		}
		return -1;
	}
	
	/** 1.2 ��һ�����ڵ���ĳ�������±� */
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
	
	/** 1.3 ��һ������ĳ�������±� */
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
	
	/** 1.4 С��ĳ����������±� ���ο�1.2��*/
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
	
	/** 1.5 ĳ����λ�õ���С�±� ���ο�1.2��*/
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
	
	/** 1.6 ĳ����λ�õ�����±� ���ο�1.3��*/
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
	
	/** 1.7 ������ĳ�����ĳ��ִ��� ���ο�1.2 1.3��*/
	// ��׳���жϣ�������������û���� ?? 
	public int getCount(int array[], int value) {
	    int first = firstGreatOrEqual2(array, value);  
	    int last = firstGreat2(array, value);  
	    return last - first;
	}
	/** �ο� 1.2 ��һ�����ڵ���ĳ�������±� */
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
	/** �ο� 1.3 ��һ������ĳ�������±� */
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
