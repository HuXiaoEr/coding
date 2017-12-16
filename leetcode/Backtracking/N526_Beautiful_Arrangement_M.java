package leetcode.Backtracking;
/**
Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers successfully 
if one of the following is true for the ith position (1 ¡Ü i ¡Ü N) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Now given N, how many beautiful arrangements can you construct?

Example 1:
Input: 2
Output: 2
Explanation: 

The first beautiful arrangement is [1, 2]:

Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).

Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).

The second beautiful arrangement is [2, 1]:

Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).

Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
Note:
N is a positive integer and will not exceed 15.
 */

//YES
public class N526_Beautiful_Arrangement_M {
	
	//my code
    public int countArrangement(int N) {
    	int[] res = new int[1];
    	int[] num = new int[N+1];
    	for(int i = 1; i <= N; i++) num[i] = i;
    	count(num, 1, res);
    	return res[0];
    }
	private void count(int[] num, int start, int[] res) {
		if(start == num.length) res[0]++;
		for(int i = start; i < num.length; i++){
			if(isBeautiful(start, num[i])){
				swap(num, start, i);
				count(num, start+1, res);
				swap(num, start, i);
			}
		}
	}
	private void swap(int[] num, int i, int j) {
		int temp = num[i];
		num[i] = num[j];
		num[j] = temp;
	}
	private boolean isBeautiful(int num1, int num2) {
		return ((num1 % num2) == 0) || ((num2 % num1) == 0);
	}
	
	//another idea
	int count = 0;
    public int countArrangement2(int N) {
        if (N == 0) return 0;
        helper(N, 1, new int[N + 1]);
        return count;
    }
    private void helper(int N, int pos, int[] used) {
        if (pos > N) {
            count++;
            return;
        }
        for (int i = 1; i <= N; i++) {
            if (used[i] == 0 && (i % pos == 0 || pos % i == 0)) {
                used[i] = 1;
                helper(N, pos + 1, used);
                used[i] = 0;
            }
        }
    }
}
