package it.unibo.generics.graph.api;

import java.util.List;

/*
 * Interface used to represent a generic pathfinding algorithm
 */
public interface Pathfinder<N> {

    /* Takes the source and the destination nodes, returns the list of all the nodes in the path */
    List<N> calculatePath(N source, N dest);

}
