import java.util.*;

public class Inventory <T extends InventoryItem> {
    private static int totalItem;
    private static int maxItem = 20;
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
            while(idx>0 && toPut.compareInventory(this.items.get(idx-1)) > 0 && !toPut.equals(this.items.get(idx-1))){
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

    public ArrayList<String> printAll(boolean withAmount){
        ArrayList<String> toReturn = new ArrayList<String>();
        for(int i=0; i<this.items.size(); i++){
            String toAdd = this.items.get(i).printDetail();
            if(withAmount){
                toAdd += " -- You have : ";
                toAdd += Integer.toString(this.amounts.get(i));
            }
            toReturn.add(toAdd);
        }
        return toReturn;
    }
}
