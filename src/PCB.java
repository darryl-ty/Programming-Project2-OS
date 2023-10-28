public class PCB implements Comparable <PCB>{
    //Represents Process Control Block object

    private static int ID = 0;
    private status s;
    private int cycles_remaining;
    private int arrivalTime = 0;
    private int deadline = 0;
    public enum status{RUNNING, READY, WAITING, HOLD};
    public PCB()
    {
        //Default constructor
        ++ID;
        s = status.HOLD;
        cycles_remaining = 1000;
    }

    public PCB(int cycles_remaining, int arrivalTime, int deadline)
    {
        //Constructor to create PCB with specified number of remaining CPU cycles
        this.cycles_remaining = cycles_remaining;
        this.arrivalTime = arrivalTime;
        this.deadline = deadline;
        s = status.HOLD;
        ++ID;
    }

    public int compareTo(PCB p) {
        if (this.getCycles() < p.getCycles()) {
            return -1;
        } else if (this.getCycles() > p.getCycles()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void changeStatus(status newstat)
    {
        s = newstat;
    }

    public void decrementCycles()
    {
        //Process one CPU cycle
        --cycles_remaining;
    }
    public int getCycles() { return cycles_remaining; }
    public int getID()
    {
        return ID;
    }
    public int getArrivalTime() {return arrivalTime;}
    public int getDeadline() {return deadline;}

    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setCycles(int c)
    {
        cycles_remaining = c;
    }
    public void setArrivalTime(int t)
    {
        arrivalTime = t;
    }
    public void setDeadline(int deadline) {this.deadline = deadline;}


}
