package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
The gray code is a binary numeral system where two successive values differ in only one bit.

Given a non-negative integer n representing the total number of bits in the code, 
print the sequence of gray code. A gray code sequence must begin with 0.

For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:

00 - 0
01 - 1
11 - 3
10 - 2
Note:
For a given n, a gray code sequence is not uniquely defined.

For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.

For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 */

//NO
public class N89_Gray_Code_M {

    public List<Integer> grayCode(int n) {
    	List<Integer> res = new ArrayList<>();
    	res.add(0);
    	for(int i = 0; i < n; i++){
    		for(int j = res.size()-1; j >= 0; j--){
    			//res.add(res.get(j)+(int)Math.pow(2, i));
    			res.add(res.get(j) + (1 << i)); //better
    		}
    	}
    	return res;
    }
    
    //another idea
    // -> G(i) = i^ (i/2) : only one bit difference comes from i^(i>>1) and (i+1)^((i+1)>>1)
    public List<Integer> grayCode2(int n) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
        return result;
    }
}
