import java.util.*;
import java.io.*;

public class Player {
    private Inventory<Engimon> InventoryE;
    private Inventory<Skill> InventoryS;
    private int playerX, playerY;
    private Engimon activeEngimon;

    public Player(Engimon active){
        this.playerX = 0;
        this.playerY = 0;
        this.activeEngimon = active;
        this.activeEngimon.setPos(0, 1);
        this.InventoryE = new Inventory<Engimon>();
        this.InventoryS = new Inventory<Skill>();
    }

    public Player(String filepath){  
        try{
            Scanner file = new Scanner(new BufferedReader(new FileReader(filepath)));
            String playerPos[] = file.nextLine().trim().split(",");
            this.playerX = Integer.parseInt(playerPos[0]);
            this.playerY = Integer.parseInt(playerPos[1]);
            String activeEngimonInfo = file.nextLine();
            if (!activeEngimonInfo.equals("-")){
                this.activeEngimon = new Engimon(activeEngimonInfo);
                String engimonPos[] = file.nextLine().trim().split(",");
                this.activeEngimon.setPos(Integer.parseInt(engimonPos[0]), Integer.parseInt(engimonPos[0]));
            }
            this.InventoryE = new Inventory<Engimon>();
            int IESize = Integer.parseInt(file.nextLine());
            for (int i = 0; i < IESize; i++){
                this.addEngimon(new Engimon(file.nextLine()));
            }
            this.InventoryS = new Inventory<Skill>();
            int ISsize = Integer.parseInt(file.nextLine());
            for (int i = 0; i < ISsize; i++){
                this.addSkillItem(new Skill(file.nextLine()));
            }
        } catch(FileNotFoundException ex){
            System.out.println("Nama file yang anda masukkan salah");
        }
    }

    public void savePlayer(String fileName){
        try{
            BufferedWriter fileWriter =  new BufferedWriter(new FileWriter("../files/"+ fileName + ".txt", true));
            fileWriter.write(Integer.toString(this.playerX) + "," + Integer.toString(this.playerY)); fileWriter.newLine();
            if (this.activeEngimon != null){
                fileWriter.write(this.activeEngimon.toString()); fileWriter.newLine();
                fileWriter.write(Integer.toString(this.activeEngimon.getEngimonX()) + "," + Integer.toString(this.activeEngimon.getEngimonY()));
                fileWriter.newLine();
            } else {
                fileWriter.write("-"); fileWriter.newLine();
            }
            fileWriter.write(Integer.toString(this.InventoryE.getSize())); fileWriter.newLine();
            for (int i = 0; i < this.InventoryE.getSize(); i++){
                fileWriter.write(this.InventoryE.get(i).engimonToString());
                fileWriter.newLine();
            }
            fileWriter.write(Integer.toString(this.InventoryS.getSize())); fileWriter.newLine();
            for (int i = 0; i < this.InventoryS.getSize(); i++){
                fileWriter.write(this.InventoryS.get(i).skillToString());
                fileWriter.newLine();
            }
            fileWriter.flush();
            fileWriter.close();

        } catch(Exception ex){
            System.out.println("Error");
        }
    }

    public void w(){
        if (this.activeEngimon != null){
            this.activeEngimon.setPos(this.playerX, this.playerY);
        }
        this.playerX--;
    }
    public void a(){
        if (this.activeEngimon != null){
            this.activeEngimon.setPos(this.playerX, this.playerY);
        }
        this.playerY--;
    }
    public void s(){
        if (this.activeEngimon != null){
            this.activeEngimon.setPos(this.playerX, this.playerY);
        }
        this.playerX++;
    }
    public void d(){
        if (this.activeEngimon != null){
            this.activeEngimon.setPos(this.playerX, this.playerY);
        }
        this.playerY++;
    }

    public void removeActiveEngimon(){
        this.activeEngimon = null;
    }

    public void setActiveEngimon(int idx){
        try {
            Engimon choosen = this.InventoryE.remove(idx);
            if (this.activeEngimon != null){
                choosen.setPos(this.activeEngimon.getEngimonX(), this.activeEngimon.getEngimonY());
                this.activeEngimon.setPos(-1, -1);
                this.InventoryE.put(this.activeEngimon);
            } else {
                choosen.setPos(this.playerX, this.playerY); // blm fix
            }
            this.activeEngimon = choosen;
            System.out.println("Active engimon has been changed successfully\n");
        }
        catch(IndexOutOfBoundsException e) {
            System.out.println("Invalid selection (Index out of range)\n");
        }
    }
    /*
    public void manageActiveEngimon(Scanner sc){
        String entry;
        System.out.println("Active engimon :");
        if (this.activeEngimon == null){
            System.out.println("No active engimon");
            System.out.println("What are you going to do?");
            System.out.println("1. Set an active engimon");
            System.out.println("2. Back");
            System.out.print("Choose the number : ");
            entry = sc.nextLine();
            while (true) {
                if (entry.equals("1")) {
                    this.InventoryE.printAll(false);
                    System.out.print("Select the number : ");
                    int selected = sc.nextInt();
                    sc.nextLine();
                    setActiveEngimon(selected-1);
                    break;
                }
                else if (entry.equals("2")) {
                    break;
                }
                else {
                    System.out.println("Invalid action\n");
                }
            }
        } 
        else {
            this.activeEngimon.printDetail();
            while (true) {
                System.out.println("What are you going to do?");
                System.out.println("1. Change Engimon");
                System.out.println("2. Interact");
                System.out.println("3. Back");
                System.out.print("Choose the number : ");
                entry = sc.nextLine();
                if (entry.equals("1")) {
                    this.InventoryE.printAll(false);
                    System.out.print("Select the number : ");
                    int selected = sc.nextInt();
                    sc.nextLine();
                    setActiveEngimon(selected-1);
                    break;
                }
                else if (entry.equals("2")) {
                    this.activeEngimon.interact();
                }
                else if (entry.equals("3")) {
                    break;
                }
                else {
                    System.out.println("Invalid action\n");
                }
            }
        }
    }
    */

    public void select(String section, int idx, Scanner sc){
        try {
            if (section.equals("E")){
                //this.InventoryE.get(idx).printDetail();
                System.out.println(this.InventoryE.getAmount(idx));
                System.out.println("Action available : ");
                System.out.println("1. Set to active");
                System.out.println("2. Interact");
                System.out.println("3. Discard");
                System.out.println("4. Cancel");
                System.out.print("Choose number : ");
                String input = sc.nextLine();
                if (input.equals("1")){
                    setActiveEngimon(idx);
                } else if (input.equals("2")){
                    this.InventoryE.get(idx).interact();
                } else if (input.equals("3")){
                    String name = this.InventoryE.get(idx).getName();
                    System.out.print("Discard " + name + "? (Y/N) : ");
                    String confirm = sc.nextLine();
                    if (confirm.equals("Y") || confirm.equals("y")){
                        this.InventoryE.discard(idx, 1);
                    } else if (confirm.equals("N") || confirm.equals("n")){
                        System.out.println("Discard canceled\n");
                    } else {
                        System.out.println("Invalid input. Discard canceled\n");
                    }
                } else if (input.equals("4")){
                    return;
                } else {
                    System.out.println("Invalid action\n");
                }
            }
            else if (section.equals("S")){
                this.InventoryS.get(idx).printDetail();
                System.out.println("Action available : ");
                System.out.println("1. Use Skill");
                System.out.println("2. Discard");
                System.out.println("3. Cancel");
                System.out.print("Choose the number : ");
                String input = sc.nextLine();
                if (input.equals("1")){
                    Skill choosen = this.InventoryS.remove(idx);
                    this.activeEngimon.addSkill(choosen);
                } else if (input.equals("2")){
                    String name = this.InventoryS.get(idx).getName();
                    System.out.print("Discard " + name + "? (Y/N) : ");
                    String confirm = sc.nextLine();
                    if (confirm.equals("Y") || confirm.equals("y")){
                        int amountLeft = this.InventoryS.getAmount(idx);
                        System.out.print("Amount to discard (up to " + amountLeft + ") : ");
                        int discardAmount = sc.nextInt();
                        sc.nextLine();
                        this.InventoryS.discard(idx, discardAmount);
                    } else if (confirm.equals("N") || confirm.equals("n")){
                        System.out.println("Discard canceled\n");
                    } else {
                        System.out.println("Invalid input. Discard canceled\n");
                    }
                } else if (input.equals("3")){
                    return;
                } else {
                    System.out.println("Invalid action\n");
                }
            }
            else {
                System.out.println("Invalid selection\n");
            }
        } catch(IndexOutOfBoundsException e) {System.out.println("Invalid selection (Index out of range)\n");}
    }

    public void openInventory(Scanner sc){
        String input;
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
            input = sc.nextLine();
            if (input.equals("1")) {
                System.out.println("Select with format E/S + number (ex : E3 / S1)");
                System.out.print("input : ");
                String selected = sc.nextLine();
                if (selected.length() == 2) {
                    select(selected.substring(0,1), Character.getNumericValue(selected.charAt(1)) - 1, sc);
                } else if (selected.length() == 3) {
                    select(selected.substring(0,1), Integer.parseInt(selected.substring(1,3)) - 1, sc);
                } else {
                    System.out.println("Invalid selection\n");
                }
            } else if (input.equals("2")) {
                break;
            } else {
                System.out.println("Invalid action\n");
            }
        }
    }
    
    public void addEngimon(Engimon e) {
        this.InventoryE.put(e);
    }

    public void addSkillItem(Skill s) {
        this.InventoryS.put(s);
    }

    public void showCommands(){
        System.out.println("Commands available :");
        System.out.println("1. W");
        System.out.println("2. A");
        System.out.println("4. S");
        System.out.println("5. D");
        System.out.println("6. Inventory");
        System.out.println("7. Manage Active Engimon");
        System.out.println("8. Battle");
        System.out.println("9. Breeding");
        System.out.println("10. Save Game");
        System.out.println("11. Exit");
    }

    public Engimon getActiveEngimon(){
        return this.activeEngimon;
    }
    public ArrayList<String> getInventory(boolean withAmount){
        if(withAmount){
            return this.InventoryS.printAll(withAmount);
        }else{
            return this.InventoryE.printAll(withAmount);
        }
    }

    public int getPlayerX(){
        return this.playerX;
    }
    public int getPlayerY(){
        return this.playerY;
    }
}


