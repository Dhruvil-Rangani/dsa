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
