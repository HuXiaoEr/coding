import java.util.*;


public class Main1{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = Integer.valueOf(in.nextLine());
        int[] num = new int[n];
        for (int i = 0; i < n; i ++) {
            num[i] = Integer.valueOf(in.nextLine());
        }
        for (int i = 0; i < n; i ++) {
        	int j = i+1;
        	int min = 0;
        	for(; j < n; j++){
        		if(num[j] > num[i]){
        			min = num[j];
        			break;
        		}
        	}
        	if(j == n) System.out.println(-1);
        	else System.out.println(min);
        }
    }
}