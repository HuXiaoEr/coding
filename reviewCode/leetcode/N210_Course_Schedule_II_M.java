package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

/**
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, 
return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. 
If it is impossible to finish all courses, return an empty array.

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. 
So the correct course order is [0,1]

4, [[1,0],[2,0],[3,1],[3,2]]
There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. 
Another correct ordering is[0,2,1,3].

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.
click to show more hints.

Hints:
1. This problem is equivalent to finding the topological order in a directed graph. 
   If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
   Topological sort could also be done via BFS.

 */

//YES TWO HALF
public class N210_Course_Schedule_II_M {
	
	//BFS
	//beats 92.33%
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		Deque<Integer> queue = new LinkedList<>();
		int[] indegree = new int[numCourses];
		int count = 0;
        ArrayList<Integer>[] edges = (ArrayList<Integer>[])new ArrayList[numCourses];
        int[] order = new int[numCourses];
        for(int i = 0; i < numCourses; i++) edges[i] = new ArrayList<Integer>();  // ∑¿÷πjava.lang.NullPointerException
        for(int i = 0; i < prerequisites.length; i++){
        	if(edges[prerequisites[i][1]] == null) edges[prerequisites[i][1]] = new ArrayList<Integer>();
        	edges[prerequisites[i][1]].add(prerequisites[i][0]);
        	indegree[prerequisites[i][0]]++;
        }
        for(int i = 0; i < numCourses; i++){
        	if(indegree[i] == 0) queue.add(i);
        }
        while(!queue.isEmpty()){
        	int course = queue.poll();
        	order[count++] = course;
            if(edges[course] != null)  // java.lang.NullPointerException
        	    for(int c : edges[course]){
        	    	indegree[c]--;
        		    if(indegree[c] == 0) queue.add(c);
        	    }
        }
        if(count != numCourses) return new int[0];  // important
		return order;
    }
	
	// DFS  post-order
	// 6ms 97.14%
	public int[] findOrder2(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] edges = (ArrayList<Integer>[])new ArrayList[numCourses];
        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        Deque<Integer> stack = new LinkedList<>();
        int[] order = new int[numCourses];
        for(int i = 0; i < numCourses; i++) edges[i] = new ArrayList<Integer>();  // ∑¿÷πjava.lang.NullPointerException
        for(int i = 0; i < prerequisites.length; i++){
        	edges[prerequisites[i][1]].add(prerequisites[i][0]);
        }
        for(int i=0; i<numCourses; i++){
        	if(!visited[i])
        		if(dfsHasCyc(edges, visited, onStack, i, stack))  //”–ª∑
        			return new int[0];
        }
        for(int i = 0; i < numCourses; i++){
        	order[i] = stack.pop();
        }
		return order;
    }
	private boolean dfsHasCyc(ArrayList<Integer>[] edges, boolean[] visited, boolean[] onStack, int course, Deque<Integer> stack) {
		onStack[course] = true;
    	visited[course] = true;
    	for(int i=0; i<edges[course].size();i++){  // java.lang.NullPointerException
    		int next = edges[course].get(i);
    		if(onStack[next]) return true;
            if(!visited[next] && dfsHasCyc(edges,visited,onStack,next,stack))
                return true;
        }
    	stack.push(course);
    	onStack[course] = false;
        return false;
	}
}
