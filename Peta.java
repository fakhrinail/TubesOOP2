/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author abida
 */
public class Peta {
    public static char Tundra='1';
    public static char Sea='2';
    public static char Grassland='3';
    public static char Mountain='4';
    private final String filename;
    ArrayList<Cell> mapLayout;
    Arra

    public Peta(String filename){
        this.mapLayout = new ArrayList<>();
        this.filename = filename;
        this.loadMap();
    }
    
    public static final void setMapIcon(char tundra, char sea, char grassLand, char mountain){
        Tundra = tundra;
        Sea = sea;
        Grassland = grassLand;
        Mountain = mountain;
    }
    
    private Boolean loadMap(){
        Boolean stats = true;
        //System.out.println("welcome");
        try{
            File file = new File(filename);
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String temp = buff.readLine();
            int i =0 ;
            while(temp != null){
                //int j;
                CellType ctype;
                for(int j=0;j<temp.length();j++){
                    if(temp.charAt(j)==Tundra){
                        ctype = CellType.TUNDRA;
                    }else if(temp.charAt(j)==Mountain){
                        ctype = CellType.MOUNTAIN;
                    }else if(temp.charAt(j)==Sea){
                        ctype = CellType.SEA;
                    }else if(temp.charAt(j)==Tundra){
                        ctype = CellType.TUNDRA;
                    }else{
                        throw  new PetaException();
                    }
                    mapLayout.add(new Cell(ctype,i,j));
                }

                i++;
                temp = buff.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
            stats = false;
        }finally{
            //System.out.println("fin");
            return stats;
        }
    }

    private void generateEngimon(){

    }
    
    
}
