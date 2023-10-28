import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



class PCBDeadlineComparator implements Comparator<PCB>{
    @Override
    public int compare(PCB o1, PCB o2) {
        if (o1.getDeadline() < o2.getDeadline())
            return -1;
        else if (o1.getDeadline() > o2.getDeadline())
            return 1;
        return 0;
    }
}
public class Main{

    public static void InitQueue(Queue<PCB> Q, PCB [] P)
    {
        //FIFO Queue
        //Initialize ready queue. FIFO queue.
        for(int i = 0; i < P.length; i++)
        {
            Q.add(P[i]);
        }
    }

    public static void InitQueue(PriorityQueue<PCB> PQ, PCB [] P)
    {
        //Initialize ready queue. Priority queue.
        for(int i = 0; i < P.length; i++)
        {
            PQ.add(P[i]);
        }
    }
    public static void ViewProcesses(PCB [] P, int size)
    {
        //View all process objects. Do not modify.
        System.out.println("Process ID         Duration");
        System.out.println(" __________________________");
        for (int i = 0; i < size; i++)
        {
            System.out.println(P[i].getID() + "            " + P[i].getCycles());
        }
    }
    public static void InitializeProcesses(PCB [] P)
    {
        //Instantiates PCB objects. Do not modify.
        for(int i = 0; i < P.length; i++)
            P[i] = new PCB();
    }
    public static void GenerateProcesses(PCB [] P)
    {
        //Creates random processes
        int val;
        for(int i = 1; i <= 5; i++)
        {
            P[i-1].setID(i);
            val = 5 + (int)(Math.random() * 60);
            P[i-1].setCycles(val); //Generate random cycle time
            P[i-1].setArrivalTime(0);
        }
    }
    public static void FCFS(Queue<PCB> Q){
        PCB p;
        while(!Q.isEmpty()) {
            p = new PCB();
            p = Q.remove();

            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            for(int i = p.getCycles(); i >= 0; i--) {
                if (p.getCycles() > 0) {
                    p.decrementCycles();
                    System.out.println(p.getCycles() + " cycles remain.");
                } else {
                    System.out.println("Process " + p.getID() + " has finished.");
                    break;
                }
            }

        }


    }

    public static void SJN(PriorityQueue<PCB> PQ){

        PCB p;
        while(!PQ.isEmpty()) {
            p = new PCB();
            p = PQ.remove();

            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            for(int i = p.getCycles(); i >= 0; i--) {
                if (p.getCycles() > 0) {
                    p.decrementCycles();
                    System.out.println(p.getCycles() + " cycles remain.");
                } else {
                    System.out.println("Process " + p.getID() + " has finished.");
                    break;
                }
            }

        }

    }

    public static void EDF(PriorityQueue<PCB> PQD){ //TODO -  Fix function
        PQD.clear();

        int val;
        for(int i = 0; i <= 5; i++) {
            PCB P = new PCB();
            P.setID(i);
            val = 5 + (int)(Math.random() * 2);
            int val2 = 5 + (int)(Math.random() * 60);
            P.setCycles(val); //Generate random cycle time
            P.setArrivalTime(0);
            P.setDeadline(val2);
            PQD.add(P);
        }

        System.out.println("Process ID\tDuration\tArrival Time\tDeadline");
        System.out.println(" _______________________________________________________________________");
        for (PCB process : PQD){
            System.out.println(process.getID()+"\t"+process.getCycles()+"\t"+process.getArrivalTime()+"\t"+process.getDeadline());
        }


        PCB p;
        while(!PQD.isEmpty()) {
            p = new PCB();
            p = PQD.remove();

            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            for(int i = p.getCycles(); i >= 0; i--) {
                if (p.getCycles() > 0) {
                    p.decrementCycles();
                    System.out.println(p.getCycles() + " cycles remain.");
                } else {
                    System.out.println("Process " + p.getID() + " has finished.");
                    break;
                }
            }

        }

    }

    public static void RoundRobin(Queue <PCB> Q, int quantum){
        //Simulate Round Robin scheduling on processes in P using the
        //given time quantum
        int ticks = 0;
        PCB p;

        while(!Q.isEmpty()) //While more processes remain in queue
        {
            p = new PCB();
            p = Q.remove(); //Fetch and remove process at head of queue

            System.out.println("Running process " + p.getID() + ". " + p.getCycles() + " cycles remain.");

            //"Running" process here
            for(int i = p.getCycles(); i > 0; i--)
            {

                ticks++;
                if(p.getCycles() > 0 && ticks <= quantum)
                {
                    //Use 1 cpu cycle
                    p.decrementCycles();    //Use one cycle
                    System.out.println(p.getCycles() + " cycles remain.");
                }
                if(ticks > quantum)
                {
                    //Time's up. Go to next process.
                    System.out.println("Process " + p.getID() + " preempted");
                    ticks = 0;
                    Q.add(p); //Add preempted process to back of queue
                    break;

                }
                else if(p.getCycles() == 0)
                {
                    System.out.println("Process " + p.getID() + " has finished.");
                    ticks = 0;
                    break;
                }
            }

        }


    }

    public static void readProcessesFromFile() throws FileNotFoundException {

        Queue<PCB> jobQueue = new LinkedList<>();

        System.out.println("Please enter a filename to process jobs: ");
        Scanner userInput = new Scanner(System.in);

        File jobFile = new File(userInput.next());
        Scanner fileScanner = new Scanner(jobFile);

        //Skip the header of the file.
        fileScanner.nextLine();

        while(fileScanner.hasNext()){
            String jobData = fileScanner.nextLine();
            String[] dataArr = jobData.split("\t"); //DO NOT DO TABS IN INTELLIJ, IT DOES NOT PROPERLY WRITE A \t IN THE FILE.
            int cycles = Integer.parseInt(dataArr[1]);
            int arrives = Integer.parseInt(dataArr[2]);
            int deadline = Integer.parseInt(dataArr[3]);
            PCB p = new PCB(cycles, arrives, deadline);
            jobQueue.add(p);
        }

        fileScanner.close();
    }

    public static void main(String []args) throws FileNotFoundException {

        int choice;
        PCB [] P = new PCB[5];

        //Here: create collections of PCB objects for each scheduling algorithm


        Queue<PCB> Q = new LinkedList<>();
        PriorityQueue<PCB> PQ = new PriorityQueue<>();
        PriorityQueue<PCB> PQD = new PriorityQueue<>(new PCBDeadlineComparator());

        InitializeProcesses(P);
        GenerateProcesses(P);
        InitQueue(Q, P);
        InitQueue(PQ, P);
        Scanner sc = new Scanner(System.in);
        do
        {
            System.out.println("Process scheduling simulator. Make your choice:");
            System.out.println("1) View Processes");
            System.out.println("2) Run processes (First Come First Served)");
            System.out.println("3) Run processes (Shortest Job Next)");
            System.out.println("4) Run processes (Round Robin)");
            System.out.println("5) Run processes (Earliest Deadline First)");
            System.out.println("6) Read processes from file");
            System.out.println("7) Quit");
            choice = sc.nextInt();

            switch(choice)
            {
                case 1:
                    ViewProcesses(P, P.length);
                    break;
                case 2:
                    FCFS(Q);
                    break;
                case 3:
                    SJN(PQ);
                    break;
                case 4:
                    RoundRobin(Q, 5);
                    break;
                case 5:
                    EDF(PQD);
                    break;
                case 6:
                    readProcessesFromFile();
                    break;
            }


        }while(choice != 6);

    }

}
