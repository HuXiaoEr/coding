package JzOffer;

// over
public class N42_翻转句子中的单词顺序_左旋转字符串 {
	
	//翻转句子中的单词顺序
	public String ReverseSentence(String str) {
        if(str == null) return null;
        String reverse = reverse(str);
        StringBuilder sb = new StringBuilder();
        String s = "";
        for(int i = 0; i < str.length(); i++){
        	if(reverse.charAt(i) != ' ') s += reverse.charAt(i);
        	else{
        		if(s.length() != 0){  // 注意 two
        			sb.append(reverse(s));
        			s = "";
        		}
        		sb.append(" ");
        	}
        }
        sb.append(reverse(s));  // 别忘了 two
        return sb.toString();
    }
	private String reverse(String str) {
		char[] arr = str.toCharArray();
		int lo = 0;
		int hi = arr.length-1;
		while(lo < hi) exch(arr, lo++, hi--);
		return new String(arr);
	}

	private void exch(char[] arr, int lo, int hi) {
		char c = arr[lo];
		arr[lo] = arr[hi];
		arr[hi] = c;
	}
	/**
	public String ReverseSentence(String str) {
		if(str == null) return null;
		StringBuilder sb = new StringBuilder();
		StringBuilder resSb = new StringBuilder();
		String res = reverse(str, sb);
        System.out.println(res);
        for(int i = 0; i < res.length(); i++)
        {
        	if(res.charAt(i) != ' ' && i != res.length()-1) sb.append(res.charAt(i));
        	else
        	{
        		if(i == res.length()-1) sb.append(res.charAt(i));
        		resSb.append(reverse(sb.toString(), sb));
        		if(i != res.length()-1)
        		resSb.append(' ');
        	}
        }
		return resSb.toString();
    }

	private String reverse(String str, StringBuilder sb) {
		sb.delete(0, sb.length());
		for(int i = str.length()-1; i >= 0; i--)
        {
        	sb.append(str.charAt(i));
        }
        String res = sb.toString();
        sb.delete(0, sb.length());
		return res;
	}
	*/
	public static void main(String[] args) {
		System.out.println(new StringBuilder().toString());
	}
	
	//左旋转字符串
	/*
	 * 翻转前面的字符串
	 * 翻转后面的字符串
	 * 翻转整个字符串
	 */
	
	public String LeftRotateString(String str,int n) {
		if(str == null) return null;
		int len = str.length();
		if(len == 0) return ""; // 注意健壮性判断 two
		n = n % str.length();  // 注意健壮性判断 two
		String str1 = reverse(str.substring(0, n));
		String str2 = reverse(str.substring(n));
		return reverse(str1+str2);
	}
	/**
		class Solution {
		public:
		    string LeftRotateString(string str, int n) {
		        int len = str.length();
		        if(len == 0) return "";
		        n = n % len;
		        str += str;
		        return str.substr(n, len);
		    }
		};
	 */
	//another idea
	public String LeftRotateString1(String str,int n) {
		if(str == null) return null;
		int len = str.length();
		if(len == 0) return "";
		n = n % len;
		str += str;
		return str.substring(n, len+n);
	}
	
	//my idea
	public String LeftRotateString2(String str,int n) {
        if(str == null) return null;
        if(str.length() == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int i = (n % str.length()); i < str.length(); i++)
        {
        	sb.append(str.charAt(i));
        }
        for(int i = 0; i< (n % str.length()); i++)
        {
        	sb.append(str.charAt(i));
        }
		return sb.toString();
    }
}


