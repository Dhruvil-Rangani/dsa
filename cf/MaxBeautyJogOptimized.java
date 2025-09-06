import java.util.*;

public class MaxBeautyJogOptimized {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] beauties = new int[n];
            for (int i = 0; i < n; i++) {
                beauties[i] = sc.nextInt();
            }
            System.out.println(maxBeautyScore(beauties));
        }
        sc.close();
    }

    private static long maxBeautyScore(int[] beauties) {
        int n = beauties.length;
        TreeMap<Integer, Integer> window = new TreeMap<>();
        long result = Long.MIN_VALUE;

        // Data structure to keep top 3 beauties (descending order)
        TreeMap<Integer, Integer> top3 = new TreeMap<>(Collections.reverseOrder());

        int l = 0;
        for (int r = 0; r < n; r++) {
            add(window, beauties[r]);
            addToTop3(top3, beauties[r]);

            // Balance so that top3 always contains exactly top 3 largest beauties
            balanceSets(window, top3);

            // Slide l while window size + top3 size >= 3 and update result
            while (window.size() + top3Size(top3) >= 3) {
                long top3Sum = sumTop3(top3);
                long dist = r - l;
                result = Math.max(result, top3Sum - dist);

                if (window.size() + top3Size(top3) == 3) {
                    break; // minimal window size reached
                }

                // Remove element at left pointer and rebalance collections
                int val = beauties[l];
                l++;
                if (!removeFromTop3(top3, val)) {
                    remove(window, val);
                }
                balanceSets(window, top3);
            }
        }
        return result;
    }

    // Add element to TreeMap multiset
    private static void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    // Remove element from TreeMap multiset, safe removal
    private static void remove(TreeMap<Integer, Integer> map, int val) {
        Integer countObj = map.get(val);
        if (countObj == null) {
            // Value not present; ignore safe removal
            return;
        }
        int count = countObj;
        if (count == 1) map.remove(val);
        else map.put(val, count - 1);
    }

    // Add to top3 (descending order)
    private static void addToTop3(TreeMap<Integer, Integer> top3, int val) {
        add(top3, val);
    }

    // Remove from top3, returns true if removed, false otherwise
    private static boolean removeFromTop3(TreeMap<Integer, Integer> top3, int val) {
        if (top3.containsKey(val)) {
            remove(top3, val);
            return true;
        }
        return false;
    }

    private static int top3Size(TreeMap<Integer, Integer> top3) {
        int size = 0;
        for (int v : top3.values()) size += v;
        return size;
    }

    // Calculate sum of top 3 elements in top3 map
    private static long sumTop3(TreeMap<Integer, Integer> top3) {
        long sum = 0;
        int count = 0;
        for (int val : top3.keySet()) {
            int freq = top3.get(val);
            for (int i = 0; i < freq && count < 3; i++, count++) {
                sum += val;
            }
            if (count == 3) break;
        }
        return sum;
    }

    // Balance top3 and window so top3 has exactly 3 largest elements
    private static void balanceSets(TreeMap<Integer, Integer> window, TreeMap<Integer, Integer> top3) {
        // Move excess from top3 to window if >3 elements
        while (top3Size(top3) > 3) {
            int smallestTop3 = top3.lastKey();
            remove(top3, smallestTop3);
            add(window, smallestTop3);
        }
        // Move largest from window to top3 if top3 has <3 elements
        while (top3Size(top3) < 3 && window.size() > 0) {
            int largestWindow = window.lastKey();
            remove(window, largestWindow);
            add(top3, largestWindow);
        }
        // Swap if window has larger elements than smallest in top3
        if (top3Size(top3) == 3 && window.size() > 0) {
            int smallestTop3 = top3.lastKey();
            int largestWindow = window.lastKey();
            if (largestWindow > smallestTop3) {
                remove(top3, smallestTop3);
                add(window, smallestTop3);
                remove(window, largestWindow);
                add(top3, largestWindow);
            }
        }
    }
}
