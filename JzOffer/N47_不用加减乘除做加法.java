package JzOffer;

/**
 * 
 * @author ��С��
 *
 *дһ������������������֮�ͣ�Ҫ���ں������ڲ���ʹ��+��-��*��/����������š�
 */

// over
public class N47_���üӼ��˳����ӷ� {
	/*
	 * not right
	public int Add(int num1,int num2) {
        int sum = 0;
        int carry = 0;
        int flag = 1;
		while(num2 > 0 || num1 > 0)
		{
			int temp1 = num1 & flag;
			int temp2 = num2 & flag;
			sum = (sum << 1) | (temp1 ^ temp2 ^ carry);
			if((carry == 1 && temp1 == 1) || (temp2 == 1 && temp1 == 1) || (carry == 1 && temp2 == 1)) carry = 1;
			else carry = 0;
			flag <<= 1;
			num1 >>= 1;
			num2 >>= 1;
			System.out.println(num1 + " " + num2);
		}
		return sum;
    }
    */
	public static void main(String[] args) {
		System.out.println(new N47_���üӼ��˳����ӷ�().Add(2, 3));
	}
	/*
	 * �����µı�����������������a��b��ֵ
	 * 
	 * �Ӽ����� �������
	 * a = a + b;
	 * b = a - b;
	 * a = a - b;
	 * 
	 * ���
	 * a = a ^ b;
	 * b = a ^ b;
	 * a = a ^ b;
	 */
	public int Add(int num1,int num2) {
		int carry = 1;
		int sum = 0;
		while(carry != 0)
		{
			sum = num1 ^ num2;
			carry = (num1 & num2) << 1;
			num1 = carry;
			num2 = sum;
		}
		return sum;
	}
}
