abstract class AbstractCell {
    private CellType cellType;
    private Integer xLoc;
    private Integer yLoc;
    private Engimon engimon;
    private Boolean player;

    public abstract Boolean isEmpty();
}