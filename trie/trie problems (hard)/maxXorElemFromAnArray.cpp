#include <bits/stdc++.h>
using namespace std;

// Using Trie data structure - Optimal approach
// TC:  O(32N + Q(logQ) + 32Q) where N is size of array and Q is number of queries
// SC: O(32N + Q) for trie data structure and O(Q) for offline queries

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
    	vector<int> maximizeXor(vector<int>& nums, vector<vector<int> >& queries) {
    		sort(nums.begin(), nums.end());
            vector<int> ans(queries.size(), 0);
            vector<pair<int, pair<int, int>>> offineQueries;

            int ind = 0;
            for(auto &query : queries) {
                offineQueries.push_back({query[1], {query[0], ind++}});
            }

            sort(offineQueries.begin(), offineQueries.end());
            int i = 0;
            int n = nums.size();

            Trie trie;

            for(auto &query : offineQueries) {
                while(i < n && nums[i] <= query.first) {
                    trie.insert(nums[i]);
                    i++;
                }

                if(i != 0) {
                    ans[query.second.second] = trie.getMax(query.second.first);
                } else {
                    ans[query.second.second] = -1;
                }
            }

            return ans;
    	}
};