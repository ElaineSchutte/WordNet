import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import java.lang.Integer;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;


public class ShortestCommonAncestor {
 private final Digraph graph;
 
 
 public ShortestCommonAncestor(Digraph G) {
     graph = G;
 }
 
 public int length(int v, int w) {
  BreadthFirstDirectedPaths vBfs = new BreadthFirstDirectedPaths(graph,v);
  BreadthFirstDirectedPaths wBfs = new BreadthFirstDirectedPaths(graph,w);
  // Initialize sca and minLength with impossible values (DAG has root so sca and minLength must exist)
  int sca = -1;
  int minLength = Integer.MAX_VALUE;
  // Find sca and minLength
  for (int i = 0; i < graph.V(); i++) {
   if (vBfs.hasPathTo(i) && wBfs.hasPathTo(i))
    if (vBfs.distTo(i) + wBfs.distTo(i) < minLength) {
     sca = i;
     minLength = vBfs.distTo(i) + wBfs.distTo(i);
    }
  }
  return minLength;
 }
 
 public int ancestor(int v, int w) {
  BreadthFirstDirectedPaths vBfs = new BreadthFirstDirectedPaths(graph,v);
  BreadthFirstDirectedPaths wBfs = new BreadthFirstDirectedPaths(graph,w);
  int sca = -1;
  int minLength = Integer.MAX_VALUE;
  for (int i = 0; i < graph.V(); i++) {
   if (vBfs.hasPathTo(i) && wBfs.hasPathTo(i))
    if (vBfs.distTo(i) + wBfs.distTo(i) < minLength) {
     sca = i;
     minLength = vBfs.distTo(i) + wBfs.distTo(i);
    }
  }
  return sca;
 }
 
 public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
  BreadthFirstDirectedPaths aBfs = new BreadthFirstDirectedPaths(graph,subsetA);
  BreadthFirstDirectedPaths bBfs = new BreadthFirstDirectedPaths(graph,subsetB);
  int sca = -1;
  int minLength = Integer.MAX_VALUE;
  for (int i = 0; i < graph.V(); i++) {
   if (aBfs.hasPathTo(i) && bBfs.hasPathTo(i))
    if (aBfs.distTo(i) + bBfs.distTo(i) < minLength) {
     sca = i;
     minLength = aBfs.distTo(i) + bBfs.distTo(i);
    }
  }
  return minLength;
 }
 
 public int ancestor(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
  BreadthFirstDirectedPaths aBfs = new BreadthFirstDirectedPaths(graph,subsetA);
  BreadthFirstDirectedPaths bBfs = new BreadthFirstDirectedPaths(graph,subsetB);
  int sca = -1;
  int minLength = Integer.MAX_VALUE;
  for (int i = 0; i < graph.V(); i++) {
   if (aBfs.hasPathTo(i) && bBfs.hasPathTo(i))
    if (aBfs.distTo(i) + bBfs.distTo(i) < minLength) {
     sca = i;
     minLength = aBfs.distTo(i) + bBfs.distTo(i);
    }
  }
  return sca;
 }
 
 // TODO
 public static void main(String[] args) {

    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    ShortestCommonAncestor sca = new ShortestCommonAncestor(G);
    while (!StdIn.isEmpty()) {
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        int length   = sca.length(v, w);
        int ancestor = sca.ancestor(v, w);
        StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
    }

 }
}