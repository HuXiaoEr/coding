package JzOffer;

/**
 * 
 * @author 胡小二
 *
 *给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 *其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 *不能使用除法。
 */

// over
public class N52_构建乘积数组_不能用除法 {
	
	
	/*
	 * best
	 * 剑指的思路： 
	 * B[i]的值可以看作下图的矩阵中每行的乘积。
	 * 下三角用连乘可以很容求得，上三角，从下向上也是连乘。 
	 * 因此我们的思路就很清晰了，先算下三角中的连乘，即我们先算出B[i]中的一部分，
	 * 然后倒过来按上三角中的分布规律，把另一部分也乘进去。
	 */
	public int[] multiply(int[] A) {
		int length = A.length;
		int[] B = new int[length];
		if (length != 0) {
			B[0] = 1; // 计算下三角连乘            
			for (int i = 1; i < length; i++) {
				B[i] = B[i - 1] * A[i - 1];
			}
			int temp = 1; // 计算上三角            
			for (int j = length - 2; j >= 0; j--) {
				temp *= A[j + 1];
				B[j] *= temp;
			}
		}
		return B;
	}

	/*
	 * better o(n)time
	 */
	public int[] multiply2(int[] A) {
		if (A == null || A.length < 2)
			return null;
		int[] B = new int[A.length];
		int[] C = new int[A.length];
		int[] D = new int[A.length];
		C[0] = 1;
		for (int i = 1; i < A.length; i++) {
			C[i] = C[i - 1] * A[i - 1];
		}
		D[A.length - 1] = 1;
		for (int j = A.length - 2; j >= 0; j--) {
			D[j] = D[j + 1] * A[j + 1];
		}
		for (int i = 0; i < A.length; i++) {
			B[i] = C[i] * D[i];
		}
		return B;
	}

	public int[] multiply3(int[] A) {
		if (A == null || A.length < 2)
			return null;
		int[] B = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			B[i] = 1;
			for (int j = 0; j < A.length; j++) {
				if (j == i)
					continue;
				B[i] = B[i] * A[j];
			}
		}
		return B;
	}

}
