import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.HashMap;
import edu.princeton.cs.algs4.Map;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Integer;

//Synset=words that match
//hypernyms=ids with relationships to other ids

public class WordNet {
 //use ArrayList b/c easy access for large group of numbers, (shouldn't be any duplicate #s anyway)
  private final Map<String,ArrayList<Integer>> wordToid; //Word is the key, ID number is the value
  private final Map<Integer,String> idToword; //ID number is the key, words are the value
//  private final Map<Integer,ArrayList<Integer>> hypGraph;
  private Digraph graph;

   // constructor takes the name of the two input files
 public WordNet(String synsets, String hypernyms){
   In in= new In(synsets); //inputs file from filename
   wordToid= new HashMap<String,ArrayList<Integer>>();
   idToword= new HashMap<Integer,String>();
//   hypGraph= new HashMap<Integer,ArrayList<Integer>>();
   String regex=",";
   String regex2=" ";
    
    while (in.hasNextLine()!=0) {
     
      String synLine=in.readLine();

      String[] synSplit=synLine.split(regex);
      int synID=Integer.parseInt(synSplit[0]);
      String preSplit=synSplit[1];
      String[] synWord=preSplit.split(regex2);
      idToword.put(synID,preSplit);
      for (String syn:synWord){ //Goes through string of words
        if(wordToid.containsKey(syn)==0){ //if map doesn't already have this word
		  List<Integer> newID= new ArrayList<Integer>();
          wordToid.put(syn, newID); //put it in the map and give it an empty arraylist
		  wordToid.get(syn).add(synID); //add the current ID to the list
		}
		else {//if it does exist
			wordToid.get(syn).add(synID); //add the id to current array list
        }
	  }     
	}
	  Digraph graph= new Digraph(idToword.size()); //create directed graph with #of ids
	  in=new In(hypernyms);
	  while (in.hasNextLine()!=0) {
		  String hypLine=in.readLine();
		  String[] hypSplit=hypLine.split(regex);
		  int hypID=Integer.parseInt(hypSplit[0]);
		  List<Integer> List= new ArrayList<Integer>();
		  for (String hyp:hypSplit){
			  int hypInt=Integer.parseInt(hyp);
			/*  if (hyp==hypID) {
				  hypGraph.put(hypID, List); 
			  else{ hypGraph.get(hypID).add(hyp);}*/
			  if(hypInt!=hypID) {
				graph.addEdge(hypID,hypInt); //adds edge directly into graph
				}
			}
		}
   }
  

   // all WordNet nouns
   public Iterable<String> nouns(){
	   return wordToid.keySet();
   }

   // is the word a WordNet noun?
     public boolean isNoun(String word){
		 return wordToid.containsKey(word);
   }

   // a synset (second field of synsets.txt) that is a shortest common ancestor
   // of noun1 and noun2 (defined below)
     public String sca(String noun1, String noun2){
   }

   // distance between noun1 and noun2 (defined below)
     public int distance(String noun1, String noun2){
   }

   // do unit testing of this class
     public static void main(String[] args){
   }
}

