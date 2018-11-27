public class Chromosome {
    private String code, goal;
    private int costScore;

    //Makes a random string
    public Chromosome(String goal){
       this.goal = goal;
        this.code = "";

        for (int i = 0; i < goal.length(); i++) {
            code += (char)(Math.random()*256);
        }
        costScore = 9999;

    }
    public Chromosome(String code, String goal){
        this.goal = goal;
        this.code = code;

        calculateScore(goal);

    }

    //pivot at middle, returns 2 strings
    public Chromosome[] mate(Chromosome other){
        int pivot = Math.round(code.length()/2) +1 ;
        String child1 = other.getCode().substring(0, pivot) + code.substring(pivot);
        String child2 = code.substring(0, pivot) + other.getCode().substring(pivot);
        Chromosome[] kids = new Chromosome[2];
        kids[0] = new Chromosome(child1, goal);
        kids[1] = new Chromosome(child2, goal);
        return kids;
    }

    //alternating chars
//    public Chromosome[] mate2(Chromosome other){...}

    public void mutate(double chance){
        if(Math.random() > chance)
            return;
//        int upOrDown = (int)(Math.random()*2);

        int index = (int)(Math.random()*code.length());
        int charI = (int)(code.charAt(index));
        int upOrDown = 1;
        if(Math.random() < .5)
            upOrDown *= -1;
        charI += (int)(Math.random()*5+1)* upOrDown;
        code = code.substring(0, index) + (char)(charI) + code.substring(index+1);
        if(Math.random() < chance)
            mutate(chance);

    }

    public void calculateScore(String goal){
        int total=0;
        for (int i = 0; i < goal.length(); i++) {
            total+= Math.abs(goal.charAt(i) - code.charAt(i));
        }
        costScore = total;
    }

    public String getCode(){
        return code;
    }

    public int getCostScore(){
        return costScore;
    }
}
