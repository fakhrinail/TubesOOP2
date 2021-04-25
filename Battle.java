import java.util.*;

public class Battle {
    private Player self;
    private Engimon myEngimon;
    private Engimon opponent;
    private Peta map;

    public Battle(Player self){
        this.self = self;
        this.myEngimon = self.getActiveEngimon();
        this.opponent = null;
    }

    private boolean isEngimonActive(){
        return self.getActiveEngimon() != null;
    }

    private ArrayList<Engimon> getAdjacentEngimons(){
        // nunggu map
        // kalo > 1 pilih yg mana
        ArrayList<Engimon> wildEngimons = map.
        ArrayList<Engimon> adjacentEngimons = null;
        // for (Engimon wildEngimon : map.listOfWildEngimon) { 
        //     int wildX = wildEngimon.getEngimonX();
        //     int wildY = wildEngimon.getEngimonY();
        //     if (abs(wildX-self.getX()) + abs(wildEngimonY-self.getY()) <= 1) {
        //         adjacentEngimons.add(wildEngimon);
        //     }
        // }

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
            // skillPower += skill.getMastery()*skill.getBasePower();
        }

        return skillPower;
    }

    private Engimon chooseOpponent(ArrayList<Engimon> adjacentEngimons) {
        Engimon opponent = adjacentEngimons.get(0);

        return opponent;
    }

    public void battle(){
        if (isEngimonActive()) {
            ArrayList<Engimon> adjacentEngimons = getAdjacentEngimons();
            this.opponent = chooseOpponent(adjacentEngimons);
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
                    // dapet engimon kalo cukup
                    // dapet exp
                    int exp = opponent.getLevel()*100/myEngimon.getLevel();
                    myEngimon.addExperience(exp);
                    // dapet skill di slot pertama
                    Skill rewardSkill = opponent.getSkills().get(0);
                    self.addSkillItem(rewardSkill);
                }
            } else {
                System.out.println("Battle is cancelled!");
            }
        } else {
            System.out.println("No active Engimon detected! Battle is cancelled");
        }
    }
}