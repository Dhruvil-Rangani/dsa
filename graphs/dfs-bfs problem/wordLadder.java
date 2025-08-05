
import java.util.*;
// LeetCode 127. Word Ladder
// https://leetcode.com/problems/word-ladder/

class Solution {
    static class Pair {
        String word;
        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(beginWord, 1));

        Set<String> st = new HashSet<>(wordList);
        st.remove(beginWord);

        while(!q.isEmpty()) {
            Pair pair = q.poll();
            String word = pair.word;
            int steps = pair.steps;

            if(word.equals(endWord)) return steps;

            for(int i = 0; i < word.length(); i++) {
                char[] wordArray = word.toCharArray();
                for(char c = 'a'; c <= 'z'; c++) {
                    wordArray[i] = c;
                    String newWord = new String(wordArray);
                    if(st.contains(newWord)) {
                        st.remove(newWord);
                        q.add(new Pair(newWord, steps + 1));
                    }
                }
            }
        }

        return 0;
    }
}