import java.util.*;

public class Battle {
    private Player self;
    private Engimon myEngimon;
    private Engimon opponent;
    private Peta map;

    public Battle(Player self, Peta map){
        this.self = self;
        this.myEngimon = self.getActiveEngimon();
        this.opponent = null;
        this.map = map;
    }

    private boolean isEngimonActive(){
        return self.getActiveEngimon() != null;
    }

    public ArrayList<Integer> getAdjacentEngimons(){
        ArrayList<Engimon> wildEngimons = map.getWildEngimons();
        ArrayList<Integer> adjacentEngimons = new ArrayList<Integer>();
        int playerX = self.getPlayerX();
        int playerY = self.getPlayerY();
        for (int i=0; i<wildEngimons.size(); i++) { 
            Engimon wildEngimon = wildEngimons.get(i);
            int wildX = wildEngimon.getEngimonX();
            int wildY = wildEngimon.getEngimonY();
            if (Math.abs(wildX-playerX) + Math.abs(wildY-playerY) <= 1) {
                adjacentEngimons.add(i);
            }
        }

        return adjacentEngimons;
    }

    private double calculateElementalAdvantage(Engimon sourceEngimon, Engimon comparedEngimon){
        double advantage = 0;
        Elemental e1, e2;
        int myElementCount = sourceEngimon.getElementals().size();
        int oppElementCount = comparedEngimon.getElementals().size();
        for (int i = 0; i < myElementCount; i++) {
            for (int j = 0; j < oppElementCount; j++) {
                e1 = sourceEngimon.getElementals().get(i);
                e2 = comparedEngimon.getElementals().get(i);
                advantage = Math.max(advantage, e1.getAdv(e2));
            }
        }

        return advantage;
    }   

    private double calculateSkillPower(Engimon engimon){
        double skillPower = 0.0;
        ArrayList<Skill> engimonSkills = engimon.getSkills();
        for (Skill skill : engimonSkills) {
            skillPower += skill.getMastery()*skill.getBasePower();
        }

        return skillPower;
    }

    public Engimon chooseOpponent(ArrayList<Engimon> adjacentEngimons) {
        if (adjacentEngimons.size() == 0) {
            System.out.println("No adjacent Engimon");
            return null;
        }
        else if (adjacentEngimons.size() == 1) {
            return adjacentEngimons.get(0);
        }
        else {
            System.out.println("There are multiple Engimons near you");
            System.out.print("Choose one you want to battle with");
            for (int i = 0; i < adjacentEngimons.size(); i++) {
                System.out.println((i+1) + adjacentEngimons.get(i).getName());
            }

            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            if (input < 1 || input > adjacentEngimons.size()) {
                System.out.println("Invalid input");
                scanner.close();
                return null;
            } else {
                scanner.close();
                return adjacentEngimons.get(input-1);
            }
        }
    }

    public void battle(){
        if (isEngimonActive()) {
            this.opponent = map.getWildEngimons().get(getAdjacentEngimons().get(0));
            // tampilkan status lawan
            this.opponent.printDetail();
            double opponentPower = calculateSkillPower(this.opponent) + calculateElementalAdvantage(opponent, myEngimon)*opponent.getLevel();
            double myEngimonPower = calculateSkillPower(this.myEngimon) + calculateElementalAdvantage(myEngimon, opponent)*myEngimon.getLevel();
            // tampilkan total power
            System.out.println("Your Engimon total power is" + myEngimonPower);
            System.out.println("Your opponent total power is" + opponentPower);
            // kasih opsi lanjut/ga
            System.out.println("Do you want to continue? input 1 if yes, 0 if no");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            sc.close();
            if (input == 1) {
                // case kalah
                if (myEngimonPower < opponentPower) {
                    int engimonLife = myEngimon.getLife();
                    myEngimon.setLife(engimonLife-1); // tiap set life cek 0 atau ga
                    if (myEngimon.getLife() == 0) {
                        myEngimon.death();
                        self.removeActiveEngimon();
                    } 
                    // nanti throw exception mungkin?
                    // hilang 1 life
                    // life 0 = mati, lanjut command
                }
                // case menang
                else {
                    int exp = opponent.getLevel()*100/myEngimon.getLevel();
                    myEngimon.addExperience(exp);
                    Skill rewardSkill = new Skill(opponent.getSkills().get(0));
                    self.addSkillItem(rewardSkill);
                    self.addEngimon(opponent);
                    //map.deleteEngimon(opponent);
                }
            } else {
                System.out.println("Battle is cancelled!");
            }
        } else {
            System.out.println("No active Engimon detected! Battle is cancelled");
        }
    }
}