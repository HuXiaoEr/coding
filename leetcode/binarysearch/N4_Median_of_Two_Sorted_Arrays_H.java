package leetcode.binarysearch;
/**
There are two sorted arrays nums1 and nums2 of size m and n respectively.

Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

Example 1:
nums1 = [1, 3]
nums2 = [2]

The median is 2.0
Example 2:
nums1 = [1, 2]
nums2 = [3, 4]

The median is (2 + 3)/2 = 2.5
 */

//理解题意
/*
median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. 
So the median is the mean of the two middle value.

Examples:

[2,3,4] , the median is 3

[2,3], the median is (2 + 3) / 2 = 2.5

For this problem, what you should do is find the median after merging this two ordered array.
 */
public class N4_Median_of_Two_Sorted_Arrays_H {
    
	// https://discuss.leetcode.com/topic/5728/share-one-divide-and-conquer-o-log-m-n-method-with-clear-description
	
	//
    public double findMedianSortedArrays3(int[] nums1, int[] nums2) {
        //Get the middle element
        int mid = (nums2.length+nums1.length+1)/2;
        //Find that middle element
        double res = getkth(nums1, nums2, mid);
        //If the combined length is even then find mid+1 element as well
        if((nums2.length+nums1.length) % 2 == 0) {
            res += getkth(nums1, nums2, mid+1);
            //Find the average of two elements
            res = res/2;
        }
        return res;
    }
    public int getkth(int[] A, int[] B, int k) {
        //Make sure array A is the smaller array
        if(B.length < A.length ) return getkth(B, A, k);
        //If smaller array is empty, simply return the value from second array
        if(A.length == 0) return B[k-1];
        //If k is 1, then it must be the smaller of first element of the array
        if(k == 1) return Math.min(A[0], B[0]);
        
        //Get the index for array A to compare
        int i = Math.min((A.length), k/2);
        //Index for array B must be such that i + j = k
        int j = k - i;
        
        //Remove the smaller elemets from the array A if, ith index of A is smaller than jth index of B
        if(A[i- 1] <  B[j-1]) {
            int[] newA = new int[A.length - i];
            //Make a new array and copy the rest of the array elements
            System.arraycopy(A, i, newA, 0, (A.length - i));
            return getkth(newA, B, k - i);
        }
        else {
            int[] newB = new int[B.length - j];
            System.arraycopy(B, j, newB, 0, (B.length - j));
            return getkth(A, newB, k - j);
        }
    }
    
    //https://discuss.leetcode.com/topic/16797/very-concise-o-log-min-m-n-iterative-solution-with-detailed-explanation/2
    //未懂
    //O(log(min(M,N)))
    public double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int N1 = nums1.length, N2 = nums2.length;
        if (N1 > N2) return findMedianSortedArrays2(nums2, nums1);
        
        int lo = 0, hi = 2 * N1;
        while (lo <= hi) {
            int C1 = (lo + hi) / 2;
            int C2 = N1 + N2 - C1;
            
            double L1 = (C1 == 0) ? Integer.MIN_VALUE : nums1[(C1-1)/2];
            double R1 = (C1 == 2*N1) ? Integer.MAX_VALUE : nums1[C1/2];
            double L2 = (C2 == 0) ? Integer.MIN_VALUE : nums2[(C2-1)/2];
            double R2 = (C2 == 2*N2) ? Integer.MAX_VALUE : nums2[C2/2];
            
            if (L1 > R2) hi = C1 - 1;
            else if (L2 > R1) lo = C1 + 1;
            else return (Math.max(L1, L2) + Math.min(R1, R2)) / 2;
        }
        return -1;
    }
}
