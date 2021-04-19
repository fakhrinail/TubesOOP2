import java.util.*;
public class DriverKoleksiSpecies {
    public static void main(String[] args){
        Elemental.loadElementals();
        KoleksiSkill allSkill = new KoleksiSkill();
        KoleksiSpecies allSpecies = new KoleksiSpecies(allSkill);
        allSpecies.printAll();
    }
}
