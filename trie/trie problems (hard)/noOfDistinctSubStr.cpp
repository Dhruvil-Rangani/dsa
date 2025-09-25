#include <bits/stdc++.h>
using namespace std;

// brute force approach using set
// TC: O(N^2) where N is length of string
// SC: O(N^2) for set data structure

class Solution{	
	public:
		int countDistinctSubstring(string s){
			int n = s.size();
            set<string> st;

            for(int i = 0; i < n; i++) {
                string temp = "";
                for(int j = i; j < n; j++) {
                    temp = temp + s[j];
                    st.insert(temp);
                }
            }

            return st.size() + 1;
		}
};

// optimal approach using trie data structure
// TC: O(N^2) where N is length of string
// SC: O(N^2) for trie data structure (worst case all characters are distinct) and average case O(N)

struct Node {
    Node* links[26];

    bool containsKey(char ch) {
        return (links[ch - 'a'] != nullptr);
    }

    void put(char ch, Node* node){
        links[ch - 'a'] = node;
    }

    Node* get(char ch) {
        return links[ch - 'a'];
    }
};

class Solution{	
    private:
        void deleteTrie(Node* node) {
            for(int i = 0; i < 26; i++) {
                if(node->links[i] != nullptr) {
                    deleteTrie(node->links[i]);
                }
            }
            delete node;
        }
	public:
		int countDistinctSubstring(string s){
			int cnt = 0;
            Node* root = new Node();

            for(int i = 0; i < s.size(); i++) {
                Node* node = root;
                for(int j = i; j < s.size(); j++) {
                    if(!node->containsKey(s[j])) {
                        cnt++;
                        node->put(s[j], new Node());
                    }
                    node = node->get(s[j]);
                }
            }
            deleteTrie(root);
            return cnt + 1;
		}
};