import java.util.*;

public class Skill implements InventoryItem {
    private String name;
    private int mastery;
    private int basePower;
    private List<Elemental> compatibles;
    
    public Skill(String[] SkillInfo){
        this.name = SkillInfo[0];
        this.mastery = 1;
        this.basePower = Integer.parseInt(SkillInfo[1]);
        this.compatibles = new ArrayList<Elemental>();
        for(int i=2; i<SkillInfo.length; i++){
            Elemental toAdd = new Elemental(SkillInfo[i]);
            this.compatibles.add(toAdd);
        }
    }
    //Copy
    public Skill(Skill other){
        this.name = other.name;
        this.mastery = other.mastery;
        this.basePower = other.basePower;
        this.compatibles = new ArrayList<Elemental>();
        for(int i=2; i<other.compatibles.size(); i++){
            Elemental toAdd = other.compatibles.get(i);
            this.compatibles.add(toAdd);
        }
    }

    //Getters dan Setters
    public String getName(){
        return this.name;
    }
    public int getBasePower(){
        return this.basePower;
    }
    public int getMastery() {
        return this.mastery;
    }
    public void setMastery(int m) {
        this.mastery = m;
    }

    //Metode pribadi
    public boolean isCompatible(Elemental e1, Elemental e2){
        boolean toReturn = false;
        for(int i=0; i<this.compatibles.size(); i++){
            toReturn = toReturn || this.compatibles.get(i).equals(e1) || this.compatibles.get(i).equals(e2);
        }
        return toReturn;
    }

    //Realisasi InventoryItem
    public void printDetail(){
        System.out.print(basePower);
        System.out.print("/");
        System.out.print(mastery);
        System.out.print("/" + name);
    }
    public int getComparator1(){
        return this.basePower;
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
    @Override
    public boolean equals(InventoryItem other){
        return this.getName() == other.getName();
    }

    /*Realisasi Comparable
    @Override
    public int compareTo(Skill s) {
        int compareLevel = ((Skill)s).mastery;
        return this.mastery-compareLevel;
    }
    */
}
