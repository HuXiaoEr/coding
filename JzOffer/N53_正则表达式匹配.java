package JzOffer;

/**
 * 
 * @author 胡小二
 *
 *请实现一个函数用来匹配包括'.'和'*'的正则表达式。
 *模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 
 *在本题中，匹配是指字符串的所有字符匹配整个模式。
 *例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
/*
 * str到头，pattern不到头  eg : "" "c*" ...
 * "aaa","a*a"
 */

/*
1 当模式中的第二个字符不是“*”时：
	字符串第一个字符和模式中的第一个字符相匹配：字符串和模式都后移一个字符
	不匹配或字符串越界 false
2 当模式中的第二个字符是“*”时：
	2.1 字符串第一个字符跟模式第一个字符不匹配 或 字符串越界
		(2.1.1) 模式后移2个字符
	2.2 字符串第一个字符跟模式第一个字符匹配	
		(2.2.1) 模式后移2字符，相当于x*被忽略；
		(2.2.2) 字符串后移1字符，模式后移2字符；
		(2.2.3) 字符串后移1字符，模式不变，即继续匹配字符下一位，因为*可以匹配多位；

 */

// over
public class N53_正则表达式匹配 {
	public boolean match(char[] str, char[] pattern) {
		if(str == null || pattern == null) return false;
		return match(0, 0, str, pattern);
	}
	private boolean match(int i, int j, char[] str, char[] pattern) {
		if(i == str.length && j == pattern.length) return true;
		if(i != str.length && j == pattern.length) return false; // ★
		if(j+1 < pattern.length && pattern[j+1] == '*') { // ★
			if(i < str.length && (pattern[j] == '.' || pattern[j] == str[i]))
				return match(i, j+2, str, pattern)
						|| match(i+1, j+2, str, pattern)
						|| match(i+1, j, str, pattern);
			else return match(i, j+2, str, pattern);
		}
		//模式下一个不是*
		// i < str.length
		if(i < str.length && (pattern[j] == '.' || pattern[j] == str[i])) return match(i+1, j+1, str, pattern); 
		else return false;

	}
}