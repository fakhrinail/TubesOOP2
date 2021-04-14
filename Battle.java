public class Battle {
    private Player self;
    private Engimon opponent;

    public Battle(Player self){
        this.self = self;
        this.opponent = null;
    }

    private boolean isEngimonAdjacent(Engimon myEngimon, Map map){ // nunggu isi map
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

    private float calculateElementalAdvantage(Engimon myEngimon, Engimon opponet){

    }

    private float calculateSkillPower(Engimon engimon){

    }

    private float calculateTotalPower(Engimon engimon){

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
            System.out.println("Do you want to continue?");
            // case kalah
            if (myEngimonPower < opponentPower) {
                int engimonLife = myEngimon.getLife();
                myEngimon.setLife(engimonLife-1); // tiap set life cek 0 atau ga
                // nanti throw exception mungkin?
            }
            else {
                self
            }
                // hilang 1 life
                // life 0 = mati, lanjut command
            // case menang
                // dapet engimon kalo cukup
                // dapet exp
                // dapet skill di slot pertama
        } else {
            System.out.println("No active Engimon detected! Battle is cancelled");
        }
    }
}