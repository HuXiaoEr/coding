package bytedance;

import java.util.*;

public class ����ջ {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNext()) {
			int n = in.nextInt();
			int[] a = new int[n + 1];
			in.nextLine();
			for (int i = 0; i < n; i++) {
				a[i] = in.nextInt();
			}
			a[n] = -1; // ����������һ��Ԫ��-1��ʹ��ջ��û��ջ��Ԫ��ȫ����ջ
			int max = 0;
			Stack<Integer> stack = new Stack<>();
			for (int i = 0; i <= n; i++) {
				while (!stack.isEmpty() && a[i] <= a[stack.peek()]) {
					int temp = a[stack.pop()]; // ��ջԪ����Ϊ�����������Сֵ
					int start = stack.isEmpty() ? -1 : stack.peek();
					int sum = 0;
					for (int j = start + 1; j < i; j++) { // ����������������Ԫ�صĺ�
						sum += a[j];
					}
					max = Math.max(max, temp * sum);
				}
				stack.push(i);
			}
			System.out.println(max);
		}
	}
}
