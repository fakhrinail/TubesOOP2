interface InventoryItem {
    
    public String getName();
    public String printDetail();
    public int compareInventory(InventoryItem other);
    public int getComparator1();
    public int getComparator2();
    public boolean equals(InventoryItem other);
}
