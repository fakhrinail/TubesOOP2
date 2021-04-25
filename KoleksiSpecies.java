import java.util.*;
import java.io.*;

public class KoleksiSpecies {
    private ArrayList<Species> allSpecies;
    private ArrayList<Species> landSpecies;
    private ArrayList<Species> waterSpecies;
    public KoleksiSpecies(String filePath){
        this.allSpecies = new ArrayList<Species>();
        this.landSpecies = new ArrayList<Species>();
        this.waterSpecies = new ArrayList<Species>();
        try{
            //use buffer to make input more efficient, instead plain filereader
            Scanner file = new Scanner(new BufferedReader(new FileReader(filePath)));
            while(file.hasNext()){
                Species toAdd = new Species(file.nextLine());
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
    public Species getSpeciesbyName(String name){
        for(int i=0; i<this.allSpecies.size(); i++){
            if(this.allSpecies.get(i).getSpecies().equals(name)){
                return new Species(this.allSpecies.get(i));
            }
        }
        return null;
    }

    public ArrayList<Species> getAllSpecies(){
        return this.allSpecies;
    }
}
