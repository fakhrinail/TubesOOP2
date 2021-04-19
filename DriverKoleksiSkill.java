import java.util.*;

public class DriverKoleksiSkill {
    public static void main(String[] args){
        Elemental.loadElementals();
        System.out.println("HALO");
        KoleksiSkill allSkill = new KoleksiSkill();
        allSkill.printAll();
        Elemental Fire = new Elemental("Fire");
        Elemental Fire2 = new Elemental("Fire");
        Elemental Electric = new Elemental("Electric");
        Elemental None = new Elemental("None");
        List<Skill> tes1 = allSkill.getCompatibleSkills(Fire, Electric);
        System.out.println(tes1.size());
        tes1.stream().forEach(s -> {
            s.printDetail();
            System.out.println();
        });
        Skill halo = new Skill(tes1.get(0));
        halo.setMastery(2);
        halo.printDetail();
        tes1.stream().forEach(s -> {
            s.printDetail();
            System.out.println();
        });
    }
}
