package it.unibo.generics.graph.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import it.unibo.generics.graph.api.Graph;

public class GraphImpl<N> implements Graph<N> {
    private Map<N, Set<N>> adjacencyMatrix = new HashMap<N, Set<N>>();

    public void addNode(N node) {
        if (node != null) {
            this.adjacencyMatrix.putIfAbsent(node, new HashSet<N>());
        }
    }

    public void addEdge(N source, N target) {
        if (source != null && target != null) {
            this.adjacencyMatrix.get(source).add(target);
        }
    }

    public Set<N> nodeSet() {
        return this.adjacencyMatrix.keySet();
    }

    public Set<N> linkedNodes(N node) {
        return this.adjacencyMatrix.get(node);
    }

    public List<N> getPath(N source, N target) {
        return null;
    }
}
