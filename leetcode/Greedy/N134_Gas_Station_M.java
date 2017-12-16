package leetcode.Greedy;
/**
There are N gas stations along a circular route, where the amount of gas at station i is gas[i].

You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). 
You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.

Note:
The solution is guaranteed to be unique.
 */

//YES
public class N134_Gas_Station_M {
	
	//Mine Code -> bad performance
    public int canCompleteCircuit(int[] gas, int[] cost) {
       int length = gas.length;
       int res = 0;
       for(int i = 0; i < length; i++){
    	   res = index(i, length, gas, cost);
    	   if(res != -1) return res;
       }
    	return res;
    }
	private int index(int start, int length, int[] gas, int[] cost) {
		int count = 0;
		int sgas = 0, scost = 0;
		int tempIndex = start;
		while(count < length){
			if(tempIndex == length) tempIndex -= length;
			sgas += gas[tempIndex];
			scost = cost[tempIndex];
			if(sgas < scost) return -1;
			sgas -= scost;
			tempIndex++;
			count++;
		}
		return start;
	}

	/*
	 * If car starts at A and can not reach B. 
	 * 		Any station between A and B can not reach B.(B is the first station that A can not reach.)
	 * If the total number of gas is bigger than the total number of cost. There must be a solution.
	 */
	public int canCompleteCircuit3(int[] gas, int[] cost) {
	    int sumGas = 0;
	    int sumCost = 0;
	    int start = 0;
	    int tank = 0;
	    for (int i = 0; i < gas.length; i++) {
	        sumGas += gas[i];
	        sumCost += cost[i];
	        tank += gas[i] - cost[i];
	        if (tank < 0) {
	            start = i + 1;
	            tank = 0;
	        }
	    }
	    if (sumGas < sumCost) {
	        return -1;
	    } else {
	        return start;
	    }
	}
	
/*
We prove the following statement.
 If sum of all gas[i]-cost[i] is greater than or equal to 0, then there is a start position you can travel the whole circle.
Let i be the index such that the the partial sum

gas[0]-cost[0]+gas[1]-cost[1]+...+gas[i]-cost[i]
is the smallest, then the start position should be start=i+1 ( start=0 if i=n-1). Consider any other partial sum, for example,

gas[0]-cost[0]+gas[1]-cost[1]+...+gas[i]-cost[i]+gas[i+1]-cost[i+1]
Since gas[0]-cost[0]+gas[1]-cost[1]+...+gas[i]-cost[i] is the smallest, we must have

gas[i+1]-cost[i+1]>=0
in order for gas[0]-cost[0]+gas[1]-cost[1]+...+gas[i]-cost[i]+gas[i+1]-cost[i+1] to be greater.
The same reasoning gives that

 gas[i+1]-cost[i+1]>=0
 gas[i+1]-cost[i+1]+gas[i+2]-cost[i+2]>=0
 .......
 gas[i+1]-cost[i+1]+gas[i+2]-cost[i+2]+...+gas[n-1]-cost[n-1]>=0
What about for the partial sums that wraps around?

gas[0]-cost[0]+gas[1]-cost[1]+...+gas[j]-cost[j] + gas[i+1]-cost[i+1]+...+gas[n-1]-cost[n-1]
>=
gas[0]-cost[0]+gas[1]-cost[1]+...+gas[i]-cost[i] + gas[i+1]-cost[i+1]+...+gas[n-1]-cost[n-1]
>=0
The last inequality is due to the assumption that the entire sum of gas[k]-cost[k] is greater than or equal to 0.
So we have that all the partial sums

gas[i+1]-cost[i+1]>=0,
gas[i+1]-cost[i+1]+gas[i+2]-cost[i+2]>=0,
gas[i+1]-cost[i+1]+gas[i+2]-cost[i+2]+...+gas[n-1]-cost[n-1]>=0,
...
gas[i+1]-cost[i+1]+...+gas[n-1]-cost[n-1] + gas[0]-cost[0]+gas[1]-cost[1]+...+gas[j]-cost[j]>=0,
...
Thus i+1 is the position to start. Coding using this reasoning is as follows:

*/
    public int canCompleteCircuit2(int[] gas, int[] cost) {
    	int minsubsum = Integer.MAX_VALUE;
    	int totalsum = 0;
    	int start = 0;
    	for(int i = 0; i < gas.length; i++){
    		totalsum += gas[i] - cost[i];
    		if(totalsum < minsubsum){
    			minsubsum = totalsum;
    			start = i+1;
    		}
    	}
    	return (totalsum < 0) ? -1 : (start%gas.length);
    }
	
}
