public class Job implements Runnable, Comparable<Job> {

    String pid;
    int num;
    long startTime;
    int tmp = 0;
    int i;

    @Override
    public void run() {
        this.i = num;
        this.startTime = System.currentTimeMillis();
        for (; i >= 0; i--) {
            tmp++;
        }
    }

    public Job(int pid, int num) {
        this.pid = "Job " + pid;
        this.num = num;
    }

    @Override
    public int compareTo(Job o) {
        return this.num - o.num;
    }
}