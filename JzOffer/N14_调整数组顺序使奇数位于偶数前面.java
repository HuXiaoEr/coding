package JzOffer;

// over
public class N14_��������˳��ʹ����λ��ż��ǰ�� {
	
	// ��֤ԭʼ���˳�򲻱�
	// ���Ʋ�������
	public void reOrderArray(int [] array) {
        if(array == null) return;
        for(int i = 0; i < array.length; i++)
        {
        	if((array[i] & 1) == 1)
        	{
        		for(int j = i; (j >= 1) && ((array[j-1] & 1 ) == 0); j--)
        		{
        			exch(array, j, j-1);
        		}
        	}
        }
    }
	private static void exch(int[] arr, int j, int i) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	// ���ܱ�֤ԭʼ���˳�򲻱�
	public void reOrderArray2(int [] array) {
        if(array == null) return;
        int start = 0;
        int end = array.length-1;
        while(start <= end)
        {
        	if((array[start] & 1) == 1)
        	{
        		start++;
        		continue;
        	}
        	else if((array[end] & 1) == 0)
        	{
        		end--;
        		continue;
        	}
        	else
        	{
        		int temp = array[start];
        		array[start] = array[end];
        		array[end] = temp;
        		start++;
        		end--;
        	}
        }
    }
}
