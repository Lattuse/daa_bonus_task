package org.lattuse.algo.model;

import java.util.Objects;

public class Edge implements Comparable<Edge> {
    public final int u;
    public final int v;
    public final long weight;
    public final int id; // here I put the unique id just for display, kinda obvious
    public boolean inMST = false;

    public Edge(int id, int u, int v, long weight) {
        this.id = id;
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    public int other(int x) {
        if (x == u) return v;
        if (x == v) return u;
        throw new IllegalArgumentException("Vertex not part of edge");
    }

    @Override
    public int compareTo(Edge o) {
        return Long.compare(this.weight, o.weight);
    }

    @Override
    public String toString() {
        return String.format("Edge(id=%d, %d-%d, w=%d, inMST=%b)", id, u, v, weight, inMST);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge)) return false;
        Edge edge = (Edge) o;
        return id == edge.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

