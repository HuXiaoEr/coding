package JzOffer;

// over
public class N24_判断数组是否是二叉搜索树的后序遍历序列 {
	public boolean VerifySquenceOfBST(int [] sequence) {
		//attention
		//if(sequence == null) return false;
		if(sequence == null || sequence.length == 0) return false;
		return helpVerifySquenceOfBS(sequence, 0, sequence.length-1);
    }

	private boolean helpVerifySquenceOfBS(int[] sequence, int start, int end) {
		if(start >= end) return true;
		int root = sequence[end];
		int i = 0;
		for(i = start; i < end; i++)
		{
			if(sequence[i] > root) break;
		}
		for(int j = i + 1; j < end; j++)
		{
			if(sequence[j] < root) return false;
		}
		return helpVerifySquenceOfBS(sequence, start, i-1) && helpVerifySquenceOfBS(sequence, i, end-1);
	}
}
