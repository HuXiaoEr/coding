package reviewCode.leetcode;

/**
 Given n non-negative integers representing an elevation map where the width of each bar is 1,
 compute how much water it is able to trap after raining.

 For example,
 Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.


 The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1].
 In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image! */

//NO
//two pointers
public class N42_Trapping_Rain_Water_H {


    public int trap(int[] A){
        int a=0;
        int b=A.length-1;
        int max=0;
        int leftmax=0;
        int rightmax=0;
        while(a<=b){
            leftmax=Math.max(leftmax,A[a]);
            rightmax=Math.max(rightmax,A[b]);
            if(leftmax<rightmax){
                max+=(leftmax-A[a]);       // leftmax is smaller than rightmax, so the (leftmax-A[a]) water can be stored
                a++;
            }
            else{
                max+=(rightmax-A[b]);
                b--;
            }
        }
        return max;
    }


        public int trap5(int[] height) {
            int n = height.length, l = 0, r = n - 1, water = 0, minHeight = 0;
            while (l < r) {
                while (l < r && height[l] <= minHeight)
                    water += minHeight - height[l++];
                while (r > l && height[r] <= minHeight)
                    water += minHeight - height[r--];
                minHeight = Math.min(height[l], height[r]);
            }
            return water;
        }


    public int trap2(int[] A) {
        if(A.length==0) return 0;
        int[] A1=new int[A.length];
        int[] A2=new int[A.length];
        int max=A[0];
        for(int i =0;i<A.length;i++) {
            if(A[i]>max) max=A[i];
            A1[i]=max;
        }
        max=A[A.length-1];
        for(int i=A.length-1;i>=0;i--){
            if(A[i]>max) max=A[i];
            A2[i]=max;
        }
        int sum=0;
        for(int i=0;i<A.length;i++){
            sum=sum+Math.min(A1[i],A2[i])-A[i];
        }
        return sum;
    }

    public int trap3(int[] height) {
        if (height.length <= 2) return 0;
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        for (int i = 0; i < height.length; i++) {
            if (height[i] > max) {
                max = height[i];
                maxIndex = i;
            }
        }
        int leftMax = height[0];
        int water = 0;
        for (int i = 1; i < maxIndex; i++) {
            if (height[i] > leftMax) {
                leftMax = height[i];
            } else {
                water += leftMax - height[i];
            }
        }
        int rightMax = height[height.length - 1];
        for (int i = height.length - 2; i > maxIndex; i--) {
            if (height[i] > rightMax) {
                rightMax = height[i];
            } else {
                water += rightMax - height[i];
            }
        }

        return water;
    }

}
