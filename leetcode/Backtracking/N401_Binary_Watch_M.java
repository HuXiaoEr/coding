package leetcode.Backtracking;

import java.util.ArrayList;
import java.util.List;

/**
A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).

Each LED represents a zero or one, with the least significant bit on the right.


For example, the above binary watch reads "3:25".

Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.

Example:

Input: n = 1
Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
Note:
The order of output does not matter.
The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */

//NO
public class N401_Binary_Watch_M {
	
	//backtracking
	public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);
            List<Integer> list2 = generateDigit(nums2, num - i);
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }
    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }
    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
	
    
    //better for understanding backtracking
	String[][] hour = { { "0" }, { "1", "2", "4", "8" }, { "3", "5", "6", "9", "10" }, { "7", "11" } };
	String[][] minute = { { "00" }, // 1
			{ "01", "02", "04", "08", "16", "32" }, // 6
			{ "03", "05", "06", "09", "10", "12", "17", "18", "20", "24", "33", "34", "36", "40", "48" }, // 15
			{ "07", "11", "13", "14", "19", "21", "22", "25", "26", "28", "35", "37", "38", "41", "42", "44", "49",
					"50", "52", "56" }, // 20
			{ "15", "23", "27", "29", "30", "39", "43", "45", "46", "51", "53", "54", "57", "58" }, // 14
			{ "31", "47", "55", "59" } }; // 4
	public List<String> readBinaryWatch3(int num) {
		List<String> ret = new ArrayList();
		for (int i = 0; i <= 3 && i <= num; i++) {
			if (num - i <= 5) {
				for (String str1 : hour[i]) {
					for (String str2 : minute[num - i]) {
						ret.add(str1 + ":" + str2);
					}
				}
			}
		}
		return ret;
	}
	
	//another idea  similar to mine
	final int[] watch = {1,2,4,8,1,2,4,8,16,32};
    public List<String> readBinaryWatch4(int num) {
            List<String> list = new ArrayList<String>();
            if(num>=0) read_recursion(num, 0, list, 0 ,0);
            return list;
    }
    private void read_recursion(int num, int start, List<String> list, int hour, int minute){
    	if(num <= 0) {
    	    if(hour<12 && minute<60){
	    	        if(minute<10) list.add(hour+":0"+minute);
	    	        else list.add(hour+":"+minute);
    	    }
    	} else {
	            for(int i=start; i<watch.length; i++){
	    		if(i<4) read_recursion(num-1, i+1, list, hour+watch[i], minute);
	    		else read_recursion(num-1, i+1, list, hour, minute+watch[i]);
	            }
            }
    }
	
    //straightforward solution
	public List<String> readBinaryWatch2(int num) {
	    List<String> times = new ArrayList<>();
	    for (int h=0; h<12; h++)
	        for (int m=0; m<60; m++)
	            if (Integer.bitCount(h * 64 + m) == num) //Integer.bitCount(m + (h << 6)) or Integer.bitCount(m) + Integer.bitCount(h)
	                times.add(String.format("%d:%02d", h, m));
	    return times;        
	}
}
