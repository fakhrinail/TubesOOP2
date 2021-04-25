public class Cell {
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
        return engimon;
    }

    public Boolean setEngimon(Engimon engimon) {
        this.engimon = engimon;
        this.player = false; // misal berhasil assign engimon
        return true;
    }

    public Boolean setPlayer(Player player){
        return true;
    }

    public void setEmpty(){
        this.engimon = null;
        this.player = false;
    }

    public Boolean checkPlace(int x,int y){
        return this.getX()==x && this.getY==y;
    }

    public Boolean checkPlace(int x,int y, String Species){
        Boolean a = this.getX()==x && this.getY==y;
        
        return a;
    }


}
