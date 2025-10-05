package cli;

import algorithms.KadanesAlgorithm;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Runs benchmark for Kadane's algorithm and writes CSV metrics.
 * Generates reproducible inputs using a baseSeed and writes:
 * n,trial,time_ns,comparisons,updates,arrayAccesses
 */
public class BenchmarkRunner {
    public static void main(String[] args) throws IOException {
        final int[] ns = new int[] {100, 1000, 5000, 10000, 20000};
        final int warmups = 3;
        final int trials = 10;
        final long baseSeed = 123456L;

        try (FileWriter fw = new FileWriter("metrics_kadane_ns.csv")) {
            fw.write("algo,n,trial,time_ns,comparisons,updates,arrayAccesses\n");

            for (int n : ns) {
                System.out.println("Running n = " + n);
                for (int t = 0; t < warmups + trials; t++) {
                    long seed = baseSeed + t + (n * 131542391L);
                    Random rnd = new Random(seed);

                    int[] arr = new int[n];
                    for (int i = 0; i < n; i++) arr[i] = rnd.nextInt(2001) - 1000;

                    boolean isWarm = t < warmups;
                    int trialIndex = isWarm ? -1 : (t - warmups);

                    PerformanceTracker tracker = new PerformanceTracker();
                    KadanesAlgorithm kadane = new KadanesAlgorithm(tracker);

                    tracker.startTimer();
                    KadanesAlgorithm.Result res = kadane.findMaxSubarray(arr);
                    tracker.stopTimer();

                    long dt = tracker.getElapsedNs();
                    if (!isWarm) {
                        fw.write(String.format("kadane,%d,%d,%d,%d,%d,%d\n",
                                n, trialIndex, dt, tracker.getComparisons(), tracker.getUpdates(), tracker.getArrayAccesses()));
                    }
                    fw.flush();
                }
            }
        }

        System.out.println("Done. metrics_kadane_ns.csv generated.");
    }
}

