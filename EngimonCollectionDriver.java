class EngimonCollectionDriver{
	public static void main(String[] args){
    EngimonCollection koleksi = new EngimonCollection();

    Engimon bbb = koleksi.getEngimon(0); //harusnya pake clone?
    bbb.showDetail();
    bbb.setParents("aaa", "bbb", "ccc", "ddd");
    bbb.setName("bagusan dikit namanya");
    bbb.setLife(3);
    bbb.showDetail();

    Engimon ccc = koleksi.getEngimon(0); //harusnya pake clone?
    ccc.showDetail(); //masih salah
  }
}