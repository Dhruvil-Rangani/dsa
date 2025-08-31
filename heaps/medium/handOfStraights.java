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

// second approach using queue
// well-written interview ready code
// Time Complexity: O(n log n)
// Space Complexity: O(n)

class Solution2 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for(int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        Queue<Integer> queue = new LinkedList<>();
        int lastCard = -1;
        int currentOpenGroups = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int currentCard = entry.getKey();
            if(
                (currentOpenGroups > 0 && currentCard > lastCard + 1) || 
                currentOpenGroups > map.get(currentCard)
            ) return false;
            queue.offer(map.get(currentCard) - currentOpenGroups);
            lastCard = currentCard;
            currentOpenGroups = map.get(currentCard);
            if(queue.size() == groupSize) currentOpenGroups -= queue.poll();
        }

        return currentOpenGroups == 0;
    }
}

// third approach using hashmap
// Time Complexity: O(n)
// Space Complexity: O(n)
// is this the most solution possible? yes it is the most optimal solution possible
class Solution3 {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if(n % groupSize != 0) return false;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i : hand) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for(int card : hand) {
            int startCard = card;
            while(map.getOrDefault(startCard - 1, 0) > 0) startCard--;
            while(startCard <= card) {
                while(map.getOrDefault(startCard, 0) > 0) {
                    for(int nextCard = startCard; nextCard < startCard + groupSize; nextCard++) {
                        if(map.getOrDefault(nextCard, 0) == 0) return false;
                        map.put(nextCard, map.get(nextCard) - 1);
                    }
                }
                startCard++;
            }
        }

        return true;
    }
}
