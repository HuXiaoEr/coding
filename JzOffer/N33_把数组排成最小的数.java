package JzOffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *��1��Ч������
 *��2����������
 */
//right 
public class N33_�������ų���С���� {
	
	//two
	//�ֵ���
	 public String PrintMinNumber2(int [] numbers) {
		 ArrayList<Integer> num = new ArrayList<>();
		 for(int i = 0; i < numbers.length; i++)
		 {
			 num.add(numbers[i]);
		 }
		 Collections.sort(num, new Comparator<Integer>(){
			 public int compare(Integer int1, Integer int2)
			 {
				 String str1 = int1 + "" + int2;
				 String str2 = int2 + "" + int1;
				 return str1.compareTo(str2);
			 }
		 });
		 String res = "";
		 for(int i = 0; i < num.size(); i++)
		 {
			 res += num.get(i);
		 }
		 return res;
	 }
	 
	 ///////////////////////////////////////////////////////////////////////////////

	 //mine not best
	 String min = "";
	 public String PrintMinNumber(int [] numbers) {
		 array(numbers, 0, "");//""��Ҫд��null��
		 return min;
	 }
	private void array(int[] numbers, int start, String string) {
		if(start == numbers.length-1)
		{
			string = string + numbers[start];
			if(min == "") min = string;
			else if(string.compareTo(min) < 0) min = string;
			return;
		}
		for(int i = start; i < numbers.length; i++)
		{
			int temp = numbers[start];
			numbers[start] = numbers[i];
			numbers[i] = temp;
			array(numbers, start+1, string+numbers[start]);
			temp = numbers[start];
			numbers[start] = numbers[i];
			numbers[i] = temp;
		}
	}
	public static void main(String[] args){
		N33_�������ų���С���� t = new N33_�������ų���С����();
		//t.PrintMinNumber(new int[]{3, 32, 321});
		System.out.println(t.PrintMinNumber2(new int[]{3, 32, 321}));
		
	}

}
