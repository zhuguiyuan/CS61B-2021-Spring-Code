package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int opCount = 10000;
        for (int i = 1000; i <= 128000; i *= 2) {
            SLList<Integer> lstToTime = new SLList<>();
            for (int j = 0; j < i; j += 1) {
                lstToTime.addLast(0);
            }
            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < opCount; j += 1) {
                lstToTime.getLast();
            }
            double time = sw.elapsedTime();
            ns.addLast(i);
            times.addLast(time);
            opCounts.addLast(opCount);
        }
        printTimingTable(ns, times, opCounts);
    }

}
