package org.lattuse.algo;

import org.lattuse.algo.algorithm.KruskalMST;
import org.lattuse.algo.model.Edge;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Edge Removal from MST");

        // Okay let's build example graph
        int n = 7; // vertices 0..6
        List<Edge> allEdges = new ArrayList<>();
        addEdge(allEdges, 0, 0, 1, 7);
        addEdge(allEdges, 1, 0, 3, 5);
        addEdge(allEdges, 2, 1, 2, 8);
        addEdge(allEdges, 3, 1, 3, 9);
        addEdge(allEdges, 4, 1, 4, 7);
        addEdge(allEdges, 5, 2, 4, 5);
        addEdge(allEdges, 6, 3, 4, 15);
        addEdge(allEdges, 7, 3, 5, 6);
        addEdge(allEdges, 8, 4, 5, 8);
        addEdge(allEdges, 9, 4, 6, 9);
        addEdge(allEdges, 10, 5, 6, 11);

        System.out.println("\nOriginal graph edges:");
        for (Edge e : allEdges) System.out.println(e);

        // Build MST
        List<Edge> mst = KruskalMST.buildMST(n, allEdges);
        System.out.println("\nMST edges before removal (total weight = " + KruskalMST.totalWeight(mst) + "):");
        printEdgeList(mst);

        // remove one edge from MST: choose an MST edge to remove (for demo just take the first)
        if (mst.isEmpty()) {
            System.out.println("MST empty - nothing to remove");
            return;
        }
        Edge removed = mst.get(0);
        removed.inMST = false; // mark removed
        System.out.println("\nRemoved MST edge: " + removed);

        // Build adjacency of remaining MST edges (without removed)
        List<Edge> currentMST = new ArrayList<>(mst);
        currentMST.remove(0);

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (Edge e : currentMST) {
            adj.get(e.u).add(e.v);
            adj.get(e.v).add(e.u);
        }

        // Find components after removal using DFS/BFS
        int[] comp = new int[n];
        Arrays.fill(comp, -1);
        int compId = 0;
        for (int i = 0; i < n; i++) {
            if (comp[i] == -1) {
                bfsLabel(i, compId, comp, adj);
                compId++;
            }
        }

        System.out.println("\nNumber of components after removal: " + compId);
        for (int i = 0; i < compId; i++) {
            System.out.print("Component " + i + ": ");
            for (int v = 0; v < n; v++) if (comp[v] == i) System.out.print(v + " ");
            System.out.println();
        }

        // aaand find replacement edge: minimal weight edge connecting different components
        Edge best = null;
        for (Edge e : allEdges) {
            if (e.inMST) continue;
            int cu = comp[e.u];
            int cv = comp[e.v];
            if (cu != cv) {
                if (best == null || e.weight < best.weight) best = e;
            }
        }

        if (best == null) {
            System.out.println("\nNo replacement edge found; graph becomes disconnected permanently.");
        } else {
            best.inMST = true;
            currentMST.add(best);
            System.out.println("\nReplacement edge chosen: " + best);

            System.out.println("\nNew MST edges (total weight = " + KruskalMST.totalWeight(currentMST) + "):");
            printEdgeList(currentMST);
        }
    }

    private static void addEdge(List<Edge> list, int id, int u, int v, long w) {
        list.add(new Edge(id, u, v, w));
    }

    private static void printEdgeList(List<Edge> list) {
        for (Edge e : list) System.out.println(e);
    }

    private static void bfsLabel(int start, int id, int[] comp, List<List<Integer>> adj) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        comp[start] = id;
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) {
                if (comp[v] == -1) {
                    comp[v] = id;
                    q.add(v);
                }
            }
        }
    }
}
