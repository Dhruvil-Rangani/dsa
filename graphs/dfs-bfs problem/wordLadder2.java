import java.util.*;
// LeetCode 126. Word Ladder II
// https://leetcode.com/problems/word-ladder-ii/

class Solution {
       public List<List<String>> findSequences(String beginWord, String endWord, List<String> wordList) {
        Queue<List<String>> q = new LinkedList<>();
        Set<String> set = new HashSet<>(wordList);
        q.add(new ArrayList<>(Arrays.asList(beginWord)));
        set.remove(beginWord);
        Set<String> toErase = new HashSet<>();

        List<List<String>> ans = new ArrayList<>();
        while(!q.isEmpty()) {
            int size = q.size();

            for(int i = 0; i < size; i++) {
                List<String> seq = q.poll();
                String word = seq.get(seq.size() - 1);
                if(word.equals(endWord)) {
                    if(ans.isEmpty()) {
                        ans.add(new ArrayList<>(seq));
                    } else if (ans.get(ans.size() - 1).size() == seq.size()) {
                        ans.add(new ArrayList<>(seq));
                    } 
                }

                for(int pos = 0; pos < word.length(); pos++) {
                    char original = word.charAt(pos);
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(original == c) continue;
                        char[] wordArray = word.toCharArray();
                        wordArray[pos] = c;
                        String newWord = new String(wordArray);

                        if(set.contains(newWord)) {
                            seq.add(newWord);
                            q.add(new ArrayList<>(seq));
                            toErase.add(newWord);
                            seq.remove(seq.size() - 1);
                        }
                    }
                }
            }

            for(String it : toErase) set.remove(it);
            if(!ans.isEmpty()) break;
        }

        return ans;
    }
}

// Below is the DFS solution for the same problem. Optimized using a map to store the steps. 
// This is more efficient for larger inputs. Since it avoids recomputing the steps for each word.
// LeetCode 126. Word Ladder II
// https://leetcode.com/problems/word-ladder-ii/
class Solution2 {
    private void dfs(String word, String beginWord, List<String> seq, Map<String, Integer> map, List<List<String>> ans) {
        if(word.equals(beginWord)) {
            Collections.reverse(seq);
            ans.add(new ArrayList<>(seq));
            Collections.reverse(seq);
            return;
        }

        int val = map.get(word);
        for(int i = 0; i < word.length(); i++) {
            char[] wordArray = word.toCharArray();
            for(char c = 'a'; c <= 'z'; c++) {
                wordArray[i] = c;
                String newWord = new String(wordArray);
                if(map.containsKey(newWord) && map.get(newWord) + 1 == val) {
                    seq.add(newWord);
                    dfs(newWord, beginWord, seq, map, ans);
                    seq.remove(seq.size() - 1);
                }
            }
        }
    }
       public List<List<String>> findSequences(String beginWord, String endWord, List<String> wordList) {
       int len = beginWord.length();

       Set<String> set = new HashSet<>(wordList);
       Map<String, Integer> map = new HashMap<>();
       Queue<String> q = new LinkedList<>();

       q.add(beginWord);
       set.remove(beginWord);

       int steps = 1;
       map.put(beginWord, steps);

       while(!q.isEmpty()) {
        String word = q.poll();
        steps = map.get(word);

        for(int i = 0; i < len; i++) {
            char[] wordArray = word.toCharArray();

            for(char c = 'a'; c <= 'z'; c++) {
                wordArray[i] = c;
                String newWord = new String(wordArray);

                if(set.contains(newWord)) {
                    map.put(newWord, steps + 1);
                    q.add(newWord);
                    set.remove(newWord);
                }
            }
        }
       }

       if(!map.containsKey(endWord)) return new ArrayList<>();
       List<List<String>> ans = new ArrayList<>();
       List<String> seq = new ArrayList<>();
       seq.add(endWord);
       dfs(endWord, beginWord, seq, map, ans);
       return ans;
    }
}



