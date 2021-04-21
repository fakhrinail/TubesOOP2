import java.util.*;

public class Species {
    private String species;
    private ArrayList<Skill> skills;
    private ArrayList<Elemental> elementals; 
    private String interaction;

    public Species(String[] SpeciesInfo, String interaction, KoleksiSkill allSkill){
        this.species = SpeciesInfo[0];
        this.skills = new ArrayList<Skill>();
        this.skills.add(allSkill.getSkill(SpeciesInfo[1]));
        this.elementals = new ArrayList<Elemental>();
        for(int i=2; i<SpeciesInfo.length; i++){
            this.elementals.add(new Elemental(SpeciesInfo[i]));
        }
        this.interaction = interaction;
    }
    public Species(Species other){
        this.species = other.species;
        this.elementals = other.elementals;
        this.skills = new ArrayList<Skill>();
        for(int i=0; i<other.skills.size(); i++){
            this.skills.add(new Skill(other.skills.get(i)));
        }
        this.interaction = other.interaction;
    }
    public void printSpeciesDetail(){
        System.out.println(this.species);
        this.skills.get(0).printDetail();
        System.out.println(this.elementals.get(0).getName());
        System.out.println(this.interaction);
    }
}