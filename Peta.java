/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.*;

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

    public Peta(String pathfile){
        this.mapLayout = new ArrayList<>();
        filename = pathfile;
        try{
            this.listSpesies = new KoleksiSpecies("files/species.txt").getAllSpecies();
            Scanner file = new Scanner(new BufferedReader(new FileReader(pathfile)));
            String wildEngMax = file.nextLine().trim();
            String playerloc[] = file.nextLine().trim().split(";");
            Integer nWildEngimon = Integer.valueOf(file.nextLine().trim());
            this.wildEngimons =  new ArrayList<>();
            for(Integer i=0; i < nWildEngimon; i++){
                String asd[] = file.nextLine().trim().split(";");
                Species spc = this.listSpesies.stream().filter(z -> z.getSpecies().equals(asd[1])).findFirst().get();
                this.wildEngimons.add(new Engimon(spc,asd[0],"","","","",Integer.parseInt(asd[2]), Integer.parseInt(asd[3])));
                this.wildEngimons.get(i).setPos(Integer.parseInt(asd[4]), Integer.parseInt(asd[5]));
            }
            String temp = file.nextLine();
            int i =0 ;
            while(file.hasNextLine()){
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
                    }
                    mapLayout.add(new Cell(ctype,i,j));
                }
                this.mapHeight++;
                i++;
                temp = file.nextLine();
            }
            this.maxWildEngimon = Integer.parseInt(wildEngMax);
            //System.out.println("map count = "+mapLayout.size());
            //System.out.println("loc x = "+Integer.parseInt(playerloc[0]));
            //System.out.println("loc y = "+playerloc[1]);
            Cell c = searchMap(Integer.parseInt(playerloc[0]),Integer.parseInt(playerloc[1]));
            if(c==null){System.out.println("error null");}
            else{
                c.setPlayer();
                editCellInMap(c);
            }
            this.mapWidth = mapLayout.size()/this.mapHeight;

            for(int x=0;x< this.wildEngimons.size(); x++){
                Engimon k = this.wildEngimons.get(x);
                Cell cz = searchMap(k.getEngimonX(), k.getEngimonY());
                if(cz!=null){
                    cz.setEngimon(k);
                    editCellInMap(cz);
                }else{
                    System.out.println("find: import wild engimon error");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }


    }

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

    public void saveMap(String flname){
        try{
            BufferedWriter bfw = new BufferedWriter(new FileWriter("files/"+ flname + ".txt", false));
            
            //Write max wild enginom di map
            bfw.write(maxWildEngimon.toString()); bfw.newLine();

            //Write current player position
            bfw.write(currPlayer.getPlayerX()+";"+currPlayer.getPlayerY()); bfw.newLine();
            
            //Write number of Write Engimon
            bfw.write(Integer.toString(this.wildEngimons.size())); bfw.newLine();

            //Write List of Wild Engimon
            for(Engimon e : wildEngimons){
                bfw.write(e.getName() + ";"
                + e.getSpecies() + ";"
                + e.getLife() + ";"
                + e.getLevel() + ";"
                + e.getEngimonX() + ";"
                + e.getEngimonY()); 
                bfw.newLine();
            }

            //Write Map detail
            for(int i=0; i < mapHeight; i++){
                String s = "";
                for(int j=0; j < mapWidth; j++){
                    Cell cell = searchMap(i, j);
                    if(cell.getCellType().equals(CellType.GRASSLAND)){
                        s = s + Peta.Grassland;
                    }else if(cell.getCellType().equals(CellType.SEA)){
                        s = s + Peta.Sea;
                    }else if(cell.getCellType().equals(CellType.MOUNTAIN)){
                        s = s + Peta.Mountain;
                    }else if(cell.getCellType().equals(CellType.TUNDRA)){
                        s = s + Peta.Tundra;
                    }
                }
                bfw.write(s); bfw.newLine(); 
            }
            bfw.write("---endofmap");
            bfw.flush();
            bfw.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }
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
            //System.out.println(randVar);
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
                if(c!=null){
                    c.setEmpty();
                    editCellInMap(c);
                }

                i.setPos(nextX, nextY);
                c = this.searchMap(nextX, nextY);
                c.setEngimon(i);
                editCellInMap(c);
            }
        });
        
        this.rebuildMap();
    }

    public void deleteEngimon(int engimonAt){
        if(engimonAt<this.wildEngimons.size()){
            Cell c = searchMap(wildEngimons.get(engimonAt).getEngimonX(), wildEngimons.get(engimonAt).getEngimonY());
            c.setEmpty();
            editCellInMap(c);
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
