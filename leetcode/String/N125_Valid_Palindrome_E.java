package leetcode.String;
/**
Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

For example,
"A man, a plan, a canal: Panama" is a palindrome.
"race a car" is not a palindrome.

Note:
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */

// NO
public class N125_Valid_Palindrome_E {
	
	// wrong
    public boolean isPalindrome(String s) {
        if(s == null || s.length() == 0) return true;
        return isPalindrome(s, 0, s.length()-1);
    }
	private boolean isPalindrome(String s, int lo, int hi) {
		int i = lo;
		int j = hi;
		while(i < j){
			// java.lang.StringIndexOutOfBoundsException
			while(!((('A' <= s.charAt(i)) && (s.charAt(i) <= 'Z')) 
					|| (('a' <= s.charAt(i)) && (s.charAt(i) <= 'z')) 
					|| (('0' <= s.charAt(i)) && (s.charAt(i) <= '9')))) 
				i++;
			// java.lang.StringIndexOutOfBoundsException
			while(!((('A' <= s.charAt(i)) && (s.charAt(i) <= 'Z')) 
					|| (('a' <= s.charAt(i)) && (s.charAt(i) <= 'z')) 
					|| (('0' <= s.charAt(i)) && (s.charAt(i) <= '9')))) 
				j++;
			if(!(s.charAt(i) == s.charAt(j) || Math.abs(s.charAt(i)-s.charAt(j)) == Math.abs('a'- 'A'))) return false;
		}
		return false;
	}
	
	// not mine
	public boolean isPalindrome2(String s) {
		if (s.isEmpty()) {
			return true;
		}
		int head = 0, tail = s.length() - 1;
		char cHead, cTail;
		while (head <= tail) {
			cHead = s.charAt(head);
			cTail = s.charAt(tail);
			if (!Character.isLetterOrDigit(cHead)) { // бя
				head++;
			} else if (!Character.isLetterOrDigit(cTail)) {
				tail--;
			} else {
				if (Character.toLowerCase(cHead) != Character.toLowerCase(cTail)) { // бя
					return false;
				}
				head++;
				tail--;
			}
		}
		return true;
	}

	 public boolean isPalindrome3(String s) {
	        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
	        String rev = new StringBuffer(actual).reverse().toString();
	        return actual.equals(rev);
	    }
}
