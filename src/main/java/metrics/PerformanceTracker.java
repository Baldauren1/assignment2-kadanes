package metrics;
public class PerformanceTracker {
    private long comparisons = 0;
    private long updates = 0;
    private long arrayAccesses = 0;
    private long startNs = 0;
    private long elapsedNs = 0;

    public void incrementComparisons() { comparisons++; }
    public void incrementUpdates() { updates++; }
    public void incrementArrayAccesses() { arrayAccesses++; }

    public long getComparisons() { return comparisons; }
    public long getUpdates() { return updates; }
    public long getArrayAccesses() { return arrayAccesses; }

    public void reset() {
        comparisons = updates = arrayAccesses = 0;
        startNs = elapsedNs = 0;
    }

    public void startTimer() { startNs = System.nanoTime(); }
    public void stopTimer() { elapsedNs = System.nanoTime() - startNs; }
    public long getElapsedNs() { return elapsedNs; }

    @Override
    public String toString() {
        return String.format("time(ns)=%d | comparisons=%d | updates=%d | arrayAccesses=%d",
                elapsedNs, comparisons, updates, arrayAccesses);
    }
}

