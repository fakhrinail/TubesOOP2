import java.lang.reflect.Array;
import java.util.ArrayList;

public class Cell extends AbstractCell {
    private CellType cellType;
    private Integer xLoc;
    private Integer yLoc;
    private Engimon engimon;
    private Boolean player;

    public Cell(CellType cellType, Integer x, Integer y){
        this.cellType = cellType;
        this.xLoc = x;
        this.yLoc = y;
        this.engimon = null;
        this.player = false;
    }

    public CellType getCellType() {
        return cellType;
    }

    public Integer getX() {
        return xLoc;
    }

    public void setX(Integer xLoc) {
        this.xLoc = xLoc;
    }

    public Integer getY() {
        return yLoc;
    }

    public void setY(Integer yLoc) {
        this.yLoc = yLoc;
    }

    public Engimon getEngimon() {
        if(this.engimon!=null){return engimon;}
        return null;
    }

    public Boolean setEngimon(Engimon engimon) {
        this.engimon = engimon;
        this.player = false; // misal berhasil assign engimon
        return true;
    }

    public Boolean setPlayer(){
        if(this.engimon!=null){
            this.player = true;
            return this.player;
        }

        return false;
    }

    public void setEmpty(){
        this.engimon = null;
        this.player = false;
    }

    public Boolean checkPlace(int x,int y){
        return this.getX()==x && this.getY()==y;
    }

    public Boolean checkPlace(int x,int y, Engimon engimon){
        Boolean a = this.getX()==x && this.getY()==y;
        Boolean b = false;
        ArrayList<Elemental> arl = engimon.getElementals();
        if(this.cellType.equals(CellType.MOUNTAIN)){
            for(Elemental e: arl){
                b = e.equals(new Elemental("Fire")) || b;
            }
        }else if(this.cellType.equals(CellType.SEA) ){
            for(Elemental e: arl){
                b = e.equals(new Elemental("Water")) || b;
            }
        }else if(this.cellType.equals(CellType.GRASSLAND) ){
            for(Elemental e: arl){
                b = e.equals(new Elemental("Electric")) || e.equals(new Elemental("Ground")) || b;
            }
        }else if(this.cellType.equals(CellType.TUNDRA) ){
            for(Elemental e: arl){
                b = e.equals(new Elemental("Ice")) || b;
            }
        }
        

        
        return a&&b;
    }

    public Boolean isEmpty(){
        return this.engimon==null&& this.player==false;
    }
}
