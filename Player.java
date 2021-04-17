import java.util.*;

public class Player {
    private Inventory<Engimon> InventoryE;
    private Inventory<Skill> InventoryS;
    public Player(){
        this.InventoryE = new Inventory<Engimon>();
        this.InventoryS = new Inventory<Skill>();
    }
    
}
