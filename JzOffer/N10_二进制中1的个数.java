package JzOffer;

// over
public class N10_��������1�ĸ��� {
	
	// ��������һλ֮�����λ��0
	//mine, not best
	public int NumberOf1(int n) {
		int res = 0;
		while(n != 0)  //not n > 0;	 careful n < 0  ��
		{
			if((n & 1) == 1) res++;
			n = n >>> 1;
		}
		return res;
    }
	
	// ע�����һ�������Ĳ�ͬ
	// ��������һλ֮�����λ��0
	public int NumberOf1_1(int n) {
		int res = 0;
		int flag = 1;
		// or
		// while(flag != 0)
		while(flag > 0)  // ע�� while����
		{
			if((n & flag) != 0) res++;
			flag = flag << 1;
		}
		return res;
    }
	
	//best
	public int NumberOf1_2(int n) {
		int res = 0;
		while(n != 0)
		{
			res++;
			n = (n-1) & n; // important
		}
		return res;
    }
}
