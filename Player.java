import java.util.*;

public class Player {
    private Inventory<Engimon> InventoryE;
    private Inventory<Skill> InventoryS;
    private int playerX, playerY;
    private Engimon activeEngimon;

    public Player(){
        this.playerX = 0;
        this.playerY = 0;
        this.activeEngimon = new Engimon("Wildcard", "startermon", "dewa", "zeus", "dewi", "athena", "lets roll cuk", 100, 10);
        this.activeEngimon.setPos(0, 1);
        this.InventoryE = new Inventory<Engimon>();
        this.InventoryS = new Inventory<Skill>();
    }

    public void w(){
        this.activeEngimon.setPos(this.playerX, this.playerY);
        this.playerX--;
    }
    public void a(){
        this.activeEngimon.setPos(this.playerX, this.playerY);
        this.playerY--;
    }
    public void s(){
        this.activeEngimon.setPos(this.playerX, this.playerY);
        this.playerX++;
    }
    public void d(){
        this.activeEngimon.setPos(this.playerX, this.playerY);
        this.playerY--;
    }

    public void setActiveEngimon(int idx){
        Engimon choosen = this.InventoryE.remove(idx);
        choosen.setPos(this.activeEngimon.getX(), this.activeEngimon.getY());
        this.activeEngimon.setPos(-1, -1);
        this.InventoryE.put(this.activeEngimon);
        this.activeEngimon = choosen;
    }

    public void manageActiveEngimon(){
        System.out.println("Active engimon :");
        this.activeEngimon.printDetail();
        int entry;
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("What are you going to do?");
            System.out.println("1. Change Engimon");
            System.out.println("2. Interact");
            System.out.println("3. Back");
            System.out.println("(choose the number)");
            System.out.print("input : ");
            entry = sc.nextInt();
            if (entry == 1) {
                this.InventoryE.printAll(false);
                System.out.print("Select the number : ");
                int selected = System.in.read();
                setActiveEngimon(selected-1);
                break;
            }
		    else if (entry == 2) {
                this.activeEngimon.interact();
		    }
            else if (entry == 3) {
                break;
            }
            else {
                System.out.println("Invalid action");
            }
        }
    }

    public void select(String section, int idx){
        Scanner sc = new Scanner(System.in);
        try {
            if (section.equals("E")){
                this.InventoryE.get(idx).printDetail();
                System.out.println("Action available : ");
                System.out.println("1. Set to active");
                System.out.println("2. Interact");
                System.out.println("3. Cancel");
                System.out.print("Choose number : ");
                int input = sc.nextInt();
                if (input == 1){
                    setActiveEngimon(idx);
                } else if (input == 2){
                    this.InventoryE.get(idx).interact();
                } else if (input == 3){
                    return;
                } else {
                    System.out.println("Invalid action");
                }
            }
            else if (section.equals("S")){
                this.InventoryS.get(idx).printDetail();
                System.out.println("Action available : ");
                System.out.println("1. Use Skill");
                System.out.println("2. Cancel");
                System.out.print("Choose the number : ");
                int input = sc.nextInt();
                if (input == 1){
                    Skill choosen = this.InventoryS.remove(idx);
                    choosen.useSkill();
                } else if (input == 2){
                    return;
                } else {
                    System.out.println("Invalid action");
                }
            }
            else {
                System.out.println("Invalid selection");
            }
        } catch(IndexOutOfBoundsException e) {System.out.println("Index out of range");}
    }

    public void openInventory(){
        int input;
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("Engimon list :");
            this.InventoryE.printAll(false);
            System.out.println();
            System.out.println("Skill item list :");
            this.InventoryS.printAll(true);
            System.out.println();
            System.out.println("Action available : ");
            System.out.println("1. Select item");
            System.out.println("2. Back");
            System.out.print("Choose the number : ");
            input = sc.nextInt();
            if (input == 1) {
                System.out.println("Select with format E/S + number (ex : E3 / S1)");
                System.out.print("input : ");
                String selected = sc.nextLine();
                if (selected.length() != 2) {
                    System.out.println("Invalid selection");
                } else {
                    select(selected.substring(0,0), Character.getNumericValue(selected.charAt(1)));
                }
            } else if (input == 2) {
                break;
            } else {
                System.out.println("Invalid action");
            }
        }
    }
}


