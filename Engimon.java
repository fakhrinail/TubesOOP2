public class Engimon{
  private String species; private String name;
  private String interaction;
  // private String parent1Species;
  // private String parent1Name;
  // private String parent2Species;
  // private String parent2Name; buat array
  private int countSkill; private int countElement;
  // ArrayList<> skill;
  // ArrayList<> elements;
  private int life; private int level;
  private int experience; private int cumulativeExperience;
  private int posX; private int posY;

  public Engimon(String species, String name, String parent1Species, 
  String parent1Name, String parent2Species, String parent2Name, 
  String interaction, int life, int level){
    this.species = species; this.name = name;
    this.interaction = interaction;
    // this.parentName = new String[2];
    // this.parentSpecies = new String[2];
    // this.parentName[0] = parentName; this->parentName[1] = parentName2;
    // this.parentSpecies[0] = parentSpecies; this->parentSpecies[1] = parentSpecies2;
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
    this.experience += exp; this.cumulativeExperience += exp;
    if (this.experience > 100){
      int addLevel = this.experience/100;
      for (int i=0; i<addLevel; i++){
        levelUp();
      }
    }
  }

  public void levelUp(){
    this.experience -= 100;
    this.level++;
  }

  public void death(){
    System.out.println("Engimon "+this.name+" mati.");
  }

  public void showDetail(){
    System.out.println("Species "+this.species);
    System.out.println("Nama "+this.name);
    System.out.println("Life "+this.life+"/3");
    System.out.println("Level "+this.level);
    System.out.println("Experience "+this.experience);
    System.out.println("Cumulative Experience "+this.cumulativeExperience+"/5000??");
  }

}