import java.util.Scanner;
import javax.swing.*;
import javax.lang.model.element.Element;

class Main {
    public static void main(String[] args){
        GamePage frame = new GamePage();
        frame.initGamestate("");
        frame.initComponent();
        /*
        Elemental.loadElementals();
        KoleksiSkill listSkill = new KoleksiSkill();
        Skill tes1 = listSkill.getSkill("Fireball");
        Skill tes2 = listSkill.getSkill("Waterbom");
        Skill tes3 = listSkill.getSkill("Chidori");
        Engimon a = new Engimon();
        Engimon b = new Engimon();
        Engimon c = new Engimon();
        Player me = new Player();
        for (int i = 0; i < 5; i++) {
            me.addSkillItem(tes1);
            me.addSkillItem(tes2);
            me.addSkillItem(tes3);
            me.addEngimon(a);
            me.addEngimon(b);
            me.addEngimon(c);
        }
        Scanner sc = new Scanner(System.in);
        me.manageActiveEngimon(sc);
        me.openInventory(sc);
        me.manageActiveEngimon(sc);
        */
    }
}