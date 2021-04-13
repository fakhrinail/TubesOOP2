class EngimonDriver{
	public static void main(String[] args){
    Engimon aaa = new Engimon("a", "b", "c", "d", "e", "f", "g", 1, 1);
    aaa.addExperience(2069);
    aaa.showDetail();
    aaa.addExperience(5000);
    aaa.showDetail();
  }
}