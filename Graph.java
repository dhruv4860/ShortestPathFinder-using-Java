package ShortestPathFinder;

import java.util.*;

public class Graph {
    private final int vertices;
    private final List<List<Node>> adjList;

    static class Node {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    public Graph(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>(vertices);

        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Node(dest, weight));
        adjList.get(dest).add(new Node(src, weight));  // For undirected graph
    }

    public List<List<Node>> getAdjList() {
        return adjList;
    }

    public int getVertices() {
        return vertices;
    }
}

