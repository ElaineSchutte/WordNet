import edu.princeton.cs.algs4.In;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.lang.String;
import java.lang.Integer;
import edu.princeton.cs.algs4.Digraph;

public class WordNet {
	private final HashMap<String,ArrayList<Integer>> wordToId; //Word is the key, ID number is the value
	private final HashMap<Integer,ArrayList<String>> idToWord; //ID number is the key, words are the value
	private final Digraph graph;
	
	public WordNet(String synsets, String hypernyms) {
		wordToId = new HashMap<String,ArrayList<Integer>>();
		idToWord = new HashMap<Integer,ArrayList<String>>();
		
		// constructor takes the name of the two input files
		// To Process synsets
		In input = new In(synsets);
		int totalSynsets=0;
		while (input.hasNextLine()) {
			// Parse line
			String[] synLine = input.readLine().split(",");
			int synId = Integer.parseInt(synLine[0]);
			String[] synWords = synLine[1].split(" ");
			// Map words to unique Id
			for (int i = 0; i < synWords.length; i++) {
				if (!wordToId.containsKey(synWords[i])) {//if map doesn't already have this word
					List<Integer> newIdList = new ArrayList<Integer>();
					wordToId.put(synWords[i],newIdList); //put it in the map and give it an empty arraylist
					
				}
				wordToId.get(synWords[i]).add(synId);//add the id to current array list
			}
			// Map unique Id to words
			List<String> newWordList = new ArrayList<String>();
			idToWord.put(synId,newWordList);
			for (int i = 0; i < synWords.length; i++){
				newWordList.add(synWord[i]);}
			idToWord.put(synId,newWordList);
			totalSynsets++;
		}
		
		Digraph graph= new Digraph(totalSynsets);
		// Create directed graph with #of synsets
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
	
	public Iterable<String> nouns() {
		return wordToId.keySet();
	}
	
	public boolean isNoun(String word) {
		return wordToId.containsKey(word);
	}
	
	// TODO
	public String sca(String word1, String word2) {}
	
	// TODO
	public int distance(String word1, String word2) {}
	
	// TODO
	public static void main(String[] args) {}
}