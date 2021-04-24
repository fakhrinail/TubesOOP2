public class Cell {
    private CellType cellType;
    private Integer xLoc;
    private Integer yLoc;
    private Engimon engimon;
    private Boolean player;

    public CellType getCellType() {
        return cellType;
    }

    public Integer getxLoc() {
        return xLoc;
    }

    public void setx(Integer xLoc) {
        this.xLoc = xLoc;
    }

    public Integer gety() {
        return yLoc;
    }

    public void sety(Integer yLoc) {
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
        return true
    }


    public Cell(CellType cellType, Integer x, Integer y){
        this.cellType = cellType;
        this.xLoc = x;
        this.yLoc = y;
        this.engimon = null;
        this.player = false;
    }


}
