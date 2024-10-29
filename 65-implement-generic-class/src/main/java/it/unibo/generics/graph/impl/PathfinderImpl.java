package it.unibo.generics.graph.impl;

import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class PathfinderImpl<N> {
    private final static int EDGE_WEIGHT = 1; // We consider that the graph is unweighted

    private Map<N, Set<N>> graph;
    private Map<N, PathfinderNode<N>> nodesAttributes;

    public PathfinderImpl(Map<N, Set<N>> graph) {

        if (graph == null) {
            throw new IllegalArgumentException();
        }

        this.graph = new HashMap<>();
        this.nodesAttributes = new HashMap<>();

        // Copy the adjacency map and initialize the nodes' attributes
        for (N node: graph.keySet()) {
            this.graph.put(node, new HashSet<>());

            for (N adjacentNode: graph.get(node)) {
                this.graph.get(node).add(adjacentNode);
            }

            this.nodesAttributes.put(node, new PathfinderNode<N>(node));
        }
    }

    public List<N> bellmanFord(N source, N dest) {
        if (!this.graph.containsKey(source) || !this.graph.containsKey(dest)) {
            throw new IllegalArgumentException();
        }

        // Initialize the nodes' attributes
        this.initializeSingleSource(source);

        // Relax all the edges in the adjacency map
        for (int i = 0; i < this.graph.size() - 1; i++) {

            for (N src: this.graph.keySet()) {
                for (N dst: this.graph.get(src)) {
                    this.relax(src, dst);
                }
            }
        }

        // Calculate the path using the attributes of the relaxed nodes
        List<N> path = new LinkedList<N>();

        N node = dest;
        do {
            path.addFirst(node);
            node = this.nodesAttributes.get(node).getParent();
        } while (node != null);

        return path;
    }

    // Initialize all the nodes' attributes
    private void initializeSingleSource(N source) {
        for (PathfinderNode<N> node: this.nodesAttributes.values()) {
            node.setDistance(null);
            node.setParent(null);

            if (node.getNode() == source) {
                node.setDistance(0);
            }
        }
    }

    // Relax an edge betwen two nodes
    private void relax(N source, N dest) {
        PathfinderNode<N> src = this.nodesAttributes.get(source);
        PathfinderNode<N> dst = this.nodesAttributes.get(dest);

        // If the source node doesn't have a distance yet, we can't relax the edge
        if (src.getDistance() == null) {
            return;
        }

        // Checks if the dst node can be reached passing by the source in a shorter distance
        if (dst.getDistance() == null || dst.getDistance() > src.getDistance() + EDGE_WEIGHT) {
            dst.setDistance(src.getDistance() + EDGE_WEIGHT);
            dst.setParent(source);
        }
    }


}
