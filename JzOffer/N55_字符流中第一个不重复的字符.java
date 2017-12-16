package JzOffer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 
 * @author 胡小二
 *
 *请实现一个函数用来找出字符流中第一个只出现一次的字符。
 *例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 *当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。 
 *
 *如果当前字符流没有存在出现一次的字符，返回#字符。
 */

// over
public class N55_字符流中第一个不重复的字符 {
	
	
/**
	提供一个高效的算法： 
  	思路：时间复杂度O（1），空间复杂度O（n） 
          1、用一个128大小的数组统计每个字符出现的次数 
          2、用一个队列，如果第一次遇到ch字符，则插入队列；其他情况不在插入 
          3、求解第一个出现的字符，判断队首元素是否只出现一次，如果是直接返回，否则删除继续第3步骤 

  	分析：可以看出相同的字符只被插入一次，最多push128个，同时大多数情况会直接返回队首。所以大家不要被里面的while循环迷惑
*/
	Queue<Character> queue = new LinkedList<Character>();
	int[] hashtable = new int[256];

	public void Insert(char ch) {
		hashtable[ch]++;
		if(hashtable[ch] == 1) queue.add(ch);
	}
	public char FirstAppearingOnce() {
		while(!queue.isEmpty() && hashtable[queue.peek()] > 1) queue.poll();
		if(queue.isEmpty()) return '#';
		return queue.peek();
	}
	
	StringBuffer s = new StringBuffer();
	// Insert one char from stringstream    
	public void Insert2(char ch) {
		s.append(ch);
		if (hashtable[ch] == 0)
			hashtable[ch] = 1;
		else
			hashtable[ch] += 1;
	} 
	// return the first appearence once char in current stringstream    
	public char FirstAppearingOnce2() {
		char[] str = s.toString().toCharArray();
		for (char c : str) {
			if (hashtable[c] == 1)
				return c;
		}
		return '#';
	}

    public static void main(String[] args) {
    	N55_字符流中第一个不重复的字符 n55 = new N55_字符流中第一个不重复的字符();
		n55.Insert('g');
		n55.Insert('o');
		n55.Insert('o');
		n55.Insert('g');
		//System.out.println(n55.FirstAppearingOnce());
		//System.out.println(n55.FirstAppearingOnce());
		//System.out.println(n55.FirstAppearingOnce());
		System.out.println((char)253333333);
	}
}
