package JzOffer;

/**
 * 
 * @author ��С��
 * 
 *��1+2+3+...+n��
 *Ҫ����ʹ�ó˳�����for��while��if��else��switch��case�ȹؼ���
 *�������ж���䣨A?B:C����
 */

// ��over
public class N46_��1��n���Ӻ� {
	
	/*
	 * 1.�������߼���Ķ�·����ʵ�ֵݹ���ֹ��
	 * 2.��n==0ʱ��(n>0)&&((sum+=Sum_Solution(n-1))>0)ִֻ��ǰ����жϣ�Ϊfalse��Ȼ��ֱ�ӷ���0��
	 * 3.��n>0ʱ��ִ��sum+=Sum_Solution(n-1)��ʵ�ֵݹ����Sum_Solution(n)��
	 * 
	 * ���⽣ָoffer��˵���ù��캯������ģ�������java�У�
	 * temp[] test = new temp[n]
	 * �����ĳ�ʼ������������ù��캯��
	 */
	public int Sum_Solution(int n) {
        int sum = n;
        boolean ans = (n > 0) && ((sum += Sum_Solution(n-1)) > 0);
        return sum;
    }
	
	/*
	 * Math���ݺ���
	 */
	public int Sum_Solution2(int n) {
		n = (int) (Math.pow(n, 2)+n)>>1;
		return n;
    }
}
