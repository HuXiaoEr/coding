package JzOffer;

/*
 * e E 均可以
 * "-.123" true
 * "+.123" true
 * "123.45e+6" true
 * "123.45e-6" true
 * "+e2" false;
 * "-e2" false;
 * "12e+5.4" false
 * "12e-5.4" false
 * "e." false
 * ".e" false
 * "12e" false
 */
public class N54_判断字符串是否标识数值 {
	
	public static boolean isNumeric(char[] str) {
		String string = String.valueOf(str);
		return string.matches("[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?");
	}
	public static void main(String[] args) {
		System.out.println(isNumeric("".toCharArray()));
	}
	public boolean isNumeric2(String str) {
		try {
			double re = Double.parseDouble(new String(str));
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean isNumeric3(char[] str) {
		if(str == null) return false;
		if(str.length == 1 && (str[0] < '0' || str[0] > '9')) return false;
		//if(str.length >= 2 && (str[0] ==  '+' || str[0] == '-') && (str[1] == '.' || str[1] == 'e'))
			//return false;
		if(str.length >= 2 && (str[0] ==  '+' || str[0] == '-') && (str[1] == 'e')) //"+e2" false; "+.123" true
			return false;

		boolean pointFlag = false;
		boolean eFlag = false;
		for(int i = 0; i < str.length; i++)
		{
			if(i == 0 && str[i] != '+' && str[i] != '-' && (str[i] > '9' || str[i] < '0'))
				return false;
			if(i != 0 && (str[i] > '9' || str[i] < '0'))
			{
				if(str[i] == '.' && i == str.length-1) return false;  //"12." false
				if((str[i] == 'e' || str[i] == 'E') && i == str.length-1) return false;  //"12e" false
				if(str[i] == '.' && ((str[i] == 'e' || str[i] == 'E') || (str[i] == 'e' || str[i] == 'E'))) return false; //"e." ".e" false
				if((str[i] == 'e' || str[i] == 'E') && (str[i+1] == '.' || str[i-1] == '.')) return false; //"e." ".e" false
				if(str[i] == '.' && eFlag) return false;   //"12e+5.4" false
				
				
				if(!pointFlag && str[i] == '.') pointFlag = true;
				else if(!eFlag && (str[i] == 'e' || str[i] == 'E')) eFlag = true;
				else if(pointFlag && (str[i] == 'e' || str[i] == 'E')) eFlag = true;
				else return false;
				if((str[i] == 'e' || str[i] == 'E') && (str[i+1] == '+' || str[i+1] == '-')) //"123.45e+6" true
				{
					eFlag = true;
					i++;
				}
			}
		}
		return true;
    }
}
