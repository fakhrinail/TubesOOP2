public class Skill implements InventoryItem {
    private String name;
    private int mastery;
    private int basePower;
    
    public Skill(String n, int m, int b){
        this.name = n;
        this.mastery= m;
        this.basePower = b;
    }

    public String getName(){
        return this.name;
    }
    public void printDetail(){
        System.out.print(basePower);
        System.out.print(" " + name);
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
}
