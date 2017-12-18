package JzOffer;

/**
 * 
 * @author ��С��
 *
 *��һ������Ϊn����������������ֶ���0��n-1�ķ�Χ�ڡ� 
 *������ĳЩ�������ظ��ģ�����֪���м����������ظ��ġ�Ҳ��֪��ÿ�������ظ����Ρ�
 *���ҳ�����������һ���ظ������֡� 
 *���磬������볤��Ϊ7������{2,3,1,0,2,5,3}����ô��Ӧ��������ظ�������2����3��
 */
// over
public class N51_0��n��1�ĳ���Ϊn�������е��ظ����� {
	
	/*
	 * o(n)time o(1)space
	 * ÿ���������ֻҪ�������ξͿ��Էŵ���ȷλ��
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
		N51_0��n��1�ĳ���Ϊn�������е��ظ����� n51 = new N51_0��n��1�ĳ���Ϊn�������е��ظ�����();
		int[] p = new int[1];
		n51.duplicate2(new int[]{1,2,3,1,4}, 5, p);
		System.out.println(p[0]);
	}
}
