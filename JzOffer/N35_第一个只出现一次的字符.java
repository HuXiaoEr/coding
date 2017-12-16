package JzOffer;

public class N35_第一个只出现一次的字符 {
	 public int FirstNotRepeatingChar(String str) {
		 int[] hashTable = new int[256];
		 for(int i = 0; i < str.length(); i++)
		 {
			 hashTable[str.charAt(i)]++;
		 }
		 for(int i = 0; i < str.length(); i++)
		 {
			 if(hashTable[str.charAt(i)] == 1) return i;
		 }
		 return -1;
	 }
}
