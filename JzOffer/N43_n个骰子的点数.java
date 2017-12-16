package JzOffer;

public class N43_n个骰子的点数 {
	
	
	//over
	//better
	public static void printPosibility(int n)
	{
		int[][] data = new int[2][maxValue*n+1];
		int flag = 0;
		for(int i = 1; i <= maxValue; i++) { // ★
			data[flag][i] = 1;
		}
		for(int i = 2; i <= n; i++){
			for(int j = 0; j < i; j++) data[1-flag][j] = 0;  // ★
			for(int j = i; j <= maxValue*i; j++){
				for(int k = 1; k <= maxValue && j-k >= 0; k++)
					data[1-flag][j] += data[flag][j-k];
			}
			flag = 1 - flag;
		}
		double total = Math.pow(maxValue, n);
		for(int i = n; i <= maxValue*n; i++)
		{
			System.out.println(i + "的概率  = " + data[flag][i]/total);
		}
	}
	
	
	// over
	static int maxValue = 6;
	public static void printPosibility2(int n)
	{
		int[] data = new int[maxValue*n-n+1];
		printPosibility(n, data);
		double total = Math.pow(maxValue, n);
		for(int i = 0; i < maxValue*n-n+1; i++)
		{
			System.out.println(i+n + "的概率  = " + data[i]/total);
		}
	}
	private static void printPosibility(int n, int[] data) {
		for(int i = 1; i <= maxValue; i++)
		{
			printPosibility(n, n, i, data);
		}
	}
	private static void printPosibility(int original, int current, int sum, int[] data) {
		if(current == 1)
		{
			data[sum-original]++;
		}
		else
		{
			for(int i = 1; i <= maxValue; i++)
			{
				printPosibility(original, current-1, sum+i, data);
			}
		}
	}
	
	public static void main(String[] args) {
		//int[] res = new int[1];
		//for(int i = 1; i <=3; i++) res[0]++;
		//System.out.println(res[0]);
		new N43_n个骰子的点数().printPosibility(3);
	}
}
