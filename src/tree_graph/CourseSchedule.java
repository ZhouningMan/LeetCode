package tree_graph;

import java.util.*;
import java.util.stream.Stream;

public class CourseSchedule {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = (List<Integer>[])new ArrayList[numCourses];
        for (int i = 0; i < prerequisites.length; i++) {
            if( graph[prerequisites[i][1]] == null) graph[prerequisites[i][1]] = new ArrayList<>();
            graph[prerequisites[i][1]].add(prerequisites[i][0]);
        }

        int[] visited = new int[numCourses];
        for(int i = 0; i < numCourses; ++i) {
            if(visited[i] == 0) {
                if(!dfs(graph, i, new HashSet<>(), visited)) return false;
            }
        }
        return true;
    }


    private boolean dfs(List<Integer>[] graph, int vertex, Set<Integer> path, int[] visited) {
        if(path.contains(vertex)) return  false;
        if(visited[vertex] == 1) return true;
        path.add(vertex);
        visited[vertex] = 1;
        if(graph[vertex] != null) {
            for(Integer next : graph[vertex]) {
                if(!dfs(graph, next, path, visited)) return false;
            }
        }
        path.remove(vertex);
        return true;
    }

}
