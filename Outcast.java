public class Outcast {
	private WordNet wn;
  
  public Outcast(WordNet wordnet){
	  wn=wordnet;
  }
  
  public String outcast(String[] nouns){
	  int[nouns.size] distance =
	  for (int i=0; i<nouns.size; i++) {
		distance[i]=0;
		}
	  
	  int max=-1, maxIndex=-1;
	  for (i=0; i<nouns.size; i++) {
		  for (int j=0; j<nouns.size; j++) {
			  distance[i]+=wn.distance(nouns[i],nouns[j]);
			}
			if (distance[i]> max) {
				max=distance[i];
			maxIndex[i];
			}
		}
		return nouns[maxIndex];
  }
  
  public static void main(String[] args){
  }
}