package reviewCode.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
There are a total of n courses you have to take, labeled from 0 to n - 1.

Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?

For example:

2, [[1,0]]
There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.

2, [[1,0],[0,1]]
There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. 
So it is impossible.

Note:
The input prerequisites is a graph represented by a list of edges, not adjacency matrices. 
Read more about how a graph is represented.
You may assume that there are no duplicate edges in the input prerequisites.

Hints:
1. This problem is equivalent to finding if a cycle exists in a directed graph. 
   If a cycle exists, no topological ordering exists and therefore it will be impossible to take all courses.
2. Topological Sort via DFS - A great video tutorial (21 minutes) on Coursera explaining the basic concepts of Topological Sort.
3. Topological sort could also be done via BFS.
 */

// Topological sort
public class N207_Course_Schedule_M {

	//BFS
	//Topological sort
    public boolean canFinish(int numCourses, int[][] prerequisites) {
    	// O(V + E)
        List<Integer>[] matrix = new List[numCourses];
        int[] indegree = new int[numCourses];
        // E part
        for (int[] pre : prerequisites) {
            int preCourse = pre[1];
            int readyCourse = pre[0];
            List<Integer> list = matrix[preCourse];
            if (list == null) {
    	        list = new LinkedList<>();   
    	        matrix[preCourse] = list;
            }
            list.add(readyCourse);
            indegree[readyCourse]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) queue.offer(i);
        }
        int count = 0;
        // V part
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            count++;
            List<Integer> adjacent = matrix[vertex];
            if (adjacent == null) continue;
            for (int neighbor : adjacent) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0)
                    queue.offer(neighbor);
            }
        }
        return count == numCourses;
    }
    //
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites.length == 0) return true;

        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses];
        for (int i = 0; i < prerequisites.length; i++) // Indegree - how many prerequisites are needed.
            indegree[prerequisites[i][0]]++;    

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) 
            if (indegree[i] == 0)
                queue.add(i);

        // How many courses don't need prerequisites.
        int canFinishCount = queue.size();  
        while (!queue.isEmpty()) {
            int prerequisite = queue.remove(); // Already finished this prerequisite course.
            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) { 
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        canFinishCount++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }

        return (canFinishCount == numCourses);    
    }
    
// ================================== DFS ===========================================
    
    public boolean canFinish4(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList<Integer>();
            
        boolean[] visited = new boolean[numCourses];
        boolean[] onStack = new boolean[numCourses];
        for(int i=0; i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
        	if(!visited[i])
        		if(dfsHasCyc4(graph, visited, onStack, i))
        			return false;
        }
        return true;
    }
    private boolean dfsHasCyc4(ArrayList<Integer>[] graph, boolean[] visited, boolean[] onStack, int course){
    	onStack[course] = true;
    	visited[course] = true;
    	for(int i=0; i<graph[course].size();i++){
    		int next = graph[course].get(i);
    		if(onStack[next]) return true;
            if(!visited[next] && dfsHasCyc4(graph,visited,onStack,next))
                return true;
        }
    	onStack[course] = false;
        return false;
    }
    
    
    public boolean canFinish3(int numCourses, int[][] prerequisites) {
        ArrayList<Integer>[] graph = new ArrayList[numCourses];
        for(int i=0;i<numCourses;i++)
            graph[i] = new ArrayList<Integer>();
            
        //boolean[] visited = new boolean[numCourses];
        //boolean[] onStack = new boolean[numCourses];
        
        boolean[] visited = new boolean[numCourses];
        for(int i=0; i<prerequisites.length;i++){
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        for(int i=0; i<numCourses; i++){
            if(dfsHasCyc(graph,visited,i))
                return false;
        }
        return true;
    }

    private boolean dfsHasCyc(ArrayList<Integer>[] graph, boolean[] visited, int course){
        if(visited[course])
            return true;
        else
            visited[course] = true;;

        for(int i=0; i<graph[course].size();i++){
            if(dfsHasCyc(graph,visited,(int)graph[course].get(i)))
                return true;
        }
        visited[course] = false;
        return false;
    }

}
