package algorithms;

import metrics.PerformanceTracker;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KadanesAlgorithmTest {

    @Test
    public void testBasicArray() {
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        KadanesAlgorithm kadane = new KadanesAlgorithm(new PerformanceTracker());
        KadanesAlgorithm.Result r = kadane.findMaxSubarray(arr);
        assertEquals(6, r.maxSum);
        assertEquals(3, r.startIndex);
        assertEquals(6, r.endIndex);
    }

    @Test
    public void testAllNegative() {
        int[] arr = {-5, -3, -8, -2};
        KadanesAlgorithm kadane = new KadanesAlgorithm(new PerformanceTracker());
        KadanesAlgorithm.Result r = kadane.findMaxSubarray(arr);
        assertEquals(-2, r.maxSum);
        assertEquals(3, r.startIndex);
        assertEquals(3, r.endIndex);
    }

    @Test
    public void testSingleElement() {
        int[] arr = {5};
        KadanesAlgorithm kadane = new KadanesAlgorithm(new PerformanceTracker());
        KadanesAlgorithm.Result r = kadane.findMaxSubarray(arr);
        assertEquals(5, r.maxSum);
        assertEquals(0, r.startIndex);
        assertEquals(0, r.endIndex);
    }

    @Test
    public void testEmptyArrayThrows() {
        KadanesAlgorithm kadane = new KadanesAlgorithm(new PerformanceTracker());
        assertThrows(IllegalArgumentException.class, () -> kadane.findMaxSubarray(new int[]{}));
    }
}

