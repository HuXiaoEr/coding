package JzOffer;

public class N9_쳲���������_������̨��_��̬��̨�� {
	
	// over
	public int Fibonacci(int n) {
		if(n < 2) return n;
		int pre = 0, in = 1, res = 0;
		for(int i = 2; i <= n; i++)
		{
			res = pre + in;
			pre = in;
			in = res;
		}
		return res;
    }
	
	// ������̨��
	// һֻ����һ�ο�������1��̨�ף�Ҳ��������2���������������һ��n����̨���ܹ��ж�����������
    public int JumpFloor(int n) {
		if(n <= 2) return n;
    	int pre = 1;
    	int now = 2;
    	int temp = 0;
    	for(int i = 3; i <= n; i++){
    		temp = now;
    		now += pre;
    		pre = temp;
    	}
    	return now;
    }
	
    // ��̬��̨��
    // һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n���������������һ��n����̨���ܹ��ж�����������
    public int JumpFloorII(int target) {
        //return (int)Math.pow(2, target-1);  // ע��ǿת
        return 1 << --target;
    }
    
	/*
	 * ���ܿ�����1��̨�ף�Ҳ������2��...Ҳ������n��������������һ��n��̨�׹��ж�����������
	 * f(n) = 2 ^ (n-1)
	 * 
	 * ������
	 * f(n-1) = f(0) + f(1)+f(2)+f(3) + ... + f((n-1)-1) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2)
	 * f(n) = f(0) + f(1) + f(2) + f(3) + ... + f(n-2) + f(n-1) = f(n-1) + f(n-1)
	 * ���Եó���
	 * f(n) = 2*f(n-1)
	 * 
	 *  -> f(n) = 2 ^ (n-1)
	 */
}
