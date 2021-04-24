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
public abstract class Peta {
    public static char Tundra='1';
    public static char Sea='2';
    public static char Grassland='3';
    public static char Mountain='4';
    private final String filename;
    ArrayList<ArrayList<Integer>> mapLayout; 
    
    public Peta(String filename){
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
        try{
            File file = new File(filename);
            BufferedReader buff = new BufferedReader(new FileReader(file));
            String temp = buff.readLine();
            while(temp != null){
                System.out.println(temp);
                temp = buff.readLine();
            }
        }catch(Exception e){
            stats = false;
        }finally{
            return stats;
        }
    }
    
    
    
    
    
}
