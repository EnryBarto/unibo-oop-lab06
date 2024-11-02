package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;
import it.unibo.generics.graph.api.Pathfinder;

public class GraphImpl<N> implements Graph<N> {
    // Stores all the edges from one node (the key of the map) to all the other nodes which is connected to
    private Map<N, Set<N>> adjacencyMap = new HashMap<N, Set<N>>();

    // Adds a node in the adjacency map only if it's not already present 
    public void addNode(final N node) {
        if (node != null) {
            this.adjacencyMap.putIfAbsent(node, new HashSet<N>());
        }
    }

    // Adds a node to the set of the target nodes which the source node is connected to
    public void addEdge(final N source, final N target) {
        if (source != null && target != null) {
            this.adjacencyMap.get(source).add(target);
        }
    }

    // Return the set of the nodes of the graph
    public Set<N> nodeSet() {
        return this.adjacencyMap.keySet();
    }

    // Returns the set of all the nodes which the source node passed is connected to
    public Set<N> linkedNodes(final N node) {
        return this.adjacencyMap.get(node);
    }

    // Calculate the path to reach a target node from a source node
    public List<N> getPath(final N source, final N target) {
        final Pathfinder<N> pathFinder = new BellmanFord<>(adjacencyMap);
        return pathFinder.calculatePath(source, target);
    }
}
