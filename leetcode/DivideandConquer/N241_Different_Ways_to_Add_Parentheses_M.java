package leetcode.DivideandConquer;

import java.util.LinkedList;
import java.util.List;

/**
Given a string of numbers and operators, 
return all possible results from computing all the different possible ways to group numbers and operators. 
The valid operators are +, - and *.


Example 1
Input: "2-1-1".

((2-1)-1) = 0
(2-(1-1)) = 2
Output: [0, 2]


Example 2
Input: "2*3-4*5"

(2*(3-(4*5))) = -34
((2*3)-(4*5)) = -14
((2*(3-4))*5) = -10
(2*((3-4)*5)) = -10
(((2*3)-4)*5) = 10
Output: [-34, -14, -10, -10, 10]
 */

//NO
public class N241_Different_Ways_to_Add_Parentheses_M {

	//Not Mine
	//optimization : using hashmap to avoid calculate the same expression when input is same.
	//Map<String, List<Integer>> map = new HashMap<>();
	public List<Integer> diffWaysToCompute(String input) {
		
		//if(map.containsKey(input)) return map.get(input);
		List<Integer> ret = new LinkedList<Integer>();
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == '-' || input.charAt(i) == '*' || input.charAt(i) == '+') {
				String part1 = input.substring(0, i);
				String part2 = input.substring(i + 1);
				List<Integer> part1Ret = diffWaysToCompute(part1);
				List<Integer> part2Ret = diffWaysToCompute(part2);
				for (Integer p1 : part1Ret) {
					for (Integer p2 : part2Ret) {
						int c = 0;
						switch (input.charAt(i)) {
						case '+':
							c = p1 + p2;
							break;
						case '-':
							c = p1 - p2;
							break;
						case '*':
							c = p1 * p2;
							break;
						}
						ret.add(c);
					}
				}
			}
		}
		if (ret.size() == 0) {
			ret.add(Integer.valueOf(input));
		}
		//map.put(input, res);
		return ret;
	}
}
