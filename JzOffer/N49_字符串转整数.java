package JzOffer;

/**
 * 
 * @author 胡小二
 * 
 *将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。 
 *数值为0或者字符串不是一个合法的数值则返回0
 */

// 注意是★整数，不是数值。
// over
public class N49_字符串转整数 {
	
	/*
	 * null
	 * ""
	 * 只有一个"+" "-"
	 * 溢出
	 * 
	 * 首位是"+" "-"号
	 * 首位是数字
	 * 首位是其它
	 * 
	 * 除去首位，包含数字外的其它
	 * 
	 * 定义全局变量 标识合法的0或不合法的0
	 * 
	 */
	
	// 只有一个"+" "-" 溢出 定义全局变量 标识合法的0或不合法的0
	
	
	// 1.字符串为空
	// 2.只有一个正或负号
	// 3.整数上下溢出 Integer.MAX_VALUE (2^31-1)  Integer.MIN_VALUE(-2^31)
	boolean inValid;
	public int StrToInt(String str) {
		if (str == null || str.length() == 0){
			inValid = true;
			return 0;
		}
		char[] c = str.toCharArray();
		boolean minus = false;
		long num = 0;
		int i = 0;
		// 数组溢出：下标大于数组长度！比如c.length ==1,当有c[1]出现时则数组溢出
		if (c[i] == '+') ++i;
		else if (c[i] == '-') {
			++i;
			minus = true;
		}
		if (i < c.length) num = StrToIntCore(c, minus, i);  //★
		else {
			inValid = true;
			return 0;
		}
		return (int)num;
	}
	private int StrToIntCore(char[] str, boolean minus, int i) {
		long num = 0;
		for (int j = i; j < str.length; j++) {
			if (str[j] >= '0' && str[j] <= '9') {
				int flag = minus ? -1 : 1;
				// str[j]-'0' 是为了把字符转为int数字
				num = num * 10 + flag * (str[j] - '0');
				if ((!minus && num > Integer.MAX_VALUE) || (minus && num < Integer.MIN_VALUE)) {
					inValid = true;
					num = 0;
					break;
				}
			}
			else {
				inValid = true;
				num = 0;
				break;
			}
		}
		return (int)num;
	}
	
	// better ?
	private long StrToInt(String str, int idx, boolean neg) {
		long res = 0;
		int temp = 0;
		for(int i = idx; i < str.length(); i++){
			System.out.println(res);
			if(!Character.isDigit(str.charAt(i))) return 0;
			temp  = str.charAt(i) - '0';
			if(!neg) res = res * 10 + temp;
			else res = res * 10 - temp;
			if((!neg && res > Integer.MAX_VALUE) || (neg && res < Integer.MIN_VALUE)) return 0;
		}
		return res;
	}
}
