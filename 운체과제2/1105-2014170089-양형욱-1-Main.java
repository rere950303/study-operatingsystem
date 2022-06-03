//
// (운체과제)
//
// Created by hyungwook on 2021/12/02.
//

public class Main {

    public static void main(String[] args) throws InterruptedException {
        FCFS fcfs = new FCFS();
        SJF sjf = new SJF();
        RR rr = new RR(13);

        System.out.println("CPU Burst Time: Job 1 < Job 2 < Job 3 < Job 4");

        fcfs.execute();
        sjf.execute();
        rr.execute();

        System.out.println();
        System.out.println("The Scheduling Algorithm Evaluation end" );
    }
}