import java.util.HashMap;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Integer;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/**
 * @author Elaine Schutte
 * @author Abigail Payne
 */

public class WordNet {
 private final HashMap<String,ArrayList<Integer>> wordToId; //Word is the key, ID number is the value
 private final HashMap<Integer,ArrayList<String>> idToWord; //ID number is the key, words are the value
 private final Digraph graph;
 
 /**
 * 
 * This function constructs an WordNet using the
 * synsets and hypernyms files
 * 
 * @param synsets name of file with synsets
 * @param hypernyms name of file with hypernyms
 * @return WordNet constructed from files
 */
 
 public WordNet(String synsets, String hypernyms) {
     if (synsets == null) {throw new NullPointerException();}
     if (hypernyms == null) {throw new NullPointerException();}

     
  wordToId = new HashMap<String,ArrayList<Integer>>();
  idToWord = new HashMap<Integer,ArrayList<String>>();
  
  // constructor takes the name of the two input files
  // To Process synsets
  In input = new In(synsets);
  while (input.hasNextLine()) {
   // Parse line
   String[] synLine = input.readLine().split(",");
   int synId = Integer.parseInt(synLine[0]);
   String[] synWords = synLine[1].split(" ");
   // Map words to unique Id
   for (int i = 0; i < synWords.length; i++) {
    if (!wordToId.containsKey(synWords[i])) {//if map doesn't already have this word
     ArrayList<Integer> newIdList = new ArrayList<Integer>();
     wordToId.put(synWords[i],newIdList); //put it in the map and give it an empty arraylist
     //double check
    }
    wordToId.get(synWords[i]).add(synId);//add the id to current array list
   }
   // Map unique Id to words
   ArrayList<String> newWordList = new ArrayList<String>();
   idToWord.put(synId,newWordList);
   for (int i = 0; i < synWords.length; i++)
    idToWord.get(synId).add(synWords[i]);
  }
  
  graph= new Digraph(idToWord.size());
  // Create directed graph with #of ids
  // Process hypernyms
  input = new In(hypernyms);
  while (input.hasNextLine()) {
   // Parse line
   String[] line = input.readLine().split(",");
   int Id= Integer.parseInt(line[0]);
   int[] hyperIds = new int[line.length - 1];
   for (int i = 0; i < hyperIds.length; i++)
    hyperIds[i] = Integer.parseInt(line[i + 1]);
   // Add edges from unique Id to each hypernym Id directly into graph
   for (int i = 0; i < hyperIds.length; i++)
    graph.addEdge(Id,hyperIds[i]);
  }
 }
 
  /**
 * 
 * This function returns a list of nouns 
 *
 * @return a String Iterable list containing the nouns
 */
 
 public Iterable<String> nouns() {
  return wordToId.keySet();
 }
 
  /**
 * 
 * This function discovers if a string is a noun in the list
 * 
 * @param word the string being searched for
 * @return a true or false value
 */
 
 public boolean isNoun(String word) {
     if (word == null) {throw new NullPointerException();}
  return wordToId.containsKey(word);
 }
 
 /**
 * 
 * This function finds a synset that is the
 * shortest ancestor of two nouns
 * 
 * @param word1 the first of two words used to find sca
 * @param word2 the second of two words used to find sca
 * @return string containing the shortest common ancestor
 */
 
 public String sca(String word1, String word2) { 
     if (word1 == null) {throw new NullPointerException();}
     if (word2 == null) {throw new NullPointerException();}
     if (! isNoun(word1)) {throw new IllegalArgumentException();}
     if (! isNoun(word2)) {throw new IllegalArgumentException();}
     ArrayList<Integer> word1ID = wordToId.get(word1);
     ArrayList<Integer> word2ID = wordToId.get(word2);
     ShortestCommonAncestor shortAn = new ShortestCommonAncestor(graph);
     if (shortAn.ancestor(word1ID, word2ID) == 0) return "None";
     int ancestorID = shortAn.ancestor(word1ID, word2ID);
     String ancestorWord = idToWord.get(ancestorID).get(0);
     
     return ancestorWord; 
 }
 
 /**
 * 
 * This function finds the distance between two words
 * 
 * @param word1 the first of two words used to calculate distance
 * @param word2 the second of two words used to calculate distance
 * @return the integer value describing the length
 */
 
 public int distance(String word1, String word2) {
     if (word1 == null) {throw new NullPointerException();}
     if (word2 == null) {throw new NullPointerException();}
     if (! isNoun(word1)) {throw new IllegalArgumentException();}
     if (! isNoun(word2)) {throw new IllegalArgumentException();}
     ShortestCommonAncestor shortAn = new ShortestCommonAncestor(graph);
     ArrayList<Integer> word1ID = wordToId.get(word1);
     ArrayList<Integer> word2ID = wordToId.get(word2);
     return shortAn.length(word1ID, word2ID);
 }
 
 // TODO
 public static void main(String[] args) {
    In in = new In(args[0]);
    Digraph G = new Digraph(in);
    ShortestCommonAncestor rawr = new ShortestCommonAncestor(G);
    while (!StdIn.isEmpty()) {
        int v = StdIn.readInt();
        int w = StdIn.readInt();
        int length   = rawr.length(v, w);
        int ancestor = rawr.ancestor(v, w);
        //System.out.println(sca("cat", "cow"));
//StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
        

 }
 }
}
