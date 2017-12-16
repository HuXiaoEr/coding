package JzOffer;

// 从后往前
public class N4_替换字符串中的空格 {
	
	//mine
	public String replaceSpace(StringBuffer str) {
		if(str == null) return null;
    	StringBuffer res = new StringBuffer();
    	for(int i = 0; i < str.length(); i++)
    	{
    		if(str.charAt(i) == ' ') res.append("%20");
    		else res.append(str.charAt(i));
    	}
		return res.toString();
    }
	
	//best
	
	public String replaceSpace2(StringBuffer str) {
		
		return null;
    }
}
