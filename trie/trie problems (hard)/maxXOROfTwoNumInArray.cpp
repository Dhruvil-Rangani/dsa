#include <bits/stdc++.h>
using namespace std;

// brute force approach using 2 loops
// TC: O(N^2) where N is size of array
// SC: O(1)

class Solution {
public:
    int findMaximumXOR(vector<int>& nums) {
        int n = nums.size();
        unordered_map<int, int> map;
        int maxi = 0;
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                maxi = max(maxi, (nums[i] ^ nums[j]));
            }
        }

        return maxi;
    }
};

// Using Trie data structure - Optimal approach
// TC: O(32 N + 32 M) where N is size of array and M is max element in array
// SC: O(32M) for trie data structure

struct Node {
    Node* links[2];

    bool containsKey(int bit) {
        return (links[bit] != nullptr);
    }

    Node* get(int bit) {
        return links[bit];
    }

    void put(int bit, Node* node) {
        links[bit] = node;
    }
};

class Trie {
private:
    Node* root;
public:
    Trie() {
        root = new Node();
    }

    void insert(int num) {
        Node* node = root;
        for(int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if(!node->containsKey(bit)) {
                node->put(bit, new Node());
            }
            node = node->get(bit);
        }
    }

    int getMax(int num) {
        Node* node = root;
        int maxNum = 0;
        for(int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if(node->containsKey(1 - bit)){
                maxNum |= (1 << i);
                node = node->get(1 - bit);
            } else {
                node = node->get(bit);
            }
        }
        return maxNum;
    }
};

class Solution {
	public:
		int findMaximumXOR(vector<int>& nums) {
		    Trie trie;
            for(int num : nums) {
                trie.insert(num);
            }

            int maxi = 0;
            for(int num : nums) {
                maxi = max(maxi, trie.getMax(num));
            }
            return maxi;
    	}
};
