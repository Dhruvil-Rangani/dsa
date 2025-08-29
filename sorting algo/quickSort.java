import java.util.Random;

// QuickSort implementation using Hoare partition scheme with randomized pivot
// Time Complexity: O(n log n) on average, O(n^2) in the worst case
// Space Complexity: O(log n) due to recursion stack

class Solution {
    // Utility method to swap two elements in an array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int partition(int[] nums, int low, int high) {
        // Use a randomized pivot to avoid worst-case O(n^2) behavior
        int randomIndex = low + new Random().nextInt(high - low + 1);
        swap(nums, low, randomIndex);

        int pivot = nums[low];
        int i = low;
        int j = high;

        while (i <= j) {
            // Find an element on the left that should be on the right
            while (nums[i] < pivot) {
                i++;
            }
            // Find an element on the right that should be on the left
            while (nums[j] > pivot) {
                j--;
            }

            // Swap the two elements if the pointers haven't crossed
            if (i <= j) {
                swap(nums, i, j);
                i++;
                j--;
            }
        }
        return i;
    }

    private void qs(int[] nums, int low, int high) {
        if (low < high) {
            int pIndex = partition(nums, low, high);
            qs(nums, low, pIndex - 1);
            qs(nums, pIndex, high); // Corrected recursive call for Hoare partition
        }
    }

    public int[] quickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return nums;
        }
        qs(nums, 0, nums.length - 1);
        return nums;
    }
}
