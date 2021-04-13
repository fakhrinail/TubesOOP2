import java.util.ArrayList;

public class EngimonCollection{
  ArrayList<Engimon> collection;
  
  public EngimonCollection(){
    this.collection =  new ArrayList<Engimon>();
    this.collection.add(new Engimon("Firemon", "wildFiremon", 
    "noparent", "noparent", "noparent", "noparent", 
    "Ready to burn down the forest, bos?", 1, 1));
    this.collection.add(new Engimon("Watermon", "wildWatermon", 
    "noparent", "noparent", "noparent", "noparent", 
    "Even the atlantis was drowned.", 1, 1));
  }

  public Engimon getEngimon(int i){
    return this.collection.get(i);
  }
}