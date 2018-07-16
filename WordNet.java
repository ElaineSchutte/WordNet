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
  private Digraph graph;

   // constructor takes the name of the two input files
 public WordNet(String synsets, String hypernyms){
   In in= new In(synsets); //inputs file from filename
   wordToid= new HashMap<String,ArrayList<Integer>>();
   idToword= new HashMap<Integer,String>();
    
    while (in.hasNextLine()!=0) {
     
      String synLine=in.readLine();
      String regex=",";

      String[] synSplit=synLine.split(regex);
      int synID=Integer.parseInt(synSplit[0]);
      String preSplit=synSplit[1];
      String regex2=" ";
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
	  this.graph= new Digraph(idToword.size()); //create directed graph with #of ids
   }

   // all WordNet nouns
   public Iterable<String> nouns(){
   }

   // is the word a WordNet noun?
     public boolean isNoun(String word){
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

