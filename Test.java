import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.*;
import java.io.*;

public class Test {
    public static void main(String[] args) {
        Elemental.loadElementals("files/elementals.txt");
        // Elemental fire = new Elemental("Fire");
        // Elemental water = new Elemental("Water");
        // Elemental electr = new Elemental("Electric");
        // Elemental ground = new Elemental("Ground");
        // Elemental ice = new Elemental("Ice");
        KoleksiSpecies listSpecies = new KoleksiSpecies("files/species.txt");
        // ArrayList<Skill> pyro_skill = new ArrayList<Skill>(listSkill.getCompatibleSkills(fire, fire));
        //pyro_skill.remove(4);
        // ArrayList<Elemental> firemon = new ArrayList<Elemental>(); firemon.add(fire);
        Species pyro_sp = listSpecies.getSpeciesbyName("Firemon");
        Engimon pyro = new Engimon(pyro_sp, "pyro", "none", "none", "none", "none", 3, 4);
        //pyro.printAllDetail();

        //ArrayList<Skill> swamp_skill = new ArrayList<Skill>(listSkill.getCompatibleSkills(water, ground));
        //ArrayList<Elemental> narutomon = new ArrayList<Elemental>(); narutomon.add(water); narutomon.add(ground);
        Species swamp_sp = listSpecies.getSpeciesbyName("Narutomon");
        Engimon swamp = new Engimon(swamp_sp, "swampert", "none", "none", "none", "none", 3, 4);
        //swamp.printAllDetail();

        // ArrayList<Skill> magnet_skill = new ArrayList<Skill>(listSkill.getCompatibleSkills(electr, electr));
        //magnet_skill.remove(4);
        // ArrayList<Elemental> electricmon = new ArrayList<Elemental>(); electricmon.add(electr);
        Species magnet_sp = listSpecies.getSpeciesbyName("Electricmon");
        Engimon magnet = new Engimon(magnet_sp, "magnet", "none", "none", "none", "none", 3, 4);
        //magnet.printAllDetail();

        Engimon breedResult;
        try {
            breedResult = swamp.breed(magnet);
            System.out.println(breedResult.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        //System.out.println(pyro.getSkills().get(0).getName());
        //breedResult.printAllDetail();
        //System.out.println(pyro.getLevel()+" "+magnet.getLevel());
        
        // for (Skill skill : breedResult.getSkills()) {
        //     System.out.println(skill.getName()+" "+skill.getBasePower()+" "+skill.getMastery());
        // }
    }
}
