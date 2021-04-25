import java.util.ArrayList;
import java.util.Collection;

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

  // public Engimon breed(Engimon other) {
  //   try {
  //     this.level -= 3;
  //     other.level -= 3;
  //     if (this.countElement == other.countElement && this.countElement == 1) {
  //       if (this.elements.equals(other.elements)) {
  //         ArrayList<Skill> inherSkills = this.skill.addAll(other.skill);
  //         ArrayList<Skill> tempSkills = new ArrayList<Skill>();
  //         for (Skill skill : inherSkills) {
  //           for (Skill skill2 : other.skill) {
  //             if (skill.getName().equals(skill2.getName())) {
  //               if (skill.getMastery() == skill2.getMastery()) {
  //                 if (skill.getMastery() != 3) {
  //                   skill.setMastery(skill.getMastery() + 1);
  //                 }
  //               }
  //               else if (skill.getMastery() < skill2.getMastery()) {
  //                 skill.setMastery(skill2.getMastery());
  //               }
  //             }
  //             else {
  //               tempSkills.add(skill2);
  //             }
  //           }
  //         }
  //         inherSkills.addAll(tempSkills);
  //         Collection.sort(inherSkills);
  //         if (inherSkills.size() > 3) {
  //           for (int i = 4; i < inherSkills.size(); i++) {
  //             inherSkills.remove(i);
  //           }
  //         }
  //         Engimon child = Engimon(); //construct dengan skill diatas
  //         return child;
  //       }
  //       else{
  //         //kayak atas dengan sedikit perubahan
  //       }
  //     }
  //   } catch (Exception e) {
  //     //TODO: handle exception
  //   } finally {
  //     this.level += 3;
  //     other.level += 3;
  //   }
  // }

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
  
  // Realisasi InventoryItem
  public void printDetail(){
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
