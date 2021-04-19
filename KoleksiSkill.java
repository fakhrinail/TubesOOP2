import java.util.*;
import java.io.*;

public class KoleksiSkill {
    private List<Skill> allSkill;
    public KoleksiSkill(){
        this.allSkill = new ArrayList<Skill>();
        try{
            //use buffer to make input more efficient, instead plain filereader
            Scanner file = new Scanner(new BufferedReader(new FileReader("files/skills.txt")));
            while(file.hasNext()){
                String[] SkillInfo = file.nextLine().trim().split(" ");
                Skill toAdd = new Skill(SkillInfo);
                this.allSkill.add(toAdd);
            }
        } catch(FileNotFoundException ex){
            System.out.println("Nama file yang anda masukkan salah");
        }
    }
    public void printAll(){
        this.allSkill.stream().forEach(s -> {
            s.printDetail();
            System.out.println();
        });
    }
    public List<Skill> getCompatibleSkills(Elemental e1, Elemental e2){
        List<Skill> toReturn = new ArrayList<Skill>();
        for(int i=0; i<this.allSkill.size(); i++){
            if(this.allSkill.get(i).isCompatible(e1, e2)){
                toReturn.add(this.allSkill.get(i));
            }
        }
        return toReturn;
    }
    public Skill getSkill(String name){
        for(int i=0; i<this.allSkill.size(); i++){
            Skill s = this.allSkill.get(i);
            if(s.getName().equals(name)){
                return new Skill(s);
            }
        }
        return null;
    }
}
