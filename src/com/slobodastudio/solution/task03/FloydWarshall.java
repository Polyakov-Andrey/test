/*
 * This class gets from http://algowiki.net/wiki/index.php?title=Floyd-Warshall%27s_algorithm
 *
 * PS. Andrey Polyakov - not author
 */
package com.slobodastudio.solution.task03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Floyd-Warshall algorithm for determining the shortest path between two nodes
 * in a graph. <p> Thread Safety: immutable
 */
public final class FloydWarshall {

    /**
     * A 2-dimensional matrix is an adjacency matrix. At each step in the
     * algorithm, D[i][j] is the shortest path from i to j using intermediate
     * vertices {1..k−1}. All weights of paths is initialized to
     * <code>initializeWeight(final Node[] nodes, final Edge[] edges) </code>
     * method.
     */
    private final int[][] D;
    /**
     * A 2-dimensional matrix is an antecedence matrix. At each step in the
     * algorithm, P[i][j] is defined as the peak prior to the top of j on the
     * shortest path from vertex i to vertex j with intermediate vertices in the
     * set {1, 2, ..., k}
     */
    private final Node[][] P;

    /**
     * Create an instance of this class by describing the graph upon which it
     * will operate. <p> Note
     * <code>Node.id</code> must contain the index of the node in the
     * <code>nodes</code> parameter. Thus
     * <code>Node[1].id</code> must equal one. <p> On small computers the
     * practical maximum graph size with a 4-byte Node is about 10,000, at which
     * point the data size of an instance begins to exceed 1,5 GB.
     *
     * @param nodes array of Node; must be completely populated
     * @param edges array of Edge, completely populated; order is not important
     */
    public FloydWarshall(final Node[] nodes, final Edge[] edges) {
        final int maxNodes = 10000;  // roughly 1,5 GB 
        assert nodes.length < maxNodes : "nodes.length cannot exceed " + maxNodes
                + ".\nSize of class data structures is at least (2*(node size)*nodes.length**2).";
        assert (nodes != null) : "The nodes is empty";
        assert (edges != null) : "The edges is empty";

        D = initializeWeight(nodes, edges);
        P = new Node[nodes.length][nodes.length];

        for (int k = 0; k < nodes.length; k++) {
            for (int i = 0; i < nodes.length; i++) {
                for (int j = 0; j < nodes.length; j++) {
                    if (D[i][k] != Integer.MAX_VALUE
                            && D[k][j] != Integer.MAX_VALUE
                            && D[i][k] + D[k][j] < D[i][j]) {
                        D[i][j] = D[i][k] + D[k][j];
                        P[i][j] = nodes[k];
                    }
                }
            }
        }
    }

    public FloydWarshall(final List<Node> nodes, final List<Edge> edges) {
//        Node[] n = nodes.toArray(new Node[0]);
//        Edge[] e = edges.toArray(new Edge[0]);
        
        this(nodes.toArray(new Node[0]),edges.toArray(new Edge[0]));
    }

    /**
     * Determines the length of the shortest path from vertex A (source) to
     * vertex B (target), calculated by summing the weights of the edges
     * traversed. <p> Note that distance, like path, is not commutative. That
     * is, distance(A,B) is not necessarily equal to distance(B,A).
     *
     * @param source Start Node
     * @param target End Node
     * @return The path length as the sum of the weights of the edges traversed,
     * or
     * <code>Integer.MAX_VALUE</code> if there is no path
     */
    public int getShortestDistance(final Node source, final Node target) {
        assert (source != null) : "The source is empty";
        assert (target != null) : "The target is empty";

        return D[source.id][target.id];
    }

    public int getShortestDistance(final Edge rute) {
        assert (rute != null) : "The rute is empty";

        int d = getShortestDistance(rute.getFrom(), rute.getTo());
        rute.setWeight(d);

        return d;
    }

    /**
     * Describes the shortest path from vertex A (source) to vertex B (target)
     * by returning a collection of the vertices traversed, in the order
     * traversed. If there is no such path an empty collection is returned. <p>
     * Note that because each Edge applies only to one direction of traverse,
     * the path from A to B may not be the same as the path from B to A.
     *
     * @param source the start vertex
     * @param target the end vertex
     * @return A List (ordered Collection) of Node, possibly empty
     */
    public List<Node> getShortestPath(final Node source, final Node target) {
        assert (source != null) : "The source is empty";
        assert (target != null) : "The target is empty";

        if (D[source.id][target.id] == Integer.MAX_VALUE) {
            return new ArrayList<Node>(); // no path
        }
        final List<Node> path = getIntermediatePath(source, target);
        path.add(0, source);
        path.add(target);
        return path;
    }

    public List<Node> getShortestPath(final Edge rute) {
        assert (rute != null) : "The rute is empty";

        return getShortestPath(rute.getFrom(), rute.getTo());
    }

    /**
     * This method constructs path from vertex
     * <code>source</code> to vertex
     * <code>target</code>.
     *
     * @param source the start vertex
     * @param target the end vertex
     * @return A List (ordered Collection) of Node, possibly empty
     */
    private List<Node> getIntermediatePath(final Node source, final Node target) {
        assert (source != null) : "The source is empty";
        assert (target != null) : "The target is empty";

        if (P[source.id][target.id] == null) {
            return new ArrayList<Node>();
        }
        final List<Node> path = new ArrayList<Node>();
        path.addAll(getIntermediatePath(source, P[source.id][target.id]));
        path.add(P[source.id][target.id]);
        path.addAll(getIntermediatePath(P[source.id][target.id], target));
        return path;
    }

    /**
     * This method constructs adjacency matrix. Infinity is equal to value of
     * Integer.MAX_VALUE. Size of occupied space is Node.length**2.
     *
     * @param nodes array of Node; must be completely populated
     * @param edges array of Edge, completely populated; order is not important
     * @return A 2-dimensional matrix is an adjacency matrix.
     */
    private int[][] initializeWeight(final Node[] nodes, final Edge[] edges) {
        assert (nodes != null) : "The source is empty";
        assert (edges != null) : "The target is empty";

        int[][] W = new int[nodes.length][nodes.length];

        for (int i = 0; i < nodes.length; i++) {
            Arrays.fill(W[i], Integer.MAX_VALUE);
        }
        for (Edge e : edges) {
            W[e.getFrom().id][e.getTo().id] = e.getWeight();
        }
        return W;
    }
}