public class Engimon implements InventoryItem {
    private String name;
    private int level;
    private Element element;
    public Engimon(String n, int lv, Element e){
        this.name = n;
        this.level = lv;
        this.element = e;
    }
    public String getName(){
        return this.name;
    }
    public void printDetail(){
        
    }
    public int compareTo(InventoryItem other){
        if(this.getComparator1() == other.getComparator1()){
            return this.getComparator2() - other.getComparator2();
        }else{
            return this.getComparator1() - other.getComparator1();
        }
    }
    public int getComparator1(){
        return this.element.getID();
    }
    public int getComparator2(){
        return this.level;
    }
    public boolean equals(InventoryItem other){
        return false;
    }
}
