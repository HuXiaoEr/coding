package reviewCode.leetcode;

//YES HALF TWO
public class N198_House_Robber_E {
	public int rob(int[] nums) {
		if(nums == null || nums.length == 0) return 0;
		//if(nums.length == 1) return nums[0];
		//if(nums.length == 2) return Math.max(nums[0], nums[1]);
		int[] dpy = new int[nums.length];
		int[] dpn = new int[nums.length];
		dpy[0] = nums[0];
		dpn[0] = 0;
		for(int i = 1; i < nums.length; i++){
			dpy[i] = nums[i] + dpn[i-1];
			dpn[i] = Math.max(dpy[i-1], dpn[i-1]);
		}
		return Math.max(dpy[nums.length-1], dpn[nums.length-1]);
	}

}
