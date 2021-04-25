import java.util.*;
import java.io.*;

public class KoleksiSpecies {
    private ArrayList<Species> allSpecies;
    private ArrayList<Species> landSpecies;
    private ArrayList<Species> waterSpecies;
    public KoleksiSpecies(KoleksiSkill allSkill){
        this.allSpecies = new ArrayList<Species>();
        this.landSpecies = new ArrayList<Species>();
        this.waterSpecies = new ArrayList<Species>();
        try{
            //use buffer to make input more efficient, instead plain filereader
            Scanner file = new Scanner(new BufferedReader(new FileReader("files/species.txt")));
            while(file.hasNext()){
                String[] partition = file.nextLine().trim().split(":");
                String[] SpeciesInfo = partition[0].trim().split(" ");
                Species toAdd = new Species(SpeciesInfo, partition[1], allSkill);
                this.allSpecies.add(toAdd);
            }
        } catch(FileNotFoundException ex){
            System.out.println("Nama file yang anda masukkan salah");
        }
    }
    public void printAll(){
        this.allSpecies.stream().forEach(s -> {
            s.printSpeciesDetail();
            System.out.println();
        });
    }

    public ArrayList<Species> getAllSpecies(){
        return this.allSpecies;
    }
}
