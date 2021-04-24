import java.util.*;

public class Battle {
    private Player self;
    private Engimon opponent;

    public Battle(Player self){
        this.self = self;
        this.opponent = null;
    }

    private boolean isEngimonAdjacent(Engimon myEngimon, Peta map){ // nunggu isi map
        for (iterable_type iterable_element : iterable) {
            
        }
    }

    private boolean isEngimonActive(){
        return self.getActiveEngimon() != null;
    }

    private Engimon getAdjacentEngimon(){
        // nunggu map
        // kalo > 1 pilih yg mana
        for (iterable_type iterable_element : iterable) { 
            
        }
    }

    private float calculateElementalAdvantage(Engimon myEngimon, Engimon opponent){
        float advantage;
        myEngimon.getElement; 

        return advantage;
    }   

    private float calculateSkillPower(Engimon engimon){
        float skillPower;

        return skillPower;
    }

    private float calculateTotalPower(Engimon engimon){
        float totalPower;

        return totalPower;
    }

    public void battle(){
        if (isEngimonActive()) {
            Engimon myEngimon = Player.getActiveEngimon();
            this.opponent = getAdjacentEngimon();
            // tampilkan status lawan
            this.opponent.showDetail();
            Float opponentPower = calculateTotalPower(this.opponent);
            Float myEngimonPower = calculateTotalPower(myEngimon);
            // tampilkan total power
            System.out.println("Your Engimon total power is" + myEngimonPower.toString());
            System.out.println("Your opponent total power is" + opponentPower.toString());
            // kasih opsi lanjut/ga
            System.out.println("Do you want to continue? input 1 if yes, 0 if no");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
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
                    int exp = ;
                    myEngimon.addExperience(exp);
                    // dapet skill di slot pertama
                }
            } else {
                System.out.println("Battle is cancelled!");
            }
        } else {
            System.out.println("No active Engimon detected! Battle is cancelled");
        }
    }
}