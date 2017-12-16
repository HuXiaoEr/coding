package leetcode.binarysearch;

/**
 Given an array of integers sorted in ascending order, find the starting and ending position of a given target value.

 Your algorithm's runtime complexity must be in the order of O(log n).

 If the target is not found in the array, return [-1, -1].

 For example,
 Given [5, 7, 7, 8, 8, 10] and target value 8,
 return [3, 4].
 */

//YES
//binary search
public class N34_Search_for_a_Range_M {

    //My code
    public int[] searchRange(int[] nums, int target) {
        if(nums == null) return new int[]{-1, -1};
        int s = -1;
        int e = -1;
        int lo = 0;
        int hi = nums.length-1;
        while(lo <= hi){
            int mid = lo + ((hi-lo) >> 1);
            if(target < nums[mid]) hi = mid-1;
            else if(target > nums[mid]) lo = mid+1;
            else if(mid-1 >= 0 && nums[mid-1] == target) hi = mid-1; //鈽� 娉ㄦ剰mid-1 IndexOutOfBoundary
            else{
                s = mid;
                break;
            }
        }
        if(s == -1) return new int[]{-1, -1};
        lo = 0;
        hi = nums.length-1;
        while(lo <= hi){
            int mid = lo + ((hi-lo) >> 1);
            if(target < nums[mid]) hi = mid-1;
            else if(target > nums[mid]) lo = mid+1;
            else if(mid+1 < nums.length && nums[mid+1] == target) lo = mid+1; //鈽� 娉ㄦ剰mid+1 IndexOutOfBoundary
            else{
                e = mid;
                break;
            }
        }
        return new int[]{s, e};
    }

    //best
    public int[] searchRange3(int[] A, int target) {
        int start = N34_Search_for_a_Range_M.firstGreaterEqual(A, target);
        if (start == A.length || A[start] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{start, N34_Search_for_a_Range_M.firstGreaterEqual(A, target + 1) - 1};
    }
    //find the first number that is greater than or equal to target.
    //could return A.length if target is greater than A[A.length-1].
    //actually this is the same as lower_bound in C++ STL.
    private static int firstGreaterEqual(int[] A, int target) {
        int low = 0, high = A.length;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            //low <= mid < high
            if (A[mid] < target) {
                low = mid + 1;
            } else {
                //should not be mid-1 when A[mid]==target.
                //could be mid even if A[mid]>target because mid<high.
                high = mid;
            }
        }
        return low;
    }

    //better
    public int[] searchRange2(int A[], int target) {
        int[] ret = new int[]{-1, -1};
        if(A == null || A.length == 0) return ret;
        int n = A.length;
        int i = 0, j = n - 1;
        // Search for the left one
        while (i < j)
        {
            int mid = (i + j) /2;
            if (A[mid] < target) i = mid + 1;
            else j = mid;
        }
        if (A[i]!=target) return ret;
        else ret[0] = i;

        // Search for the right one
        j = n-1;  // We don't have to set i to 0 the second time.
        while (i < j)
        {
            int mid = (i + j) /2 + 1;	// Make mid biased to the right
            if (A[mid] > target) j = mid - 1;
            else i = mid;				// So that this won't make the search range stuck.
        }
        ret[1] = j;
        return ret;
    }
}
