package JzOffer;

// �ص�
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/*
 * ���У�
 * N46_Permutations_M �����ظ�
 * N47_Permutations_II_M ���ظ�
 * 
 * ��ϣ�
 * N78_Subsets_M �����ظ� 
 * bitmap �����ظ�
 * N40_Combination_Sum_II_M ���ظ�
 * N90_Subsets_II_M ���ظ�
 *
 */


/**
 * �ַ���Ԫ���ظ�
 * ��һ��recursion + HashSet
 * ������recursion no HashSet
 * ������iterative �ֵ���ȫ����
 */
public class N28_�ַ���ȫ����_ȫ��� {
	private int strLength;
	// ��һ��recursion + HashSet
	public ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<String>();
		if(str == null) return res;
		
		//Don't forget 
		if(str.length() == 0) return res;
		
		//�ַ���Ԫ���ظ���ȥ���ظ�����
		HashSet<String> set = new HashSet<String>();
		char[] strChar = str.toCharArray();
		strLength = str.length();
		help(strChar, 0, set);
		res.addAll(set);
		
		//�������
		Collections.sort(res);
		return res;
	}
	private void help(char[] strChar, int length, HashSet<String> set) {
		if(length == strLength-1)
		{
			set.add(String.valueOf(strChar));
			return;
		}
		for(int i = length; i < strChar.length; i++)
		{
			char temp = strChar[length];
			strChar[length] = strChar[i];
			strChar[i] = temp;
			help(strChar, length+1, set);
			temp = strChar[length];
			strChar[length] = strChar[i];
			strChar[i] = temp;
		}
	}
	
	// ������recursion no HashSet
    public ArrayList<String> Permutation2(String str) {
    	ArrayList<String> res = new ArrayList<String>();
    	if(str == null || str.length() == 0) return res;
    	char[] arr = str.toCharArray();
    	Arrays.sort(arr);
    	boolean[] used = new boolean[str.length()];
    	Permutation(arr, used, res, "");
    	Collections.sort(res);
    	return res;
    }
	private void Permutation(char[] arr, boolean[] used, ArrayList<String> res, String s) {
		if(s.length() == arr.length){
			res.add(s);
		}
		else{
			for(int i = 0; i < arr.length; i++){
				if(used[i] || i > 0 && arr[i] == arr[i-1] && !used[i-1]) continue;
				used[i] = true;
		    	Permutation(arr, used, res, s+arr[i]);
		    	used[i] = false;
			}
		}
	}
	
	
	// str�������ظ��ַ�
	public static void combination1(String str) {
        /*ȫ��ϣ�
         * ˼·�����ö����Ƶ����ԣ�ÿ�μ�1���ɱ�������λ�Ĳ�ͬ������ܺ����
        ����ͬ��
            */
	// https://discuss.leetcode.com/topic/46162/a-general-approach-to-backtracking-questions-in-java-subsets-permutations-combination-sum-palindrome-partioning
        char arr[] = str.toCharArray();
        int all = arr.length;
        int nbit = 1 << all;
        for (int i = 0; i < nbit; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 0; j < all; j++) {
                if ((i & (1 << j)) != 0) {
                    sb.append(arr[j]);
                }
            }
            System.out.println(sb);
        }
	}
	
	public static void main(String[] args) {
		//System.out.println(new N_28().Permutation("aacc"));
		//new N_28().combiantion(new char[]{'a', 'b', 'b'});
		N28_�ַ���ȫ����_ȫ��� robot = new N28_�ַ���ȫ����_ȫ���();
	}
}
