import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class FCFS {

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public void execute() throws InterruptedException {
        double sum = 0;
        LinkedList<Job> list = new LinkedList<>();
        long[] waitingTimes = new long[4];

        for (int i = 4; i >= 1; i--) {
            list.add(new Job(i, i * 50000000));
        }

        long now = System.currentTimeMillis();

        for (Job job : list) {
            executor.submit(job);
        }

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        for (int i = 0; i < 4; i++) {
            waitingTimes[i] = list.get(i).startTime - now;
        }

        long error = waitingTimes[0];

        for (int i = 0; i < 4; i++) {
            waitingTimes[i] -= error;
            sum += waitingTimes[i];
        }

        System.out.println("============ FCFS ============");
        System.out.println("---------- Gantt chart ----------");
        for (int i = 0; i < 4; i++) {
            System.out.println(list.get(i).pid + " starting time: " + waitingTimes[i]);
        }
        System.out.println("----------------------------");

        for (int i = 0; i < 4; i++) {
            System.out.println(list.get(i).pid + " waiting time: " + waitingTimes[i]);
        }
        System.out.println("---> average waiting time: " + sum / 4);
    }
}