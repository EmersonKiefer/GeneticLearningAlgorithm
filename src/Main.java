public class Main {
    public static void main(String[] args) {
        //make a population and run it
        int popSize = 20;
        String goal = "Hello World!";
        Population pop;
        int sum = 0;
        int num = 1000;
        pop = new Population(popSize, goal);

        while (!pop.nextGen());

            pop.display();



    }
}
