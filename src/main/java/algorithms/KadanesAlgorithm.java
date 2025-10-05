package algorithms;

import metrics.PerformanceTracker;

///* Kadane's algorithm: returns max subarray sum and start/end indices. */

public class KadanesAlgorithm {

    private final PerformanceTracker tracker;

    public KadanesAlgorithm(PerformanceTracker tracker) {
        this.tracker = tracker;
    }

    public Result findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty.");
        }

        // initialize
        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;

        tracker.incrementArrayAccesses(); // read arr[0] once for maxSoFar
        tracker.incrementArrayAccesses(); // read arr[0] once for maxEndingHere

        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccesses(); // arr[i] read

            // decide: extend previous subarray or start new at i
            tracker.incrementComparisons();
            if (maxEndingHere + arr[i] < arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
                tracker.incrementUpdates();
            } else {
                maxEndingHere = maxEndingHere + arr[i];
                tracker.incrementUpdates();
            }

            tracker.incrementComparisons();
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
                tracker.incrementUpdates();
            }
        }

        return new Result(maxSoFar, start, end);
    }

    public static class Result {
        public final int maxSum;
        public final int startIndex;
        public final int endIndex;

        public Result(int maxSum, int startIndex, int endIndex) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public String toString() {
            return String.format("MaxSum=%d, start=%d, end=%d", maxSum, startIndex, endIndex);
        }
    }
}
