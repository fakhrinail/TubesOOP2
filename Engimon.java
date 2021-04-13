import java.util.ArrayList;

public class Engimon implements Move{
  private String species; private String name;
  private String interaction;
  private ArrayList<String> parentNames;
  private ArrayList<String> parentSpecies;
  private int countSkill; private int countElement;
  private ArrayList<String> skill;
  private ArrayList<String> elements;
  private int life; private int level;
  private int experience; private int cumulativeExperience;
  private int posX; private int posY;
  private final int maxCumulativeExperience = 5000; //5000?

  public Engimon(String species, String name, String parent1Species, 
  String parent1Name, String parent2Species, String parent2Name, 
  String interaction, int life, int level){
    this.species = species; this.name = name;
    this.interaction = interaction;
    this.parentNames = new ArrayList<String>(2);
    this.parentNames.add(parent1Name); this.parentNames.add(parent2Name);
    this.parentSpecies = new ArrayList<String>(2);
    this.parentSpecies.add(parent1Species); this.parentSpecies.add(parent2Species);
    // this.learnedSkills = new Skill[4];
    // this.elements = new Element[3];
    this.life = life; this.level = level;
    this.experience = 0; this.cumulativeExperience = 0;
    this.countElement = 0; this.countSkill = 0;
    this.posX = -1; this.posY = -1;
  }

  public String getSpecies(){
    return this.species;
  }
  
  public String getName(){
    return this.name;
  }

  public int getCountElement(){
    return this.countElement;
  }

  public int getCountSkill(){
    return this.countSkill;
  }

  public int getLife(){
    return this.life;
  }

  public int getLevel(){
    return this.level;
  }

  public void getPos(){
    System.out.println(this.posX);
    System.out.println(this.posY);
  }

  public void setName(String name){
    this.name = name;
  }

  public void setPos(int x, int y){
    this.posX = x; this.posY = y;
  }

  public void interact(){
    System.out.println(this.name + ": " + this.interaction);
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

  public void showDetail(){
    // harusnya gui
    System.out.println("Species "+this.species);
    System.out.println("Nama "+this.name);
    System.out.println("Life "+this.life+"/3");
    for (int i=0; i<2; i++){
      System.out.println("Parent "+(i+1));
      System.out.println("Nama: "+this.parentNames.get(i));
      System.out.println("Species "+this.parentSpecies.get(i));
    }
    System.out.println("Level "+this.level);
    System.out.println("Experience "+this.experience+"/100");
    System.out.println("Cumulative Experience "+this.cumulativeExperience+"/5000");
  }

  public void moveX(int x){
    if (x == 1){
      this.posX++;
    } else if(x == -1){
      this.posX--;
    }
  }

  public void moveY(int y){
    if (y == 1){
      this.posY++;
    } else if(y == -1){
      this.posY--;
    }
  }
}