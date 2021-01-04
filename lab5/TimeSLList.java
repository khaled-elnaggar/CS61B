import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
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
        final int M = 1000;
        List<Integer> Ns = new ArrayList<>(Arrays.asList(1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 512000, 1024000));
        List<Integer> opCounts = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        for (int ns : Ns) {
            opCounts.add(M);
        }

        for (int ns : Ns) {
            SLList<Integer> sList = new SLList<>();
            for (int i = 0; i < ns; i++) {
                sList.addFirst(i);
            }
            Stopwatch sw = new Stopwatch();
            for(int i = 0; i < M; i++){
                sList.getLast();
            }
            times.add(sw.elapsedTime());
        }
        printTimingTable(Ns, times, opCounts);
    }
}

