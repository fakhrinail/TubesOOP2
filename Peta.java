/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

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
    private Integer mapWidth = 0;
    private Integer mapHeight = 0;
    private ArrayList<Cell> mapLayout;
    private ArrayList<Engimon> wildEngimons;
    private Player currPlayer;
    private Integer maxWildEngimon;
    private ArrayList<Species> listSpesies = new ArrayList<>();

    public Peta(String filename,Integer maxWildEngimon){
        this.mapLayout = new ArrayList<>();
        this.filename = filename;
        this.loadMap();
        this.wildEngimons = new ArrayList<>();
        this.currPlayer = null;
        this.maxWildEngimon = maxWildEngimon;
    }

    public Peta(String filename,Integer maxWildEngimon, ArrayList<Species> listSpesies){
        this.mapLayout = new ArrayList<>();
        this.filename = filename;
        this.loadMap();
        this.wildEngimons = new ArrayList<>();
        this.currPlayer = null;
        this.maxWildEngimon = maxWildEngimon;
        this.listSpesies = listSpesies;
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
                CellType ctype = null;
                for(int j=0;j<temp.length();j++){
                    if(temp.charAt(j)==Tundra){
                        ctype = CellType.TUNDRA;
                    }else if(temp.charAt(j)==Mountain){
                        ctype = CellType.MOUNTAIN;
                    }else if(temp.charAt(j)==Sea){
                        ctype = CellType.SEA;
                    }else if(temp.charAt(j)==Grassland){
                        ctype = CellType.GRASSLAND;
                    }else{
                        //throw  new PetaException();
                    }
                    mapLayout.add(new Cell(ctype,i,j));
                }
                mapHeight++;
                i++;
                temp = buff.readLine();
            }
            mapWidth = mapLayout.size()/mapHeight;
        }catch(Exception e){
            e.printStackTrace();
            stats = false;
        }finally{
            //System.out.println("fin");
            return stats;
        }
    }

    public void generateEngimon(){
        if(this.wildEngimons.size()<maxWildEngimon){
            Random random = new Random();
            Integer randVar = Math.abs(random.nextInt()) % this.mapLayout.size();
            System.out.println(randVar);
            Cell cl = mapLayout.get(randVar);
            Integer attemp = 0; 
            while(!cl.isEmpty() && attemp<5){
                randVar = Math.abs(random.nextInt()) % this.mapLayout.size();
                cl = mapLayout.get(randVar);
                attemp++;
            }
            if(attemp<5){
                CellType ct = cl.getCellType();
                //tambah random engimon sesuai dengan tipe cell
                Species spc = new Species();
                if(ct.equals(CellType.GRASSLAND)){
                    if(this.wildEngimons.size()%2==0){
                        spc= this.listSpesies.stream()
                    .filter(i-> i.getSpecies().equalsIgnoreCase("Electricmon"))
                    .findFirst().get();
                    }else{
                        spc= this.listSpesies.stream()
                    .filter(i-> i.getSpecies().equalsIgnoreCase("Groundmon"))
                    .findFirst().get();
                    }
                }else  if(ct.equals(CellType.MOUNTAIN)){
                    spc= this.listSpesies.stream().filter(i-> i.getSpecies().equalsIgnoreCase("Firemon")).findFirst().get();
                }else if(ct.equals(CellType.SEA)){  
                    spc= this.listSpesies.stream().filter(i-> i.getSpecies().equalsIgnoreCase("Watermon")).findFirst().get();
                }else if(ct.equals(CellType.TUNDRA)){
                    spc= this.listSpesies.stream().filter(i-> i.getSpecies().equalsIgnoreCase("Icemon")).findFirst().get();
                }
           

                Engimon eng = new Engimon(spc, "Randommon","","","","",1,Math.abs(random.nextInt())%15);
                eng.setPos(cl.getX(), cl.getY());
                cl.setEngimon(eng);
                this.wildEngimons.add(eng);
                editCellInMap(cl);
            }
        }
    }

    public void randomEngimon(){
        this.wildEngimons.forEach(i->{
            Random r = new Random();
            Integer nextX = i.getEngimonX() + Math.abs(r.nextInt())%3-1;
            Integer nextY = i.getEngimonY() + Math.abs(r.nextInt())%3-1;
            Cell c = this.searchMap(nextX, nextY);
            Integer attemp = 0;
            //FIXME : tambahkan cek untuk Celltype sesuai degnan elemen engimoon
            //FIXME DONE
            while((c==null || (nextX==i.getEngimonX()&&nextY==i.getEngimonY()) || !c.isEmpty() || !c.checkPlace(nextX, nextY,i))&& attemp<5){
                nextX = i.getEngimonX() + Math.abs(r.nextInt()) % 3-1;
                nextY = i.getEngimonY() + Math.abs(r.nextInt()) % 3-1;
                c = this.searchMap(nextX, nextY);
                attemp++;
            }
            editCellInMap(c);
            if(attemp<5){
                c = searchMap(i.getEngimonX(), i.getEngimonY());
                c.setEmpty();
                editCellInMap(c);

                i.setPos(nextX, nextY);
                c = this.searchMap(nextX, nextY);
                c.setEngimon(i);
                editCellInMap(c);
            }
        });
        
        this.rebuildMap();
    }

    public void deleteEngimon(Integer engimonAt){
        if(engimonAt<this.wildEngimons.size()){
            wildEngimons.remove(engimonAt);
        }
    }

    private void rebuildMap(){
        // this.mapLayout.forEach(i -> {
        //     i.setEmpty();
        //     if(i.checkPlace(playerX, playerY)){
        //         i.setPlayer();
        //     }
        // });
        // this.wildEngimons.forEach(i->{
        //     int j =0;
        //     boolean flag = false;
        //     while(!flag && j<this.mapLayout.size()){
        //         if(mapLayout.get(j).checkPlace(i.getEngimonX(), i.getEngimonY())){
        //             mapLayout.get(j).setEngimon(i); flag = true;
        //         }
        //         j++;
        //     }
        // });
        
        //pastikan tidak ada yang crash
    }

    public void setPlayerPos(Player p){
        Cell c; 
        if(this.currPlayer!=null){
            c = searchMap(currPlayer.getPlayerX(), currPlayer.getPlayerY());
            c.setEmpty();
            editCellInMap(c);
        }
        this.currPlayer = p;
        c = searchMap(p.getPlayerX(), p.getPlayerY());
        c.setPlayer();
        editCellInMap(c);
        //Pastikan tidak ada yang crash
    }

    public Cell searchMap(int x, int y){
        for(Cell c:mapLayout){
            if(c.checkPlace(x, y)){return c;}
        }
        return null;
    }

    public ArrayList<Engimon> getWildEngimons(){return this.wildEngimons;}
    
    public void editCellInMap(Cell c){
        if(c!=null){
            this.mapLayout.forEach(i -> {
                if(i.checkPlace(c.getX(), c.getY())){
                    i.setEngimon(c.getEngimon());
                    if(!c.isEmpty() && c.getEngimon()==null){
                        i.setPlayer();
                    }
                }
            });
        }
    }

    public void editCellInMap(Integer x,Integer y, Engimon engimon){
        this.mapLayout.forEach(i -> {
            if(i.checkPlace(x,y)){
                i.setEngimon(engimon);
            }
        });
    }

    public void editCellInMap(Integer x,Integer y, Boolean setPlayer){
        this.mapLayout.forEach(i -> {
            if(i.checkPlace(x,y)){
                i.setEmpty();
                i.setPlayer();
            }
        });
    }

    public Integer getMapHeight(){return this.mapHeight;}
    public Integer getMapWidth(){return this.mapWidth;}

    public ArrayList<Cell> getMapLayout(){return this.mapLayout;}
}
