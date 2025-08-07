// leetcode problem: Alien Dictionary
// Problem Link: https://leetcode.com/problems/alien-dictionary/
#include <vector>
#include <queue>
#include <string>
using namespace std;
// Time Complexity: O(N + K)
// Space Complexity: O(N + K)
// N = number of characters in the dictionary, K = number of unique characters
// This code finds the order of characters in an alien language based on the given dictionary.
class Solution {
private:
    vector<int> topoSort(int V, vector<vector<int>> adj) {
        vector<int> inDegree(V, 0);
        for(int i = 0; i < V; i++) {
            for(auto it : adj[i]) {
                inDegree[it]++;
            }
        }

        vector<int> ans;
        queue<int> q;
        for(int i = 0; i < V; i++) {
            if(inDegree[i] == 0) q.push(i);
        }

        while(!q.empty()) {
            int node = q.front();
            ans.push_back(node);
            q.pop();

            for(auto it : adj[node]) {
                inDegree[it]--;
                if(inDegree[it] == 0) q.push(it);
            }
        }

        return ans;
    }
public:
	string findOrder(string dict[], int N, int K) {
		vector<vector<int>> adj(K);
        for(int i = 0; i < N - 1; i++) {
            string s1 = dict[i];
            string s2 = dict[i + 1];
            int len = min(s1.size(), s2.size());

            for(int j = 0; j < len; j++) {
                if(s1[j] != s2[j]) {
                    adj[s1[j] - 'a'].push_back(s2[j] - 'a');
                    break;
                }
            }
        }

        vector<int> topo = topoSort(K, adj);
        string ans;
        for(int i = 0; i < K; i++) {
            ans.push_back('a' + topo[i]);
            ans.push_back(' ');
        }

        return ans;
	}
};
