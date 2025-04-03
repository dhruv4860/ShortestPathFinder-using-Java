package ShortestPathFinder;

import java.util.Arrays;

public class BellmanFord {

    public static int[] bellmanFord(Graph graph, int src) {
        int V = graph.getVertices();
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {
            for (int u = 0; u < V; u++) {
                for (Graph.Node neighbor : graph.getAdjList().get(u)) {
                    int v = neighbor.vertex;
                    int weight = neighbor.weight;

                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                        dist[v] = dist[u] + weight;
                    }
                }
            }
        }

        // Check for negative weight cycles
        for (int u = 0; u < V; u++) {
            for (Graph.Node neighbor : graph.getAdjList().get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return null;
                }
            }
        }
        return dist;
    }
}
