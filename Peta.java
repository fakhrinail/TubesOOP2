/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import jdk.nashorn.api.tree.Tree;

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
    private ArrayList<Cell> mapLayout;
    private ArrayList<Engimon> wildEngimons;
    Integer playerX, playerY;

    public Peta(String filename){
        this.mapLayout = new ArrayList<>();
        this.filename = filename;
        this.loadMap();
        this.wildEngimons = new ArrayList<>();
        this.playerX = 0; this.playerY=0;
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

    public void generateEngimon(){

    }

    public void randomEngimon(){

    }

    public void deleteEngimon(Engimon engimon){
        if (this.wildEngimons.contains(engimon)){
            
        }
    }

    private void rebuildMap(){
        this.mapLayout.forEach(i -> {
            if(!i.checkPlace(playerX, playerY)){
                i.setEmpty();
            }
        });
        this.wildEngimons.forEach(i->{
            int j =0;
            boolean flag = false;
            while(!flag && j<this.mapLayout.size()){
                if(mapLayout.get(j).checkPlace(i.getEngimonX(), i.getEngimonY())){
                    mapLayout.get(j).setEngimon(i); flag = true;
                }
                j++;
            }
        });

    }

    public void setPlayerPos(Player p){
        this.playerX = p.getPlayerX();
        this.playerY = p.getPlayerY();
    }

    
    
}
