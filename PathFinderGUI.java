package ShortestPathFinder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;

import javax.swing.JTextField;

public class PathFinderGUI extends JFrame {

    private JTextField verticesField, edgesField, srcField, destField;
    private JTextArea resultArea;
    private Graph graph;

    public PathFinderGUI() {
        setTitle("Shortest Path Finder");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Vertices:"));
        verticesField = new JTextField();
        inputPanel.add(verticesField);

        inputPanel.add(new JLabel("Edges:"));
        edgesField = new JTextField();
        inputPanel.add(edgesField);

        inputPanel.add(new JLabel("Source:"));
        srcField = new JTextField();
        inputPanel.add(srcField);

        inputPanel.add(new JLabel("Destination:"));
        destField = new JTextField();
        inputPanel.add(destField);

        JButton buildButton = new JButton("Build Graph");
        JButton findPathButton = new JButton("Find Path");
        
        inputPanel.add(buildButton);
        inputPanel.add(findPathButton);

        resultArea = new JTextArea();
        resultArea.setEditable(false);
        
        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(resultArea), BorderLayout.CENTER);

        buildButton.addActionListener(e -> buildGraph());
        findPathButton.addActionListener(e -> findShortestPath());
    }

    private void buildGraph() {
        int vertices = Integer.parseInt(verticesField.getText());
        graph = new Graph(vertices);

        int edges = Integer.parseInt(edgesField.getText());
        for (int i = 0; i < edges; i++) {
            int src = Integer.parseInt(JOptionPane.showInputDialog("Edge " + (i + 1) + " - Source:"));
            int dest = Integer.parseInt(JOptionPane.showInputDialog("Edge " + (i + 1) + " - Destination:"));
            int weight = Integer.parseInt(JOptionPane.showInputDialog("Edge " + (i + 1) + " - Weight:"));
            graph.addEdge(src, dest, weight);
        }
        resultArea.setText("Graph built with " + vertices + " vertices and " + edges + " edges.");
    }

    private void findShortestPath() {
        if (graph == null) {
            resultArea.setText("Build the graph first!");
            return;
        }

        int src = Integer.parseInt(srcField.getText());
        int dest = Integer.parseInt(destField.getText());

        String[] options = {"Dijkstra", "Bellman-Ford"};
        int choice = JOptionPane.showOptionDialog(this, "Choose algorithm:", "Algorithm Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        int[] distances;
        if (choice == 0) {
            distances = Dijkstra.dijkstra(graph, src);
        } else {
            distances = BellmanFord.bellmanFord(graph, src);
        }

        if (distances != null) {
            resultArea.setText("Shortest path from " + src + " to " + dest + " is: " + distances[dest]);
        } else {
            resultArea.setText("Negative weight cycle detected.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PathFinderGUI gui = new PathFinderGUI();
            gui.setVisible(true);
        });
    }
}
