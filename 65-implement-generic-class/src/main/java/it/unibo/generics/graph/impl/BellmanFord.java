package it.unibo.generics.graph.impl;

import it.unibo.generics.graph.api.Pathfinder;
import java.util.Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.LinkedList;

public class BellmanFord<N> implements Pathfinder<N> {
    private final static int EDGE_WEIGHT = 1; // We consider that the graph is unweighted

    private final Map<N, Set<N>> adjacencyMap;
    private final Map<N, PathfinderNode<N>> nodesAttributes;

    public BellmanFord(final Map<N, Set<N>> adjacencyMap) {

        if (adjacencyMap == null) {
            throw new IllegalArgumentException(new NullPointerException());
        }

        this.adjacencyMap = new HashMap<>();
        this.nodesAttributes = new HashMap<>();

        // Copy the adjacency map and initialize the nodes' attributes
        for (N node: adjacencyMap.keySet()) {
            this.adjacencyMap.put(node, new HashSet<>());

            for (N adjacentNode: adjacencyMap.get(node)) {
                this.adjacencyMap.get(node).add(adjacentNode);
            }

            this.nodesAttributes.put(node, new PathfinderNode<N>(node));
        }
    }

    @Override
    public List<N> calculatePath(final N source, final N dest) {
        if (!this.adjacencyMap.containsKey(source) || !this.adjacencyMap.containsKey(dest)) {
            throw new IllegalArgumentException("The source and / or the destination node don't exist!");
        }

        // Initialize the nodes' attributes
        this.initializeSingleSource(source);

        // Relax all the edges in the adjacency map
        for (int i = 0; i < this.adjacencyMap.size() - 1; i++) {

            for (N src: this.adjacencyMap.keySet()) {
                for (N dst: this.adjacencyMap.get(src)) {
                    this.relax(src, dst);
                }
            }
        }

        // Calculate the path using the attributes of the relaxed nodes
        final List<N> path = new LinkedList<N>();

        N node = dest;
        do {
            path.addFirst(node);
            node = this.nodesAttributes.get(node).getParent();
        } while (node != null);

        return path;
    }

    // Initialize all the nodes' attributes
    private void initializeSingleSource(final N source) {
        for (PathfinderNode<N> node: this.nodesAttributes.values()) {
            node.setDistance(null);
            node.setParent(null);

            if (node.getNode() == source) {
                node.setDistance(0);
            }
        }
    }

    // Relax an edge betwen two nodes
    private void relax(final N source, final N dest) {
        final PathfinderNode<N> src = this.nodesAttributes.get(source);
        final PathfinderNode<N> dst = this.nodesAttributes.get(dest);

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
