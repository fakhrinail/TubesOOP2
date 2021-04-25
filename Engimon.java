import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Engimon extends Species implements Move, InventoryItem{
  private String name;
  private ArrayList<String> parentNames;
  private ArrayList<String> parentSpecies;
  private int life; private int level;
  private int experience; private int cumulativeExperience;
  private int engimonX; private int engimonY;
  private final int maxCumulativeExperience = 5000; //5000?

  public Engimon(){
    super();
    this.name = "Default";
    this.parentNames = new ArrayList<String>(2);
    this.parentSpecies = new ArrayList<String>(2);
    this.life = 3; this.level = 1;
    this.experience = 0; this.cumulativeExperience = 0;
    this.engimonX = -1; this.engimonY = -1;
  }
  
  public Engimon(Species other, String name, String parent1Species, String parent1Name, 
  String parent2Species, String parent2Name, int life, int level){
    super(other);
    this.name = name;
    this.parentNames = new ArrayList<String>(2);
    this.parentNames.add(parent1Name); this.parentNames.add(parent2Name);
    this.parentSpecies = new ArrayList<String>(2);
    this.parentSpecies.add(parent1Species); this.parentSpecies.add(parent2Species);
    this.life = life; this.level = level;
    this.experience = 0; this.cumulativeExperience = 0;
    this.engimonX = -1; this.engimonY = -1;
  }

  //Copy
  public Engimon(Engimon other){
    super(other.getSpecies(), other.getSkills(), other.getElementals(), other.getInteraction());
    this.name = other.getName();
    this.parentNames = new ArrayList<String>(2);
    this.parentNames.addAll(other.getParentNames());
    this.parentSpecies = new ArrayList<String>(2);
    this.parentSpecies.addAll(other.getParentSpecies());
    this.life = other.getLife(); this.level = other.getLevel();
    this.experience = other.getExperience(); this.cumulativeExperience = other.getCumulativeExperience();
    this.engimonX = other.getEngimonX(); this.engimonY = other.getEngimonY();
  }

  //Getters dan Setters  
  public String getName(){
    return this.name;
  }

  public ArrayList<String> getParentNames(){
    ArrayList<String> toReturn = new ArrayList<String>();
    toReturn.addAll(this.parentNames);

    return toReturn;
  }

  public ArrayList<String> getParentSpecies(){
    ArrayList<String> toReturn = new ArrayList<String>();
    toReturn.addAll(this.parentSpecies);

    return toReturn;
  }

  public int getLife(){
    return this.life;
  }

  public int getLevel(){
    return this.level;
  }

  public int getExperience(){
    return this.experience;
  }

  public int getCumulativeExperience(){
    return this.cumulativeExperience;
  }

  public int getEngimonX(){
    return this.engimonX;
  }

  public int getEngimonY(){
    return this.engimonY;
  }

  public void setName(String name){
    this.name = name;
  }

  public void setParents(String parent1Species, 
  String parent1Name, String parent2Species, String parent2Name){
    this.parentNames.set(0, parent1Name); this.parentNames.set(1, parent2Name);
    this.parentSpecies.set(0, parent1Species); this.parentSpecies.set(1, parent2Species);
  }

  public void setLife(int l){
    this.life = l;
  }

  public void setLevel(int l){
    this.level = l;
  }

  public void setPos(int x, int y){
    this.engimonX = x; this.engimonY = y;
  }

  //Method lain
  public void interact(){
    System.out.println(this.name + ": " + getInteraction());
  }

  public void addExperience(int exp){
    if (this.cumulativeExperience + exp <= 5000){
      this.experience += exp; this.cumulativeExperience += exp;
      if (this.experience > 100){
        int addLevel = this.experience/100;
        for (int i=0; i<addLevel; i++){
          levelUp();
        }
      }
    }else{
      death();
    }
  }

  public void levelUp(){
    this.experience -= 100;
    this.level++;
  }

  public void death(){
    // harusnya gui
    System.out.println("Engimon "+this.name+" mati.");
  }

  public Engimon breed(Engimon other) {
    try {
      if (this.level < 4 || other.level < 4) {
        throw new ArithmeticException("Level tidak mencukupi");
      }
      this.level -= 3;
      other.level -= 3;
      //belum tau cara dapat skill unik
      ArrayList<Skill> inherSkills = new ArrayList<Skill>(this.getSkills());
      ArrayList<Skill> tempSkills = new ArrayList<Skill>();
      for (Skill thisSkill : inherSkills) {
        for (Skill otherSkill : other.thisSkill) {
          if (thisSkill.getName().equals(otherSkill.getName())) {
            if (thisSkill.getMastery() == otherSkill.getMastery()) {
              if (thisSkill.getMastery() != 3) {
                thisSkill.setMastery(thisSkill.getMastery() + 1);
              }
            }
            else if (thisSkill.getMastery() < otherSkill.getMastery()) {
              thisSkill.setMastery(otherSkill.getMastery());
            }
          }
          else {
            tempSkills.add(otherSkill);
          }
        }
      }
      inherSkills.addAll(tempSkills);
      Collection.sort(inherSkills);
      if (inherSkills.size() > 3) {
        for (int i = 4; i < inherSkills.size(); i++) {
          inherSkills.remove(i);
        }
      }
      String nama = "Bambank";//BUAT INPUTNYA
      if (this.getElementals().equals(other.getElementals()) && this.getElementals().size() == 1) {
        Species SpeciesChild = new Species(this.getSpecies(), inherSkills, this.getElementals(), this.getInteraction());
        Engimon child = new Engimon(SpeciesChild, nama ,
                                this.getSpecies(), this.name,
                                ther.getSpecies(), other.name, 3, 1);
        return child;
      }
      else /*if (!this.getElementals().equals(other.getElementals()) && this.getElementals().size() == 1) */{
        //Kasus breed 2 elemen
        int numElmt1; //pakai random
        int numElmt2; //pakai random
        if (this.getElementals().size() == 2) {
          Random rand = new Random(); // import java.util.Random;
          numElmt1 = rand.nextInt(2);
        } else {numElmt1 = 0;}
        if (other.getElementals().size() == 2) {
          Random rand = new Random(); // import java.util.Random;
          numElmt2 = rand.nextInt(2);
        } else {numElmt2 = 0;}
        Elemental thisElmt = this.getElementals().get(numElmt1);
        Elemental otherElmt = other.getElementals().get(numElmt2);
        if (thisElmt.getAdv(otherElmt) > otherElmt.getAdv(thisElmt)) {
          //ambil this
          Species SpeciesChild = new Species(this.getSpecies(), inherSkills, this.getElementals(), this.getInteraction());
          Engimon child = new Engimon(SpeciesChild, nama ,
                               this.getSpecies(), this.name,
                               other.getSpecies(), other.name, 3, 1);
          return child;
        }
        else if (thisElmt.getAdv(otherElmt) < otherElmt.getAdv(thisElmt)) {
          //ambil other
          Species SpeciesChild = new Species(other.getSpecies(), inherSkills, other.getElementals(), other.getInteraction());
          Engimon child = new Engimon(SpeciesChild, nama,
                                 this.getSpecies(), this.name,
                                 other.getSpecies(), other.name, 3, 1);
          return child;
        }
        else {
          // gabungan
          Species SpeciesChild;
          if (thisElmt.getName().equals("Fire") || otherElmt.getName().equals("Fire")) {
            ArrayList<Elemental> childElmt = new ArrayList<Elemental>(this.getElementals());
            childElmt.addAll(other.getElementals());
            SpeciesChild = new Species("Itachimon", inherSkills, childElmt, "A nameless engimon Who protects peace within its shadows, that is a true engimon.");
          }
          else if (thisElmt.getName().equals("Ice") || otherElmt.getName().equals("Ice")) {
            ArrayList<Elemental> childElmt = new ArrayList<Elemental>(this.getElementals());
            childElmt.addAll(other.getElementals());
            SpeciesChild = new Species("Telermon", inherSkills, childElmt, "buuurrrrppppp....");
          }
          else if (thisElmt.getName().equals("Ground") || otherElmt.getName().equals("Ground")) {
            ArrayList<Elemental> childElmt = new ArrayList<Elemental>(this.getElementals());
            childElmt.addAll(other.getElementals());
            SpeciesChild = new Species("Narutomon", inherSkills, childElmt, "datebayoo");
          }
          return child;
        }
      }
      //else {
      //}
    } catch (Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  // Realisasi Move
  public void moveX(int x){
    if (x == 1){
      this.engimonX++;
    } else if(x == -1){
      this.engimonX--;
    }
  }

  public void moveY(int y){
    if (y == 1){
      this.engimonY++;
    } else if(y == -1){
      this.engimonY--;
    }
  }
  
  public void printAllDetail(){
    System.out.println("Species "+getSpecies());
    System.out.println("Nama "+this.name);
    System.out.println("Life "+this.life+"/3");
    for (int i=0; i<2; i++){
      System.out.println("Parent "+(i+1));
      System.out.println("Nama: "+this.parentNames.get(i));
      System.out.println("Species "+this.parentSpecies.get(i));
    }
    int j=0;
    for (Skill a : this.getSkills()){
      System.out.print("Skill "+j+" ");
      a.printDetail();
      System.out.println();
      j++;
    }
    System.out.println("Level "+this.level);
    System.out.println("Experience "+this.experience+"/100");
    System.out.println("Cumulative Experience "+this.cumulativeExperience+"/5000");
  }

  // Realisasi InventoryItem
  public String printDetail(){
    String toReturn = this.name;
    toReturn += "/";
    for(int i=0; i<this.elementals.size(); i++){
      toReturn += this.elementals.get(i).getName();
    }
    toReturn += "/";
    toReturn += this.name;
    return toReturn;
  }

  // masih belom bener
  public int getComparator1(){
    return this.level;
  }
  public int getComparator2(){
    return 0;
  }
  public int compareTo(InventoryItem other){
    if(this.getComparator1() == other.getComparator1()){
      return this.getComparator2() - other.getComparator2();
    }else{
      return this.getComparator1() - other.getComparator1();
    }
  }
  public boolean equals(InventoryItem other){
    return this.getName() == other.getName();
  }
}
