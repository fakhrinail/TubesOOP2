import java.util.*;
import java.io.*;

public class Elemental {
    private static HashMap<String, Integer> elementalID;
    private static double[][] elementalAdv;
    private String name;
    public static void loadElementals(String filepath){
        elementalID = new HashMap<String, Integer>();      
        try{
            //use buffer to make input more efficient, instead plain filereader
            Scanner file = new Scanner(new BufferedReader(new FileReader(filepath)));
            Integer nbElementals = Integer.parseInt(file.nextLine());
            String[] elementalNames = file.nextLine().trim().split(" ");
            for(int i=0; i<nbElementals; i++){
                elementalID.put(elementalNames[i], i+1);
            }

            elementalAdv = new double[nbElementals+1][nbElementals+1];
            for(int i=0; i<= nbElementals; i++){
                for(int j=0; j<=nbElementals; j++){
                    elementalAdv[i][j] = 0.0;
                }
            }
            for(int i=0; i< nbElementals; i++){
                String[] line = file.nextLine().trim().split(" ");
                for(int j=0; j<nbElementals; j++){
                    elementalAdv[i+1][j+1] = Double.parseDouble(line[j]);
                }
            }
        } catch(FileNotFoundException ex){
            System.out.println("Nama file yang anda masukkan salah");
        }
    }

    //For debugging
    public static void printAll(){
        for(String s : elementalID.keySet()){
            System.out.println(s);
        }
    }

    public Elemental(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public Integer getID(){
        if(this.elementalID.containsKey(this.name)){
            return this.elementalID.get(this.name);
        }else{
            return 0;
        }
    }

    public boolean equals(Elemental other){
        return this.getID() == other.getID();
    }

    public double getAdv(Elemental other){
        return this.elementalAdv[this.getID()][other.getID()];
    }
}
