import java.util.*;

public class Inventory <T extends InventoryItem> {
    private static int totalItem;
    private static int maxItem = 30;
    private List<T> items;
    private List<Integer> amounts;
    
    public Inventory(){
        this.items = new ArrayList<T>();
        this.amounts = new ArrayList<Integer>();
        this.totalItem = 0;
    }

    public void put(T toPut){
        if(totalItem == maxItem){
            System.out.println("Inventory full");
        }else{
            this.totalItem++;
            int idx = this.items.size();
            boolean toFront = true;
            while(idx>0 && toPut.compareTo(this.items.get(idx-1)) > 0 && !toPut.equals(this.items.get(idx-1))){
                idx--;
            }
            if(idx > 0 && toPut.equals(this.items.get(idx-1))){
                Integer old = this.amounts.get(idx-1);
                this.amounts.set(idx-1, old+1);
            }else{
                this.items.add(idx, toPut);
                this.amounts.add(idx, 1);
            }
        }
    }

    public T remove(int idx){
        if(0<=idx && idx<this.items.size()){
            this.totalItem--;
            if(this.amounts.get(idx) == 1){
                this.amounts.remove(idx);
                return this.items.remove(idx);
            }else{
                Integer old = this.amounts.get(idx);
                this.amounts.set(idx, old-1);
                return this.items.get(idx);
            }
        }else{
            System.out.println("Index out of range");
            return null;
        }
    }

    public T get(int idx){
        return this.items.get(idx);
    }

    public void printAll(boolean withAmount){
        for(int i=0; i<this.items.size(); i++){
            System.out.print(i+1);
            System.out.print(". " + this.items.get(i).getName() + ";");
            if(withAmount){
                System.out.print(" You have: ");
                System.out.print(this.amounts.get(i));
            }
            System.out.println();
        }
    }
}
