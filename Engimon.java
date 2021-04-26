import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
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
      ArrayList<Skill> tempSkills = new ArrayList<Skill>(other.getSkills());
      for (Skill otherSkill : other.getSkills()) {
        int i = 0;
        for (Skill thisSkill : inherSkills) {
          if (thisSkill.getName().equals(otherSkill.getName())) {
            if (thisSkill.getMastery() == otherSkill.getMastery()) {
              if (thisSkill.getMastery() != 3) {
                thisSkill.setMastery(thisSkill.getMastery() + 1);
              }
              i = 1;
            }
            else if (thisSkill.getMastery() < otherSkill.getMastery()) {
              thisSkill.setMastery(otherSkill.getMastery());
              i = 1;
            }
          }
          // if (thisSkill.getName().equals(otherSkill.getName())) {
          //   if (thisSkill.getMastery() == otherSkill.getMastery()) {
          //     if (thisSkill.getMastery() != 3) {
          //       thisSkill.setMastery(thisSkill.getMastery() + 1);
          //     }
          //   }
          //   else if (thisSkill.getMastery() < otherSkill.getMastery()) {
          //     thisSkill.setMastery(otherSkill.getMastery());
          //   }
          // }
          // else {
          //   tempSkills.add(otherSkill);
          // }
        }
        if (i == 0) {
          inherSkills.add(otherSkill);
        }
      }
      //inherSkills.addAll(tempSkills);
      
      Collections.sort(inherSkills);
      
      if (inherSkills.size() > 4) {
        int size = inherSkills.size();
        for (int i = 0; i < (size - 4); i++) {
          inherSkills.remove(0);
        }
      }
      String nama = "Bambank";//BUAT INPUTNYA
      
      if (this.getElementals().equals(other.getElementals()) && this.getElementals().size() == 1) {
        Species SpeciesChild = new Species(this.getSpecies(), inherSkills, this.getElementals(), this.getInteraction());
        Engimon child = new Engimon(SpeciesChild, nama ,
                                this.getSpecies(), this.name,
                                other.getSpecies(), other.name, 3, 1);
        return child;
      }
      else /*if (!this.getElementals().equals(other.getElementals()) && this.getElementals().size() == 1)*/ {
        //Kasus breed 2 elemen
        int numElmt1; //pakai random
        int numElmt2; //pakai random
        for (Elemental elmt : other.getElementals()) {
          System.out.println(elmt.getName());
        }
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
        System.out.println(thisElmt.getAdv(otherElmt)+" "+otherElmt.getAdv(thisElmt));
        System.out.println(thisElmt.getName()+" "+otherElmt.getName());
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
        else if (thisElmt.getName().equals(otherElmt.getName())) {
          ArrayList<Elemental> elmtChild = new ArrayList<Elemental>();
          elmtChild.add(thisElmt);
          Species SpeciesChild = new Species(this.getSpecies(), inherSkills, elmtChild, this.getInteraction());
          Engimon child = new Engimon(SpeciesChild, nama ,
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
          else /*if (thisElmt.getName().equals("Ground") || otherElmt.getName().equals("Ground")) */{
            ArrayList<Elemental> childElmt = new ArrayList<Elemental>(this.getElementals());
            childElmt.addAll(other.getElementals());
            SpeciesChild = new Species("Narutomon", inherSkills, childElmt, "datebayoo");
          }
          Engimon child = new Engimon(SpeciesChild, nama,
                                 this.getSpecies(), this.name,
                                 other.getSpecies(), other.name, 3, 1);
          return child;
        }
      }
      //else {
      //}
    } catch (ArithmeticException e) {
      System.out.println(e.getMessage());
      return new Engimon();
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
  
  public ArrayList<String> printAllDetail(){
    ArrayList<String> toReturn = new ArrayList<String>();
    toReturn.add("Umum: " + getSpecies() + "/" + this.name);
    toReturn.add("Parents: " + this.parentNames.get(0) + "/" +this.parentSpecies.get(0) + ", " + " "+this.parentNames.get(1) + "/" +this.parentSpecies.get(1));
    int j=1;
    for (Skill a : this.getSkills()){
      toReturn.add("Skill "+Integer.toString(j)+" " + a.printDetail());
      j++;
    }
    toReturn.add("Life "+Integer.toString(this.life)+"/3, " + "Level "+Integer.toString(this.level) + ", " + "Experience "+Integer.toString(this.experience)+"/100" + ", ");
    return toReturn;
  }

  // Realisasi InventoryItem
  public String printDetail(){
    String toReturn = this.name;
    toReturn += "/";
    for(int i=0; i<this.elementals.size(); i++){
      toReturn += this.elementals.get(i).getName();
    }
    toReturn += "/";
    toReturn += Integer.toString(this.level);
    return toReturn;
  }

  // masih belom bener
  public int getComparator1(){
    int toReturn = 0;
    int mul = 1;
    for(int i=0; i<this.elementals.size(); i++){
      toReturn += mul*(this.elementals.get(i).getID()-1);
      mul *= Elemental.nbElementals;
    }
    return toReturn;
  }
  public int getComparator2(){
    return this.level;
  }
  public int compareInventory(InventoryItem other){
    if(this.getComparator1() == other.getComparator1()){
      return this.getComparator2() - other.getComparator2();
    }else{
      return this.getComparator1() - other.getComparator1();
    }
  }
  public boolean equals(InventoryItem other){
    return false;
  }

  public Engimon(String input){
    super(input.substring(input.indexOf(" ") + 1, input.length()));
    String engimonInfo[] = input.substring(0, input.indexOf(" ")).trim().split(";");
    this.name = engimonInfo[0];
    this.parentNames = new ArrayList<String>(2);
    this.parentNames.add(engimonInfo[1]); this.parentNames.add(engimonInfo[2]);
    this.parentSpecies = new ArrayList<String>(2);
    this.parentSpecies.add(engimonInfo[3]); this.parentSpecies.add(engimonInfo[4]);
    this.life = Integer.parseInt(engimonInfo[5]); this.level = Integer.parseInt(engimonInfo[6]);
    this.experience = Integer.parseInt(engimonInfo[7]); this.cumulativeExperience = Integer.parseInt(engimonInfo[8]);
    this.engimonX = -1; this.engimonY = -1;
  }

  public String engimonToString(){
    String output = this.name;
    output.concat(";" + this.parentNames.get(0));
    output.concat(";" + this.parentNames.get(1));
    output.concat(";" + this.parentSpecies.get(0));
    output.concat(";" + this.parentSpecies.get(1));
    output.concat(";" + Integer.toString(this.life));
    output.concat(";" + Integer.toString(this.level));
    output.concat(";" + Integer.toString(this.experience));
    output.concat(";" + Integer.toString(this.cumulativeExperience));
    output.concat(" " + this.species);
    output.concat(";" + Integer.toString(this.skills.size()));
    for (int i = 0; i < this.skills.size(); i++){
      output.concat(";" + this.skills.get(i).skillToString());
    }
    for (int i = 0; i < this.elementals.size(); i++){
      output.concat(";" + this.elementals.get(i).getName());
    }
    output.concat(";" + this.interaction);
    return output;
  }
}
