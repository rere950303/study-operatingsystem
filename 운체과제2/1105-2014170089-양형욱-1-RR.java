import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RR {

    private final long timeSlice;

    public RR(long timeSlice) {
        this.timeSlice = timeSlice;
    }

    public void execute() throws InterruptedException {
        double sum = 0;
        LinkedList<Job> list = new LinkedList<>();
        ArrayList<ArrayList<Long>> result = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            result.add(new ArrayList<>());
        }

        for (int i = 4; i >= 1; i--) {
            list.add(new Job(i, i * 50000000));
        }

        System.out.println("============ RR ============");
        System.out.println("timeSlice: " + this.timeSlice);
        System.out.println("---------- Gantt chart ----------");

        long now = System.currentTimeMillis();

        while (!list.isEmpty()) {
            ExecutorService executor = Executors.newSingleThreadExecutor();
            int tmp = 0;
            Job job = list.poll();
            long tmpNow = System.currentTimeMillis();

            executor.execute(job);
            while (System.currentTimeMillis() - tmpNow < timeSlice) {
                tmp++;
            }
            executor.shutdown();
            if (job.i > 0) {
                list.add(new Job(Character.getNumericValue(job.pid.charAt(4)), job.i));
            }
            result.get(Character.getNumericValue(job.pid.charAt(4))).add(job.startTime - now);
            System.out.println(job.pid + " starting time: " + (job.startTime - now));
        }

        Long error = result.get(4).get(0);
        for (ArrayList<Long> longs : result) {
            longs.stream().map((i) -> i - error);
        }


        System.out.println("----------------------------");

        for (int i = 3; i >= 0; i--) {
            long tmp = 0;
            ArrayList<Long> longs = result.get(i + 1);
            if (!longs.isEmpty()) {
                tmp += longs.get(0);
            }
            int size = longs.size();
            for (int j = 1; j < size; j++) {
                tmp += longs.get(j) - longs.get(j - 1) - timeSlice;
            }
            System.out.println("Job " + (i + 1) + " waiting time: " + tmp);
            sum += tmp;
        }
        System.out.println("---> average waiting time: " + sum / 4);
    }
}