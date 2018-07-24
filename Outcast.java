import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
   
/**
 * @author Elaine Schutte
 * @author Abigail Payne
 */

public class Outcast {
 private WordNet wn;
  
/**
 * 
 * This function constructs an Outcast object containing
 * a wordnet 
 * 
 * @param wordnet wordnet used to find outcast
 * @return Outcast with constructed wordnet
 */
  public Outcast(WordNet wordnet){
      if (wordnet == null) {throw new NullPointerException();}
      wn=wordnet;
  }
   /**
 * 
 * This function finds the outcast given an array of WordNet nouns
 * 
 * @param nouns takes a of two vertices used to calculate distance
 * @return string of the outcast
 */
  public String outcast(String[] nouns){
      if (nouns == null) {throw new NullPointerException();}
      if (nouns.length == 0){throw new IllegalArgumentException();}
   int distance[] = new int[nouns.length]; 
   for (int i=0; i<nouns.length; i++) {
       distance[i]=0;
   }
   int max=-1;
   int maxIndex=-1;
   for (int i=0; i<nouns.length; i++) {
       for (int j=0; j<nouns.length; j++) {
           distance[i]+=wn.distance(nouns[i],nouns[j]);
   }
       if (distance[i]> max) {
           max=distance[i];
           maxIndex = i;
   }
  }
  return nouns[maxIndex];
  }
  
  public static void main(String[] args){
    WordNet wordnet = new WordNet(args[0], args[1]);
    Outcast outcast = new Outcast(wordnet);
    for (int t = 2; t < args.length; t++) {
        In in = new In(args[t]);
        String[] nouns = in.readAllStrings();
        StdOut.println(args[t] + ": " + outcast.outcast(nouns));
    }
  }

}
