package JzOffer;

import java.util.ArrayList;

public class N41_��Ϊs����������_��Ϊs�������������� {
	
	// over
	//��Ϊs��������������
	public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
		ArrayList<ArrayList<Integer>> res = new ArrayList<>();
		if(sum < 3) return res;
		int small = 1;
		int big = 2;
		int curSum = small + big;
		while(small < ((sum+1) >> 1))
		{
			if(curSum < sum) 
			{
				big++;
				curSum += big;
			}
			else if(curSum == sum)
			{
				ArrayList<Integer> data = new ArrayList<Integer>();
				for(int i = small; i <= big; i++)
				{
					data.add(i);
				}
				res.add(data);
				curSum -= small; // ������
				small++;  // ������
			}
			else
			{
				curSum -= small;
				small++;
			}
		}
		return res;
    }
	
	// over
	//³����
	//��Ϊs����������
	public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        if(array.length < 3) return res;
        int bigIndex = array.length-1;
        int smallIndex = 0;
        int curSum = 0;
        //int multi = array[bigIndex] * array[bigIndex];
        while(smallIndex < bigIndex)
        {
        	//System.out.println(small + " " + big);
        	//System.out.println(curSum + "***");
        	curSum = array[smallIndex] + array[bigIndex];
        	if(curSum < sum){
                smallIndex++;
                
        	}
        	else if(curSum == sum)
        	{
        		//unnecessary
        		/*if(array[smallIndex] * array[bigIndex] < multi)
        		{
        			res.add(array[smallIndex]);
        			res.add(array[bigIndex]);
        			multi = array[smallIndex] * array[bigIndex];
        		}
        		smallIndex++;
    			bigIndex--;*/
        		/**
        		 * 
					�ҵ��ĵ�һ�飨������ģ����ǳ˻���С�ġ�
					��������֤��������x+y=C��C�ǳ�������x*y�Ĵ�С��
					������y>=x��y-x=d>=0��
					��y=x+d, 2x+d=C, x=(C-d)/2, x*y=x(x+d)=(C-d)(C+d)/4=(C^2-d^2)/4��
					Ҳ����x*y��һ�����ڱ���d�Ķ��κ������Գ�����y�ᣬ�������¡�d��>=0�ģ�dԽ��, x*yҲ��ԽС��
        		 */
        		res.add(array[smallIndex]);
    			res.add(array[bigIndex]);
    			break;
        	}
        	else
        	{
        		bigIndex--;
        	}
        }
        return res;
    }
	
	public static void main(String[] args) {
		System.out.println(new N41_��Ϊs����������_��Ϊs��������������().FindNumbersWithSum(new int[]{1,2,4,7,11,15}, 15));
	}
}
