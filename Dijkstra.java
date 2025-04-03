package ShortestPathFinder;

import java.util.*;

public class Dijkstra {

    public static int[] dijkstra(Graph graph, int src) {
        int V = graph.getVertices();
        int[] dist = new int[V];
        boolean[] visited = new boolean[V];

        PriorityQueue<Graph.Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.weight));
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        pq.add(new Graph.Node(src, 0));

        while (!pq.isEmpty()) {
            int u = pq.poll().vertex;

            if (visited[u]) continue;
            visited[u] = true;

            for (Graph.Node neighbor : graph.getAdjList().get(u)) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (!visited[v] && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Graph.Node(v, dist[v]));
                }
            }
        }
        return dist;
    }
}
