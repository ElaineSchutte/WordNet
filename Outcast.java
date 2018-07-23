public class Outcast {
 private WordNet wn;
  
  public Outcast(WordNet wordnet){
   wn=wordnet;
  }
  
  public String outcast(String[] nouns){
   int distance[] = new int[nouns.length]; 
   for (int i=0; i<nouns.length; i++) {
  distance[i]=0;
  }
   
   int max=-1, maxIndex=-1;
   for (int i=0; i<nouns.length; i++) {
    for (int j=0; j<nouns.length; j++) {
     if (nouns[i]!=nouns[j]){
      distance[i]+=wn.distance(nouns[i],nouns[j]);
     }
   }
   if (distance[i]> max) {
    max=distance[i];
    maxIndex = i;
   }
  }
  return nouns[maxIndex];
  }
  
  public static void main(String[] args){
  }
}
