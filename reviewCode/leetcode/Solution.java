package reviewCode.leetcode;

public class Solution {
    public static  double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return 0.0;
        int len = nums1.length + nums2.length;
        if((len & 1) == 0) return (find(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2-1) + find(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2)) / 2;
        else return find(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, len/2);
    }
    private static double find(int[] nums1, int lo1, int hi1, int[] nums2, int lo2, int hi2, int k) {
    	if(lo1 > hi1) return nums2[lo2+k];
    	if(lo2 > hi2) return nums1[lo1+k];
    	
    	int mid1 = ((hi1 - lo1) >> 1) + lo1;
    	int mid2 = ((hi2 - lo2) >> 1) + lo2;
    	if(k >= mid1-lo1+mid2-lo2){
    		if(nums1[mid1] >= nums2[mid2]) return find(nums1, lo1, hi1, nums2, mid2+1, hi2, k-(mid2-lo2+1)-1);
    		else return find(nums1, mid1+1, hi1, nums2, lo2, hi2, k-(mid1-lo1+1)-1);
    	}
    	else{
    		if(nums1[mid1] >= nums2[mid2]) return find(nums1, lo1, mid1-1, nums2, lo2, hi2, k);
    		else return find(nums1, lo1, hi1, nums2, lo2, mid2-1, k);
    	}
	}
	public static void main(String[] args) {
		//System.out.println(new Solution().longestPalindrome55("babcbabcbaccba"));
		System.out.println(new Solution().findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
	}
	
	public String longestPalindrome55(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < s.length(); i++){
			sb.append("#" + s.charAt(i));
		}
		sb.append("#");
		StringBuilder res = new StringBuilder();
		String str = sb.toString();
		System.out.println(str);
		int[] p = new int[str.length()];
		int l = 0;
		int c = 0;
		int r = 0;
		p[0] = 0;
		int max = 0;
		for(int i = 1; i < str.length(); i++){
			if(i > r){
				c = i;
				int len = expand(str, i, i);
				l = c - len;
				r = c + len;
				p[c] = len;
				if(p[c] > p[max]) max = c;
			}
			else{
				int isym = 2 * c - i;
				if(p[isym] < r - i) p[i] = p[isym];  // attention : <   not <=
				else{
					c = i;
					int len = expand(str, i, r);
					l = c - len;
					r = c + len;
					p[c] = len;
					if(p[c] > p[max]) max = c;
				}
			}
		}
		if(str.charAt(max) == '#'){
			System.out.println("***" + max);
			for(int i = max-p[max]+1; i < max + p[max]; i += 2) res.append(str.charAt(i));
		}
		else{
			System.out.println("&&&");
			for(int i = max-p[max]+1; i < max + p[max]; i += 2) res.append(str.charAt(i));
		}
		return res.toString();
	}
	private int expand(String str, int i, int r) {
		
		int len = r - i;
		int lo = 2 * i - r - 1;
		int hi = r + 1;
		//System.out.println(len + " " + lo + " " + hi);
		while(lo >= 0 && hi < str.length() && str.charAt(lo) == str.charAt(hi)){
			len++;
			lo--;
			hi++;
		}
		return len;
	}

}
