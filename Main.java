import java.util.*;

public class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        long m = Long.parseLong(in.next());
        long n = Long.parseLong(in.next());
        if (m > n) {
            long tmp = m;
            m = n;
            n = tmp;
        }
        long k = 0;
        do {
            k = m % n;
            m = n;
            n = k;
        } while (k != 0);
        System.out.println(m);
    }
}