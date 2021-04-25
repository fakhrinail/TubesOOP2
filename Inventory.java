import java.util.*;

public class Inventory <T extends InventoryItem> {
    private static int totalItem;
    private static int maxItem = 30;
    private ArrayList<T> items;
    private ArrayList<Integer> amounts;
    
    public Inventory(){
        this.items = new ArrayList<T>();
        this.amounts = new ArrayList<Integer>();
        totalItem = 0;
    }

    public void put(T toPut){
        if(totalItem == maxItem){
            System.out.println("Inventory full");
        }else{
            totalItem++;
            int idx = this.items.size();
            while(idx>0 && toPut.compareTo(this.items.get(idx-1)) == 0 && !toPut.equals(this.items.get(idx-1))){
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

    public T remove(int idx) throws IndexOutOfBoundsException {
        if(this.amounts.get(idx) == 1){
            totalItem--;
            this.amounts.remove(idx);
            return this.items.remove(idx);
        }else{
            totalItem--;
            int old = this.amounts.get(idx);
            this.amounts.set(idx, old - 1);
            return this.items.get(idx);
        }
    }

    public void discard(int idx, int amount){
        if (amount < 0 || this.amounts.get(idx) < amount){
            System.out.println("Invalid amount");
            return;
        }
        String name = this.items.get(idx).getName();
        if (this.amounts.get(idx) == amount){
            this.amounts.remove(idx);
            this.items.remove(idx);
        } else {
            int old = this.amounts.get(idx);
            this.amounts.set(idx, old - amount);
        }
        totalItem -= amount;
        System.out.println("Successfully remove " + amount + " " + name + " from inventory\n");
    }

    public T get(int idx) throws IndexOutOfBoundsException {
        return this.items.get(idx);
    }

    public int getAmount(int idx) {
        return this.amounts.get(idx);
    }

    public void printAll(boolean withAmount){
        for(int i=0; i<this.items.size(); i++){
            System.out.print(i+1);
            System.out.print(". " + this.items.get(i).getName());
            if(withAmount){
                System.out.print(" -- You have : ");
                System.out.print(this.amounts.get(i));
            }
            System.out.println();
        }
    }
}
