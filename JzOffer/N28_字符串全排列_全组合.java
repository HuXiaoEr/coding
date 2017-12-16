package JzOffer;

// 重点
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

/*
 * 排列：
 * N46_Permutations_M ：不重复
 * N47_Permutations_II_M ：重复
 * 
 * 组合：
 * N78_Subsets_M ：不重复 
 * bitmap ：不重复
 * N40_Combination_Sum_II_M ：重复
 * N90_Subsets_II_M ：重复
 *
 */


/**
 * 字符串元素重复
 * 法一：recursion + HashSet
 * 法二：recursion no HashSet
 * 法三：iterative 字典序全排列
 */
public class N28_字符串全排列_全组合 {
	private int strLength;
	// 法一：recursion + HashSet
	public ArrayList<String> Permutation(String str) {
		ArrayList<String> res = new ArrayList<String>();
		if(str == null) return res;
		
		//Don't forget 
		if(str.length() == 0) return res;
		
		//字符串元素重复，去除重复排列
		HashSet<String> set = new HashSet<String>();
		char[] strChar = str.toCharArray();
		strLength = str.length();
		help(strChar, 0, set);
		res.addAll(set);
		
		//用于输出
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
	
	// 法二：recursion no HashSet
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
	
	
	// str不包含重复字符
	public static void combination1(String str) {
        /*全组合：
         * 思路是利用二进制的特性，每次加1即可遍历所有位的不同情况，很好理解
        代码同上
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
		N28_字符串全排列_全组合 robot = new N28_字符串全排列_全组合();
	}
}
