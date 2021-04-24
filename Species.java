import java.util.*;

public class Species {
    private String species;
    private ArrayList<Skill> skills;
    private ArrayList<Elemental> elementals; 
    private String interaction;

    public Species(){
        this.species = "Default";
        this.skills = new ArrayList<Skill>();
        this.elementals = new ArrayList<Elemental>();
        this.interaction = "Default";
    }
    
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

    public Species(String species, ArrayList<Skill> skills, ArrayList<Elemental> elementals, String interaction){
        this.species = species;
        this.skills = new ArrayList<Skill>();
        this.skills.addAll(skills);
        this.elementals = new ArrayList<Elemental>();
        this.elementals.addAll(elementals);
        this.interaction = interaction;
    }

    //Copy
    public Species(Species other){
        this.species = other.species;
        this.elementals = other.elementals;
        this.skills = new ArrayList<Skill>();
        for(int i=0; i<other.skills.size(); i++){
            this.skills.add(new Skill(other.skills.get(i)));
        }
        this.interaction = other.interaction;
    }
    
    //Getters dan Setters
    public String getSpecies(){
        return this.species;
    }

    public ArrayList<Skill> getSkills(){
        ArrayList<Skill> toReturn = new ArrayList<Skill>();
        for (Skill skill : this.skills){
            Skill toAdd = new Skill(skill);
            toReturn.add(toAdd);
        }

        return toReturn;
    }

    public ArrayList<Elemental> getElementals(){
        ArrayList<Elemental> toReturn = new ArrayList<Elemental>();
        for (Elemental elemental : this.elementals){
            Elemental toAdd = new Elemental(elemental.getName());
            toReturn.add(toAdd);
        }

        return toReturn;
    }

    public String getInteraction(){
        return this.interaction;
    }

    public void addSkill(Skill skill){
        Skill newSkill = new Skill(skill);
        this.skills.add(newSkill);
    }

    public void addElemental(Elemental elemental){
        this.elementals.add(elemental);
    }
    
    public void setSpecies(String species){
        this.species = species;
    }

    public void setInteraction(String interaction){
        this.interaction = interaction;
    }

    public void setSkills(ArrayList<Skill> skills){
        this.skills = skills;
    }

    public void setElementals(ArrayList<Elemental> elementals){
        this.elementals = elementals;
    }
    
    //Method lain
    public void printSpeciesDetail(){
        System.out.println(this.species);
        this.skills.get(0).printDetail();
        System.out.println(this.elementals.get(0).getName());
        System.out.println(this.interaction);
    }
}
