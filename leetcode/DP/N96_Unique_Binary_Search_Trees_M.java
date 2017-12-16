package leetcode.DP;

/**
Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

For example,
Given n = 3, there are a total of 5 unique BST's.

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
 */

//NO
//NO TWO
public class N96_Unique_Binary_Search_Trees_M {

	//Not Mine DP
	/*
	 * G(n): the number of unique BST for a sequence of length n.
	 * F(i, n), 1 <= i <= n: the number of unique BST, 
	 * 	 where the number i is the root of BST, and the sequence ranges from 1 to n.
	 * -> G(n) = F(1, n) + F(2, n) + ... + F(n, n). 
	 * -> F(i, n) = G(i-1) * G(n-i)	1 <= i <= n 
	 * -> G(n) = G(0) * G(n-1) + G(1) * G(n-2) + бн + G(n-1) * G(0) 
	 * -> G(0)=1, G(1)=1. 
	 */
	public int numTrees(int n) {
	    int [] G = new int[n+1];
	    G[0] = G[1] = 1;
	    
	    for(int i=2; i<=n; ++i) {
	    	for(int j=1; j<=i; ++j) {
	    		G[i] += G[j-1] * G[i-j];
	    	}
	    }
	    return G[n];
	}
	
	//same idea
	//Catalan Number бя
	/*
	 * C(n) = C(0)C(n-1) + C(1)C(n-2) + .....+ C(i-1)C(n-i)..... + C(n-1)C(0)
	 * 	->	= (2n)!/[(n+1)!n!] -> *= (n+k)/k   k = 2..n 
	 *  
	 *  ->  = [(n+k)/k  k = 1..n] / n+1  n=3 5
	 *  not = (n+k)/k   k = 2..n         n=3 4
	 *  
	 *  
	 */
	int numTrees2(int n) {
	    long ans=1,i;
	    for(i=1;i<=n;i++)
	        ans = ans*(i+n)/i;
	    return (int)(ans/i);
	}
}
