import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Population {
    private ArrayList<Chromosome> members;
    private int generationNumber;
    private String goal;

    public Population(int bigness, String goal){
        members = new ArrayList<Chromosome>();
        for (int i = 0; i < bigness; i++) {
            Chromosome temp = new Chromosome(goal);
            members.add(temp);
        }
        this.goal = goal;
        generationNumber = 0;
    }

    public boolean nextGen(){
        for (int i = 0; i < members.size(); i++) {
            this.members.get(i).calculateScore(goal);
        }
        this.sort();
        this.display();//finish method
        Chromosome[] children = this.members.get(0).mate(this.members.get(1));
        members.set(members.size()-1, children[0]);
        members.set(members.size()-2, children[1]);


        for (int i = 0; i < members.size(); i++) {
            this.members.get(i).mutate(.25);
            this.members.get(i).calculateScore(goal);
            if(this.members.get(i).getCode().equals(this.goal)){
                this.sort();
                this.display();
                return true;
            }
        }
//        sort();
//        display();
        generationNumber++;
//        if(members.get(0).getCostScore()==0){
//            return true;
//        }
        return false;

    }

    public void sort(){
        Collections.sort(members, new Comparator<Chromosome>() {
            @Override
            public int compare(Chromosome o1, Chromosome o2) {
                return o1.getCostScore() - o2.getCostScore();
            }
        });
    }
    public void display(){
        System.out.println("Generation " + generationNumber + " size " + members.size());
        for (Chromosome c : members)
            System.out.println(c.getCode() + " (" + c.getCostScore() + ")" + " " + c.getCode().length());
    }
    public int getGenerationNumber(){
        return generationNumber;
    }
}
