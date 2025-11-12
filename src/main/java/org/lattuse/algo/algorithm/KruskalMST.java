package org.lattuse.algo.algorithm;

import org.lattuse.algo.model.Edge;
import org.lattuse.algo.structure.UnionFind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KruskalMST {

    public static List<Edge> buildMST(int n, List<Edge> edges) {
        List<Edge> mst = new ArrayList<>();
        // clone list to avoid mutating original ordering/inMST flags
        List<Edge> sorted = new ArrayList<>(edges);
        Collections.sort(sorted);
        UnionFind uf = new UnionFind(n);
        for (Edge e : sorted) {
            if (uf.union(e.u, e.v)) {
                e.inMST = true;
                mst.add(e);
                if (mst.size() == n - 1) break;
            }
        }
        return mst;
    }

    public static long totalWeight(List<Edge> edges) {
        long sum = 0;
        for (Edge e : edges) sum += e.weight;
        return sum;
    }
}

