import java.util.*;

class EngimonDriver{
	public static void main(String[] args){
    Elemental.loadElementals();
    KoleksiSkill allSkill = new KoleksiSkill();
    KoleksiSpecies allSpecies = new KoleksiSpecies(allSkill);
    ArrayList<Species> speciesList = allSpecies.getAllSpecies();
    Engimon firemonTes = new Engimon(speciesList.get(0), "budi", "ayah", "Firemon", "ibu", "Firemon", 3, 1);
    Engimon watermonTes = new Engimon(speciesList.get(1), "badu", "ayah", "Watermon", "ibu", "Watermon", 3, 1);
    firemonTes.printDetail();
  }
}