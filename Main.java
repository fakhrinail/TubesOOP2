import javax.lang.model.element.Element;

class Main {
    public static void main(String[] args){
        Elemental.loadElementals();
        Skill tes1 = new Skill("Skill1", 0, 1);
        Skill tes2 = new Skill("Skill2", 0, 2);
        Skill tes3 = new Skill("Skill3", 0, 3);
        Inventory<Skill> IS = new Inventory<Skill>();
        IS.put(tes1);
        IS.put(tes2);
        IS.put(tes3);
        IS.put(tes1);
        IS.put(tes2);
        IS.printAll(true);
        IS.printAll(false);
    }
}