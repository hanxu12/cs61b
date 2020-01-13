package hw3.hash;

import java.util.HashMap;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /*
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        HashMap<Integer, Integer> oomageBank = new HashMap<>();
        for (Oomage oomage: oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            oomageBank.put(bucketNum, oomageBank.getOrDefault(bucketNum, 0) + 1);
        }
        for (Integer cnt: oomageBank.values()) {
            if (cnt < N / 50 || cnt > N / 2.5) {
                return false;
            }
        }
        return true;
    }
}
