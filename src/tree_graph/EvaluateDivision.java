package tree_graph;
import java.util.*;

public class EvaluateDivision {

    public static class Position {
        int x;
        int y;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

static class Edge {

    String numerator;
    String denominator;
    double val;

    public Edge(String numerator, String denominator, double val) {
        this.numerator = numerator;
        this.denominator = denominator;
        this.val = val;
    }
}

    private Map<String, List<Edge>> createGraphs(String[][] equations, double[] values) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for(int i = 0; i < values.length; ++i) {
            String numerator = equations[i][0];
            String denominator = equations[i][1];
            Edge nToD = new Edge(numerator, denominator, values[i]);
            Edge dToN = new Edge(denominator, numerator, 1/values[i]);
            graph.computeIfAbsent(numerator, key -> new ArrayList<>()).add(nToD);
            graph.computeIfAbsent(denominator, key -> new ArrayList<>()).add(dToN);
        }
        return  graph;
    }

    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        if(queries == null || queries.length ==0) return null;
        //build a weighted directed graph
        Map<String, List<Edge>> graph = createGraphs(equations, values);
        if(graph.size() == 0) return null;
        //dfs on the queries
        double[] result = new double[queries.length];
        for(int i = 0; i < queries.length; ++i) {
            String numerator = queries[i][0];
            String denominator= queries[i][1];
            if(!graph.containsKey(numerator) || !graph.containsKey(denominator)) {
                result[i] = -1.0;
            } else {
                result[i] = distTo(graph, new HashSet<>(), graph.get(numerator), denominator, 1);
            }
        }
        return result;
    }



    private double distTo(Map<String, List<Edge>> graph, Set<Edge> visited, List<Edge> edges, String denominator, double dist) {
        for(Edge edge : edges) {
            if(!visited.contains(edge)) {
                visited.add(edge);
                if(edge.denominator.equals(denominator)) {
                    return dist * edge.val;
                } else {
                    double val =  distTo(graph, visited,  graph.get(edge.denominator), denominator, dist * edge.val);
                    if(val > 0) return val;
                }
            }
        }
        return -1.0;
    }

    public double[] calcEquation2(String[][] equations, double[] values, String[][] queries) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < equations.length; ++i) {
            graph.computeIfAbsent(equations[i][0], k -> new HashMap<>()).put(equations[i][1], values[i]);
            graph.computeIfAbsent(equations[i][1], k -> new HashMap<>()).put(equations[i][0], 1 / values[i]);
        }

        double[] result = new double[queries.length];
        for (int i = 0; i < queries.length; ++i) {
            result[i] = dfs(graph, queries[i][0], queries[i][1],  new HashSet<>(), 1.0);
            if (result[i] == 0.0) result[i] = -1.0;
        }
        return result;
    }

    private double dfs(Map<String, Map<String, Double>> graph, String source, String dest, Set<String> visited, double currValue) {
        if (!graph.containsKey(source) || visited.contains(source)) return 0.0;
        if (source.equals(dest)) return currValue;

        visited.add(source);
        double tmp = 0.0;
        for (String child: graph.get(source).keySet()) {
            tmp = dfs(graph, child, dest, visited, graph.get(source).get(child) * currValue);
            if (tmp != 0.0) break;
        }
        visited.remove(source);
        return tmp;
    }

}
