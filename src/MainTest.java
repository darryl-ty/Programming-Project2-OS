import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private PriorityQueue<PCB> PQD = new PriorityQueue<>(new PCBArrivalTimeComparator());
    private PriorityQueue<PCB> PQ = new PriorityQueue<>();
    private Queue<PCB> Q = new LinkedList<>();

    @BeforeEach
    void setUp() {
        int val;
        int val2;
        for(int i = 0; i <= 5; i++) {
            PCB P = new PCB();
            P.setID(i);
            val = 5 + (int)(Math.random() * 2);
            val2 = 5 + (int)(Math.random() * 60);
            P.setCycles(val); //Generate random cycle time
            P.setArrivalTime(0);
            P.setDeadline(val2);
            PQD.add(P);
            PQ.add(P);
            Q.add(P);
        }
    }

    @AfterEach
    void tearDown() {
        PQD = null;
        PQ = null;
        Q = null;
    }

    @org.junit.jupiter.api.Test
    void FCFS() {
        Main.FCFS(Q);
        assertTrue(Q.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void SJN() {
        Main.SJN(PQ);
        assertTrue(PQ.isEmpty());
    }

    @org.junit.jupiter.api.Test
    void EDF() {
        Main.EDF(PQD);
        assertTrue(PQD.isEmpty());
    }
}