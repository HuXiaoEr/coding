package JzOffer;

// over
public class N40_数组中只出现一次的数字_有两个 {
	
	public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array == null || array.length < 2)
        {
        	num1[0] = -2;
        	num2[0] = -2;
        }
        int xor = array[0];
        for(int i = 1; i < array.length; i++)
        {
        	xor = xor ^ array[i];
        }
        int flag = 1;
        for(int i = 0; i < 32; i++)
        {
        	if((xor & flag) != 0) break;
        	flag = flag << 1;
        	
        }
        //best
        for(int i = 0; i < array.length; i++)
        {
        	if((array[i] & flag) == 0) num1[0] ^= array[i];
        	else num2[0] ^= array[i];
        }
        /**
      	not best
        int arrIndex1 = 0;
        int arrIndex2 = array.length;
        while(arrIndex1 < arrIndex2)
        {
        	if((array[arrIndex1] & flag) == 0) arrIndex1++;
        	
        	//unnecessary
        	else exch(array, arrIndex1, --arrIndex2);

        }
        num1[0] = array[0];
        num2[0] = array[arrIndex2];
        for(int i = 1; i < arrIndex2; i++)
        {
        	num1[0] = num1[0] ^ array[i];
        }
        for(int i = arrIndex2+1; i < array.length; i++)
        {
        	num2[0] = num2[0] ^ array[i];
        }
        */
    }

	/**
	private void exch(int[] array, int i, int midIndex) {
		int temp = array[i];
		array[i] = array[midIndex];
		array[midIndex] = temp;
	}
	*/
	
	public static void main(String[] args) {
		/*int num = 0;
		int flag = 0;
		for(int i = 0; i < 3; i++)
		{
			flag = flag + num++;
			System.out.println("		flag = " + flag);
			System.out.println("num = " + num);
		}*/
		int[] num1 = new int[1];
		int[] num2 = new int[1];
		new N40_数组中只出现一次的数字_有两个().FindNumsAppearOnce(new int[]{2,4,3,6,3,2,5,5}, num1, num2);
		System.out.println(num1[0] + " " + num2[0]);
	}
}
