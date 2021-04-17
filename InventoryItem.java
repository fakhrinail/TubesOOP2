interface InventoryItem {
    
    public String getName();
    public void printDetail();
    public int compareTo(InventoryItem other);
    public int getComparator1();
    public int getComparator2();
    public boolean equals(InventoryItem other);
}
