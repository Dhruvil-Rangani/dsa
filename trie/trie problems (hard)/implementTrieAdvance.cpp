#include <bits/stdc++.h>
using namespace std;

struct Node {
    Node *links[26];
    int cntEndWith = 0;
    int cntPrefix = 0;

    bool containsKey(char ch) { return (links[ch - 'a'] != NULL); }

    Node *get(char ch) { return links[ch - 'a']; }

    void put(char ch, Node *node) { links[ch - 'a'] = node; }

    void increaseEnd() { cntEndWith++; }

    void increasePrefix() { cntPrefix++; }

    void deleteEnd() { cntEndWith--; }

    void reducePrefix() { cntPrefix--; }

    int getEnd() { return cntEndWith; }

    int getPrefix() { return cntPrefix; };
};

class Trie {
   private:
    Node *root;

   public:
    Trie() { root = new Node(); }

    void insert(string &word) {
        Node *node = root;
        for (char ch : word) {
            if (!node->containsKey(ch)) {
                node->put(ch, new Node());
            }
            node = node->get(ch);
            node->increasePrefix();
        }
        node->increaseEnd();
    }

    int countWordsEqualTo(string &word) {
        Node *node = root;
        for (char ch : word) {
            if (node->containsKey(ch)) {
                node = node->get(ch);
            } else
                return 0;
        }

        return node->getEnd();
    }

    int countWordsStartingWith(string &word) {
        Node *node = root;
        for (char ch : word) {
            if (node->containsKey(ch)) {
                node = node->get(ch);
            } else
                return 0;
        }

        return node->getPrefix();
    }

    void erase(string &word) {
        Node *node = root;
        for (char ch : word) {
            if (node->containsKey(ch)) {
                node = node->get(ch);
                node->reducePrefix();
            } else
                return;
        }
        node->deleteEnd();
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie* obj = new Trie();
 * obj->insert(word);
 * int param_2 = obj->countWordsEqualTo(word);
 * int param_3 = obj->countWordsStartingWith(prefix);
 * obj->erase(word);
 */