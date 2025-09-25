#include <bits/stdc++.h>
using namespace std;

// https://leetcode.com/problems/longest-word-with-all-prefixes/
// https://www.codingninjas.com/codestudio/problems/longest-string-with-all-prefixes_920519
// Using Trie Data Structure
// Time Complexity: O(N * L) where N is number of words and L is average length of words
// Space Complexity: O(N * L) for Trie Data Structure inserting all words

struct Node {
    Node* links[26];
    bool flag = false;

    bool containsKey(char ch) {
        return (links[ch - 'a'] != nullptr);
    }

    Node* get(char ch) {
        return links[ch - 'a'];
    }

    void put(char ch, Node* node) {
        links[ch - 'a'] = node;
    }

    void setEnd() {
        flag = true;
    }

    bool isEnd() {
        return flag;
    }
};

class Trie {
private:
    Node* root;
public:
    Trie() {
        root = new Node();
    }

    void insert(string& word) {
        Node* node = root;
        for(char ch : word) {
            if(!node->containsKey(ch)) {
                node->put(ch, new Node());
            }
            node = node->get(ch);
        }
        node->setEnd();
    }

    bool checkIfAllPrefixExists(string& word) {
        Node* node = root;
        for(char ch : word) {
            if(node->containsKey(ch)) {
                node = node->get(ch);
                if(!node->isEnd()) return false;
            } else return false;
        }
        return true;
    } 
};

class Solution {
public:
    string completeString(vector<string>& nums) {
        Trie* trie = new Trie();
        for(string word : nums) {
            trie->insert(word);
        }
        string longest = "";

        for(string word : nums) {
            if(trie->checkIfAllPrefixExists(word)) {
                if(word.size() > longest.size()) {
                    longest = word;
                } else if(word.size() == longest.size() && word < longest) {
                    longest = word;
                }
            }
        }

        return longest.empty() ? "None" : longest;
    }
};