import java.util.*;
// Alice has a hand of cards, given as an array of integers.
// Now she wants to rearrange the cards into groups so that each group is of size groupSize, and consists of groupSize consecutive cards.
// Return true if she can rearrange the cards, or false otherwise.
// Time Complexity: O(n log n)
// Space Complexity: O(n)
// is this the most solution possible? // Yes, because we need to sort the cards and process each card at least once.
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        while(map.size() > 0) {
            int currentCard = map.firstKey();
            for(int i = 0; i < groupSize; i++) {
                if(!map.containsKey(currentCard + i)) return false;
                map.put(currentCard + i, map.get(currentCard + i) - 1);
                if(map.get(currentCard + i) == 0) map.remove(currentCard + i);
            }
        }

        return true;
    }
}