package leetcode.String;
/**
Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

Update (2015-02-10):
The signature of the C++ function had been updated. If you still see your function signature accepts a const char * argument, please click the reload button  to reset your code definition.

spoilers alert... click to show requirements for atoi.

Requirements for atoi:
The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned. 

 */


public class N8_String_to_Integer_M {

	// my code
    public int myAtoi(String str) {
        if(str == null || str.length() == 0) return 0;
        long res = 0;
        int idx = 0;
        while(idx < str.length() && str.charAt(idx) == ' ') idx++;
        boolean neg = false;
        if(str.charAt(idx) == '+'){
        	neg = false;
        	idx++;
        }
        else if(str.charAt(idx) == '-'){
        	neg = true;
        	idx++;
        }
        if(idx == str.length()) return 0;
        while(idx < str.length()){
        	char c = str.charAt(idx);
        	if(c > '9' || c < '0'){
                if(neg) res = -res;
                return (int)res;
            } 
        	res = 10 * res + (c - '0');
        	if(neg && -res < Integer.MIN_VALUE) return Integer.MIN_VALUE;
        	else if(!neg && res > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        	idx++;
        }
        if(neg) res = -res;
        return (int)res;
    }
    
    
    // not my code
    public int myAtoi2(String str) {
        int index = 0, sign = 1, total = 0;
        //1. Empty string
        if(str.length() == 0) return 0;

        //2. Remove Spaces
        while(str.charAt(index) == ' ' && index < str.length())
            index ++;

        //3. Handle signs
        if(str.charAt(index) == '+' || str.charAt(index) == '-'){
            sign = str.charAt(index) == '+' ? 1 : -1;
            index ++;
        }
        
        //4. Convert number and avoid overflow
        while(index < str.length()){
            int digit = str.charAt(index) - '0';
            if(digit < 0 || digit > 9) break;

            //check if total will be overflow after 10 times and add digit   // digit > 7
            if(Integer.MAX_VALUE/10 < total || Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;

            total = 10 * total + digit;
            index ++;
        }
        return total * sign;
    }
    
}
