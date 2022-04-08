package bearmaps;

import edu.princeton.cs.algs4.StdRandom;

public class SpeedTest {

    public static void main(String[] args) {
        int Nstart = 10_000;
        int NEnd = 100_000_000;
        int Q = 1_000;

        NaiveMinPQ<Integer> naiveMinPQ = new NaiveMinPQ<>();
        ArrayHeapMinPQ<Integer> arrayHeapMinPQ = new ArrayHeapMinPQ<>();

        for (int i = Nstart; i <= NEnd; i *= 5) {

            System.out.println("N = " + i);

//            double naiveTime = calcTimeFor(naiveMinPQ, i);
//            System.out.println("Naive imp took " + naiveTime + " seconds");

            double impTime = calcTimeFor(arrayHeapMinPQ, i);
            System.out.println("Our imp took " + impTime + " seconds");
            System.out.println();

        }
    }

    private static double calcTimeFor(ExtrinsicMinPQ naiveMinPQ, int i) {
        long start = System.currentTimeMillis();
        for (int j = 0; j < i; j++) {
            int r = StdRandom.uniform(i);
            int p = StdRandom.uniform(999_999);
            if (!naiveMinPQ.contains(r)) {
                naiveMinPQ.add(r, p);
            }
        }
        long end = System.currentTimeMillis();
        return (end - start) / 1000.0;
    }
}
