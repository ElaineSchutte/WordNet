import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import java.lang.Integer;
import java.lang.Iterable;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
   
/**
 * @author Elaine Schutte
 * @author Abigail Payne
 */

public class ShortestCommonAncestor {
 private final Digraph graph;
 
/**
 * 
 * This function constructs a ShortestCommonAncestor object containing
 * a digraph using a DAG 
 * 
 * @param G rooted DAG used to construct digraph
 * @return ShortestCommonAncestor with constructed digraph
 */
 
 public ShortestCommonAncestor(Digraph G) {
     if (G == null) throw new NullPointerException();
     if (G.V() == 0){ throw new IllegalArgumentException();}
     graph = G;
 }
 /**
 * 
 * This function finds the length of the shortest ancestral path between two points
 * 
 * @param v the first of two vertices used to calculate distance
 * @param w the second of two vertices used to calculate distance
 * @return the integer value describing the length
 */
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
 
 /**
 * 
 * This function finds the shortest common ancestor between two vertices
 * 
 * @param v the first of two vertices used to calculate sca
 * @param w the second of two vertices used to calculate sca
 * @return the integer value describing the sca
 */
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
 /**
 * 
 * This function finds the shortest ancestral path between two vertex subsets
 * 
 * @param subsetA the first of two vertex subsets used to calculate distance
 * @param subsetB the second of two vertex subsets used to calculate distance
 * @return the integer value describing the shortest length between the two points
 */
 public int length(Iterable<Integer> subsetA, Iterable<Integer> subsetB) {
     if (subsetA == null) throw new NullPointerException();
     if (subsetB == null) throw new NullPointerException();
     for (Integer subA: subsetA){
         if(subA == null) throw new NullPointerException();
     }
     for (Integer subB: subsetB){
         if(subB == null) throw new NullPointerException();
     }

     
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
 
 /**
 * 
 * This function finds the shortest common ancestor between two vertex subsets
 * 
 * @param subsetA the first of two vertex subsets used to calculate sca
 * @param subsetB the second of two vertex subsets used to calculate sca
 * @return the integer value describing the shortest common ancestor
 */
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