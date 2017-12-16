package JzOffer;

//数字排列 over
public class N12_打印1到最大的n位数_大数问题 {
	
	// 解法一：数字排列
	public static void printN(int n)
	{
		if(n < 0) return;
		helpPrintN(n, "");
	}
	private static void helpPrintN(int n, String string) {
		//if(n < 1) print(string);
		if(n < 1) 
		{
			print(string);
			return;
		}
		for(int i = 0; i <= 9; i++)
		{
			helpPrintN(n-1, string + i);
		}
	}
	private static void print(String string) {
		int i = 0;
		for(i = 0; i < string.length(); i++)
		{
			if(string.charAt(i) != '0') break;
		}
		if(i != string.length())
			System.out.println(string.substring(i, string.length()));
	}
	
	public static void main(String[] args) {
//		//N_12.printN(2);
//		String str = add2("999999999993456778899999999","229222222222222222222222222222");
//		String str1 = add("999999999993456778899999999","229222222222222222222222222222");
//        System.out.println(str);
//        System.out.println(str1);
//        System.out.println(str.equals(str1));

		String str = sub("99998","99999");
        System.out.println(str);
	}
	
	
	
	/**
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> args
     */
     
    /**
     * 用字符串模拟两个大数相加
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> n1 加数1
     * <a href="http://home.cnblogs.com/u/309701/" target="_blank">@param</a> n2 加数2
     * <a href="http://home.cnblogs.com/u/69429/" target="_blank">@return</a>   相加结果
     */
	// 解法二：字符串模拟数字的加法
	/*
	 * 全为正数
	 * 全为负数
	 * 一正一负
	 * 
	 * 不完全符合
	 * http://www.cnblogs.com/gl-developer/p/6506230.html
	 */
	
	// 两正数 or 两负数
    public static String add(String n1, String n2) {
    	StringBuilder res = new StringBuilder();
    	char[] add1 = new StringBuilder(n1).reverse().toString().toCharArray();
    	char[] add2 = new StringBuilder(n2).reverse().toString().toCharArray();
    	int maxLen = add1.length > add2.length ? add1.length : add2.length;
    	int flow = 0;
    	for(int i = 0; i < maxLen; i++){
    		int i1 = i < add1.length ? add1[i] - '0' : 0;
    		int i2 = i < add2.length ? add2[i] - '0' : 0;
    		int sum = i1 + i2 + flow;
    		if(sum >= 10){
    			flow = 1;
    			res.append(sum-10);
    		}
    		else{
    			flow = 0;
    			res.append(sum);
    		}
    	}
    	if(flow == 1) res.appendCodePoint(1);
    	return res.reverse().toString();
    }
    
    // 字符串模拟数字相减
    public static String sub(String n1, String n2) {
    	StringBuilder res = new StringBuilder();
    	char[] add1 = new StringBuilder(n1).reverse().toString().toCharArray();
    	char[] add2 = new StringBuilder(n2).reverse().toString().toCharArray();
    	int maxLen = add1.length > add2.length ? add1.length : add2.length;
    	int[] sub = new int[maxLen];
    	boolean neg = false;
    	if(add1.length < add2.length) neg = true;
    	else if(add1.length == add2.length){
    		for(int i = 0; i < add1.length; i++){
    			if(add1[i] < add2[2]){
    				neg = true;
    				break;
    			}
    		}
    	}
    	for(int i = 0; i < maxLen; i++){
    		int i1 = i < add1.length ? add1[i] - '0' : 0;
    		int i2 = i < add2.length ? add2[i] - '0' : 0;
    		if(neg) sub[i] = i2 - i1;
    		else sub[i] = i1 - i2;
    	}
    	for(int i = 0; i < maxLen-1; i++){ // i < maxLen-1
    		if(sub[i] < 0){
    			sub[i] += 10;
    			sub[i+1] -= 1;
    		}
    	}
    	boolean beg = true;
    	if(neg) res.append("-");
    	for(int i = maxLen-1; i >= 0; i--){
    		if(sub[i] == 0 && beg) continue;
    		else beg = false;
    		res.append(sub[i]);
    	}
    	return res.toString();
    }
    
    
    
    
    // no 
    public static String add2(String n1, String n2) {
    	
        StringBuffer result = new StringBuffer();
 
        // 1、反转字符串
        n1 = new StringBuffer(n1).reverse().toString();
        n2 = new StringBuffer(n2).reverse().toString();
        
//        System.out.println(n1 + " " + n2);
        int len1 = n1.length();
        int len2 = n2.length();
        int maxLen = len1 > len2 ? len1 : len2;
        boolean nOverFlow = false; // 是否越界
 
        // 2.把两个字符串补齐，即短字符串的高位用0补齐
        if (len1 < len2) {
            for (int i = len1; i < len2; i++) {
                n1 += "0";
            }
        } else if (len1 > len2) {
            for (int i = len2; i < len1; i++) {
                n2 += "0";
            }
        }
//        System.out.println(n1 + " " + n2);
 
        // 3.把两个正整数相加，一位一位的加并加上进位
        int nTakeOver = 0; // 溢出数量
        for (int i = 0; i < maxLen; i++) {
            int nSum = Integer.parseInt(n1.charAt(i) + "") + Integer.parseInt(n2.charAt(i) + "");
            if ((nSum + nTakeOver) >= 10) {
                if (i == (maxLen - 1)) {
                    nOverFlow = true;
                }
                result.append(nSum + nTakeOver - 10);
                nTakeOver=1;
            } else {
                result.append(nSum + nTakeOver);
                nTakeOver = 0;
            }
        }
 
        // 如果溢出的话表示位增加了
        if (nOverFlow) {
            result.append(1);
        }
        return result.reverse().toString();
    }

}
