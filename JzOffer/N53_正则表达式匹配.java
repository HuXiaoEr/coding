package JzOffer;

/**
 * 
 * @author ��С��
 *
 *��ʵ��һ����������ƥ�����'.'��'*'��������ʽ��
 *ģʽ�е��ַ�'.'��ʾ����һ���ַ�����'*'��ʾ��ǰ����ַ����Գ�������Σ�����0�Σ��� 
 *�ڱ����У�ƥ����ָ�ַ����������ַ�ƥ������ģʽ��
 *���磬�ַ���"aaa"��ģʽ"a.a"��"ab*ac*a"ƥ�䣬������"aa.a"��"ab*a"����ƥ��
 */
/*
 * str��ͷ��pattern����ͷ  eg : "" "c*" ...
 * "aaa","a*a"
 */

/*
1 ��ģʽ�еĵڶ����ַ����ǡ�*��ʱ��
	�ַ�����һ���ַ���ģʽ�еĵ�һ���ַ���ƥ�䣺�ַ�����ģʽ������һ���ַ�
	��ƥ����ַ���Խ�� false
2 ��ģʽ�еĵڶ����ַ��ǡ�*��ʱ��
	2.1 �ַ�����һ���ַ���ģʽ��һ���ַ���ƥ�� �� �ַ���Խ��
		(2.1.1) ģʽ����2���ַ�
	2.2 �ַ�����һ���ַ���ģʽ��һ���ַ�ƥ��	
		(2.2.1) ģʽ����2�ַ����൱��x*�����ԣ�
		(2.2.2) �ַ�������1�ַ���ģʽ����2�ַ���
		(2.2.3) �ַ�������1�ַ���ģʽ���䣬������ƥ���ַ���һλ����Ϊ*����ƥ���λ��

 */

// over
public class N53_������ʽƥ�� {
	public boolean match(char[] str, char[] pattern) {
		if(str == null || pattern == null) return false;
		return match(0, 0, str, pattern);
	}
	private boolean match(int i, int j, char[] str, char[] pattern) {
		if(i == str.length && j == pattern.length) return true;
		if(i != str.length && j == pattern.length) return false; // ��
		if(j+1 < pattern.length && pattern[j+1] == '*') { // ��
			if(i < str.length && (pattern[j] == '.' || pattern[j] == str[i]))
				return match(i, j+2, str, pattern)
						|| match(i+1, j+2, str, pattern)
						|| match(i+1, j, str, pattern);
			else return match(i, j+2, str, pattern);
		}
		//ģʽ��һ������*
		// i < str.length
		if(i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) return match(i+1, j+1, str, pattern); 
		else return false;

	}
}