package it.unibo.generics.graph.impl;

// Class used to store the attributes of a node necessary for the pathfinding algorithm
public class PathfinderNode<T> {
    private final T node;
    private Integer distance;
    private T parent;

    public PathfinderNode(final T node) {
        this.node = node;
        this.distance = null;
        this.parent = null;
    }

    public T getNode() {
        return node;
    }

    public Integer getDistance() {
        return distance;
    }

    public T getParent() {
        return parent;
    }

    public void setDistance(final Integer distance) {
        this.distance = distance;
    }

    public void setParent(final T parent) {
        this.parent = parent;
    }

}
