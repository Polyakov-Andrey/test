/*
 * This class got from http://algowiki.net/wiki/index.php?title=Edge
 * 
 * PS. Andrey Polyakov -- not author
 */
package com.slobodastudio.solution.task03;

/**
 * The Edge object is the edge graph.
 *
 * @author Andrey Polyakov
 * @version 1.0
 */
public class Edge {
    private final Node from;
    private final Node to;
    /**
     * The field value
     * <code>weight</code> is the edge weight from the graph vertex
     * <code>from</code> in the graph vertex
     * <code>to</code>.
     */
    private int weight;

    /**
     * Constructs an Edge object with the specified initial identifier
     * <code>from</code> and
     * <code>to</code> of the graph vertex, and
     * <code>weight</code> of the edge. The edge weight will not be equal to
     * infinity (
     * <code>Integer.MAX_VALUE</code>).
     *
     * @param from the graph vertex that starts from the edge.
     * @param to the graph vertex that ends from the edge.
     * @param weight the edge weight.
     */
    public Edge(final Node from, final Node to, final int weight) {
        assert (from != null) : "The graph vertex 'ftom' is empty";
        assert (to != null) : "The graph vertex 'to' is empty";
//        assert (weight != Integer.MAX_VALUE) : "The edge weight will not be equal to infinity (Integer.MAX_VALUE)";

        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    /**
     * Returns a string representation of this object. The string representation
     * consists of a list of the object's fields. In other words, this method
     * returns a string equal to the value of:
     * <code>  String.format("[%s -> %s, w=%d]", from, to, weight) </code>
     *
     * @return a string representation of this object
     */
    @Override
    public String toString() {
        return String.format("[%s -> %s, w=%d]", getFrom(), getTo(), getWeight());
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * The field value
     * <code>weight</code> is the edge weight from the graph vertex
     * <code>from</code> in the graph vertex
     * <code>to</code>.
     * @param weight the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     * <code>From</code> field is the graph vertex from which there is an edge
     * in the graph vertex of
     * <code>to</code>.
     * @return the from
     */
    public Node getFrom() {
        return from;
    }

    /**
     * <code>From</code> field is the graph vertex from which there is an edge
     * in the graph vertex of
     * <code>to</code>.
     * @return the to
     */
    public Node getTo() {
        return to;
    }
}
